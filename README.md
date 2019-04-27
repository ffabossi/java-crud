# JavaDBExemplo

Projeto simples desenvolvido no NetBeans utilizando a linguagem Java fazendo integração com o banco de dados MySQL.

As operações exemplificadas nesse projeto são as de: inserção, leitura, alteração e remoção (CRUD) de contatos no banco de dados MySQL.

* Opções de Execução (pacote iagocolodetti.javadbexemplo.view):
   * Main -> Execução via console.
   * MainGUI -> Execução com interface gráfica.

* Downloads: https://github.com/iagocolodetti/JavaDBExemplo/releases
   * [Arquivo de Script MySQL](https://github.com/iagocolodetti/JavaDBExemplo/releases/download/v1.3/contatodb.sql "contatodb.sql")
   * [Driver Necessário](https://github.com/iagocolodetti/JavaDBExemplo/releases/download/v1.3/mysql-connector-java-5.1.23-bin.jar "mysql-connector-java-5.1.23-bin.jar")
   * [Código-Fonte](https://github.com/iagocolodetti/JavaDBExemplo/archive/v1.3.zip "v1.3.zip")
<br/>
<h3>Conectando o Banco de Dados MySQL no NetBeans</h3>

1. Janela -> Serviços (atalho Ctrl + 5);

2. Expandir "Banco de Dados";

3. Em "Drivers" verifique se existe o driver "MySQL (Driver Connector/J)";  
	3.1. Caso possua mas os próximos passos deem erros, adicione o driver disponibilizado aqui e faça a conexão usando ele.  
	3.2. Caso não possua, adicione o driver disponibilizado aqui.  
	**Adicionando o Driver**: Para adicionar o driver "mysql-connector-java-5.1.23-bin.jar", clique em Drivers com o botão direito do mouse -> Novo Driver...
	-> clique em Adicionar e selecione o arquivo "mysql-connector-java-5.1.23-bin.jar", Classe do Driver deverá ser: "com.mysql.jdbc.Driver" (sem aspas), o nome poderá ser personalizado.

4. Voltando em "Banco de Dados" clique com o botão direito do mouse -> Nova Conexão... -> selecione o Driver MySQL que você acabou de adicionar ou que já existia e clique em Próximo.

5. Em "Banco de dados" digite o nome dado ao banco criado no MySQL (nesse caso: contatodb), em "Nome de usuário" e "Senha" digite o seu nome de usuário e senha (caso possua) de acesso ao MySQL e clique em Finalizar;

6. Modifique as constantes da classe "ConnectionFactory.java" de acordo com os dados colocados no passo 5.
