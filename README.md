- Para acessar o sistema, necessita ter instalado na máquina o docker.

- Primeiramente deve ser executado o arquivo stack.yml para criar a instância Mysql no docker.

- No mysql instalado no docker execute o seguinte comandos sql

create table authapp;

- Após, é necessário abrir o projeto em alguma IDE e inicializar os serviços. O serviço discovery deve vir primeiro, seguido do gateway e após o restante.

- Por padrão o sistema inicializa com um usuário "root" e senha "authroot"


- A Api está no padrão REST, portanto pode ser utilizada com qualquer aplicativo que utilize esse padrão. Para efetuar um teste, realize primeiramente um login enviando uma requisição POST para http://localhost:8080/gateway/auth/login. No corpo da mensagem deve ser enviado um bloco json contendo os campos username e password. Dessa forma, um token do tipo bearer será retornado e para toda requisição realizada apartir de então deverá ser enviado no cabeçalho da requisição o mesmo token.

- Para efetuar testes de outro microserviço, um funcionário pode ser manipulado com um crud completo pelos seguintes endereços.
  - Adicionar (POST): http://localhost:8080/gateway/funcionario/add 
  - Alterar (PUT): http://localhost:8080/gateway/funcionario/edit/{codigoDoFuncionario}
  - Buscar (GET): http://localhost:8080/gateway/funcionario/buscar/{codigoDoFuncionario}
  - Excluir (DELETE): http://localhost:8080/gateway/excluir/{codigoDoFuncionario}
  - Lista geral (GET): http://localhost:8080/gateway/funcionario/
