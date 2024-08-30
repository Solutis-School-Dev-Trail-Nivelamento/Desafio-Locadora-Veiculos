package br.edu.solutis.dev.trail.locadora.exceptions;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ErrorResponse {
    private String error;
    private String message;


    public ErrorResponse(String error, String message) {
        this.error = error;
        this.message = message;
    }
}
