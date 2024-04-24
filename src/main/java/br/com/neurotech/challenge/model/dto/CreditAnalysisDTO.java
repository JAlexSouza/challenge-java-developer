package br.com.neurotech.challenge.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreditAnalysisDTO {

    private String clientName;
    private List<CreditApplicabilityDTO> credits;
}
