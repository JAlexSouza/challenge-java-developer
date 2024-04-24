package br.com.neurotech.challenge.service.model.credit;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.model.dto.CreditAnalysisDTO;

public class NoCreditAnalysis extends CreditAnalysis{

    public NoCreditAnalysis(CreditAnalysis creditAnalysis) {
        super(creditAnalysis);
    }

    public NoCreditAnalysis() {
        super();
    }

    @Override
    public void checkApplicability(NeurotechClient client, CreditAnalysisDTO creditAnalysisDTO) {
        return;
    }
}
