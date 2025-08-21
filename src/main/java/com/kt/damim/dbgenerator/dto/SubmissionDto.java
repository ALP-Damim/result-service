package com.kt.damim.dbgenerator.dto;

import com.kt.damim.dbgenerator.entity.Submission;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
public class SubmissionDto {
    private Integer examId;
    private Integer userId;
    private Instant submittedAt;
    private BigDecimal totalScore;
    private String feedback;
    private Submission.FeedbackStatus feedbackStatus;
    private Instant feedbackRequestedAt;
    private Integer feedbackRetryCount;

    public SubmissionDto(Submission submission) {
        this.examId = submission.getExamId();
        this.userId = submission.getUserId();
        this.submittedAt = submission.getSubmittedAt();
        this.totalScore = submission.getTotalScore();
        this.feedback = submission.getFeedback();
        this.feedbackStatus = submission.getFeedbackStatus();
        this.feedbackRequestedAt = submission.getFeedbackRequestedAt();
        this.feedbackRetryCount = submission.getFeedbackRetryCount();
    }
}
