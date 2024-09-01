package br.edu.solutis.dev.trail.locadora.mappers;

import br.edu.solutis.dev.trail.locadora.model.dto.CarroDTO;
import br.edu.solutis.dev.trail.locadora.model.entity.Acessorio;
import br.edu.solutis.dev.trail.locadora.model.entity.Carro;
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
public class CarroMapperImpl implements CarroMapper {

    @Autowired
    private ModeloCarroMapper modeloCarroMapper;
    @Autowired
    private AcessorioMapper acessorioMapper;

    @Override
    public CarroDTO toDto(Carro entity) {
        if ( entity == null ) {
            return null;
        }

        CarroDTO carroDTO = new CarroDTO();

        carroDTO.setModelo( modeloCarroMapper.toDto( entity.getModelo() ) );
        carroDTO.setAcessorios( acessorioMapper.toDtoList( entity.getAcessorios() ) );
        carroDTO.setId( entity.getId() );
        carroDTO.setPlaca( entity.getPlaca() );
        carroDTO.setChassi( entity.getChassi() );
        carroDTO.setCor( entity.getCor() );
        carroDTO.setValorDiaria( entity.getValorDiaria() );

        return carroDTO;
    }

    @Override
    public Carro toEntity(CarroDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Carro carro = new Carro();

        carro.setModelo( modeloCarroMapper.toEntity( dto.getModelo() ) );
        carro.setAcessorios( acessorioMapper.toEntityList( dto.getAcessorios() ) );
        carro.setId( dto.getId() );
        carro.setPlaca( dto.getPlaca() );
        carro.setChassi( dto.getChassi() );
        carro.setCor( dto.getCor() );
        carro.setValorDiaria( dto.getValorDiaria() );

        return carro;
    }

    @Override
    public List<CarroDTO> toDtoList(List<Carro> entities) {
        if ( entities == null ) {
            return null;
        }

        List<CarroDTO> list = new ArrayList<CarroDTO>( entities.size() );
        for ( Carro carro : entities ) {
            list.add( toDto( carro ) );
        }

        return list;
    }

    @Override
    public List<Carro> toEntityList(List<CarroDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Carro> list = new ArrayList<Carro>( dtos.size() );
        for ( CarroDTO carroDTO : dtos ) {
            list.add( toEntity( carroDTO ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromDto(CarroDTO dto, Carro entity) {
        if ( dto == null ) {
            return;
        }

        entity.setPlaca( dto.getPlaca() );
        entity.setChassi( dto.getChassi() );
        entity.setCor( dto.getCor() );
        entity.setValorDiaria( dto.getValorDiaria() );
        entity.setModelo( modeloCarroMapper.toEntity( dto.getModelo() ) );
        if ( entity.getAcessorios() != null ) {
            List<Acessorio> list = acessorioMapper.toEntityList( dto.getAcessorios() );
            if ( list != null ) {
                entity.getAcessorios().clear();
                entity.getAcessorios().addAll( list );
            }
            else {
                entity.setAcessorios( null );
            }
        }
        else {
            List<Acessorio> list = acessorioMapper.toEntityList( dto.getAcessorios() );
            if ( list != null ) {
                entity.setAcessorios( list );
            }
        }
    }
}
