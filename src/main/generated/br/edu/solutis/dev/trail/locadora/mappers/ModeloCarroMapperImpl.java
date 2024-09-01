package br.edu.solutis.dev.trail.locadora.mappers;

import br.edu.solutis.dev.trail.locadora.model.dto.ModeloCarroDTO;
import br.edu.solutis.dev.trail.locadora.model.entity.ModeloCarro;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-31T22:13:48-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.2 (Eclipse Adoptium)"
)
@Component
public class ModeloCarroMapperImpl implements ModeloCarroMapper {

    @Autowired
    private FabricanteMapper fabricanteMapper;

    @Override
    public ModeloCarroDTO toDto(ModeloCarro entity) {
        if ( entity == null ) {
            return null;
        }

        ModeloCarroDTO modeloCarroDTO = new ModeloCarroDTO();

        modeloCarroDTO.setFabricante( fabricanteMapper.toDto( entity.getFabricante() ) );
        modeloCarroDTO.setId( entity.getId() );
        modeloCarroDTO.setDescricao( entity.getDescricao() );
        modeloCarroDTO.setCategoria( entity.getCategoria() );

        return modeloCarroDTO;
    }

    @Override
    public ModeloCarro toEntity(ModeloCarroDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ModeloCarro modeloCarro = new ModeloCarro();

        modeloCarro.setFabricante( fabricanteMapper.toEntity( dto.getFabricante() ) );
        modeloCarro.setId( dto.getId() );
        modeloCarro.setDescricao( dto.getDescricao() );
        modeloCarro.setCategoria( dto.getCategoria() );

        return modeloCarro;
    }

    @Override
    public List<ModeloCarroDTO> toDtoList(List<ModeloCarro> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ModeloCarroDTO> list = new ArrayList<ModeloCarroDTO>( entities.size() );
        for ( ModeloCarro modeloCarro : entities ) {
            list.add( toDto( modeloCarro ) );
        }

        return list;
    }

    @Override
    public List<ModeloCarro> toEntityList(List<ModeloCarroDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<ModeloCarro> list = new ArrayList<ModeloCarro>( dtos.size() );
        for ( ModeloCarroDTO modeloCarroDTO : dtos ) {
            list.add( toEntity( modeloCarroDTO ) );
        }

        return list;
    }
}
