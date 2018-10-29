package br.com.hubfintech.desafio.model.entity.util;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Enumeracao dos Tipos de Transacao
 * @author andre
 */
public enum TransactionType {
    
    WITHDRAW("withdraw");//, DEPOSIT("deposit");
	
	@JsonIgnore
	String type;
	
	private TransactionType(String type){
		this.setType(type);
    }
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
