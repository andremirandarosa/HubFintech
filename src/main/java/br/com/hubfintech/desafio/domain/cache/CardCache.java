package br.com.hubfintech.desafio.domain.cache;

import br.com.hubfintech.desafio.domain.db.CardPersistence;
import br.com.hubfintech.desafio.model.entity.Card;
import br.com.hubfintech.desafio.model.entity.CardTransaction;
import br.com.hubfintech.desafio.model.entity.util.TransactionResultCode;
import br.com.hubfintech.desafio.model.entity.util.TransactionType;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Cache para Cartao
 * @author andre
 */
public class CardCache{
    
    /** Cache de Cartoes Thread-Safe (Key: Numero do Cartao) */
    static final private Map<String, Card> CACHE = new ConcurrentHashMap<>();
    
    /** Contatador de Transacoes */
    static private long TRANSACTIONS_COUNT = 1;
    
    /** Lista para Armazenamento Temporario dos Cartoes com Transacao Sendo Executada */
    static final private LinkedList<String> LIST_CURRENT_CARDS_TRANSACTIONS = new LinkedList<>();
    
    //==========================================================================
    
    /***
     * Verifica o Contador de Transacoes
     * @return O valor do contador
     */
    static public long getCount(){
        
        return TRANSACTIONS_COUNT;
    }
    
    /***
     * Verifica a Lista de Todos os Cartoes
     * @return Lista de Cartoes
     */
    static public List<Card> getAllCards() {

        List<Card> cards = new LinkedList<>();
        
        CACHE.keySet().stream().forEach((k) -> {
            cards.add(CACHE.get(k));
        });
        
        return cards;
    }
    
    /***
     * Verifica um Cartao
     * @param cardnumber Numero do Cartao
     * @return O Cartao encontrado (Null: Erro)
     */
    static public Card getCard(String cardnumber){
        
        if((cardnumber != null) && (cardnumber.length() > 0) && CACHE.containsKey(cardnumber))
            return CACHE.get(cardnumber);
        
        return null;
    }
    
    /***
     * Salva um Cartao
     * @param card Cartao a ser salvo
     */
    static public void putCard(Card card){
        
        if((card != null) && (card.getCardnumber() != null) && (card.getCardnumber().length() > 0))
            CACHE.put(card.getCardnumber(), card);
    }
    
    //==========================================================================
    
    /***
     * Adiciona Cartao na Lista de Transacoes Correntes
     * @param cardnumber Numero do Cartao 
     * @return Flag de adicao na lista
     */
    synchronized static public boolean addCardListCurrentCardsTransactions(String cardnumber){
    
        if(!LIST_CURRENT_CARDS_TRANSACTIONS.contains(cardnumber)){
         
            LIST_CURRENT_CARDS_TRANSACTIONS.add(cardnumber);
            
            return true;
        }
        
        return false;
    }
    
    /**
     * Remove Cartao da Lista de Transacoes Correntes
     * @param cardnumber Numero do Cartao 
     * @return Flag de remocao na lista
     */
    synchronized static public boolean removeCardListCurrentCardsTransactions(String cardnumber){
    
        if(LIST_CURRENT_CARDS_TRANSACTIONS.contains(cardnumber)){
            
            LIST_CURRENT_CARDS_TRANSACTIONS.remove(cardnumber);
         
            return true;
        }
        
        return false;
    }
    
    //==========================================================================
    
     /***
     * ************* SINCRONIZADO **************
     * Salva uma Transacao do Cartao )
     * @param card Cartao para efetuar o salvamento
     * @param transaction_type Tipo de Transacao
     * @param result_code Resultado da Transacao
     * @param transaction_amount Valor da transacao
     * @param card_avaliable_amount Novo Valor Disponivel do Cartao
     * @return Cartao Atualizado
     */
    synchronized static public Card saveTransaction(Card card, TransactionType transaction_type, TransactionResultCode result_code, BigDecimal transaction_amount, BigDecimal card_avaliable_amount){
        
        //*** Atualiza Saldo ***
        card.setAvailableAmount(card_avaliable_amount);
        
        //Adiciona Transacao
        CardTransaction transaction = new CardTransaction();
        
        transaction.setId(TRANSACTIONS_COUNT);
        TRANSACTIONS_COUNT++;
        
        transaction.setCardnumber(card.getCardnumber());
        transaction.setTransaction_type(transaction_type);
        transaction.setResult_code(result_code);
        transaction.setAmount(transaction_amount);
        transaction.setDatetime(new Date());
        
        card.getTransactions().add(transaction);
        
        putCard(card);
        
        //Adiciona na Fila de Processamento para o DB
        CardPersistence.addToPresistence(card);
        
        removeCardListCurrentCardsTransactions(card.getCardnumber());
        
        return card;
    }
    
     /***
     * ************* SINCRONIZADO **************
     * Salva uma Transacao Invalida
     * @param cardnumber Numero do Cartao
     * @param transaction_type Tipo de Transacao
     * @param result_code Resultado da Transacao
     * @param transaction_amount Valor da transacao
     * @return ID da Transacao
     */
    synchronized static public Long saveInvalidTransaction(String cardnumber, TransactionType transaction_type, TransactionResultCode result_code, BigDecimal transaction_amount){
        
        CardTransaction transaction = new CardTransaction();
        
        Long id = TRANSACTIONS_COUNT;
        TRANSACTIONS_COUNT++;
        
        transaction.setId(id);
        transaction.setCardnumber(cardnumber);
        transaction.setAmount(transaction_amount);
        transaction.setTransaction_type(transaction_type);
        transaction.setResult_code(result_code);
        transaction.setDatetime(new Date());
        
        //Adiciona na Fila de Processamento para o DB
        CardPersistence.addToPresistence(transaction);
        
        return id;
    }
}
