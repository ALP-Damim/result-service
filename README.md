# 초등학교 데이터 생성 유틸리티

초등학교 3-4학년을 위한 교육 데이터를 생성하는 Spring Boot 기반 유틸리티입니다.

## 🚀 기능

- **초등학교 과목별 데이터 생성**: 과학, 수학, 영어, 국어 과목에 맞는 클래스 생성
- **학년별 데이터 분류**: 3학년, 4학년별 데이터 생성
- **소단원 기반 클래스명**: 각 과목의 소단원을 반영한 클래스명 생성
- **초등학교 수준 문제 생성**: 과목별 초등학교 수준의 시험 문제 및 답안 생성
- **실제적인 교육 환경 시뮬레이션**: 교사, 학생, 수강신청, 출석, 시험 데이터 생성

## 🛠 기술 스택

- **Spring Boot 3.5.4**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **Java 17**
- **Gradle**

## 📋 데이터베이스 스키마

### 테이블 구조

1. **users**: 사용자 정보 (학생/교사)
   - user_id (PK, Auto Increment)
   - email (@elementary.edu 도메인)
   - password_hash
   - role (STUDENT, TEACHER)
   - is_active
   - created_at
   - updated_at

2. **user_profiles**: 사용자 상세 프로필
   - user_id (PK, FK to users)
   - name
   - desired_course (초등학교 교과목)
   - desired_job (초등학생이 꿈꿀 수 있는 직업)
   - birth_date (교사: 25-50세, 학생: 8-11세)
   - school (초등학교명)
   - phone
   - created_at
   - updated_at

3. **classes**: 강좌 정보
   - class_id (PK, Auto Increment)
   - teacher_id (FK to users)
   - teacher_name
   - class_name (소단원 포함, 예: "식물의 생활", "덧셈과 뺄셈")
   - semester (2024-1, 2024-2, 2025-1)
   - school_year (3, 4학년)
   - subject (0: 과학, 1: 수학, 2: 영어, 3: 국어)
   - zoom_url
   - held_day (비트셋으로 요일 표시)
   - starts_at, ends_at
   - capacity
   - created_at, updated_at

4. **sessions**: 강좌별 세션 정보
   - session_id (PK, Auto Increment)
   - class_id (FK to classes)
   - session_name
   - on_date

5. **enrollments**: 수강 신청 정보
   - student_id (PK, FK to users)
   - class_id (PK, FK to classes)
   - status
   - created_at

6. **attendance**: 출석 정보
   - session_id (PK, FK to sessions)
   - student_id (PK, FK to users)
   - status (PRESENT, ABSENT, LATE, EXCUSED)
   - note
   - created_at

7. **exams**: 시험 정보
   - id (PK, Auto Increment)
   - session_id (FK to sessions)
   - name (소단원 + "단원평가")
   - difficulty (EASY, MEDIUM, HARD)
   - is_ready
   - created_by (FK to users)
   - created_at

8. **questions**: 시험 문제
   - id (PK, Auto Increment)
   - exam_id (FK to exams)
   - qtype (MCQ, SHORT)
   - body (초등학교 수준 문제)
   - choices (JSON 배열, 객관식인 경우)
   - answer_key
   - points
   - position

9. **submissions**: 학생 제출물
   - id (PK, Auto Increment)
   - exam_id (FK to exams)
   - user_id (FK to users)
   - submitted_at
   - total_score
   - feedback

10. **submission_answers**: 답안 정보
    - exam_id (PK, FK to exams)
    - user_id (PK, FK to users)
    - question_id (PK, FK to questions)
    - answer_text
    - is_correct
    - score
    - elapsed_time_seconds

## 🔧 설정

### 1. 환경변수 설정

프로젝트는 환경변수를 사용하여 설정을 관리합니다. 다음 스크립트를 실행하여 환경변수를 설정하세요:

```bash
set-env.bat
```

## 🚀 실행 방법

### 1. 애플리케이션 실행

**Windows:**
```bash
gradlew.bat bootRun
```

**Linux/Mac:**
```bash
./gradlew bootRun
```

### 2. 테스트 실행

**Windows:**
```bash
gradlew.bat test
```

**Linux/Mac:**
```bash
./gradlew test
```

## 📊 생성되는 데이터

### 과목 및 소단원

#### 과학 (subject: 0)
- 식물의 생활
- 동물의 생활
- 물의 상태 변화
- 지구와 달
- 전기와 자기
- 소리와 빛
- 산과 염기
- 대기와 날씨
- 생태계와 환경
- 에너지와 생활

#### 수학 (subject: 1)
- 덧셈과 뺄셈
- 곱셈과 나눗셈
- 분수
- 소수
- 도형
- 측정
- 자료와 가능성
- 규칙성
- 비례식
- 정비례와 반비례

#### 영어 (subject: 2)
- 인사하기
- 자기소개
- 가족 소개
- 취미와 관심사
- 학교생활
- 시간과 날짜
- 날씨와 계절
- 음식과 음료
- 색깔과 모양
- 숫자와 셈하기

#### 국어 (subject: 3)
- 듣기와 말하기
- 읽기
- 쓰기
- 문법
- 문학
- 어휘
- 한자
- 독서
- 토론
- 글짓기

### 생성되는 데이터 양

**기본 설정 (application.properties):**
```properties
data.generation.enabled=true
data.generation.teacher-count=10
data.generation.student-count=100
data.generation.class-per-teacher=5
data.generation.enrollment-per-student=5
data.generation.session-per-class=10
data.generation.attendance-rate=0.7
data.generation.exam-per-session-rate=0.3
```

**생성되는 데이터:**
- **교사**: 10명 (teacher1@elementary.edu ~ teacher10@elementary.edu)
- **학생**: 100명 (student1@elementary.edu ~ student100@elementary.edu)
- **클래스**: 50개 (교사당 5개씩, 과목별 소단원 포함)
- **세션**: 500개 (클래스당 10개씩)
- **수강신청**: 500개 (학생당 5개씩)
- **출석기록**: 약 3,500개 (70% 출석률 기준)
- **시험**: 약 150개 (30% 확률로 생성)
- **문제**: 약 1,200개 (시험당 5-8개)
- **제출물**: 약 15,000개 (시험당 학생별 제출)
- **답안**: 약 120,000개 (제출물당 문제별 답안)

### 콘솔 출력 예시

```
=== 데이터 생성 통계 ===
교사: 10명
학생: 100명
클래스: 50개
세션: 500개
수강신청: 500개
출석기록: 3500개
시험: 150개
평균 출석률: 70.0%
=====================
```

## 📚 클래스명 예시

생성되는 클래스명의 예시:

- **3학년 과학 - 식물의 생활**
- **4학년 수학 - 덧셈과 뺄셈**
- **3학년 영어 - 인사하기**
- **4학년 국어 - 듣기와 말하기**
- **3학년 과학 - 동물의 생활**
- **4학년 수학 - 곱셈과 나눗셈**

## 🎯 시험 문제 예시

### 과학 문제
- "식물이 자라는데 필요한 것 중 가장 중요한 것은?"
- "동물의 생태계에서 포식자와 피식자의 관계는?"
- "물의 상태 변화 중 액체에서 기체로 변하는 것은?"

### 수학 문제
- "3학년 수학: 25 + 37 = ?"
- "4학년 수학: 156 ÷ 12 = ?"
- "3학년 수학: 1/2 + 1/4 = ?"

### 영어 문제
- "What is your name?의 뜻은?"
- "How old are you?에 대한 답으로 적절한 것은?"
- "I like apples.에서 like의 뜻은?"

### 국어 문제
- "다음 중 명사가 아닌 것은?"
- "'아름다운'의 품사는?"
- "다음 중 맞춤법이 올바른 것은?"

## 🔧 데이터 생성 시스템

### DataGenerator
- **위치**: `src/main/java/com/kt/damim/dbgenerator/data/DataGenerator.java`
- **활성화 조건**: `data.generation.enabled=true`
- **기능**: 초등학교에 맞는 대량 테스트 데이터 생성

### DataGenerationConfig
- **위치**: `src/main/java/com/kt/damim/dbgenerator/config/DataGenerationConfig.java`
- **기능**: 데이터 생성 관련 설정 관리

### 설정 옵션
```properties
# 데이터 생성 활성화
data.generation.enabled=true

# 생성할 데이터 양 설정
data.generation.teacher-count=10          # 교사 수
data.generation.student-count=100         # 학생 수
data.generation.class-per-teacher=5       # 교사당 클래스 수
data.generation.enrollment-per-student=5  # 학생당 수강 과목 수
data.generation.session-per-class=10      # 클래스당 세션 수
data.generation.attendance-rate=0.7       # 출석률 (0.0 ~ 1.0)
data.generation.exam-per-session-rate=0.3 # 시험 생성 확률 (0.0 ~ 1.0)
```

## 🧪 테스트 환경

### 테스트 설정
- **데이터베이스**: H2 인메모리 데이터베이스 사용
- **프로파일**: `test` 프로파일 활성화
- **DataGenerator**: 테스트 환경에서는 비활성화됨

### 테스트 파일 위치
- `src/test/resources/application.properties`: 테스트용 설정
- `src/test/java/`: 테스트 클래스들

## 🎓 초등학교 특화 기능

### 1. 나이별 데이터 생성
- **교사**: 25-50세 (초등학교 교사 연령대)
- **학생**: 8-11세 (초등학교 3-4학년 연령대)

### 2. 학교명
- 서울초등학교, 연세초등학교, 고려초등학교 등
- 실제 초등학교명 패턴 사용

### 3. 희망 과목
- 과학, 수학, 영어, 국어, 사회, 음악, 미술, 체육, 실과, 도덕

### 4. 희망 직업
- 교사, 의사, 과학자, 엔지니어, 예술가, 운동선수, 요리사, 경찰관, 소방관, 간호사

### 5. 시험명
- "단원평가" 형식 사용 (예: "식물의 생활 단원평가")

## 📝 향후 확장 계획

- **더 많은 과목 추가**: 사회, 음악, 미술, 체육 등
- **학년별 난이도 조정**: 3학년과 4학년의 문제 난이도 차별화
- **계절별 데이터**: 봄, 여름, 가을, 겨울에 맞는 교육 내용
- **특별활동 데이터**: 방과후 활동, 동아리 활동 등
- **성적 분석**: 과목별, 학년별 성적 통계
- **학부모 연동**: 학부모 계정 및 알림 기능
- **교육과정 연동**: 실제 초등학교 교육과정과 연동

## 📄 라이선스

이 프로젝트는 MIT 라이선스 하에 배포됩니다. 자세한 내용은 `LICENSE` 파일을 참조하세요.

## 📞 문의

프로젝트에 대한 문의사항이 있으시면 이슈를 생성해 주세요.
