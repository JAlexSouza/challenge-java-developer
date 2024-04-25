package br.com.neurotech.challenge.service;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.exception.ClientNotFoundException;
import br.com.neurotech.challenge.exception.InvalidClientIDException;
import br.com.neurotech.challenge.model.dto.ClientDTO;
import br.com.neurotech.challenge.model.mapper.ClientMapper;
import br.com.neurotech.challenge.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClientServiceImp implements ClientService {

    private final String URL = "http://localhost:8080/api/client/";
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientMapper clientMapper;

    @Override
    public ResponseEntity<?> save(ClientDTO clientDTO) {
        NeurotechClient newClient = clientRepository.save(clientMapper.convert(clientDTO));

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(URI.create(URL + newClient.getId()));

        return new ResponseEntity(responseHeaders, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ClientDTO> get(String id) throws Exception {
        try {
            Optional<NeurotechClient> client = Optional.ofNullable(clientRepository.findById(Long.parseLong(id)).orElseThrow(() -> new IllegalArgumentException()));

            return new ResponseEntity<ClientDTO>(clientMapper.convert(client.get()), HttpStatus.OK);

        } catch (NumberFormatException ex) {
            throw new InvalidClientIDException("You must provide a valid type for ID client");
        } catch (IllegalArgumentException ex) {
            throw new ClientNotFoundException("You must provide a valid ID client");
        }
    }
}
