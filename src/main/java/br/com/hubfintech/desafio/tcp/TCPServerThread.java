package br.com.hubfintech.desafio.tcp;

import br.com.hubfintech.desafio.domain.CardTransactionDomain;
import br.com.hubfintech.desafio.domain.dto.TransactionRequestDTO;
import br.com.hubfintech.desafio.domain.dto.TransactionResponseDTO;
import br.com.hubfintech.desafio.model.entity.util.TransactionResultCode;
import br.com.hubfintech.desafio.util.JSON;
import br.com.hubfintech.desafio.util.Util;
import java.net.*;
import java.io.*;

/*
 * Thread do Servidor de Socket TCP
 * @autor andre
 */
public class TCPServerThread extends Thread{
        
    private Socket socket = null;
    
    private final CardTransactionDomain cardTransactionDomain;
    
    //==========================================================================
     
    public TCPServerThread(Socket socket, CardTransactionDomain cardTransactionDomain) {
        
        super("TCP_ServerMultiThread");
        
        this.socket = socket;
        
        this.cardTransactionDomain = cardTransactionDomain;
    }
    
    @Override
    public void run() {
        
        try{
            InputStreamReader instr = new InputStreamReader(socket.getInputStream());

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader innet = new BufferedReader(instr);
            
            //Le Requisiacao do Cliente
            String request = Util.readLinesBufferedReader(innet, "}");
            
            TransactionResponseDTO response_dto;
            
            if((request != null) && (request.length() > 0)){
            
                TransactionRequestDTO request_dto = (TransactionRequestDTO) JSON.convertJSON_OBJ(request, TransactionRequestDTO.class);                
//                System.out.println(request_dto);

                if(request_dto != null){

                    //*** PROCESSA REQUISICAO ***
                    response_dto = cardTransactionDomain.processRequestTransaction(request_dto);

                }else response_dto = CardTransactionDomain.createTransactionResponseDTOInvalid(null, TransactionResultCode.PROCESSING_ERROR, -1L);
                
            }else response_dto = CardTransactionDomain.createTransactionResponseDTOInvalid(null, TransactionResultCode.PROCESSING_ERROR, -1L);
            
            String response = JSON.convertOBJ_JSON(response_dto);
            
            //Envia Resposta
            out.println(response);
//            System.out.println("RESPONSE: " + response);
                
            out.close();            
            socket.close();
            
	}catch(Exception e){
            
            System.err.println("Cliente perdeu conexao com o Servidor TCP.");
            
            e.printStackTrace();
        }
    }
}
