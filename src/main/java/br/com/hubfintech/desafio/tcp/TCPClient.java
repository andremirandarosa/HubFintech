package br.com.hubfintech.desafio.tcp;

import br.com.hubfintech.desafio.util.Util;
import java.io.*;
import java.net.*;

/*
 * Classe Cliente Socket TCP.
 * @autor Andre Luiz Miranda da Rosa (andremirandarosa@gmail.com)
 */
public class TCPClient{
    
    private final String endereco_servidor;
    
    private final int porta;
    
    //==========================================================================

    /**
     * Conecta o Cliente TCP ao Servidor TCP da AC-Med
     * @param endereco_servidor Endereco do Servidor TCP
     * @param porta Porta do Servidor TCP
     */
    public TCPClient(String endereco_servidor, int porta) {
        
        this.endereco_servidor = endereco_servidor;
        this.porta = porta;
    }
    
    /**
     * Envia a Rquisicao ao Servidor
     * @param requisicao Requisicao a ser enviada
     * @return Resposta da requisicao (Null: Erro)
     */
    public  String enviaRequisicao(String requisicao){
        
        Socket socket;
        
        PrintWriter out;
        
        BufferedReader in;

        try{           
            socket = new Socket(this.endereco_servidor, this.porta);

            out = new PrintWriter(socket.getOutputStream(), true);
            
            //Envia a Requisicao
            out.println(requisicao);
            //System.out.println("REQUEST:" + requisicao);
            
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
               
            //Verifica a Resposta
            String response = Util.readLinesBufferedReader(in, "}");
            //System.out.println("RESPONSE: " + response);

            out.close();
            in.close();        
            socket.close();
            
            return response;
            
        }catch(Exception e){
            
            System.err.println("Erro no Cliente TCP. " + e);
        }
        
        return null;
    }
}
