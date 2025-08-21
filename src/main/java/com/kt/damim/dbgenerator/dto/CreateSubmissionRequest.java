package com.kt.damim.dbgenerator.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateSubmissionRequest {
    private Integer examId;
    private Integer userId;
    private BigDecimal totalScore;
    private String feedback;
}
