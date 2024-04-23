package br.com.neurotech.challenge.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientDTO {
    private String name;
    private Integer age;
    private Double income;
}
