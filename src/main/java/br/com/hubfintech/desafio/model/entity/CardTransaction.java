package br.com.hubfintech.desafio.model.entity;

import br.com.hubfintech.desafio.model.entity.util.TransactionType;
import br.com.hubfintech.desafio.model.entity.util.TransactionResultCode;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entidade para Transacao de Cartao
 * @author andre
 */
@Entity
public class CardTransaction implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    private Long id; 
    
    /** Data e Hora da Transacao */
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;
    
    /** Numero do Cartao */
    private String cardnumber;
    
    /** Tipo de Transacao (WITHDRAW, DEPOSIT) */
    @Enumerated(EnumType.STRING)
    private TransactionType transaction_type;
    
    /** Codigo de Resultado da Transacao (APROVADA, SALDO_INSUFICIENTE, CONTA_INVALIDA, ERRO_PROCESSAMENTO) */
    @Enumerated(EnumType.STRING)
    private TransactionResultCode result_code;
    
    /** Valor da Transacao */
    private BigDecimal amount;
    
    //===========================================================

    @Override
    public String toString() {
        return "TRANSACTION [ID: " + getId() + " - TYPE: " + getTransaction_type() + " - CODE: " + getResult_code() +
               " - TIMESTAMP: " + getDatetime() + " - AMOUNT: " + getAmount() + "]";//+ " - AUTHORIZATION CODE: " + getAuthorization_code() + "]";       
    }
 
    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the transaction_type
     */
    public TransactionType getTransaction_type() {
        return transaction_type;
    }

    /**
     * @param transaction_type the transaction_type to set
     */
    public void setTransaction_type(TransactionType transaction_type) {
        this.transaction_type = transaction_type;
    }

    /**
     * @return the result_code
     */
    public TransactionResultCode getResult_code() {
        return result_code;
    }

    /**
     * @param result_code the result_code to set
     */
    public void setResult_code(TransactionResultCode result_code) {
        this.result_code = result_code;
    }

    /**
     * @return the datetime
     */
    public Date getDatetime() {
        return datetime;
    }

    /**
     * @param datetime the datetime to set
     */
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    /**
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
}
