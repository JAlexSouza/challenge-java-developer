package br.com.neurotech.challenge.controller.api;

import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.exception.ErrorResponse;
import br.com.neurotech.challenge.model.dto.AutoCreditApplicabilityDTO;
import br.com.neurotech.challenge.model.dto.CreditAnalysisDTO;
import br.com.neurotech.challenge.model.dto.CustomCreditAnalysisDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Credit", description = "Credit API")
public interface CreditAPI {

    @Operation(summary = "Consulting of credit applicability for client by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Credit Analysis returned with successfully",
                    content = { @Content(schema = @Schema(implementation = CreditAnalysisDTO.class), mediaType = MediaType.APPLICATION_JSON_VALUE) }
            ),
            @ApiResponse(responseCode = "400",
                    description = "Invalid client ID type.",
                    content = { @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE) }
            ),
            @ApiResponse(responseCode = "404",
                    description = "Client not found.",
                    content = { @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE) }
            )
    })
    @GetMapping(value = "/analysis/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CreditAnalysisDTO> creditAnalysis(@PathVariable String id) throws Exception;

    @Operation(summary = "Consulting of auto credit applicability for client by ID and auto model")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Auto credit Analysis returned with successfully",
                    content = { @Content(schema = @Schema(implementation = AutoCreditApplicabilityDTO.class), mediaType = MediaType.APPLICATION_JSON_VALUE) }
            ),
            @ApiResponse(responseCode = "400",
                    description = "Invalid client ID type.",
                    content = { @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE) }
            ),
            @ApiResponse(responseCode = "404",
                    description = "Client not found.",
                    content = { @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE) }
            )
    })
    @GetMapping(value = "/analysis", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AutoCreditApplicabilityDTO> creditAnalysis(@RequestParam(required = false) String id,
                                                                     @RequestParam(required = false) VehicleModel model ) throws Exception;

    @Operation(summary = "Custom consulting of clients from a age group")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Custom consulting returned with successfully",
                    content = { @Content(schema = @Schema(implementation = CustomCreditAnalysisDTO.class), mediaType = MediaType.APPLICATION_JSON_VALUE) }
            )
    })
    @GetMapping(value = "/custom-analysis", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<List<CustomCreditAnalysisDTO>> customCreditAnalysis();
}
