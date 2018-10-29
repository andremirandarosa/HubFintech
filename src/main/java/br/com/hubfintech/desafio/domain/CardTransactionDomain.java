package br.com.hubfintech.desafio.domain;

import br.com.hubfintech.desafio.domain.cache.CardCache;
import br.com.hubfintech.desafio.domain.dto.TransactionRequestDTO;
import br.com.hubfintech.desafio.domain.dto.TransactionResponseDTO;
import br.com.hubfintech.desafio.model.entity.Card;
import br.com.hubfintech.desafio.model.entity.CardTransaction;
import br.com.hubfintech.desafio.model.entity.util.TransactionResultCode;
import br.com.hubfintech.desafio.model.entity.util.TransactionType;
import br.com.hubfintech.desafio.model.repository.CardTransactionRepository;
import br.com.hubfintech.desafio.util.Util;
import static br.com.hubfintech.desafio.util.Util.convertBigDecimalToString;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Dominio de Transacao de Cartao
 * @author andre
 */
public class CardTransactionDomain{
    
    @Autowired
    CardTransactionRepository cardTransactionRepository;
    
    //============================= TRANSACAO ==================================
    
    /***
     * Processa uma Requisicao de Transacao
     * @param request Requisicao a ser processada
     * @return Resposta da transacao
     */
    public TransactionResponseDTO processRequestTransaction(TransactionRequestDTO request){
        
        if(request != null){
            
            TransactionResponseDTO response = new TransactionResponseDTO();
            response.setAction(request.getAction());
            
            TransactionType tt = getTransactionTypeByValue(request.getAction());
            
            BigDecimal req_ammount = Util.convertStringToBigDecimal(request.getAmount());
            
            if((tt == null) || (req_ammount == null))
                return createTransactionResponseDTOInvalid(tt, TransactionResultCode.PROCESSING_ERROR, 
                                                           CardCache.saveInvalidTransaction(request.getCardnumber(), tt, TransactionResultCode.PROCESSING_ERROR, req_ammount));
                
            //Nao Permite Transacao de Valor 0
            if(req_ammount.compareTo(BigDecimal.ZERO) <= 0)
                return createTransactionResponseDTOInvalid(tt, TransactionResultCode.PROCESSING_ERROR, 
                                                           CardCache.saveInvalidTransaction(request.getCardnumber(), tt, TransactionResultCode.PROCESSING_ERROR, req_ammount));
            
            //******************************************************************
            
            //Aguarda Nao Existir Transacao Corrente para o Cartao
            while(!CardCache.addCardListCurrentCardsTransactions(request.getCardnumber())){                
                
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    System.out.println("Erro na espera da transacao concorrente do cartao: " + request.getCardnumber());
                }
            }
            
            //Verifica Cartao                       
            Card card = CardCache.getCard(request.getCardnumber());

            if(card != null){
                
                //Verifica Transacao
                switch(tt){

                    case WITHDRAW:{

                        BigDecimal avaliable_amount = card.getAvailableAmount().subtract(req_ammount);

                        if(avaliable_amount.compareTo(BigDecimal.ZERO) >= 0){

                            TransactionResultCode tc = TransactionResultCode.APPROVED;
                            response.setCode(tc.getCode());

                            //*********** Salva Transacao Aprovada *************
                            card = CardCache.saveTransaction(card, tt, tc, req_ammount, avaliable_amount);
                            //**************************************************

                            response.setAuthorization_code(Util.converteLongAuthorizationCode(getIdLastCardTransaction(card)));

                        }else{
                         
                            CardCache.removeCardListCurrentCardsTransactions(request.getCardnumber());
                            
                            return createTransactionResponseDTOInvalid(tt, TransactionResultCode.INSUFFICIENT_FUNDS, 
                                                                       CardCache.saveInvalidTransaction(request.getCardnumber(), tt, TransactionResultCode.INSUFFICIENT_FUNDS, req_ammount));
                        }

                        break;
                    }

//                    case DEPOSIT:{
//                        
//                        BigDecimal avaliable_amount = card.getAvailableAmount().add(req_ammount);
//                        
//                        TransactionResultCode tc = TransactionResultCode.APPROVED;
//                        response.setCode(tc.getCode());
//                        
//                        //************** Salva Transacao Aprovada **************
//                        card = CardCache.saveTransaction(card, tt, tc, req_ammount, avaliable_amount);
//                        //******************************************************
//                        
//                        response.setAuthorization_code(Util.converteLongAuthorizationCode(getIdLastCardTransaction(card)));
//                        
//                        break;
//                    }

                    default:{
                        
                        CardCache.removeCardListCurrentCardsTransactions(request.getCardnumber());
                        
                        return createTransactionResponseDTOInvalid(tt, TransactionResultCode.PROCESSING_ERROR, 
                                                                   CardCache.saveInvalidTransaction(request.getCardnumber(), tt, TransactionResultCode.PROCESSING_ERROR, req_ammount));
                    }
                }

                return response;

            }else{
             
                CardCache.removeCardListCurrentCardsTransactions(request.getCardnumber());
                
                return createTransactionResponseDTOInvalid(tt, TransactionResultCode.INVALID_ACCOUNT, 
                                                           CardCache.saveInvalidTransaction(request.getCardnumber(), tt, TransactionResultCode.INVALID_ACCOUNT, req_ammount));
            }


            //******************************************************************
        }
     
        return createTransactionResponseDTOInvalid(null, TransactionResultCode.PROCESSING_ERROR, 
                                                   CardCache.saveInvalidTransaction(request.getCardnumber(), null, TransactionResultCode.PROCESSING_ERROR, null));
    }
    
    //==========================================================================
        
     /***
     * Varifica o Enum dos Tipos de Transacao pelo seu Valor
     * @param value Valor do Tipo de Transacao
     * @return O Enum da Transacao (Null: Erro)
     */
    public TransactionType getTransactionTypeByValue(String value){
        
        for(TransactionType tt : TransactionType.values()){
            
            if(tt.getType().equals(value))
                return tt;
        }
        
        return null;
    }
    
    /***
     * Verifica a Ultima Transacao de um Cartao
     * @param card Cartao a ser verificado
     * @return A ultima transacao (Null: Erro)
     */
    public CardTransaction getLastCardTransaction(Card card){
        
        if((card != null) && (card.getTransactions() != null) && (card.getTransactions().size() > 0))
            return card.getTransactions().get(card.getTransactions().size() - 1);
     
        return null;
    }
    
     /***
     * Verifica o ID da Ultima Transacao de um Cartao
     * @param card Cartao a ser verificado
     * @return O Id da ultima transacao (Null: Erro)
     */
    public Long getIdLastCardTransaction(Card card){
        
        CardTransaction t = getLastCardTransaction(card);
        
        if(t != null)
            return t.getId();
        
        return null;
    }
    
    /***
     * Salva uma Transacao de Cartao
     * @param ct Transacao a ser salva
     * @return Transacao salva
     */
    public CardTransaction saveCardTransaction(CardTransaction ct){
    
        return cardTransactionRepository.saveCardTransaction(ct);
    }
    
     //=============================== DTOS ====================================
     
     /***
     * Cria um DTO de Requiscao de Transacao
     * @param tt Tipo de transacao
     * @param cardnumber Numero do Cartao
     * @param amount Valor da transacao
     * @return O DTO de Requisicao
     */
    static public TransactionRequestDTO createTransactionRequestDTO(TransactionType tt, String cardnumber, BigDecimal amount){
        
        TransactionRequestDTO request_dto = new TransactionRequestDTO();
        request_dto.setAction(tt.getType());
        request_dto.setCardnumber(cardnumber);
        request_dto.setAmount(convertBigDecimalToString(amount));
        
        return request_dto;
    }
    
    /***
     * Cria um DTO de Resposta de Transacao
     * @param tt Tipo de Transacao
     * @param trc Codigo de Resultado da Transacao
     * @param ac Codigo de Autorizacao
     * @return O DTO de Resposta
     */
    static public TransactionResponseDTO createTransactionResponseDTO(TransactionType tt, TransactionResultCode trc, Long ac){
    
        TransactionResponseDTO response_dto = new TransactionResponseDTO();
        
        if(tt != null)
            response_dto.setAction(tt.getType());
        
        response_dto.setCode(trc.getCode());
        
        if(ac != null)
            response_dto.setAuthorization_code(Util.converteLongAuthorizationCode(ac));
     
        return response_dto;
    }
    
    /***
     * Cria um DTO de Resposta de Transacao com Saldo Insuficiente
     * @param tt Tipo de Transacao
     * @param ac Codigo de Autorizacao
     * @return O DTO de Resposta
     */
    static public TransactionResponseDTO createTransactionResponseDTOInsufficientFunds(TransactionType tt, Long ac){
    
        return createTransactionResponseDTO(tt, TransactionResultCode.INSUFFICIENT_FUNDS, ac);
    }
    
    /***
     * Cria um DTO de Resposta de Transacao com Erro de Processamento
     * @param tt Tipo de Transacao
     * @param trc Codigo de Resultado da Transacao
     * @param ac Codigo de Autorizacao
     * @return O DTO de Resposta
     */
    static public TransactionResponseDTO createTransactionResponseDTOInvalid(TransactionType tt, TransactionResultCode trc, Long ac){
    
        return createTransactionResponseDTO(tt, trc, ac);
    }
}
