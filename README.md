# Comunicação entre processos: Socket

Este projeto cria um aplicativo cliente-servidor simples onde o cliente se conecta ao servidor e ambos podem trocar mensagens de texto. O servidor escuta por conexões em uma porta específica e permite que vários clientes se conectem (devido à fila de espera de 100). No entanto, o código atual lida apenas com uma conexão por vez.

## Importações das classes:

* java.io: Fornece classes para operações de entrada e saída de dados.
* java.net: Oferece classes para funcionalidades de rede.
* java.util (utilizado apenas no Cliente): Fornece classes utilitárias, como Scanner para entrada do usuário.

## Classe Servidor:

* ServerSocket server: Esta variável armazena uma referência ao objeto ServerSocket. O ServerSocket escuta por conexões de entrada em uma porta específica (5050 neste caso).

* Socket connection: Esta variável guarda o objeto Socket que representa a conexão estabelecida com um cliente.

* ObjectOutputStream output: Esse fluxo é usado para enviar objetos (mensagens) ao cliente.

* ObjectInputStream input: Esse fluxo é usado para receber objetos (mensagens) do cliente.

* Método runServer: É o método principal do servidor. Ele executa as seguintes etapas:

* Criar ServerSocket: Cria um objeto ServerSocket na porta 5050, permitindo uma fila de até 100 conexões pendentes.
* Aguardar conexão: Entra em um loop infinito, esperando que um cliente se conecte.
* Aceitar conexão: Quando um cliente se conecta, o servidor chama server.accept() para estabelecer um objeto Socket representando a conexão.
* Obter fluxos de E/S: Cria objetos ObjectOutputStream e ObjectInputStream para enviar e receber dados (mensagens) pela conexão estabelecida.

### Processar conexão:
* Libera o prompt para envio de mensagem resposta ("SERVER>>> ") ao cliente.
* Entra em um loop que continua até o cliente enviar uma mensagem "CLIENT>>> SAIR".
* Lê uma mensagem do cliente usando input.readObject().
* Captura uma potencial ClassNotFoundException caso o tipo de objeto recebido seja inesperado.
* Fechar conexão: Uma vez que o loop termina, fecha os fluxos e o objeto Socket, indicando uma conexão encerrada.

## Classe Cliente:

* Socket client: Representa o objeto Socket para a conexão do lado do cliente com o servidor.

* ObjectOutputStream output: Semelhante ao servidor, usado para enviar objetos (mensagens) ao servidor.

* ObjectInputStream input: Usado para receber objetos (mensagens) do servidor.

* String message: Armazena a string da mensagem a ser enviada ou recebida.

* Método runClient: É o método principal do cliente. Ele executa as seguintes etapas:

* Criar Socket: Tenta se conectar a um servidor em execução no "localhost" (máquina local) na porta 5050.
* Obter fluxos de E/S: Semelhante ao servidor, cria fluxos para enviar e receber mensagens.

### Processar conexão:
* Usa um objeto Scanner para ler a entrada do usuário a partir do terminal.
* Entra em um loop que continua até o usuário digitar "SERVER>>> SAIR".
* Solicita uma mensagem ao usuário com "CLIENT>>> ".
* Lê a entrada do usuário e adiciona "CLIENT>>> " à mensagem.
* Envia a mensagem ao servidor usando output.writeObject().
* Lê uma mensagem do servidor usando input.readObject().
* Captura uma potencial ClassNotFoundException caso o tipo de objeto recebido seja inesperado.
* Fechar conexão: Semelhante ao servidor, fecha os fluxos e o objeto Socket quando o loop termina.

# Atividade Prática:
* Crie seu Repositório no github do seu perfil para incluir os programas;
* Compile e Execute os programas;
* Para executar deverá iniciar o Server em uma bash e o cliente em outro bash, separados;
* Realize o upload dos prints das execuções no seu repositório;
* Envie o link do seu repositório do github com resposta da atividade;

## Envie as seguintes mensagems:
* CLIENT>>> olá
* SERVER>>> olá, tudo bem?
* CLIENT>>> tudo certo! vou sair da conversa ok?
* SERVER>>> ok
* CLIENT>>> sair
* SERVER>>> ok
* CLIENT>>> sair
* SERVER>>> sair