package br.com.neurotech.challenge.service.model.credit;

import br.com.neurotech.challenge.entity.CreditType;
import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.model.dto.CreditAnalysisDTO;
import br.com.neurotech.challenge.model.dto.CreditApplicabilityDTO;

public class PayrollLoansCreditAnalysis extends CreditAnalysis{

    public PayrollLoansCreditAnalysis(CreditAnalysis creditAnalysis){
        super(creditAnalysis);
    }
    @Override
    public void checkApplicability(NeurotechClient client, CreditAnalysisDTO creditAnalysisDTO) {
        Boolean isApplicable = client.getAge() > 20 && client.getAge() < 66 && client.getIncome() > 5000 && client.getIncome() < 15000;

        creditAnalysisDTO.getCredits().add(CreditApplicabilityDTO.builder()
                .creditType(CreditType.INTEREST_VARIABLE)
                .eligible(isApplicable)
                .build());

        this.nextCreditAnalysis.checkApplicability(client, creditAnalysisDTO);
    }
}
