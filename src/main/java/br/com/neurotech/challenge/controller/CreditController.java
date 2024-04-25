package br.com.neurotech.challenge.controller;

import br.com.neurotech.challenge.controller.api.CreditAPI;
import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.model.dto.AutoCreditApplicabilityDTO;
import br.com.neurotech.challenge.model.dto.CreditAnalysisDTO;
import br.com.neurotech.challenge.model.dto.CustomCreditAnalysisDTO;
import br.com.neurotech.challenge.service.CreditServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credit")
public class CreditController implements CreditAPI {

    @Autowired
    private CreditServiceImp creditService;

    @Override
    public ResponseEntity<CreditAnalysisDTO> creditAnalysis( String id) throws Exception {
        return creditService.creditAnalysis(id);
    }

    @Override
    public ResponseEntity<AutoCreditApplicabilityDTO> creditAnalysis(@RequestParam(required = false) String id,
                                                                     @RequestParam(required = false) VehicleModel model ) throws Exception {
        return creditService.checkCredit(id, model);
    }

    @Override
    public ResponseEntity<List<CustomCreditAnalysisDTO>> customCreditAnalysis(){
        return creditService.customCreditAnalysis();
    }
}
