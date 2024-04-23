package br.com.neurotech.challenge.model.mapper;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.model.dto.ClientDTO;
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
}
