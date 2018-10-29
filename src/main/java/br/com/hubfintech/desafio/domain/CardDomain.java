package br.com.hubfintech.desafio.domain;

import br.com.hubfintech.desafio.api.dto.CardDTO;
import br.com.hubfintech.desafio.api.dto.CardTransactionDTO;
import br.com.hubfintech.desafio.model.entity.Card;
import br.com.hubfintech.desafio.model.entity.CardTransaction;
import br.com.hubfintech.desafio.model.repository.CardRepository;
import br.com.hubfintech.desafio.util.Util;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Dominio de Cartao
 * @author andre
 */
public class CardDomain {
    
     @Autowired
     CardRepository cardRepository;
     
     //=========================================================================
     
    /***
    * Salva um Cartao
    * @param c Cartao a ser Salvo
    * @return O cartao salvo (Null: Erro)
    */
    public Card saveCard(Card c) {
        
        return cardRepository.saveCard(c);
    }
  
    /***
     * Verifica a Lista de Todos os Cartoes
     * @return Lista de Cartoes
     */
    public List<Card> getAllCards() {
        
        return cardRepository.getAllCards();
    }
  
    /***
     * Busca um Cartao pelo Numero
     * @param cardnumber Numero do Cartao
     * @return O Cartao com o numero (Null: Erro)
     */
    public Card findCardByNumber(String cardnumber){
                
        return cardRepository.findCardByNumber(cardnumber);
    }
    
    //==========================================================================
    //===============================  DTOs ====================================
    //==========================================================================
    
    /***
     * Verifica a Lista de Todos os Cartoes como DTOs
     * @return Lista de Cartoes DTOs (Null: Erro
     */
    public List<CardDTO> getAllCardsDTOs() {
        
        return getListCardDTO(getAllCards());
    }
   
    /***
     * Converte um Cartao para DTO (contendo apenas as ultimas 10 transacoes)
     * @param c Cartao a ser convertido
     * @return DTO do Cartao (Null: Erro)
     */
    public CardDTO getCardDTO(Card c){
        
        if(c != null){
            
            CardDTO dto = new CardDTO();
            dto.setCardnumber(c.getCardnumber());
            dto.setAvailableAmount(Util.convertBigDecimalToString(c.getAvailableAmount()));
            
            if((c.getTransactions() != null) && (c.getTransactions().size() > 0)){
             
                if(c.getTransactions().size() <= 10) dto.setTransactions(getListCardTransactionDTO(c.getTransactions()));
                else dto.setTransactions(getListCardTransactionDTO(c.getTransactions().subList(c.getTransactions().size() - 10,
                                                                   c.getTransactions().size())));
            }
            
            return dto;
        }
        
        return null;
    } 
    
     /***
     * Converte uma Lista de Cartoes para DTOs
     * @param list Lsita de Cartoes a ser convertida
     * @return Lista de DTOs (Null: Erro)
     */
    public List<CardDTO> getListCardDTO(List<Card> list){
        
        if((list != null) && (list.size() > 0)){
            
            List<CardDTO> retorno = new LinkedList<>();
            
            list.stream().forEach((c) -> {
                                    retorno.add(getCardDTO(c));
                                });
            
            return retorno;
        }
        
        return null;
    } 
    
    //==========================================================================
    
     /***
     * Converte uma Transacao para DTO
     * @param t Transacao a ser convertida
     * @return DTO da Transacao (Null: Erro)
     */
    public CardTransactionDTO getCardTransactionDTO(CardTransaction t){
        
        if(t != null){
            
            CardTransactionDTO dto = new CardTransactionDTO();
            dto.setDate(Util.convertDate(t.getDatetime()));
            dto.setAmount(Util.convertBigDecimalToString(t.getAmount()));
            
            return dto;
        }
        
        return null;
    }
    
     /***
     * Converte uma Lista de Transacoes para uma Lista de DTOs
     * @param list Lista de transacoes a ser convertida
     * @return Lista de DTOs das Transacoes (Null: Erro)
     */
    public List<CardTransactionDTO> getListCardTransactionDTO(List<CardTransaction> list){
        
        if((list != null) && (list.size() > 0)){
            
            List<CardTransactionDTO> retorno = new LinkedList<>();
            
            list.stream().forEach((t) -> {
                                    retorno.add(getCardTransactionDTO(t));
                                }); 
            
            //Ultimas Transacoes no Inicio    
            Collections.reverse(retorno);

            return retorno;
        }
        
        return null;
    }
}
