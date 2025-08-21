package com.kt.damim.dbgenerator.service;

import com.kt.damim.dbgenerator.dto.CreateSubmissionAnswerRequest;
import com.kt.damim.dbgenerator.dto.SubmissionAnswerDto;
import com.kt.damim.dbgenerator.entity.SubmissionAnswer;
import com.kt.damim.dbgenerator.entity.SubmissionAnswerId;
import com.kt.damim.dbgenerator.repository.SubmissionAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SubmissionAnswerService {
    
    private final SubmissionAnswerRepository submissionAnswerRepository;
    
    public List<SubmissionAnswerDto> getAllSubmissionAnswers() {
        return submissionAnswerRepository.findAll()
                .stream()
                .map(SubmissionAnswerDto::new)
                .collect(Collectors.toList());
    }
    
    public Optional<SubmissionAnswerDto> getSubmissionAnswerById(Integer examId, Integer userId, Integer questionId) {
        SubmissionAnswerId id = new SubmissionAnswerId();
        id.setExamId(examId);
        id.setUserId(userId);
        id.setQuestionId(questionId);
        
        return submissionAnswerRepository.findById(id)
                .map(SubmissionAnswerDto::new);
    }
    
    public List<SubmissionAnswerDto> getSubmissionAnswersByExamAndUser(Integer examId, Integer userId) {
        return submissionAnswerRepository.findByExamIdAndUserId(examId, userId)
                .stream()
                .map(SubmissionAnswerDto::new)
                .collect(Collectors.toList());
    }
    
    public SubmissionAnswerDto createSubmissionAnswer(CreateSubmissionAnswerRequest request) {
        SubmissionAnswer submissionAnswer = new SubmissionAnswer();
        submissionAnswer.setExamId(request.getExamId());
        submissionAnswer.setUserId(request.getUserId());
        submissionAnswer.setQuestionId(request.getQuestionId());
        submissionAnswer.setAnswerText(request.getAnswerText());
        submissionAnswer.setIsCorrect(request.getIsCorrect());
        submissionAnswer.setScore(request.getScore());
        submissionAnswer.setSolvingTime(request.getSolvingTime());
        
        SubmissionAnswer savedSubmissionAnswer = submissionAnswerRepository.save(submissionAnswer);
        return new SubmissionAnswerDto(savedSubmissionAnswer);
    }
    
    public Optional<SubmissionAnswerDto> updateSubmissionAnswer(Integer examId, Integer userId, Integer questionId, CreateSubmissionAnswerRequest request) {
        SubmissionAnswerId id = new SubmissionAnswerId();
        id.setExamId(examId);
        id.setUserId(userId);
        id.setQuestionId(questionId);
        
        return submissionAnswerRepository.findById(id)
                .map(submissionAnswer -> {
                    submissionAnswer.setAnswerText(request.getAnswerText());
                    submissionAnswer.setIsCorrect(request.getIsCorrect());
                    submissionAnswer.setScore(request.getScore());
                    submissionAnswer.setSolvingTime(request.getSolvingTime());
                    return new SubmissionAnswerDto(submissionAnswerRepository.save(submissionAnswer));
                });
    }
    
    public boolean deleteSubmissionAnswer(Integer examId, Integer userId, Integer questionId) {
        SubmissionAnswerId id = new SubmissionAnswerId();
        id.setExamId(examId);
        id.setUserId(userId);
        id.setQuestionId(questionId);
        
        if (submissionAnswerRepository.existsById(id)) {
            submissionAnswerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
