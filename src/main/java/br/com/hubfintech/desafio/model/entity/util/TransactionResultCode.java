package br.com.hubfintech.desafio.model.entity.util;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Enumeracao dos Codigos de Resultado de uma Transacao de Cartao
 * @author andre
 */
public enum TransactionResultCode {
    
    APPROVED("00"), INSUFFICIENT_FUNDS("51"), INVALID_ACCOUNT("14"), PROCESSING_ERROR("96");
	
	@JsonIgnore
	String code;
	
	private TransactionResultCode(String code){
		this.setCode(code);
    }
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
