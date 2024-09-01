package br.edu.solutis.dev.trail.locadora.service;

import br.edu.solutis.dev.trail.locadora.exceptions.BusinessException;
import br.edu.solutis.dev.trail.locadora.mappers.CarroMapper;
import br.edu.solutis.dev.trail.locadora.model.dto.CarroDTO;
import br.edu.solutis.dev.trail.locadora.model.entity.Carro;
import br.edu.solutis.dev.trail.locadora.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private CarroMapper carroMapper;

    public CarroDTO create(CarroDTO carroDTO) {
        Carro carro = carroMapper.toEntity(carroDTO);
        carro = carroRepository.save(carro);
        return carroMapper.toDto(carro);
    }

    public CarroDTO findById(Long id) {
        Optional<Carro> carroOpt = carroRepository.findById(id);
        if (carroOpt.isEmpty()) {
            throw new BusinessException("Carro não encontrado com ID: " + id);
        }
        return carroMapper.toDto(carroOpt.get());
    }

    public List<CarroDTO> findAll() {
        List<Carro> carros = carroRepository.findAll();
        return carroMapper.toDtoList(carros);
    }

    public CarroDTO update(Long id, CarroDTO carroDTO) {
        Optional<Carro> carroOpt = carroRepository.findById(id);
        if (carroOpt.isEmpty()) {
            throw new BusinessException("Carro não encontrado com ID: " + id);
        }

        Carro carro = carroOpt.get();
        carroMapper.updateEntityFromDto(carroDTO, carro);
        carro = carroRepository.save(carro);
        return carroMapper.toDto(carro);
    }

    public void delete(Long id) {
        if (!carroRepository.existsById(id)) {
            throw new BusinessException("Carro não encontrado com ID: " + id);
        }
        carroRepository.deleteById(id);
    }
}
