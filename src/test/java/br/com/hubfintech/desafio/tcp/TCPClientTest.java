//package br.com.hubfintech.desafio.tcp;
//
//import br.com.hubfintech.desafio.domain.CardTransactionDomain;
//import br.com.hubfintech.desafio.domain.dto.TransactionRequestDTO;
//import br.com.hubfintech.desafio.model.entity.util.TransactionType;
//import br.com.hubfintech.desafio.util.JSON;
//import br.com.hubfintech.desafio.util.Util;
//import java.math.BigDecimal;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Random;
//import org.junit.Test;
//
///**
// * Teste do Cliente TCP
// * @author andre
// */
//
//public class TCPClientTest {
//    
//    static final String IP = "127.0.0.1";
//            
//    static final int PORT = 8081;
//    
//    //==========================================================================
//    
////    @Test
//    public void testSaque(){
//        
//        TransactionRequestDTO request_dto = CardTransactionDomain.createTransactionRequestDTO(TransactionType.WITHDRAW, "1234567890123456", new BigDecimal("240.00"));
////        TransactionRequestDTO request_dto = CardTransactionDomain.createTransactionRequestDTO(TransactionType.WITHDRAW, "1234567890123456", new BigDecimal("0"));
////        TransactionRequestDTO request_dto = CardTransactionDomain.createTransactionRequestDTO(TransactionType.WITHDRAW, "1234567890123456", new BigDecimal("-1"));
//        
//        String request = JSON.convertOBJ_JSON(request_dto);
//        
//        try{
//            String response = new TCPClient(IP, PORT).enviaRequisicao(request);
//            System.out.println("RESPONSE - TESTE: " + response);
//            
//        }catch(Exception e){
//
//           System.err.println("Erro ao criar o Cliente TCP." + e);
//        }
//    }
//    
////    @Test
//    public void testSaque2(){
//        
//        TransactionRequestDTO request_dto = CardTransactionDomain.createTransactionRequestDTO(TransactionType.WITHDRAW, "6543210987654321", new BigDecimal("130.47"));
//        
//        String request = JSON.convertOBJ_JSON(request_dto);
//        
//        try{
//            String response = new TCPClient(IP, PORT).enviaRequisicao(request);
//            System.out.println("RESPONSE - TESTE: " + response);
//            
//        }catch(Exception e){
//
//           System.err.println("Erro ao criar o Cliente TCP." + e);
//        }
//    }
//    
//    @Test
//    public void testMultithread(){
//        
//        int total = 30000;
//        
//        final List<String> requests = new LinkedList<>();
//        
//        //Cria as Strings de Requisicoes
//        for(int i=0; i < total; i++){
//            
//            TransactionType tt;
//            
//            tt = TransactionType.WITHDRAW;
//            
//            String cardnumber = "1234567890123456";
//            
//            if(i%2 != 0) cardnumber = Util.converteNumber_TamanhoCasasFixas(new Random(i * 999999999).nextInt(), '0', 16).replace("-", "0");
//            
//            int valor = new Random(i).nextInt(10);
//            
//            if(valor < 0)
//                valor = valor * -1;
//            
//            BigDecimal amount = new BigDecimal(valor);
//            
//            TransactionRequestDTO dto = CardTransactionDomain.createTransactionRequestDTO(tt, cardnumber, amount);
////            System.out.println(dto);
//            
//            requests.add(JSON.convertOBJ_JSON(dto));
//        }
//        
//        //Executa as Requisicoes
//        Long inicio = System.currentTimeMillis();
//        
//        for(int i=0; i < total; i++){
//            
//            final int p = i;
//            final String f_request = requests.get(i);
//            
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    
//                    try{
//                        String response = new TCPClient(IP, PORT).enviaRequisicao(f_request);
////                        System.out.println("RESPONSE - " + p + ": " + response);
//                        
//                    }catch(Exception e){
//                       System.err.println("Erro ao criar o Cliente TCP." + e);
//                    }
//                }
//            }).start();
//            
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException ex) {
//                System.out.println("Erro na espera do envio de requisicoes.");                    
//            }
//        }
//    }
//}
