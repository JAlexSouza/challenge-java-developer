package br.com.neurotech.challenge.service.model.autocredit;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.model.dto.AutoCreditApplicabilityDTO;
import br.com.neurotech.challenge.model.dto.CreditAnalysisDTO;
import br.com.neurotech.challenge.service.model.credit.CreditAnalysis;

public abstract class AutoCreditAnalysis {
    protected AutoCreditAnalysis nextAutoCreditAnalysis;

    public AutoCreditAnalysis(AutoCreditAnalysis autoCreditAnalysis){

        this.nextAutoCreditAnalysis = autoCreditAnalysis;
    }

    public AutoCreditAnalysis() {}

    public abstract AutoCreditApplicabilityDTO checkEligible(NeurotechClient client, VehicleModel vehicleModel);
}
