package br.edu.solutis.dev.trail.locadora.mappers;

import br.edu.solutis.dev.trail.locadora.model.dto.PagamentoDTO;
import br.edu.solutis.dev.trail.locadora.model.entity.Pagamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PagamentoMapper {

    @Mapping(target = "id", ignore = true)
    Pagamento toEntity(PagamentoDTO dto);

    PagamentoDTO toDto(Pagamento entity);

    List<PagamentoDTO> toDtoList(List<Pagamento> entities);

    List<Pagamento> toEntityList(List<PagamentoDTO> dtos);
}
