package br.com.hubfintech.desafio;

import br.com.hubfintech.desafio.domain.CardDomain;
import br.com.hubfintech.desafio.domain.CardTransactionDomain;
import br.com.hubfintech.desafio.domain.cache.CardCache;
import br.com.hubfintech.desafio.domain.db.CardPersistence;
import br.com.hubfintech.desafio.model.entity.Card;
import br.com.hubfintech.desafio.tcp.TCPServer;
import java.math.BigDecimal;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Executor Automatico na Inicializacao da APP
 * @author andre
 */
@Configuration
public class Autoexec {
    
    @Value("${tcp.transaction.port}")
    private int TCP_TRANSACTION_PORT;
    
    @Autowired
    CardDomain cardDomain;
    
    @Autowired
    CardTransactionDomain cardTransactionDomain;
    
    //==========================================================================
    
    @Bean
    InitializingBean init() {
        
        return () -> {     
            
            //================== SALVAMENTO DOS DADOS INICIAIS =================
            String card_number_1 = "1234567890123456";
            String card_number_2 = "6543210987654321";
            
            //Salva Cartoes
            Card c1 = new Card();
            c1.setCardnumber(card_number_1); 
            c1.setAvailableAmount(new BigDecimal("1000.00"));
            cardDomain.saveCard(c1);
            CardCache.putCard(c1);
            
            Card c2 = new Card();
            c2.setCardnumber(card_number_2);            
            c2.setAvailableAmount(new BigDecimal("1000.00"));
            cardDomain.saveCard(c2);
            CardCache.putCard(c2);
            
            System.out.println("============== DADOS INICIAIS ==============");
            
            for(Card c : CardCache.getAllCards())
                System.out.println(c);   
            
            //=================== SERVIDOR DE TRANSACOES TCP ===================
            new Thread(new Runnable(){
                 
                @Override
                public void run(){

                    try{
                        TCPServer.criaServidorTCP(TCP_TRANSACTION_PORT, cardTransactionDomain);

                    }catch(Exception e){
                        System.err.println("Erro ao criar Servidor TCP MultiThread. " + e.getMessage());
                    }
                }
                
            }).start();
            
            //================ PROCESSAMENTO DE SALVAMENTO NO DB ===============
            new Thread(new Runnable(){
                 
                @Override
                public void run(){

                    try{
                        new CardPersistence(cardDomain, cardTransactionDomain).start();

                    }catch(Exception e){
                        System.err.println("Erro ao criar Servidor TCP MultiThread. " + e.getMessage());
                    }
                }
                
            }).start();
             
        };
    }
}
