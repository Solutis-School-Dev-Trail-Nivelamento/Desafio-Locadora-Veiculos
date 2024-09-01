create database locadora;
use locadora;

select * from tb_pessoa;
select * from tb_carro_modelo;
select * from tb_carro_fabricante;
select * from tb_carro_acessorio;
select * from tb_carro;
select * from tb_carrinho;
select * from tb_aluguel;

DESCRIBE tb_carro_acessorio;

-- Verificar registros inválidos na tabela tb_carrinho
SELECT * FROM tb_carrinho WHERE cliente_id NOT IN (SELECT id FROM tb_pessoa);

-- Remover registros inválidos na tabela tb_carrinho
DELETE FROM tb_carrinho WHERE cliente_id NOT IN (SELECT id FROM tb_pessoa);

SELECT cliente_id FROM tb_carrinho WHERE cliente_id NOT IN (SELECT id FROM tb_pessoa);
