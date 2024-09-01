
package br.edu.solutis.dev.trail.locadora.service;

import br.edu.solutis.dev.trail.locadora.exceptions.BusinessException;
import br.edu.solutis.dev.trail.locadora.model.dto.AcessorioDTO;
import br.edu.solutis.dev.trail.locadora.model.entity.Acessorio;
import br.edu.solutis.dev.trail.locadora.repository.AcessorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AcessorioService {

    private final AcessorioRepository acessorioRepository;

    @Autowired
    public AcessorioService(AcessorioRepository acessorioRepository) {
        this.acessorioRepository = acessorioRepository;
    }

    public List<AcessorioDTO> findAll() {
        return acessorioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<AcessorioDTO> findById(Long id) {
        return acessorioRepository.findById(id)
                .map(this::convertToDTO);
    }

    public AcessorioDTO save(AcessorioDTO acessorioDTO) {
        if (acessorioDTO.getDescricao() == null || acessorioDTO.getDescricao().trim().isEmpty()) {
            throw new BusinessException("Descrição do acessório não pode estar em branco.");
        }
        Acessorio acessorio = convertToEntity(acessorioDTO);
        Acessorio savedAcessorio = acessorioRepository.save(acessorio);
        return convertToDTO(savedAcessorio);
    }

    public void deleteById(Long id) {
        acessorioRepository.deleteById(id);
    }

    private AcessorioDTO convertToDTO(Acessorio acessorio) {
        AcessorioDTO dto = new AcessorioDTO();
        dto.setId(acessorio.getId());
        dto.setDescricao(acessorio.getDescricao());
        return dto;
    }

    private Acessorio convertToEntity(AcessorioDTO dto) {
        Acessorio acessorio = new Acessorio();
        acessorio.setId(dto.getId());
        acessorio.setDescricao(dto.getDescricao());
        return acessorio;
    }
}
