
package br.edu.solutis.dev.trail.locadora.service;

import br.edu.solutis.dev.trail.locadora.exceptions.BusinessException;
import br.edu.solutis.dev.trail.locadora.model.dto.FabricanteDTO;
import br.edu.solutis.dev.trail.locadora.model.entity.Fabricante;
import br.edu.solutis.dev.trail.locadora.repository.FabricanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FabricanteService {

    private final FabricanteRepository fabricanteRepository;

    @Autowired
    public FabricanteService(FabricanteRepository fabricanteRepository) {
        this.fabricanteRepository = fabricanteRepository;
    }

    public List<FabricanteDTO> findAll() {
        return fabricanteRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<FabricanteDTO> findById(Long id) {
        return fabricanteRepository.findById(id)
                .map(this::convertToDTO);
    }

    public FabricanteDTO save(FabricanteDTO fabricanteDTO) {
        if (fabricanteDTO.getNome() == null || fabricanteDTO.getNome().trim().isEmpty()) {
            throw new BusinessException("Nome do fabricante não pode estar em branco.");
        }
        Fabricante fabricante = convertToEntity(fabricanteDTO);
        Fabricante savedFabricante = fabricanteRepository.save(fabricante);
        return convertToDTO(savedFabricante);
    }

    public void deleteById(Long id) {
        fabricanteRepository.deleteById(id);
    }

    private FabricanteDTO convertToDTO(Fabricante fabricante) {
        FabricanteDTO dto = new FabricanteDTO();
        dto.setId(fabricante.getId());
        dto.setNome(fabricante.getNome());
        return dto;
    }

    private Fabricante convertToEntity(FabricanteDTO dto) {
        Fabricante fabricante = new Fabricante();
        fabricante.setId(dto.getId());
        fabricante.setNome(dto.getNome());
        return fabricante;
    }
}
