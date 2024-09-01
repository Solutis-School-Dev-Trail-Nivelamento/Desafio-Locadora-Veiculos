package br.edu.solutis.dev.trail.locadora.service;

import br.edu.solutis.dev.trail.locadora.model.dto.ModeloCarroDTO;
import br.edu.solutis.dev.trail.locadora.model.entity.ModeloCarro;
import br.edu.solutis.dev.trail.locadora.model.entity.Fabricante;
import br.edu.solutis.dev.trail.locadora.repository.ModeloCarroRepository;
import br.edu.solutis.dev.trail.locadora.repository.FabricanteRepository;
import br.edu.solutis.dev.trail.locadora.mappers.ModeloCarroMapper;
import br.edu.solutis.dev.trail.locadora.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModeloCarroService {

    private final ModeloCarroRepository modeloCarroRepository;
    private final FabricanteRepository fabricanteRepository;
    private final ModeloCarroMapper modeloCarroMapper;

    @Transactional
    public ModeloCarroDTO create(ModeloCarroDTO modeloCarroDTO) {
        if (modeloCarroRepository.existsByDescricao(modeloCarroDTO.getDescricao())) {
            throw new BusinessException("Modelo de carro com a descrição já existe.");
        }

        Fabricante fabricante = fabricanteRepository.findById(modeloCarroDTO.getFabricante().getId())
                .orElseThrow(() -> new BusinessException("Fabricante não encontrado com ID: " + modeloCarroDTO.getFabricante().getId()));

        ModeloCarro modeloCarro = modeloCarroMapper.toEntity(modeloCarroDTO);
        modeloCarro.setFabricante(fabricante);

        ModeloCarro savedModeloCarro = modeloCarroRepository.save(modeloCarro);
        return modeloCarroMapper.toDto(savedModeloCarro);
    }

    @Transactional(readOnly = true)
    public ModeloCarroDTO findById(Long id) {
        ModeloCarro modeloCarro = modeloCarroRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Modelo de carro não encontrado com ID: " + id));
        return modeloCarroMapper.toDto(modeloCarro);
    }

    @Transactional(readOnly = true)
    public List<ModeloCarroDTO> findAll() {
        return modeloCarroMapper.toDtoList(modeloCarroRepository.findAll());
    }

    @Transactional
    public ModeloCarroDTO update(Long id, ModeloCarroDTO modeloCarroDTO) {
        ModeloCarro modeloCarro = modeloCarroRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Modelo de carro não encontrado com ID: " + id));

        Fabricante fabricante = fabricanteRepository.findById(modeloCarroDTO.getFabricante().getId())
                .orElseThrow(() -> new BusinessException("Fabricante não encontrado com ID: " + modeloCarroDTO.getFabricante().getId()));

        modeloCarro.setDescricao(modeloCarroDTO.getDescricao());
        modeloCarro.setCategoria(modeloCarroDTO.getCategoria());
        modeloCarro.setFabricante(fabricante);

        ModeloCarro updatedModeloCarro = modeloCarroRepository.save(modeloCarro);
        return modeloCarroMapper.toDto(updatedModeloCarro);
    }

    @Transactional
    public void delete(Long id) {
        ModeloCarro modeloCarro = modeloCarroRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Modelo de carro não encontrado com ID: " + id));
        modeloCarroRepository.delete(modeloCarro);
    }
}
