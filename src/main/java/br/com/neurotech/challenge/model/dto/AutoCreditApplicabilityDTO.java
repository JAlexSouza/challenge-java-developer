package br.com.neurotech.challenge.model.dto;

import br.com.neurotech.challenge.entity.VehicleModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AutoCreditApplicabilityDTO {
    private VehicleModel vehicleModel;
    private Boolean eligible;
}
