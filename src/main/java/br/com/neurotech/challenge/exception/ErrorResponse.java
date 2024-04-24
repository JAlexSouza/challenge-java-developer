package br.com.neurotech.challenge.exception;

import lombok.Builder;
import lombok.Data;

import java.util.Calendar;

@Data
@Builder
public class ErrorResponse {
    private Calendar timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

}
