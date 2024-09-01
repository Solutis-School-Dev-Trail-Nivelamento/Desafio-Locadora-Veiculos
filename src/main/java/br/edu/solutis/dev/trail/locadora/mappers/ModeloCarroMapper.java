package br.edu.solutis.dev.trail.locadora.mappers;

import br.edu.solutis.dev.trail.locadora.model.dto.ModeloCarroDTO;
import br.edu.solutis.dev.trail.locadora.model.entity.ModeloCarro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = FabricanteMapper.class)
public interface ModeloCarroMapper {

    ModeloCarroMapper INSTANCE = Mappers.getMapper(ModeloCarroMapper.class);

    @Mapping(source = "fabricante", target = "fabricante")
    ModeloCarroDTO toDto(ModeloCarro entity);

    @Mapping(source = "fabricante", target = "fabricante")
    ModeloCarro toEntity(ModeloCarroDTO dto);

    List<ModeloCarroDTO> toDtoList(List<ModeloCarro> entities);

    List<ModeloCarro> toEntityList(List<ModeloCarroDTO> dtos);
}
