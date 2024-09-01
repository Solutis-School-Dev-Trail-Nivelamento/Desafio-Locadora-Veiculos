package br.edu.solutis.dev.trail.locadora.mappers;

import br.edu.solutis.dev.trail.locadora.model.dto.FabricanteDTO;
import br.edu.solutis.dev.trail.locadora.model.entity.Fabricante;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FabricanteMapper {

    FabricanteMapper INSTANCE = Mappers.getMapper(FabricanteMapper.class);

    FabricanteDTO toDto(Fabricante entity);

    Fabricante toEntity(FabricanteDTO dto);

    List<FabricanteDTO> toDtoList(List<Fabricante> entities);

    List<Fabricante> toEntityList(List<FabricanteDTO> dtos);
}
