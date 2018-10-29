package br.com.hubfintech.desafio.domain.dto;

/**
 * DTO para a Resposta de uma Transacao
 * @author andre
 */
public class TransactionResponseDTO {
    
    /** Acao da Transacao */
    private String action;
    
    /** Codigo de Resultado da Transacao */
    private String code;
            
    /** Codigo de Autorizacao (ID da Transacao) */
    private String authorization_code;
    
    //==========================================================================

    @Override
    public String toString() {
        return "RESPONSE: ACTION: " + getAction() + " - CODE: " + getCode() + " - AUTHORIZATON CODE: " + getAuthorization_code(); 
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
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the authorization_code
     */
    public String getAuthorization_code() {
        return authorization_code;
    }

    /**
     * @param authorization_code the authorization_code to set
     */
    public void setAuthorization_code(String authorization_code) {
        this.authorization_code = authorization_code;
    }
}
