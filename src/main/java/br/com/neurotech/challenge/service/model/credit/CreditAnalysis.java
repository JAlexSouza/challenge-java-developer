package br.com.neurotech.challenge.service.model.credit;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.model.dto.CreditAnalysisDTO;

public abstract class CreditAnalysis {
    protected CreditAnalysis nextCreditAnalysis;

    public CreditAnalysis(CreditAnalysis creditAnalysis){
        this.nextCreditAnalysis = creditAnalysis;
    }

    public CreditAnalysis() {}

    public abstract void checkApplicability(NeurotechClient client, CreditAnalysisDTO creditAnalysisDTO);
}
