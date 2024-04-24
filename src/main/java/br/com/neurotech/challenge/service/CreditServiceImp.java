package br.com.neurotech.challenge.service;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.exception.InvalidClientIDException;
import br.com.neurotech.challenge.model.dto.AutoCreditApplicabilityDTO;
import br.com.neurotech.challenge.model.dto.CreditAnalysisDTO;
import br.com.neurotech.challenge.model.dto.CreditApplicabilityDTO;
import br.com.neurotech.challenge.repository.ClientRepository;
import br.com.neurotech.challenge.service.model.autocredit.AutoCreditAnalysis;
import br.com.neurotech.challenge.service.model.autocredit.HatchAutoCreditAnalysis;
import br.com.neurotech.challenge.service.model.autocredit.NoAutoCreditAnalysis;
import br.com.neurotech.challenge.service.model.autocredit.SUVAutoCreditAnalysis;
import br.com.neurotech.challenge.service.model.credit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CreditServiceImp implements CreditService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ResponseEntity<AutoCreditApplicabilityDTO> checkCredit(String clientId, VehicleModel model) throws Exception {
        try {
            Optional<NeurotechClient> client = Optional.ofNullable(clientRepository.findById(Long.parseLong(clientId)).orElseThrow(() -> new IllegalArgumentException()));

            // Chain of Responsibility Pattern implementation
            AutoCreditAnalysis autoCreditAnalysis = new HatchAutoCreditAnalysis(
                                                            new SUVAutoCreditAnalysis(
                                                            new NoAutoCreditAnalysis()
                                                            ));

            AutoCreditApplicabilityDTO autoCreditApplicabilityDTO = autoCreditAnalysis.checkEligible(client.get(), model);

            return new ResponseEntity<AutoCreditApplicabilityDTO>(autoCreditApplicabilityDTO, HttpStatus.OK);
        } catch (NumberFormatException ex) {
            throw new InvalidClientIDException("You must provide a valid type for ID client");
        } catch (IllegalArgumentException ex) {
            throw new InvalidClientIDException("You must provide a valid ID client");
        }
    }

    public ResponseEntity<CreditAnalysisDTO> creditAnalysis(String id) throws Exception {
        try {
            Optional<NeurotechClient> client = Optional.ofNullable(clientRepository.findById(Long.parseLong(id)).orElseThrow(() -> new IllegalArgumentException()));

            CreditAnalysisDTO creditAnalysisDTO = CreditAnalysisDTO.builder()
                    .clientName(client.get().getName())
                    .credits(new ArrayList<CreditApplicabilityDTO>())
                    .build();

            // Chain of Responsibility Pattern implementation
            CreditAnalysis creditAnalysis = new InterestFixedCreditAnalysis(
                                            new InterestVariableCreditAnalysis(
                                            new PayrollLoansCreditAnalysis(
                                            new NoCreditAnalysis()
                                            )));

            creditAnalysis.checkApplicability(client.get(), creditAnalysisDTO);

            return new ResponseEntity<CreditAnalysisDTO>(creditAnalysisDTO, HttpStatus.OK);
        } catch (NumberFormatException ex) {
            throw new InvalidClientIDException("You must provide a valid type for ID client");
        } catch (IllegalArgumentException ex) {
            throw new InvalidClientIDException("You must provide a valid ID client");
        }
    }
}
