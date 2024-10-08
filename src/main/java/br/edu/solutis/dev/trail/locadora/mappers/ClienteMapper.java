package br.edu.solutis.dev.trail.locadora.mappers;

import br.edu.solutis.dev.trail.locadora.model.dto.ClienteDTO;
import br.edu.solutis.dev.trail.locadora.model.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "alugueis", ignore = true) // Ignora o mapeamento de alugueis
    Cliente toEntity(ClienteDTO dto);

    ClienteDTO toDto(Cliente entity);

    List<ClienteDTO> toDtoList(List<Cliente> entities);

    List<Cliente> toEntityList(List<ClienteDTO> dtos);
}
