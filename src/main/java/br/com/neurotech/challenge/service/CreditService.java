package br.com.neurotech.challenge.service;

import br.com.neurotech.challenge.model.dto.AutoCreditApplicabilityDTO;
import br.com.neurotech.challenge.model.dto.CreditAnalysisDTO;
import br.com.neurotech.challenge.model.dto.CustomCreditAnalysisDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.neurotech.challenge.entity.VehicleModel;

import java.util.List;

@Service
public interface
CreditService {
	
	/**
	 * Efetua a checagem se o cliente está apto a receber crédito
	 * para um determinado modelo de veículo
	 */
	ResponseEntity<AutoCreditApplicabilityDTO> checkCredit(String clientId, VehicleModel model) throws Exception;

	public ResponseEntity<CreditAnalysisDTO> creditAnalysis(String id) throws Exception;

	public ResponseEntity<List<CustomCreditAnalysisDTO>> customCreditAnalysis();
	
}
