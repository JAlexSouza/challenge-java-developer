package br.com.neurotech.challenge.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CustomCreditAnalysisDTO {
    private String clientName;
    private Double clientIncome;

    @JsonIgnore
    private Integer clientAge;
    @JsonIgnore
    private CreditApplicabilityDTO credit;
    @JsonIgnore
    private AutoCreditApplicabilityDTO autoCredit;
}

