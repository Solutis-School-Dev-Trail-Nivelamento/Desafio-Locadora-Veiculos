package br.edu.solutis.dev.trail.locadora.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        ErrorResponse errorResponse = new ErrorResponse("Cadastro não realizado", e.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        ErrorResponse errorResponse = new ErrorResponse("Erro de negócio", e.getMessage());
        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse("Erro interno do servidor", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }@ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        // Verifique se a exceção contém informações sobre a violação de chave única
        if (e.getRootCause() != null && e.getRootCause().getMessage() != null) {
            String rootCauseMessage = e.getRootCause().getMessage();

            // Verifique a mensagem de erro para identificar se é um erro de duplicação de chave única
            if (rootCauseMessage.contains("Duplicate entry")) {
                ErrorResponse errorResponse = new ErrorResponse("Conflito de dados", "Registro duplicado encontrado: " + rootCauseMessage);
                return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
            }
        }

        // Caso não seja um erro de duplicação, trate como erro genérico de integridade
        ErrorResponse errorResponse = new ErrorResponse("Conflito de dados", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

}
