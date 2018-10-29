package br.com.hubfintech.desafio.config;

import br.com.hubfintech.desafio.domain.CardDomain;
import br.com.hubfintech.desafio.domain.CardTransactionDomain;
import br.com.hubfintech.desafio.model.repository.CardRepository;
import br.com.hubfintech.desafio.model.repository.CardTransactionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuracao Geral da App
 * @author andre.rosa
 */
@Configuration
public class Config{
    
    /* Path Padrao para a API REST */
    static public final String PATH_API = "/api";
    
    //============================= REPOSITORIES================================
    
    @Bean
    public CardRepository getCardRepository(){
        
        return new CardRepository();
    }
    
    @Bean
    public CardTransactionRepository getCardTransactionRepository(){
        
        return new CardTransactionRepository();
    }
    
    //=============================== DOMAINS ==================================
    
    @Bean
    public CardDomain getCardDomain(){

        return new CardDomain();
    }
    
    @Bean
    public CardTransactionDomain getCardTransactionDomain(){

        return new CardTransactionDomain();
    }
}
