package br.com.hubfintech.desafio.util;

import java.io.BufferedReader;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utilitarios Gerais
 * @author andre
 */
public class Util {
    
     /***
     * Converte um Long para um Codigo de Autorizacao (6 Casas Fixas Completados com Zeros)
     * @param n Numero a ser convertido
     * @return A String convertida
     */
    static public String converteLongAuthorizationCode(Long n){
        
        return converteNumber_TamanhoCasasFixas(n, '0', 6);
    }
    
    /***
     * Converte um Long para um tamanho de Casas Fixas
     * @param n Numero a ser convertido
     * @param caracter_adicao Caracter para adicao nos espacos vazios (EX: 0)
     * @param numero_casas Numero de casas para conversao
     * @return A String convertida
     */
    static public String converteNumber_TamanhoCasasFixas(Number n, char caracter_adicao, int numero_casas){
        
        return String.format("%" + caracter_adicao + numero_casas + "d", n);
    }
    
   /**
   * Converte um Date (EX: 2018-08-09 11:36:26)
   * @param data Data a ser formatada
   * @return Data formatada
   */
    static public String convertDate(Date data){
    
    	if(data != null)
    		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(data);
    	
    	return null;
    } 
    
    /***
     * Converte um BigDecimal para String (Com Virgula como Separador de Casa Decimal)
     * @param a BigDecimal a ser convertido
     * @return String do BigDecimal convertido (Null: Erro)
     */
    static public String convertBigDecimalToString(BigDecimal a){
        
        if(a != null)
            return a.setScale(2, RoundingMode.HALF_UP).toString().replace(".", ",");

        return null;
    }
    
    /***
     * Converte uma String (Com Virgula como Separador de Casa Decimal) em BigDecimal
     * @param s String do BigDecimal
     * @return O BigDecimal convertido (Null: Erro)
     */
    static public BigDecimal convertStringToBigDecimal(String s){
        
        if(s != null)
            return new BigDecimal(s.replace(',', '.'));
        
        return null;
    }
    
  /***
   * Le as Linhas de um BufferedReader
   * @param br BufferedReader a ser lido
   * @param finalizacao Texto para finalizacao da leitura
   * @return O texto lido
   */
    static public String readLinesBufferedReader(BufferedReader br, String finalizacao){
        
        StringBuilder sb = new StringBuilder();
        
        if(br != null){
            
            String line;
            
            try{
                while((line = br.readLine()) != null) {

                    sb.append(line);

                    if(line.contains(finalizacao))
                        break;
                }
            
                return sb.toString();
                
            }catch(Exception e){
                
                System.err.println("Erro ao ler Linhas do BufferedReader. " + e.getMessage());
            }
        }
        
        return null;
    }
}
