package br.edu.solutis.dev.trail.locadora.mappers;

import br.edu.solutis.dev.trail.locadora.model.dto.AcessorioDTO;
import br.edu.solutis.dev.trail.locadora.model.entity.Acessorio;
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
public class AcessorioMapperImpl implements AcessorioMapper {

    @Override
    public AcessorioDTO toDto(Acessorio entity) {
        if ( entity == null ) {
            return null;
        }

        AcessorioDTO acessorioDTO = new AcessorioDTO();

        acessorioDTO.setId( entity.getId() );
        acessorioDTO.setDescricao( entity.getDescricao() );

        return acessorioDTO;
    }

    @Override
    public Acessorio toEntity(AcessorioDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Acessorio acessorio = new Acessorio();

        acessorio.setId( dto.getId() );
        acessorio.setDescricao( dto.getDescricao() );

        return acessorio;
    }

    @Override
    public List<AcessorioDTO> toDtoList(List<Acessorio> entities) {
        if ( entities == null ) {
            return null;
        }

        List<AcessorioDTO> list = new ArrayList<AcessorioDTO>( entities.size() );
        for ( Acessorio acessorio : entities ) {
            list.add( toDto( acessorio ) );
        }

        return list;
    }

    @Override
    public List<Acessorio> toEntityList(List<AcessorioDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Acessorio> list = new ArrayList<Acessorio>( dtos.size() );
        for ( AcessorioDTO acessorioDTO : dtos ) {
            list.add( toEntity( acessorioDTO ) );
        }

        return list;
    }
}
