package br.com.neurotech.challenge.service.model.autocredit;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.model.dto.AutoCreditApplicabilityDTO;

public class SUVAutoCreditAnalysis extends AutoCreditAnalysis{

    public SUVAutoCreditAnalysis(AutoCreditAnalysis autoCreditAnalysis) {
        super(autoCreditAnalysis);
    }

    @Override
    public AutoCreditApplicabilityDTO checkEligible(NeurotechClient client, VehicleModel vehicleModel) {
        if (vehicleModel.equals(VehicleModel.SUV)){
            Boolean isEligible = client.getIncome() > 8000 && client.getAge() > 20;

            return AutoCreditApplicabilityDTO.builder()
                    .vehicleModel(vehicleModel)
                    .eligible(isEligible)
                    .build();
        }

        return this.nextAutoCreditAnalysis.checkEligible(client, vehicleModel);
    }
}
