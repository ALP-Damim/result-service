package com.kt.damim.dbgenerator.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateSubmissionAnswerRequest {
    private Integer examId;
    private Integer userId;
    private Integer questionId;
    private String answerText;
    private Boolean isCorrect;
    private BigDecimal score;
    private Integer solvingTime;
}
