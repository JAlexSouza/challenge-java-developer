package br.com.neurotech.challenge.service.model.autocredit;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.model.dto.AutoCreditApplicabilityDTO;
import br.com.neurotech.challenge.model.dto.CreditAnalysisDTO;

public class HatchAutoCreditAnalysis extends AutoCreditAnalysis {

    public HatchAutoCreditAnalysis(AutoCreditAnalysis autoCreditAnalysis) {
        super(autoCreditAnalysis);
    }

    @Override
    public AutoCreditApplicabilityDTO checkEligible(NeurotechClient client, VehicleModel vehicleModel) {
        if (vehicleModel.equals(VehicleModel.HATCH)){
            Boolean isEligible = client.getIncome() >= 5000 && client.getIncome() <= 15000;

            return AutoCreditApplicabilityDTO.builder()
                    .vehicleModel(vehicleModel)
                    .eligible(isEligible)
                    .build();
        }

        return this.nextAutoCreditAnalysis.checkEligible(client, vehicleModel);
    }

}
