package br.edu.solutis.dev.trail.locadora.exceptions;

public class NotFoundException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public NotFoundException(String s) {
        super("Resource not found.");
    }

}