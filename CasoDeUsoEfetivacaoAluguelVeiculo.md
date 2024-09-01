### Caso de Uso: Efetivação do Aluguel de Veículo

#### 1. **Nome do Caso de Uso**
Efetivação do Aluguel de Veículo

#### 2. **Ator Principal**
Cliente cadastrado

#### 3. **Partes Interessadas e Interesses**
- **Cliente**: Deseja confirmar a reserva do veículo selecionado e efetivar o aluguel.
- **Locadora de Automóveis**: Deseja garantir que o processo de aluguel seja concluído com sucesso, maximizando a utilização da frota e a satisfação do cliente.

#### 4. **Pré-condições**
- O cliente deve estar cadastrado e logado no sistema.
- O cliente deve ter selecionado um veículo e adicionado ao carrinho de aluguel.
- O veículo deve estar disponível para as datas selecionadas.

#### 5. **Pós-condições**
- O cliente terá confirmado a reserva e efetivado o aluguel do veículo.
- O sistema registrará a reserva, atualizará a disponibilidade do veículo e bloqueará as datas de aluguel no calendário.
- O cliente receberá uma confirmação com os detalhes do aluguel.

#### 6. **Fluxo Principal**

1. **Revisão do Carrinho de Aluguel**
    - O cliente acessa o carrinho de aluguel.
    - O sistema exibe um resumo dos veículos selecionados, datas de aluguel e custo total estimado.

2. **Confirmação da Reserva**
    - O cliente confirma a reserva.
    - O sistema redireciona o cliente para uma página de resumo da reserva.

3. **Visualização do Resumo da Reserva**
    - O cliente visualiza a página de resumo da reserva, contendo informações sobre o veículo selecionado, datas de aluguel, custo total estimado e termos de aluguel.
    - O cliente revisa e concorda com os termos e condições do aluguel.

4. **Escolha do Método de Pagamento**
    - O cliente escolhe um método de pagamento.
    - O sistema permite a inserção das informações do cartão de crédito ou outro método de pagamento aceito (simulado).

5. **Confirmação do Pagamento**
    - O cliente confirma o pagamento.
    - O sistema processa o pagamento e finaliza o processo de aluguel.

6. **Recebimento da Confirmação**
    - O cliente recebe na tela uma confirmação contendo todos os detalhes do aluguel, informações de contato e a fatura.
    - O sistema marca o veículo como "reservado" e bloqueia as datas de aluguel no calendário.

7. **Acesso aos Aluguéis Confirmados**
    - O cliente pode acessar seus aluguéis confirmados e detalhes futuros através da sua conta.

#### 7. **Fluxos Alternativos**

- **Erro no Pagamento**
    - Se ocorrer um erro no processamento do pagamento, o sistema exibe uma mensagem de erro.
    - O cliente pode tentar novamente ou escolher outro método de pagamento.

- **Veículo Indisponível**
    - Se o veículo se tornar indisponível antes da confirmação do pagamento, o sistema notifica o cliente e sugere veículos alternativos.
    - O cliente pode selecionar um veículo alternativo ou cancelar a reserva.

#### 8. **Requisitos Não Funcionais**
- O sistema deve ser seguro e proteger as informações de pagamento do cliente.
- O tempo de resposta para o processamento do pagamento deve ser rápido para garantir uma boa experiência do usuário.

#### 9. **Notas e Questões Abertas**
- A integração com sistemas de pagamento reais deve ser considerada para um ambiente de produção.
- A política de cancelamento e reembolso deve ser claramente comunicada ao cliente durante o processo de efetivação do aluguel.

Esse caso de uso detalha o processo de efetivação do aluguel de um veículo, desde a revisão do carrinho até a confirmação do pagamento e a atualização da disponibilidade do veículo.