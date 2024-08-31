package br.edu.solutis.dev.trail.locadora.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
@Entity
@Table(name= "tb_carrinho")
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataCriacao;

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL)
    private List<Aluguel> alugueis;

    /*@OneToMany
    private Carro carroSelecionado;

    public void limparCarrinho() {
        carroSelecionado = null;
    }*/

}
