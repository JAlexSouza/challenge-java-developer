package br.com.neurotech.challenge.service.model.autocredit;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.model.dto.AutoCreditApplicabilityDTO;

public class NoAutoCreditAnalysis extends AutoCreditAnalysis {

    public NoAutoCreditAnalysis() {
        super();
    }

    @Override
    public AutoCreditApplicabilityDTO checkEligible(NeurotechClient client, VehicleModel vehicleModel) {
        return null;
    }
}
