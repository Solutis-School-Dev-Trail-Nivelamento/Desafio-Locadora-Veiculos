package br.edu.solutis.dev.trail.locadora.mappers;

import br.edu.solutis.dev.trail.locadora.model.dto.FabricanteDTO;
import br.edu.solutis.dev.trail.locadora.model.entity.Fabricante;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-31T22:13:48-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.2 (Eclipse Adoptium)"
)
@Component
public class FabricanteMapperImpl implements FabricanteMapper {

    @Override
    public FabricanteDTO toDto(Fabricante entity) {
        if ( entity == null ) {
            return null;
        }

        FabricanteDTO fabricanteDTO = new FabricanteDTO();

        fabricanteDTO.setId( entity.getId() );
        fabricanteDTO.setNome( entity.getNome() );

        return fabricanteDTO;
    }

    @Override
    public Fabricante toEntity(FabricanteDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Fabricante fabricante = new Fabricante();

        fabricante.setId( dto.getId() );
        fabricante.setNome( dto.getNome() );

        return fabricante;
    }

    @Override
    public List<FabricanteDTO> toDtoList(List<Fabricante> entities) {
        if ( entities == null ) {
            return null;
        }

        List<FabricanteDTO> list = new ArrayList<FabricanteDTO>( entities.size() );
        for ( Fabricante fabricante : entities ) {
            list.add( toDto( fabricante ) );
        }

        return list;
    }

    @Override
    public List<Fabricante> toEntityList(List<FabricanteDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Fabricante> list = new ArrayList<Fabricante>( dtos.size() );
        for ( FabricanteDTO fabricanteDTO : dtos ) {
            list.add( toEntity( fabricanteDTO ) );
        }

        return list;
    }
}
