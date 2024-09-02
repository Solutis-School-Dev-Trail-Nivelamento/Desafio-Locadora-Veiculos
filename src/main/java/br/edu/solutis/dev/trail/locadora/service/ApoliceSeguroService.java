package br.edu.solutis.dev.trail.locadora.service;


import br.edu.solutis.dev.trail.locadora.mappers.ApoliceSeguroMapper;
import br.edu.solutis.dev.trail.locadora.model.dto.ApoliceSeguroDTO;
import br.edu.solutis.dev.trail.locadora.model.entity.ApoliceSeguro;
import br.edu.solutis.dev.trail.locadora.repository.ApoliceSeguroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApoliceSeguroService {

    @Autowired
    private ApoliceSeguroRepository repository;

    public ApoliceSeguroDTO criarApolice(ApoliceSeguroDTO dto) {
        ApoliceSeguro apolice = new ApoliceSeguroMapper().toEntity(dto);
        ApoliceSeguro salvo = repository.save(apolice);
        return new ApoliceSeguroMapper().toDTO(salvo);
    }

    public ApoliceSeguroDTO obterApolicePorId(Long id) {
        ApoliceSeguro apolice = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Apólice não encontrada"));
        return new ApoliceSeguroMapper().toDTO(apolice);
    }

    public List<ApoliceSeguroDTO> listarTodasApólices() {
        List<ApoliceSeguro> apolices = repository.findAll();
        return apolices.stream()
                .map(apolice -> new ApoliceSeguroMapper().toDTO(apolice))
                .toList();
    }

    public ApoliceSeguroDTO atualizarApolice(Long id, ApoliceSeguroDTO dto) {
        ApoliceSeguro apolice = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Apólice não encontrada"));

        // Atualizando os valores
        apolice.setValorFranquia(dto.getValorFranquia());
        apolice.setProtecaTerceiro(dto.isProtecaTerceiro());
        apolice.setProtecaCausasNaturais(dto.isProtecaCausasNaturais());
        apolice.setProtecaRoubo(dto.isProtecaRoubo());

        ApoliceSeguro atualizado = repository.save(apolice);
        return new ApoliceSeguroMapper().toDTO(atualizado);
    }

    public void deletarApolice(Long id) {
        repository.deleteById(id);
    }
}
