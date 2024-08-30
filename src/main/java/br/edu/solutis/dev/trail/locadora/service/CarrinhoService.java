package br.edu.solutis.dev.trail.locadora.service;


import br.edu.solutis.dev.trail.locadora.model.entity.Aluguel;
import br.edu.solutis.dev.trail.locadora.model.entity.Carro;
import br.edu.solutis.dev.trail.locadora.repository.CarrinhoRepository;
import br.edu.solutis.dev.trail.locadora.repository.CarroRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrinhoService {

    private static final Logger logger = LoggerFactory.getLogger(CarroService.class);
    @Autowired
    private CarrinhoRepository carrinhoRepository;
    @Autowired
    private CarroRepository carroRepository;


@Transactional
public List<Carro> obterCarros (Aluguel aluguel){
    return carroRepository.findAll();
}
}