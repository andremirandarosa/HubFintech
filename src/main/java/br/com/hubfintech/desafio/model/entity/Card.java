package br.com.hubfintech.desafio.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Entidade para Cartao
 * @author andre
 */
@Entity
public class Card implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id; 
    
    /** Numero do Cartao */
    private String cardnumber;
    
    /** Valor Disponivel */
    private BigDecimal availableAmount = BigDecimal.ZERO;
    
    /** Lista de Transacoes */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="card_id")
    @Fetch(FetchMode.SELECT)
    private List<CardTransaction> transactions = new LinkedList<>();
    
    //===========================================================

    @Override
    public String toString() {
        return "CARD [ID: " + getId() + " - CARDNUMBER: " + getCardnumber() + " - AVALIABLE AMOUNT: " + getAvailableAmount() + "]";       
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
    public BigDecimal getAvailableAmount() {
        return availableAmount;
    }

    /**
     * @param availableAmount the availableAmount to set
     */
    public void setAvailableAmount(BigDecimal availableAmount) {
        this.availableAmount = availableAmount;
    }

    /**
     * @return the transactions
     */
    public List<CardTransaction> getTransactions() {
        return transactions;
    }

    /**
     * @param transactions the transactions to set
     */
    public void setTransactions(List<CardTransaction> transactions) {
        this.transactions = transactions;
    }
}
