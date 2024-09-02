package br.edu.solutis.dev.trail.locadora.mappers;

import br.edu.solutis.dev.trail.locadora.model.dto.ApoliceSeguroDTO;
import br.edu.solutis.dev.trail.locadora.model.entity.ApoliceSeguro;

public class ApoliceSeguroMapper {

    public ApoliceSeguroDTO toDTO(ApoliceSeguro entity) {
        ApoliceSeguroDTO dto = new ApoliceSeguroDTO();
        dto.setValorFranquia(entity.getValorFranquia());
        dto.setProtecaTerceiro(entity.isProtecaTerceiro());
        dto.setProtecaCausasNaturais(entity.isProtecaCausasNaturais());
        dto.setProtecaRoubo(entity.isProtecaRoubo());
        return dto;
    }

    public ApoliceSeguro toEntity(ApoliceSeguroDTO dto) {
        ApoliceSeguro entity = new ApoliceSeguro();
        entity.setValorFranquia(dto.getValorFranquia());
        entity.setProtecaTerceiro(dto.isProtecaTerceiro());
        entity.setProtecaCausasNaturais(dto.isProtecaCausasNaturais());
        entity.setProtecaRoubo(dto.isProtecaRoubo());
        return entity;
    }
}