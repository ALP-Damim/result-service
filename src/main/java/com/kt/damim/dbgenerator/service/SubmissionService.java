package com.kt.damim.dbgenerator.service;

import com.kt.damim.dbgenerator.dto.CreateSubmissionRequest;
import com.kt.damim.dbgenerator.dto.SubmissionDto;
import com.kt.damim.dbgenerator.entity.Submission;
import com.kt.damim.dbgenerator.entity.SubmissionId;
import com.kt.damim.dbgenerator.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SubmissionService {
    
    private final SubmissionRepository submissionRepository;
    
    public List<SubmissionDto> getAllSubmissions() {
        return submissionRepository.findAll()
                .stream()
                .map(SubmissionDto::new)
                .collect(Collectors.toList());
    }
    
    public Optional<SubmissionDto> getSubmissionById(Integer examId, Integer userId) {
        return submissionRepository.findByExamIdAndUserId(examId, userId)
                .map(SubmissionDto::new);
    }
    
    public List<SubmissionDto> getSubmissionsByExamId(Integer examId) {
        return submissionRepository.findByExamId(examId)
                .stream()
                .map(SubmissionDto::new)
                .collect(Collectors.toList());
    }
    
    public SubmissionDto createSubmission(CreateSubmissionRequest request) {
        Submission submission = new Submission();
        submission.setExamId(request.getExamId());
        submission.setUserId(request.getUserId());
        submission.setTotalScore(request.getTotalScore());
        submission.setFeedback(request.getFeedback());
        
        Submission savedSubmission = submissionRepository.save(submission);
        return new SubmissionDto(savedSubmission);
    }
    
    public Optional<SubmissionDto> updateSubmission(Integer examId, Integer userId, CreateSubmissionRequest request) {
        return submissionRepository.findByExamIdAndUserId(examId, userId)
                .map(submission -> {
                    submission.setTotalScore(request.getTotalScore());
                    submission.setFeedback(request.getFeedback());
                    return new SubmissionDto(submissionRepository.save(submission));
                });
    }
    
    public boolean deleteSubmission(Integer examId, Integer userId) {
        SubmissionId id = new SubmissionId();
        id.setExamId(examId);
        id.setUserId(userId);
        
        if (submissionRepository.existsById(id)) {
            submissionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
