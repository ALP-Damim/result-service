package com.kt.damim.dbgenerator.controller;

import com.kt.damim.dbgenerator.dto.CreateSubmissionAnswerRequest;
import com.kt.damim.dbgenerator.dto.SubmissionAnswerDto;
import com.kt.damim.dbgenerator.service.SubmissionAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/submission-answers")
@RequiredArgsConstructor
public class SubmissionAnswerController {
    
    private final SubmissionAnswerService submissionAnswerService;
    
    @GetMapping
    public ResponseEntity<List<SubmissionAnswerDto>> getAllSubmissionAnswers() {
        List<SubmissionAnswerDto> submissionAnswers = submissionAnswerService.getAllSubmissionAnswers();
        return ResponseEntity.ok(submissionAnswers);
    }
    
    @GetMapping("/{examId}/{userId}/{questionId}")
    public ResponseEntity<SubmissionAnswerDto> getSubmissionAnswerById(
            @PathVariable Integer examId,
            @PathVariable Integer userId,
            @PathVariable Integer questionId) {
        Optional<SubmissionAnswerDto> submissionAnswer = submissionAnswerService.getSubmissionAnswerById(examId, userId, questionId);
        return submissionAnswer.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/{examId}/{userId}")
    public ResponseEntity<List<SubmissionAnswerDto>> getSubmissionAnswersByExamAndUser(
            @PathVariable Integer examId,
            @PathVariable Integer userId) {
        List<SubmissionAnswerDto> submissionAnswers = submissionAnswerService.getSubmissionAnswersByExamAndUser(examId, userId);
        return ResponseEntity.ok(submissionAnswers);
    }
    
    @PostMapping
    public ResponseEntity<SubmissionAnswerDto> createSubmissionAnswer(@RequestBody CreateSubmissionAnswerRequest request) {
        SubmissionAnswerDto createdSubmissionAnswer = submissionAnswerService.createSubmissionAnswer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubmissionAnswer);
    }
    
    @PutMapping("/{examId}/{userId}/{questionId}")
    public ResponseEntity<SubmissionAnswerDto> updateSubmissionAnswer(
            @PathVariable Integer examId,
            @PathVariable Integer userId,
            @PathVariable Integer questionId,
            @RequestBody CreateSubmissionAnswerRequest request) {
        Optional<SubmissionAnswerDto> updatedSubmissionAnswer = submissionAnswerService.updateSubmissionAnswer(examId, userId, questionId, request);
        return updatedSubmissionAnswer.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{examId}/{userId}/{questionId}")
    public ResponseEntity<Void> deleteSubmissionAnswer(
            @PathVariable Integer examId,
            @PathVariable Integer userId,
            @PathVariable Integer questionId) {
        boolean deleted = submissionAnswerService.deleteSubmissionAnswer(examId, userId, questionId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
