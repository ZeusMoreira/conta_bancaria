**Instruções para iniciar o desafio**

1 – Instale o banco de dados MySQL; <br/>
2 – Baixe o servidor Tomcat 8.5 (https://tomcat.apache.org/download-80.cgi); <br/>
4 – Baixe o JDK 8; <br/>
5 – Importe o projeto na IDE e faça a configuração necessária para rodá-lo com java 8; <br/>
6 – Configure o servidor Tomcat para o projeto;<br/>
7 – No MySQL crie um banco e informe os dados de acesso (nome do banco, usuário e senha) no arquivo “persistence.xml”.<br/>
8 – Para dar a carga inicial no banco, procure pela classe “CriarTabela.java”, execute o método “main” da mesma e após faça o mesmo com a classe “CriarLançamentos.java”.<br/>
9 – Faça um teste, inicie o tomcat e após acesse o endereço “http://localhost:8080/Financeiro/Login.xhtml”, informe o login e senha admin/123. Se tudo ocorreu certo, irá exibir uma listagem de lançamentos.<br/>

Qualquer dúvida sobre a configuração do ambiente, pode me consultar ou usar o material de apoio, cujo qual o projeto foi baseado: https://drive.google.com/file/d/1yOEfcUECQ5nEG85xRT9broHprfVZHBV6/view?usp=sharing <br/>

**DESAFIO** <br/>
**Atividade 1)** Crie o cadastro de uma Conta Bancária, com os seguintes campos:<br/>
Menu Cadastro/Conta Bancária<br/>

**id** → tipo inteiro, chave primaria e autoincremento;<br/>
**numero** → tipo string, tamanho máximo 12 caracteres, não nulo;<br/>
**dataCadastro** → tipo data, não nulo (Obs.: esse campo deve ser preenchido automaticamente no ato do cadastro);<br/>
**banco** → tipo string, tamanho máximo 100 caracteres, não nulo;<br/>
**saldo** → tipo bigdecimal, não nulo;<br/>
**tipo** → tipo enum(FISICA ou JURIDICA), não nulo;<br/>
![img](https://i.imgur.com/vhaQLUO.png)

**Atividade 2)** Faça a tela de consulta de conta bancária, conforme abaixo:<br/>
Menu Consultas/Contas Bancárias<br/>
![img](https://i.imgur.com/YxAC1Ym.png) <br/>
Regras:<br/>
1 - Ao carregar a tela, deve listar todas as contas disponíveis;<br/>
2 – Deve haver uma opção para buscar uma conta por número;<br/>
3 – Deve haver opção para editar (redirecionar para tela de cadastro com os dados da conta selecionada);<br/>
4 – Deve haver opção para excluir (uma conta só pode ser excluída se não haver nenhum lançamento vinculado a ela).<br/>

**Atividade 3)** Altere a entidade “Lancamento” e inclua o atributo “ContaBancaria”. Esse será um relacionamento: “muitos lançamentos podem  fazer parte de uma conta bancária“. <br/>

![img](https://i.imgur.com/skajm32.png) <br/>

1 – O combobox “Conta” deverá listar todas as contas cadastradas; <br/>
2 – O combobox deve exibir o atributo banco concatenado com o atributo numero conta; <br/>
3 – Se o tipo do lançamento for receita, o valor deverá ser adicionado ao saldo da conta selecionada; <br/>
4 – Se o tipo do lançamento for despesa, o valor deverá ser debitado do saldo da conta selecionada; <br/>
