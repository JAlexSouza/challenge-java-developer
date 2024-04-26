package br.com.neurotech.challenge.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientDTO {

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 30)
    private String name;
    @NotNull
    private Integer age;
    @NotNull
    private Double income;
}
