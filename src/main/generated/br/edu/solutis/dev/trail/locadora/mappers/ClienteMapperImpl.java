package br.edu.solutis.dev.trail.locadora.mappers;

import br.edu.solutis.dev.trail.locadora.model.dto.ClienteDTO;
import br.edu.solutis.dev.trail.locadora.model.entity.Cliente;
import br.edu.solutis.dev.trail.locadora.model.entity.Sexo;
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
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public Cliente toEntity(ClienteDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setNome( dto.getNome() );
        cliente.setCpf( dto.getCpf() );
        cliente.setEmail( dto.getEmail() );
        cliente.setDataNascimento( dto.getDataNascimento() );
        if ( dto.getSexo() != null ) {
            cliente.setSexo( Enum.valueOf( Sexo.class, dto.getSexo() ) );
        }
        cliente.setCnh( dto.getCnh() );

        return cliente;
    }

    @Override
    public ClienteDTO toDto(Cliente entity) {
        if ( entity == null ) {
            return null;
        }

        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setNome( entity.getNome() );
        clienteDTO.setCpf( entity.getCpf() );
        clienteDTO.setEmail( entity.getEmail() );
        clienteDTO.setDataNascimento( entity.getDataNascimento() );
        if ( entity.getSexo() != null ) {
            clienteDTO.setSexo( entity.getSexo().name() );
        }
        clienteDTO.setCnh( entity.getCnh() );

        return clienteDTO;
    }

    @Override
    public List<ClienteDTO> toDtoList(List<Cliente> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ClienteDTO> list = new ArrayList<ClienteDTO>( entities.size() );
        for ( Cliente cliente : entities ) {
            list.add( toDto( cliente ) );
        }

        return list;
    }

    @Override
    public List<Cliente> toEntityList(List<ClienteDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Cliente> list = new ArrayList<Cliente>( dtos.size() );
        for ( ClienteDTO clienteDTO : dtos ) {
            list.add( toEntity( clienteDTO ) );
        }

        return list;
    }
}
