package br.com.neurotech.challenge.controller;

import br.com.neurotech.challenge.controller.api.ClientAPI;
import br.com.neurotech.challenge.model.dto.ClientDTO;
import br.com.neurotech.challenge.service.ClientServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/client")
public class ClientController implements ClientAPI {

    @Autowired
    private ClientServiceImp clientService;

    @Override
    public ResponseEntity<?> save(ClientDTO clientDTO) {
        return clientService.save(clientDTO);
    }

    @Override
    public ResponseEntity<ClientDTO> get(String id) throws Exception {
        return clientService.get(id);
    }

}
