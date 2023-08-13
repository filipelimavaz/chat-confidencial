# Chat com Criptografia usando Sockets em Java </br> Projeto da disciplina de Auditoria e Segurança de Sistemas

Este é um projeto de chat simples desenvolvido em Java para a cadeira de Auditoria e Segurança de Sistemas. Utilizei sockets para permitir a comunicação entre dois clientes e um servidor. O diferencial deste chat é que as mensagens são criptografadas antes de serem enviadas ao servidor e depois descriptografadas antes de serem exibidas nos clientes.

## Funcionamento

O projeto é composto por três partes: dois clientes e um servidor.

### Servidor

O servidor é responsável por intermediar a comunicação entre os clientes. Ele recebe as mensagens criptografadas enviadas pelos clientes, descriptografa essas mensagens e as envia para os demais clientes conectados. Além disso, o servidor também exibe as mensagens criptografadas recebidas dos clientes no seu console.

### Clientes

Cada cliente se conecta ao servidor e pode enviar mensagens para outros clientes. As mensagens digitadas pelos clientes são criptografadas antes de serem enviadas ao servidor, para garantir a segurança da comunicação. Os clientes exibem as mensagens recebidas e enviadas no console, permitindo um chat em tempo real.

## Criptografia

A criptografia utilizada neste projeto é uma cifra de transposição. Cada caractere da mensagem é deslocado por uma determinada quantidade de posições no conjunto de caracteres Unicode. A chave de criptografia é gerada aleatoriamente cada vez que uma mensagem é enviada.

## Como Executar

1. Compile e execute o servidor.
2. Compile e execute os clientes, pelo menos dois deles, para simular a troca de mensagens.

Certifique-se de que todas as partes estejam em execução antes de iniciar o chat.

## Uso

- Execute o servidor primeiro.
- Execute pelo menos dois clientes.
- Digite as mensagens nos consoles dos clientes para enviá-las para o servidor e para os outros clientes.
- As mensagens criptografadas são exibidas no console do servidor.
- As mensagens descriptografadas e o chat ocorrem nos consoles dos clientes.
