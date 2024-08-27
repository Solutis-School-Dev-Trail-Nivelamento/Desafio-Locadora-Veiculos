## Caso de Uso: Cadastro de Cliente
**Nome:**
Cadastro de Cliente

**Ator Principal:**
Cliente em Potencial

**Fluxo Principal:**
1. O cliente acessa a página inicial da locadora.
2. O cliente clica no link ou botão para se cadastrar.
3. O sistema exibe um formulário de cadastro solicitando nome completo, data de nascimento, CPF, número da CNH, e e-mail.
4. O cliente preenche o formulário e submete.
5. O sistema valida os dados inseridos:
   - Verifica se o e-mail já está em uso.
   - Verifica se o CPF já está cadastrado.
6. Se os dados forem válidos:
   - O sistema salva o cliente no banco de dados.
   - O sistema exibe uma mensagem de sucesso na tela.
   - O cliente é redirecionado para a página inicial onde pode acessar os serviços da locadora.
7. Se os dados não forem válidos:
   - O sistema exibe uma mensagem de erro indicando o problema.
   - O cliente permanece na página de cadastro para corrigir as informações.
8. Fluxo Alternativo:
   - Dados Inválidos:
        - Se o cliente fornecer um e-mail ou CPF já cadastrado, o sistema informa o erro e solicita a correção.