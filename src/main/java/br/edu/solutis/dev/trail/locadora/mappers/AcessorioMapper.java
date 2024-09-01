package br.edu.solutis.dev.trail.locadora.mappers;

import br.edu.solutis.dev.trail.locadora.model.dto.AcessorioDTO;
import br.edu.solutis.dev.trail.locadora.model.entity.Acessorio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AcessorioMapper {

    AcessorioMapper INSTANCE = Mappers.getMapper(AcessorioMapper.class);

    AcessorioDTO toDto(Acessorio entity);

    Acessorio toEntity(AcessorioDTO dto);

    List<AcessorioDTO> toDtoList(List<Acessorio> entities);

    List<Acessorio> toEntityList(List<AcessorioDTO> dtos);
}
