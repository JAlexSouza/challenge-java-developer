package br.com.neurotech.challenge.model.mapper;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.model.dto.ClientDTO;
import br.com.neurotech.challenge.model.dto.CustomCreditAnalysisDTO;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public NeurotechClient convert(ClientDTO clientDTO){
        return NeurotechClient.builder()
                .name(clientDTO.getName())
                .age(clientDTO.getAge())
                .income(clientDTO.getIncome())
                .build();
    }

    public ClientDTO convert(NeurotechClient client){
        return ClientDTO.builder()
                .name(client.getName())
                .age(client.getAge())
                .income(client.getIncome())
                .build();
    }

    public NeurotechClient convert(CustomCreditAnalysisDTO client){
        return NeurotechClient.builder()
                .name(client.getClientName())
                .age(client.getClientAge())
                .income(client.getClientIncome())
                .build();
    }

    public CustomCreditAnalysisDTO convertToCustomCreditAnalysisDTO(NeurotechClient client){
        return CustomCreditAnalysisDTO.builder()
                .clientName(client.getName())
                .clientIncome(client.getIncome())
                .clientAge(client.getAge())
                .build();
    }
}
