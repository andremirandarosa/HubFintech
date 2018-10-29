package br.com.hubfintech.desafio.model.repository;

import br.com.hubfintech.desafio.model.crud.CardTransactionCRUD;
import br.com.hubfintech.desafio.model.entity.CardTransaction;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Repositorio para Transacoes de Cartao
 * @author andre
 */
public class CardTransactionRepository {
    
    
    @Autowired
    CardTransactionCRUD cardTransactionCrud;
    
    //==========================================================================
    
     /***
      * Salva uma Transacao
      * @param t Transacao a ser Salva
      * @return A transacao salva (Null: Erro)
      */
    public CardTransaction saveCardTransaction(CardTransaction t) {
        
        if(t != null)
            return cardTransactionCrud.save(t);
        
        return null;
    }
    
    /***
     * Verifica a Lista de Todas as Transacoes
     * @return Lista de Cartoes
     */
    public List<CardTransaction> getAllCardTransactions(){
        
        return (List<CardTransaction>) cardTransactionCrud.findAll();
    }
    
    /***
     * Verifica um Cartao pelo seu ID
     * @param id ID do Cartao
     * @return Optional do Cartao com o ID especificado
     */
    public Optional<CardTransaction> getCardTransactionById(long id) {
        
        return cardTransactionCrud.findById(id);
    }
}
