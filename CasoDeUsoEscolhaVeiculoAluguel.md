### Caso de Uso: Escolha de Veículo para Aluguel

#### 1. **Nome do Caso de Uso**
Escolha de Veículo para Aluguel

#### 2. **Ator Principal**
Cliente cadastrado

#### 3. **Partes Interessadas e Interesses**
- **Cliente**: Deseja alugar um veículo que atenda às suas necessidades de locomoção.
- **Locadora de Automóveis**: Deseja facilitar o processo de aluguel de veículos para os clientes, maximizando a satisfação e a utilização da frota.

#### 4. **Pré-condições**
- O cliente deve estar cadastrado e logado no sistema.
- Deve haver veículos disponíveis para aluguel no sistema.

#### 5. **Pós-condições**
- O cliente terá reservado um veículo para o período especificado.
- O sistema registrará a reserva e atualizará a disponibilidade do veículo.

#### 6. **Fluxo Principal**

1. **Página Inicial**
    - O cliente acessa a página inicial da locadora.
    - O sistema exibe a seção "Seleção de Veículos".

2. **Visualização de Veículos Disponíveis**
    - O cliente visualiza uma lista ou grade de veículos disponíveis para aluguel.
    - Cada veículo exibe informações como fabricante, modelo, categoria, acessórios e preço por dia, além de uma imagem representativa.

3. **Aplicação de Filtros**
    - O cliente aplica filtros para refinar a busca, como categoria de veículo (carro, SUV, caminhonete) ou acessórios (ar-condicionado, sistema de navegação, etc.).
    - O sistema atualiza a lista de veículos conforme os filtros aplicados.

4. **Seleção de Veículo**
    - O cliente clica em um veículo de interesse.
    - O sistema redireciona o cliente para a página de detalhes do veículo.

5. **Visualização de Detalhes do Veículo**
    - O cliente visualiza informações detalhadas sobre o veículo, incluindo especificações técnicas e descrição.

6. **Seleção do Período de Aluguel**
    - O cliente seleciona o período de aluguel, especificando as datas de início e término.
    - O sistema calcula o custo total estimado com base no período selecionado.

7. **Adição ao Carrinho de Aluguel**
    - O cliente adiciona o veículo ao carrinho de aluguel.
    - O sistema exibe um resumo dos veículos selecionados, suas datas de aluguel e o custo total estimado.

8. **Revisão e Confirmação da Reserva**
    - O cliente revisa o carrinho, faz ajustes se necessário e confirma a reserva.
    - O sistema registra a reserva e atualiza a disponibilidade do veículo.
    - O cliente recebe uma confirmação na tela com os detalhes da reserva.

#### 7. **Fluxos Alternativos**

- **Filtro Não Retorna Resultados**
    - Se nenhum veículo corresponder aos filtros aplicados, o sistema exibe uma mensagem informando que não há veículos disponíveis com os critérios selecionados.
    - O cliente pode ajustar os filtros e tentar novamente.

- **Erro ao Adicionar ao Carrinho**
    - Se ocorrer um erro ao adicionar o veículo ao carrinho, o sistema exibe uma mensagem de erro.
    - O cliente pode tentar adicionar o veículo novamente ou escolher outro veículo.

#### 8. **Requisitos Não Funcionais**
- O sistema deve ser responsivo e funcionar em diferentes dispositivos (desktop, tablet, smartphone).
- O tempo de resposta para a aplicação de filtros e carregamento de detalhes do veículo deve ser rápido para garantir uma boa experiência do usuário.

#### 9. **Notas e Questões Abertas**
- A integração com sistemas de pagamento e a confirmação de pagamento não estão cobertas por este caso de uso e devem ser tratadas separadamente.
- A política de cancelamento e reembolso deve ser claramente comunicada ao cliente durante o processo de reserva.

Esse caso de uso detalha o processo de escolha de um veículo para aluguel, desde a visualização inicial até a confirmação da reserva, garantindo que todas as funcionalidades necessárias sejam implementadas para atender às necessidades do cliente.