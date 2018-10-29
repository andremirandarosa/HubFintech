package br.com.hubfintech.desafio.domain.dto;

/**
 * DTO para a Requisicao de uma Transacao
 * @author andre
 */
public class TransactionRequestDTO {
    
    /** Acao da Transacao */
    private String action;
    
    /** Numero do Cartao */
    private String cardnumber;
            
    /** Valor da Transacao */
    private String amount;
    
    //==========================================================================

    @Override
    public String toString() {
        return "REQUEST: ACTION: " + getAction() + " - CARDNUMBER: " + getCardnumber() + " - AMOUNT: " + amount; 
    }
    
    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
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
