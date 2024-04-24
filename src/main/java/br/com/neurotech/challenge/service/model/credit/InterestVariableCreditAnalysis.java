package br.com.neurotech.challenge.service.model.credit;

import br.com.neurotech.challenge.entity.CreditType;
import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.model.dto.CreditAnalysisDTO;
import br.com.neurotech.challenge.model.dto.CreditApplicabilityDTO;

public class InterestVariableCreditAnalysis extends CreditAnalysis{

    public InterestVariableCreditAnalysis(CreditAnalysis creditAnalysis){
        super(creditAnalysis);
    }
    @Override
    public void checkApplicability(NeurotechClient client, CreditAnalysisDTO creditAnalysisDTO) {
        Boolean isApplicable = client.getAge() > 65;

        creditAnalysisDTO.getCredits().add(CreditApplicabilityDTO.builder()
                .creditType(CreditType.PAYROLL_LOANS)
                .eligible(isApplicable)
                .build());

        this.nextCreditAnalysis.checkApplicability(client, creditAnalysisDTO);
    }
}
