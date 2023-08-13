package br.com.dcx.ufpb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {

    private static final List<PrintStream> CLIENT_OUTPUT_STREAMS = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(4000);
        System.out.println("Servidor aguardando conexões...");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Cliente conectado: " + socket.getInetAddress());

            PrintStream clientOutput = new PrintStream(socket.getOutputStream());
            CLIENT_OUTPUT_STREAMS.add(clientOutput);

            Thread thread = new Thread(new ClienteHandler(socket, clientOutput));
            thread.start();
        }
    }

    private static class ClienteHandler implements Runnable {
        private Socket socket;
        private PrintStream clientOutput;

        public ClienteHandler(Socket socket, PrintStream clientOutput) {
            this.socket = socket;
            this.clientOutput = clientOutput;
        }

        @Override
        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String mensagem;
                while ((mensagem = reader.readLine()) != null) {
                    String[] partes = mensagem.split("___");
                    int chave = Integer.parseInt(partes[0]);
                    mensagem = partes[1];

                    System.out.println("Mensagem criptografada: " + mensagem);

                    String mensagemDecifrada = decifraTransposicao(mensagem, chave);

                    for (PrintStream client : CLIENT_OUTPUT_STREAMS) {
                        if (client != clientOutput) {
                            client.println(mensagemDecifrada);
                        }
                    }
                }

                reader.close();
                socket.close();
                CLIENT_OUTPUT_STREAMS.remove(clientOutput);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String decifraTransposicao(String mensagemCifrada, int chave) {
        StringBuilder decifrada = new StringBuilder();

        for (char c : mensagemCifrada.toCharArray()) {
            decifrada.append((char) ((c - chave + 65536) % 65536));
        }

        return decifrada.toString();
    }
}
