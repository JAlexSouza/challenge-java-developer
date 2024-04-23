package br.com.neurotech.challenge.controller;

import br.com.neurotech.challenge.model.dto.ClientDTO;
import br.com.neurotech.challenge.service.ClientServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientServiceImp clientService;
    @PostMapping
    public ResponseEntity<?> save(@RequestBody ClientDTO clientDTO){
        return clientService.save(clientDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> get(@PathVariable String id){
        return clientService.get(id);
    }
}
