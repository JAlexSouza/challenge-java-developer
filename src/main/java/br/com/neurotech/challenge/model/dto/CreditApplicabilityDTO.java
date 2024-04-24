package br.com.neurotech.challenge.model.dto;

import br.com.neurotech.challenge.entity.CreditType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreditApplicabilityDTO {
    private CreditType creditType;
    private Boolean eligible;
}
