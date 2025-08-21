# Submission & SubmissionAnswer API 테스트

## Submission API

### 1. 모든 Submission 조회
```bash
curl -X GET http://localhost:8080/api/submissions
```

### 2. 특정 Submission 조회
```bash
curl -X GET http://localhost:8080/api/submissions/1/1
```

### 3. 특정 시험의 모든 Submission 조회
```bash
curl -X GET http://localhost:8080/api/submissions/exam/1
```

### 4. Submission 생성
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

### 5. Submission 수정
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

### 6. Submission 삭제
```bash
curl -X DELETE http://localhost:8080/api/submissions/1/1
```

## SubmissionAnswer API

### 1. 모든 SubmissionAnswer 조회
```bash
curl -X GET http://localhost:8080/api/submission-answers
```

### 2. 특정 SubmissionAnswer 조회
```bash
curl -X GET http://localhost:8080/api/submission-answers/1/1/1
```

### 3. 특정 시험과 사용자의 모든 SubmissionAnswer 조회
```bash
curl -X GET http://localhost:8080/api/submission-answers/1/1
```

### 4. SubmissionAnswer 생성
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

### 5. SubmissionAnswer 수정
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

### 6. SubmissionAnswer 삭제
```bash
curl -X DELETE http://localhost:8080/api/submission-answers/1/1/1
```

## API 엔드포인트 요약

### Submission API
- `GET /api/submissions` - 모든 submission 조회
- `GET /api/submissions/{examId}/{userId}` - 특정 submission 조회
- `GET /api/submissions/exam/{examId}` - 특정 시험의 모든 submission 조회
- `POST /api/submissions` - submission 생성
- `PUT /api/submissions/{examId}/{userId}` - submission 수정
- `DELETE /api/submissions/{examId}/{userId}` - submission 삭제

### SubmissionAnswer API
- `GET /api/submission-answers` - 모든 submission answer 조회
- `GET /api/submission-answers/{examId}/{userId}/{questionId}` - 특정 submission answer 조회
- `GET /api/submission-answers/{examId}/{userId}` - 특정 시험과 사용자의 모든 submission answer 조회
- `POST /api/submission-answers` - submission answer 생성
- `PUT /api/submission-answers/{examId}/{userId}/{questionId}` - submission answer 수정
- `DELETE /api/submission-answers/{examId}/{userId}/{questionId}` - submission answer 삭제
