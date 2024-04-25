package br.com.neurotech.challenge.controller.api;

import br.com.neurotech.challenge.exception.ErrorResponse;
import br.com.neurotech.challenge.model.dto.ClientDTO;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Client", description = "Client API")
public interface ClientAPI {

    @Operation(summary = "Create a new Client")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Client created with successfully")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody ClientDTO clientDTO);

    @Operation(summary = "Consulting of client information by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Client returned with successfully.",
                    content = { @Content(schema = @Schema(implementation = ClientDTO.class), mediaType = MediaType.APPLICATION_JSON_VALUE )}
            ),
            @ApiResponse(responseCode = "400",
                    description = "Invalid client ID type.",
                    content = { @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE )}
            ),
            @ApiResponse(responseCode = "404",
                    description = "Client not found.",
                    content = { @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE )}
            )
    })
    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ClientDTO> get(@PathVariable String id) throws Exception;
}
