package br.com.neurotech.challenge.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ClientDTO {
    private String name;
    private Integer age;
    private Double income;
}
