//package br.com.hubfintech.desafio.util;
//
//import java.math.BigDecimal;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// * Teste dos Utilitarios Gerais
// * @author andre
// */
//public class UtilTest {
//   
//    @Test
//    public void testConverteNumber_TamanhoCasasFixas() {
//
//        String result = Util.converteNumber_TamanhoCasasFixas(10, '0', 6);
//        assertEquals("000010", result);
//    }
//
//    @Test
//    public void testConvertDate() {
//        
//        String s_data = "2018-10-26 16:09:01";
//        
//        try{
//            String result = Util.convertDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s_data));
//            
//            assertEquals(s_data, result);
//            
//        } catch (ParseException e) {
//            System.err.println("Erro ao converver Date.");
//        }
//    }
//    
//    @Test
//    public void testConvertBigDecimalToString(){
//        
//        String valor = Util.convertBigDecimalToString(BigDecimal.valueOf(1234.56));
//        
//        assertNotNull(valor);
//        assertEquals("1234,56", valor);
//    }
//    
//    @Test
//    public void testConvertStringToBigDecimalg(){
//        
//        BigDecimal bd = Util.convertStringToBigDecimal("1,10");
//        
//        assertNotNull(bd);
//        assertEquals(new BigDecimal("1.10"), bd);
//    }
//}
