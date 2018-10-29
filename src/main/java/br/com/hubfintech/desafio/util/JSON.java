package br.com.hubfintech.desafio.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/*
 * Utilitario para Conversao de Dados para o Formato JSON
 * @autor andre
 */
public class JSON{
    
    /**Serializador JSON*/
    static private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    
    //==========================================================================
    
    /***
     * Converte um Objeto para uma String no formato JSON
     * @param obj Objeto Java
     * @return String JSON do Objeto convertido (ERRO: null)
     */
    static public String convertOBJ_JSON(Object obj){
        
        try{
            return OBJECT_MAPPER.writeValueAsString(obj);
            
        }catch(JsonProcessingException e){
            
            System.err.println("Erro ao converter Objeto em JSON. " + e.getMessage());
            
            return null;
        }
    }
    
    /***
     * Converte uma String no formato JSON para um Objeto
     * @param json String no formato JSON
     * @param classe Classe do Objeto a ser convertido 
     * @return O Objeto convertido do JSON (ERRO: null)
     */
    static public Object convertJSON_OBJ(String json, Class classe){
        
        try{
            return OBJECT_MAPPER.readValue(json, classe);
            
         }catch(IOException e){
            
             System.err.println("Erro ao converter JSON em Objeto. " + e.getMessage());
            
             return null;
        }
    }
}
