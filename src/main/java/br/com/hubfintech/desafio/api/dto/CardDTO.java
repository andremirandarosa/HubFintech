package br.com.hubfintech.desafio.api.dto;

import java.util.LinkedList;
import java.util.List;

/**
 * DTO para Dados do Cartao na API
 * @author andre
 */
public class CardDTO {
    
    /** Numero do Cartao */
    private String cardnumber;
    
    /** Valor Disponivel */
    private String availableAmount;
    
    /** Lista de Transacoes */
    private List<CardTransactionDTO> transactions = new LinkedList<>();
    
    //==========================================================================

    @Override
    public String toString() {
        return "Card: CARD NUMBER: " + getCardnumber() + " - AVALIABLE AMOUNT: " + getAvailableAmount();       
    }
    
    /**
     * @return the cardnumber
     */
    public String getCardnumber() {
        return cardnumber;
    }

    /**
     * @param cardnumber the cardnumber to set
     */
    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    /**
     * @return the availableAmount
     */
    public String getAvailableAmount() {
        return availableAmount;
    }

    /**
     * @param availableAmount the availableAmount to set
     */
    public void setAvailableAmount(String availableAmount) {
        this.availableAmount = availableAmount;
    }

    /**
     * @return the transactions
     */
    public List<CardTransactionDTO> getTransactions() {
        return transactions;
    }

    /**
     * @param transactions the transactions to set
     */
    public void setTransactions(List<CardTransactionDTO> transactions) {
        this.transactions = transactions;
    }
}
