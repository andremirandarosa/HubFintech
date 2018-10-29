package br.com.hubfintech.desafio.api.dto;

/**
 * DTO para Dados da Transacao na API
 * @author andre
 */
public class CardTransactionDTO {
    
    /** Data e Hora da Transacao */
    private String date;
    
    /** Valor da Transacao */
    private String amount;
    
    //==========================================================================

    @Override
    public String toString() {
        return "TRANSACTION: DATE: " + getDate() + " - AMOUNT: " + getAmount();       
    }
    
    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }
}
