# Projeto Final LPOO - Biblioteca de Jogos Pessoais

## Descrição Geral
Este projeto consiste em um sistema de desktop para gerenciamento de uma biblioteca de jogos pessoais.
O objetivo é permitir que o usuário cadastre, organize e acompanhe todos os jogos que possui ou já jogou, abrangendo diversas plataformas como PC, PlayStation, Xbox, Nintendo e Mobile.
O Sistema oferece funcionalidades de CRUD (Create, Read, Update e Delete) para gerenciar as informações de cada jogo, como nome, plataforma gênero, status de progresso, nota pessoal e comentários.

***

## Explicação da Modelagem

**Diagrama de Classes do Projeto**

![Diagrama de Classes](/assets/Diagrama_Classes_Biblioteca.jpg)

* **Entidade `Jogo`**: É a classe central do modelo. Ela representa um jogo e armazena todos os seus atributos principais, como `nome`, `nota` e `comentarios`. Esta classe é mapeada como uma entidade JPA para a tabela `jogos` no banco de dados.

* **Enumerações (`Plataforma`, `Genero`, `Status`)**: Para garantir a consistência e a integridade dos dados, foram utilizadas enumerações para campos com valores predefinidos.
    * `Plataforma`: Define as plataformas de jogos permitidas (PC, PLAYSTATION, XBOX, NINTENDO e MOBILE).
    * `Genero`: Define os gêneros dos jogos (ACAO, AVENTURA, RPG, ESTRATEGIA, ESPORTES e MUNDO_ABERTO).
    * `Status`: Define o estado atual do jogo na coleção do usuário (JOGANDO, FINALIZADO, ABANDONADO e PROXIMO).
    * O relacionamento entre a entidade `Jogo` e esses dados é mapeado pela anotação `@Enumerated(EnumType.STRING)`, que salva o nome do valor do enum no banco de dados.

***

## Funcionalidades Principais

* **Tela Principal:** Apresenta estatísticas rápidas sobre a coleção, como total de jogos, jogos finalizados, jogos em andamento e a média de notas da biblioteca.
* **Cadastro de Jogos:** Permite adicionar novos jogos à coleção através de um formulário completo com validação de campos obrigatórios.
* **Gerenciamento da Biblioteca:** Tela principal para visualização de todos os jogos em uma tabela, com funcionalidades para Editar e Excluir jogos selecionados.
* **Filtro de Jogos:** Na tela de gerenciamento, é possível filtrar a lista de jogos por status e por nome para facilitar a busca.

***

## Como Usar o Sistema
Para executar este projeto em sua máquina local, siga o passo a passo.

### Pré-requisitos
* Java (JDK 11 ou superior)
* Apache Maven
* PostgreSQL

### Configuração
1. **Clone o Repositório**
  ```bash
    git clone https://github.com/anestrom/ProjetoFinal_LPOO_MarjoryToazza
  ```

2. **Crie o Banco de Dados**
   No seu PostgreSQL, crie um novo banco de dados com o nome `ProjetoFinal_MarjoryToazza`

3.  **Configure a Conexão**
    * Abra o arquivo `src/main/resources/META-INF/persistence.xml`.
    * Localize a propriedade `javax.persistence.jdbc.password` e altere o valor para a sua senha do PostgreSQL.
    ```xml
    <property name="javax.persistence.jdbc.password" value="sua_senha_aqui"/>
    ```
    
### Execução
1.  Abra o projeto em sua IDE (NetBeans).
2.  Execute um "Limpar e Construir Projeto" para baixar as dependências do Maven.
3.  As tabelas do banco serão criadas automaticamente na primeira execução, graças à configuração do JPA.
4.  Execute a classe principal que contém a interface gráfica para iniciar o sistema.

***

## Tecnologias Utilizadas

* **Linguagem:** Java
* **Gerenciador de Projetos:** Apache Maven
* **Persistência:** JPA (Hibernate)
* **Banco de Dados:** PostgreSQL
* **Interface Gráfica:** Java Swing 
* **Testes:** JUnit 5

***

## Autor
### Marjory Ane Toazza