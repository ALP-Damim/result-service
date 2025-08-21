package com.kt.damim.dbgenerator.dto;

import com.kt.damim.dbgenerator.entity.SubmissionAnswer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class SubmissionAnswerDto {
    private Integer examId;
    private Integer userId;
    private Integer questionId;
    private String answerText;
    private Boolean isCorrect;
    private BigDecimal score;
    private Integer solvingTime;

    public SubmissionAnswerDto(SubmissionAnswer submissionAnswer) {
        this.examId = submissionAnswer.getExamId();
        this.userId = submissionAnswer.getUserId();
        this.questionId = submissionAnswer.getQuestionId();
        this.answerText = submissionAnswer.getAnswerText();
        this.isCorrect = submissionAnswer.getIsCorrect();
        this.score = submissionAnswer.getScore();
        this.solvingTime = submissionAnswer.getSolvingTime();
    }
}
