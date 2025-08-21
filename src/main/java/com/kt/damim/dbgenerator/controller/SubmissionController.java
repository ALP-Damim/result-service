package com.kt.damim.dbgenerator.controller;

import com.kt.damim.dbgenerator.dto.CreateSubmissionRequest;
import com.kt.damim.dbgenerator.dto.SubmissionDto;
import com.kt.damim.dbgenerator.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
public class SubmissionController {
    
    private final SubmissionService submissionService;
    
    @GetMapping
    public ResponseEntity<List<SubmissionDto>> getAllSubmissions() {
        List<SubmissionDto> submissions = submissionService.getAllSubmissions();
        return ResponseEntity.ok(submissions);
    }
    
    @GetMapping("/{examId}/{userId}")
    public ResponseEntity<SubmissionDto> getSubmissionById(
            @PathVariable Integer examId,
            @PathVariable Integer userId) {
        Optional<SubmissionDto> submission = submissionService.getSubmissionById(examId, userId);
        return submission.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/exam/{examId}")
    public ResponseEntity<List<SubmissionDto>> getSubmissionsByExamId(@PathVariable Integer examId) {
        List<SubmissionDto> submissions = submissionService.getSubmissionsByExamId(examId);
        return ResponseEntity.ok(submissions);
    }
    
    @PostMapping
    public ResponseEntity<SubmissionDto> createSubmission(@RequestBody CreateSubmissionRequest request) {
        SubmissionDto createdSubmission = submissionService.createSubmission(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubmission);
    }
    
    @PutMapping("/{examId}/{userId}")
    public ResponseEntity<SubmissionDto> updateSubmission(
            @PathVariable Integer examId,
            @PathVariable Integer userId,
            @RequestBody CreateSubmissionRequest request) {
        Optional<SubmissionDto> updatedSubmission = submissionService.updateSubmission(examId, userId, request);
        return updatedSubmission.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{examId}/{userId}")
    public ResponseEntity<Void> deleteSubmission(
            @PathVariable Integer examId,
            @PathVariable Integer userId) {
        boolean deleted = submissionService.deleteSubmission(examId, userId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
