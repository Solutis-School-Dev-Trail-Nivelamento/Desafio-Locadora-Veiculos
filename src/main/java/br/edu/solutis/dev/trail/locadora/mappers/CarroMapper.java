package br.edu.solutis.dev.trail.locadora.mappers;

import br.edu.solutis.dev.trail.locadora.model.dto.CarroDTO;
import br.edu.solutis.dev.trail.locadora.model.entity.Carro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ModeloCarroMapper.class, AcessorioMapper.class})
public interface CarroMapper {

    CarroMapper INSTANCE = Mappers.getMapper(CarroMapper.class);

    @Mapping(source = "modelo", target = "modelo")
    @Mapping(source = "acessorios", target = "acessorios")
    CarroDTO toDto(Carro entity);

    @Mapping(source = "modelo", target = "modelo")
    @Mapping(source = "acessorios", target = "acessorios")
    Carro toEntity(CarroDTO dto);

    List<CarroDTO> toDtoList(List<Carro> entities);

    List<Carro> toEntityList(List<CarroDTO> dtos);

    @Mapping(target = "id", ignore = true) // Não queremos atualizar o ID
    void updateEntityFromDto(CarroDTO dto, @MappingTarget Carro entity);
}
