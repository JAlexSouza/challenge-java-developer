package br.com.neurotech.challenge.service.model.credit;

import br.com.neurotech.challenge.entity.CreditType;
import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.model.dto.CreditAnalysisDTO;
import br.com.neurotech.challenge.model.dto.CreditApplicabilityDTO;

public class InterestFixedCreditAnalysis extends CreditAnalysis{

    public InterestFixedCreditAnalysis(CreditAnalysis creditAnalysis){
        super(creditAnalysis);
    }
    @Override
    public void checkApplicability(NeurotechClient client, CreditAnalysisDTO creditAnalysisDTO) {
        Boolean isApplicable = client.getAge() > 17 && client.getAge() < 26;

        creditAnalysisDTO.getCredits().add(CreditApplicabilityDTO.builder()
                .creditType(CreditType.INTEREST_FIXED)
                .eligible(isApplicable)
                .build());

        this.nextCreditAnalysis.checkApplicability(client, creditAnalysisDTO);
    }
}
