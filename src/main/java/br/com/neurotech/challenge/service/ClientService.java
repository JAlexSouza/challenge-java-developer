package br.com.neurotech.challenge.service;

import br.com.neurotech.challenge.model.dto.ClientDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.neurotech.challenge.entity.NeurotechClient;

@Service
public interface ClientService {
	
	/**
	 * Salva um novo cliente
	 * 
	 * @return ID do cliente recém-salvo
	 */
	ResponseEntity<?> save(ClientDTO client) throws Exception;
	
	/**
	 * Recupera um cliente baseado no seu ID
	 */
	ResponseEntity<?> get(String id) throws Exception;

}
