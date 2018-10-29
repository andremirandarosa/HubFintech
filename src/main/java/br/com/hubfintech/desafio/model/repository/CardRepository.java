package br.com.hubfintech.desafio.model.repository;

import br.com.hubfintech.desafio.model.crud.CardCRUD;
import br.com.hubfintech.desafio.model.entity.Card;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Repositorio para Cartoes
 * @author andre
 */
public class CardRepository {
    
    @Autowired
    CardCRUD cardCrud;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    //==========================================================================
    
     /***
      * Salva um Cartao
      * @param c Cartao a ser Salvo
      * @return O cartao salvo (Null: Erro)
      */
    public Card saveCard(Card c) {
        
        if(c!= null)
            return cardCrud.save(c);
        
        return null;
    }
    
    /***
     * Verifica a Lista de Todos os Cartoes
     * @return Lista de Cartoes
     */
    public List<Card> getAllCards() {
        
        return (List<Card>) cardCrud.findAll();
    }
    
    /***
     * Verifica um Cartao pelo seu ID
     * @param id ID do Cartao
     * @return Optional do Cartao com o ID especificado
     */
    public Optional<Card> getCardById(long id) {
        
        return cardCrud.findById(id);
    }
    
     /***
     * Busca um Cartao pelo Numero
     * @param cardnumber Numero do Cartao
     * @return O Cartao com o numero (Null: Erro)
     */
    public Card findCardByNumber(String cardnumber){
        
        if((cardnumber != null) && (cardnumber.length() > 0)){
            
            try{
                return (Card) entityManager.createQuery("SELECT c FROM Card c WHERE c.cardnumber LIKE '" + cardnumber + "'")
                                           .getSingleResult();
            }catch (Exception e){}
        }
                
        return null;
    }
}
