package br.com.hubfintech.desafio.tcp;

import br.com.hubfintech.desafio.domain.CardTransactionDomain;
import java.net.*;
import java.io.*;

/*
 * Classe Servidor Socket TCP Multithread
 * @autor andre
 */
public class TCPServer{
    
    static public void criaServidorTCP(int porta, CardTransactionDomain cardTransactionDomain) throws IOException{
        
        ServerSocket serverSocket;
        
        try{            
            serverSocket = new ServerSocket();

            //Habilita o Socket Hijacking
            serverSocket.setReuseAddress(true);

            InetSocketAddress adress = new InetSocketAddress("127.0.0.1", porta);

            serverSocket.bind(adress);

            System.out.println("============================================");
            System.out.println("Servidor TCP criado com sucesso: IP: " + adress.getAddress().getHostAddress() + " na Porta: " + adress.getPort());
            System.out.println("============================================");
        
            while(true)
                new TCPServerThread(serverSocket.accept(), cardTransactionDomain).start();
            
        }catch(IOException e){
            
            System.err.println("Erro ao criar Servidor TCP na Porta: " + porta + e);
        }
    }
}
