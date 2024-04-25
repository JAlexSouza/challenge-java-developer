package br.com.neurotech.challenge.service;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.exception.ClientNotFoundException;
import br.com.neurotech.challenge.exception.InvalidClientIDException;
import br.com.neurotech.challenge.model.dto.*;
import br.com.neurotech.challenge.model.mapper.ClientMapper;
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

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CreditServiceImp implements CreditService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

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
            throw new ClientNotFoundException("You must provide a valid ID client");
        }
    }

    @Override
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

    @Override
    public ResponseEntity<List<CustomCreditAnalysisDTO>> customCreditAnalysis(){
        List<NeurotechClient> clients = clientRepository.findAgeGroup();

        List<CustomCreditAnalysisDTO> customCreditAnalysisDTO = clients.stream()
                                                            .map(clientMapper::convertToCustomCreditAnalysisDTO)
                                                            .collect(Collectors.toList());

        // Chain of Responsibility Pattern implementation
        CreditAnalysis creditAnalysis = new InterestFixedCreditAnalysis(new NoCreditAnalysis());
        AutoCreditAnalysis autoCreditAnalysis = new HatchAutoCreditAnalysis(new NoAutoCreditAnalysis());

        // Credit Analysis
        for (CustomCreditAnalysisDTO client : customCreditAnalysisDTO ) {
            List<CreditApplicabilityDTO> creditApplicabilityDTOList = new ArrayList<>();

            CreditAnalysisDTO creditAnalysisDTO = CreditAnalysisDTO.builder()
                                                        .credits(creditApplicabilityDTOList)
                                                        .build();

            creditAnalysis.checkApplicability(clientMapper.convert(client), creditAnalysisDTO);

            AutoCreditApplicabilityDTO autoCreditApplicabilityDTO = autoCreditAnalysis.checkEligible(clientMapper.convert(client), VehicleModel.HATCH);

            client.setCredit(creditAnalysisDTO.getCredits().get(0));
            client.setAutoCredit(autoCreditApplicabilityDTO);
        }

        customCreditAnalysisDTO = customCreditAnalysisDTO.stream()
                .filter(client -> client.getCredit().getEligible())
                .filter(client -> client.getAutoCredit().getEligible())
                .collect(Collectors.toList());

        if(customCreditAnalysisDTO.isEmpty())
            return new ResponseEntity<List<CustomCreditAnalysisDTO>>(customCreditAnalysisDTO, HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<CustomCreditAnalysisDTO>>(customCreditAnalysisDTO, HttpStatus.OK);
    }
}
