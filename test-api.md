# Result Service API 테스트 가이드

## API 그룹 구조

### 1. Submission Management API Group
- **API 그룹명**: submission-management
- **설명**: 시험 제출물 관리 API
- **버전**: v1

### 2. Submission Answer Management API Group  
- **API 그룹명**: submission-answer-management
- **설명**: 시험 답안 관리 API
- **버전**: v1

---

## 1. Submission Management API Group

### API 1.1: 모든 Submission 조회
- **API 이름**: get-all-submissions
- **HTTP 메서드**: GET
- **경로**: `/api/submissions`
- **설명**: 모든 시험 제출물 목록을 조회합니다.

```bash
curl -X GET http://localhost:8080/api/submissions
```

**응답 예시**:
```json
[
  {
    "examId": 1,
    "userId": 1,
    "totalScore": 85.5,
    "feedback": "좋은 성과입니다!",
    "feedbackStatus": "PENDING"
  }
]
```

### API 1.2: 특정 Submission 조회
- **API 이름**: get-submission-by-id
- **HTTP 메서드**: GET
- **경로**: `/api/submissions/{examId}/{userId}`
- **설명**: 특정 시험과 사용자의 제출물을 조회합니다.

```bash
curl -X GET http://localhost:8080/api/submissions/1/1
```

**경로 변수**:
- `examId` (Integer): 시험 ID
- `userId` (Integer): 사용자 ID

### API 1.3: 특정 시험의 모든 Submission 조회
- **API 이름**: get-submissions-by-exam
- **HTTP 메서드**: GET
- **경로**: `/api/submissions/exam/{examId}`
- **설명**: 특정 시험의 모든 제출물을 조회합니다.

```bash
curl -X GET http://localhost:8080/api/submissions/exam/1
```

**경로 변수**:
- `examId` (Integer): 시험 ID

### API 1.4: Submission 생성
- **API 이름**: create-submission
- **HTTP 메서드**: POST
- **경로**: `/api/submissions`
- **설명**: 새로운 시험 제출물을 생성합니다.

```bash
curl -X POST http://localhost:8080/api/submissions \
  -H "Content-Type: application/json" \
  -d '{
    "examId": 1,
    "userId": 1,
    "totalScore": 85.5,
    "feedback": "좋은 성과입니다!"
  }'
```

**요청 본문 스키마**:
```json
{
  "examId": "integer",
  "userId": "integer", 
  "totalScore": "decimal",
  "feedback": "string"
}
```

### API 1.5: Submission 수정
- **API 이름**: update-submission
- **HTTP 메서드**: PUT
- **경로**: `/api/submissions/{examId}/{userId}`
- **설명**: 기존 시험 제출물을 수정합니다.

```bash
curl -X PUT http://localhost:8080/api/submissions/1/1 \
  -H "Content-Type: application/json" \
  -d '{
    "examId": 1,
    "userId": 1,
    "totalScore": 90.0,
    "feedback": "훌륭한 성과입니다!"
  }'
```

### API 1.6: Submission 삭제
- **API 이름**: delete-submission
- **HTTP 메서드**: DELETE
- **경로**: `/api/submissions/{examId}/{userId}`
- **설명**: 특정 시험 제출물을 삭제합니다.

```bash
curl -X DELETE http://localhost:8080/api/submissions/1/1
```

---

## 2. Submission Answer Management API Group

### API 2.1: 모든 SubmissionAnswer 조회
- **API 이름**: get-all-submission-answers
- **HTTP 메서드**: GET
- **경로**: `/api/submission-answers`
- **설명**: 모든 시험 답안 목록을 조회합니다.

```bash
curl -X GET http://localhost:8080/api/submission-answers
```

**응답 예시**:
```json
[
  {
    "examId": 1,
    "userId": 1,
    "questionId": 1,
    "answerText": "정답입니다",
    "isCorrect": true,
    "score": 10.0,
    "solvingTime": 120
  }
]
```

### API 2.2: 특정 SubmissionAnswer 조회
- **API 이름**: get-submission-answer-by-id
- **HTTP 메서드**: GET
- **경로**: `/api/submission-answers/{examId}/{userId}/{questionId}`
- **설명**: 특정 시험 답안을 조회합니다.

```bash
curl -X GET http://localhost:8080/api/submission-answers/1/1/1
```

**경로 변수**:
- `examId` (Integer): 시험 ID
- `userId` (Integer): 사용자 ID
- `questionId` (Integer): 문제 ID

### API 2.3: 특정 시험과 사용자의 모든 SubmissionAnswer 조회
- **API 이름**: get-submission-answers-by-exam-and-user
- **HTTP 메서드**: GET
- **경로**: `/api/submission-answers/{examId}/{userId}`
- **설명**: 특정 시험과 사용자의 모든 답안을 조회합니다.

```bash
curl -X GET http://localhost:8080/api/submission-answers/1/1
```

### API 2.4: SubmissionAnswer 생성
- **API 이름**: create-submission-answer
- **HTTP 메서드**: POST
- **경로**: `/api/submission-answers`
- **설명**: 새로운 시험 답안을 생성합니다.

```bash
curl -X POST http://localhost:8080/api/submission-answers \
  -H "Content-Type: application/json" \
  -d '{
    "examId": 1,
    "userId": 1,
    "questionId": 1,
    "answerText": "정답입니다",
    "isCorrect": true,
    "score": 10.0,
    "solvingTime": 120
  }'
```

**요청 본문 스키마**:
```json
{
  "examId": "integer",
  "userId": "integer",
  "questionId": "integer",
  "answerText": "string",
  "isCorrect": "boolean",
  "score": "decimal",
  "solvingTime": "integer"
}
```

### API 2.5: SubmissionAnswer 수정
- **API 이름**: update-submission-answer
- **HTTP 메서드**: PUT
- **경로**: `/api/submission-answers/{examId}/{userId}/{questionId}`
- **설명**: 기존 시험 답안을 수정합니다.

```bash
curl -X PUT http://localhost:8080/api/submission-answers/1/1/1 \
  -H "Content-Type: application/json" \
  -d '{
    "examId": 1,
    "userId": 1,
    "questionId": 1,
    "answerText": "수정된 답안입니다",
    "isCorrect": true,
    "score": 15.0,
    "solvingTime": 100
  }'
```

### API 2.6: SubmissionAnswer 삭제
- **API 이름**: delete-submission-answer
- **HTTP 메서드**: DELETE
- **경로**: `/api/submission-answers/{examId}/{userId}/{questionId}`
- **설명**: 특정 시험 답안을 삭제합니다.

```bash
curl -X DELETE http://localhost:8080/api/submission-answers/1/1/1
```

---

## APIM 설정 가이드

### 1. API 그룹 생성
```bash
# Submission Management API 그룹 생성
az apim api-group create \
  --resource-group <resource-group-name> \
  --service-name <apim-service-name> \
  --group-id submission-management \
  --display-name "Submission Management" \
  --description "시험 제출물 관리 API"

# Submission Answer Management API 그룹 생성  
az apim api-group create \
  --resource-group <resource-group-name> \
  --service-name <apim-service-name> \
  --group-id submission-answer-management \
  --display-name "Submission Answer Management" \
  --description "시험 답안 관리 API"
```

### 2. 개별 API 등록
각 API를 개별적으로 등록하여 세밀한 제어가 가능합니다:

```bash
# 예시: 모든 Submission 조회 API 등록
az apim api create \
  --resource-group <resource-group-name> \
  --service-name <apim-service-name> \
  --api-id get-all-submissions \
  --path /api/submissions \
  --display-name "Get All Submissions" \
  --description "모든 시험 제출물 조회" \
  --api-version v1 \
  --group-id submission-management \
  --protocols https
```

### 3. API 버전 관리
- **v1**: 현재 버전
- 향후 호환성 변경 시 v2, v3 등으로 버전 관리

### 4. 정책 설정
각 API 그룹별로 다른 정책을 적용할 수 있습니다:
- Rate limiting
- Authentication
- CORS 설정
- 로깅 및 모니터링

---

## 테스트 시나리오

### 시나리오 1: 시험 제출 프로세스
1. Submission 생성 (API 1.4)
2. SubmissionAnswer 생성 (API 2.4) - 여러 문제에 대해
3. Submission 조회 (API 1.2)
4. SubmissionAnswer 조회 (API 2.3)

### 시나리오 2: 시험 결과 관리
1. 모든 Submission 조회 (API 1.1)
2. 특정 시험의 모든 Submission 조회 (API 1.3)
3. Submission 수정 (API 1.5)
4. SubmissionAnswer 수정 (API 2.5)

### 시나리오 3: 데이터 정리
1. SubmissionAnswer 삭제 (API 2.6)
2. Submission 삭제 (API 1.6)
