package br.com.dcx.ufpb;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente2 {

    private static int contador = 0;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o seu nome: ");
        String nome = scanner.nextLine();

        Socket socket = new Socket("localhost", 4000);
        Thread thread = new Thread(new ServidorHandler(socket));
        thread.start();

        PrintStream saida = new PrintStream(socket.getOutputStream());
        String mensagem;

        int chaveInicial = geraChaveUnica();
        String clienteConectou = cifraTransposicao(nome + " conectou-se", chaveInicial);
        saida.println(chaveInicial + "___" + clienteConectou);

        while (true) {
            mensagem = scanner.nextLine();

            int chave = geraChaveUnica();

            String mensagemCifrada = cifraTransposicao(nome + ": " + mensagem, chave);
            saida.println(chave + "___" + mensagemCifrada);
        }
    }

    private static class ServidorHandler implements Runnable {
        private Socket socket;

        public ServidorHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String mensagem;
                while ((mensagem = reader.readLine()) != null) {
                    System.out.println(mensagem);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static int geraChaveUnica() {
        return contador++;
    }

    private static String cifraTransposicao(String mensagem, int chave) {
        StringBuilder cifrada = new StringBuilder();

        for (char c : mensagem.toCharArray()) {
            cifrada.append((char) ((c + chave) % 65536));
        }

        return cifrada.toString();
    }
}
