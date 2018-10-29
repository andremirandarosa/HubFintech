//package br.com.hubfintech.desafio.domain;
//
//import br.com.hubfintech.desafio.api.dto.CardDTO;
//import br.com.hubfintech.desafio.api.dto.CardTransactionDTO;
//import br.com.hubfintech.desafio.domain.cache.CardCache;
//import br.com.hubfintech.desafio.model.entity.Card;
//import br.com.hubfintech.desafio.model.entity.CardTransaction;
//import br.com.hubfintech.desafio.model.entity.util.TransactionResultCode;
//import br.com.hubfintech.desafio.model.entity.util.TransactionType;
//import br.com.hubfintech.desafio.util.Util;
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.LinkedList;
//import java.util.List;
//import org.junit.Assert;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
///**
// * Teste de Dominio de Cartoes
// * @author andre
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class CardDomainTest {
//    
//    @Autowired
//    CardDomain cardDomain;
//    
//    //==========================================================================
//
//    
//    @Test
//    public void testGetAllCardsDTOs() {
//     
//        Card c1 = new Card();
//        c1.setCardnumber("111111111111");            
//        c1.setAvailableAmount(BigDecimal.valueOf(1000));  
//
//        LinkedList<CardTransaction> list1 = new LinkedList<>();
//        
//        CardTransaction t1 = new CardTransaction();
//        t1.setTransaction_type(TransactionType.WITHDRAW);
//        t1.setDatetime(new Date());
//        t1.setResult_code(TransactionResultCode.APPROVED);
//        t1.setAmount(BigDecimal.valueOf(11.11));
//        list1.add(t1);        
//        CardTransaction t2 = new CardTransaction();
//        t2.setTransaction_type(TransactionType.WITHDRAW);
//        t2.setDatetime(new Date());
//        t2.setResult_code(TransactionResultCode.INVALID_ACCOUNT);
//        t2.setAmount(BigDecimal.valueOf(22.22));        
//        list1.add(t2);     
//        
//        c1.setTransactions(list1);
////        cardDomain.saveCard(c1);
//        CardCache.putCard(c1);
//        
//        //======
//        
//        Card c2 = new Card();
//        c2.setCardnumber("2222222222222222");            
//        c2.setAvailableAmount(BigDecimal.valueOf(3000));  
//
//        LinkedList<CardTransaction> list2 = new LinkedList<>();
//        
//        CardTransaction t3 = new CardTransaction();
//        t3.setTransaction_type(TransactionType.WITHDRAW);
//        t3.setDatetime(new Date());
//        t3.setResult_code(TransactionResultCode.INVALID_ACCOUNT);
//        t3.setAmount(BigDecimal.valueOf(33.33));
//        
//        list2.add(t3);        
//        c2.setTransactions(list2);
////        cardDomain.saveCard(c2);
//        CardCache.putCard(c2);
//        
//        //====
//        
//        List<CardDTO> cards = cardDomain.getAllCardsDTOs();
//        
//        assertNotNull(cards);
//        
////        cards.stream().forEach((c) -> { 
////            
////            System.out.println(c); 
////            
////            if(c.getTransactions() != null){
////                
////                c.getTransactions().stream().forEach((t) -> {             
////                    System.out.println("          " + t); 
////                });
////            }
////        });
//        
//    }
//    
//    @Test
//    public void testGetCardDTO(){
//        
//        Card c = new Card();
//        c.setCardnumber("1234567890123456");            
//        c.setAvailableAmount(BigDecimal.valueOf(1000));  
//
//        LinkedList<CardTransaction> list = new LinkedList<>();
//        
//        CardTransaction t = new CardTransaction();
//        t.setTransaction_type(TransactionType.WITHDRAW);
//        t.setDatetime(new Date());
//        t.setResult_code(TransactionResultCode.APPROVED);
//        t.setAmount(BigDecimal.valueOf(1.23));
//        
//        list.add(t);        
//        c.setTransactions(list);
//        
//        //======
//        
//        CardDTO dto = cardDomain.getCardDTO(c);
//        
//        assertNotNull(dto);
//        Assert.assertEquals("1234567890123456", dto.getCardnumber());
//        Assert.assertEquals("1000,00", dto.getAvailableAmount());
//        
//        assertNotNull(dto.getTransactions());
//        assertTrue(dto.getTransactions().size() == 1);
//        
//        
//        assertNotNull(dto.getTransactions().get(0));
//        assertTrue(Util.convertDate(t.getDatetime()).compareTo(dto.getTransactions().get(0).getDate()) == 0);
//        assertTrue("1,23".compareTo(dto.getTransactions().get(0).getAmount()) == 0);
//    } 
//    
//    @Test
//    public void testGetListCardDTO(){
//      
//        List<Card> cards = new LinkedList<>();
//        
//        Card c1 = new Card();
//        c1.setCardnumber("1234567890123456");            
//        c1.setAvailableAmount(BigDecimal.valueOf(1000));  
//
//        LinkedList<CardTransaction> list1 = new LinkedList<>();
//        
//        CardTransaction t1 = new CardTransaction();
//        t1.setTransaction_type(TransactionType.WITHDRAW);
//        t1.setDatetime(new Date());
//        t1.setResult_code(TransactionResultCode.APPROVED);
//        t1.setAmount(BigDecimal.valueOf(1.23));
//        
//        list1.add(t1);        
//        c1.setTransactions(list1);
//        cards.add(c1);
//        
//        //======
//        
//        Card c2 = new Card();
//        c2.setCardnumber("6543210987654321");            
//        c2.setAvailableAmount(BigDecimal.valueOf(3000));  
//
//        LinkedList<CardTransaction> list2 = new LinkedList<>();
//        
//        CardTransaction t2 = new CardTransaction();
//        t2.setTransaction_type(TransactionType.WITHDRAW);
//        t2.setDatetime(new Date());
//        t2.setResult_code(TransactionResultCode.INVALID_ACCOUNT);
//        t2.setAmount(BigDecimal.valueOf(56.32));
//        
//        list2.add(t2);        
//        c2.setTransactions(list2);
//        cards.add(c2);
//        
//        //======
//        
//        List<CardDTO> list_dto = cardDomain.getListCardDTO(cards);
//        
//        assertNotNull(list_dto);
//        Assert.assertTrue(list_dto.size() == 2);
//        
//        assertNotNull(list_dto.get(0));
//        Assert.assertEquals("1234567890123456", list_dto.get(0).getCardnumber());
//        Assert.assertEquals("1000,00", list_dto.get(0).getAvailableAmount());
//        assertNotNull(list_dto.get(0).getTransactions());
//        assertTrue(list_dto.get(0).getTransactions().size() == 1);
//        assertNotNull(list_dto.get(0).getTransactions().get(0));
//        assertTrue(Util.convertDate(t1.getDatetime()).compareTo(list_dto.get(0).getTransactions().get(0).getDate()) == 0);
//        assertTrue("1,23".compareTo(list_dto.get(0).getTransactions().get(0).getAmount()) == 0);
//        
//        assertNotNull(list_dto.get(1));
//        Assert.assertEquals("6543210987654321", list_dto.get(1).getCardnumber());
//        Assert.assertEquals("3000,00", list_dto.get(1).getAvailableAmount());
//        assertNotNull(list_dto.get(1).getTransactions());
//        assertTrue(list_dto.get(1).getTransactions().size() == 1);
//        assertNotNull(list_dto.get(1).getTransactions().get(0));
//        assertTrue(Util.convertDate(t2.getDatetime()).compareTo(list_dto.get(1).getTransactions().get(0).getDate()) == 0);
//        assertTrue("56,32".compareTo(list_dto.get(1).getTransactions().get(0).getAmount()) == 0);
//    } 
//    
//      
//    @Test
//    public void testGetListCardTransactionDTO(){
//        
//        LinkedList<CardTransaction> list = new LinkedList<>();
//        
//        CardTransaction t1 = new CardTransaction();
//        t1.setTransaction_type(TransactionType.WITHDRAW);
//        t1.setDatetime(new Date());
//        t1.setResult_code(TransactionResultCode.APPROVED);
//        t1.setAmount(BigDecimal.valueOf(1.23));        
//        list.add(t1);        
//        
//        CardTransaction t2 = new CardTransaction();
//        t2.setTransaction_type(TransactionType.WITHDRAW);
//        t2.setDatetime(new Date());
//        t2.setResult_code(TransactionResultCode.INVALID_ACCOUNT);
//        t2.setAmount(BigDecimal.valueOf(56.32));        
//        list.add(t2);        
//        
//        //======
//        
//        List<CardTransactionDTO> list_dto = cardDomain.getListCardTransactionDTO(list);
//        
//        Assert.assertNotNull(list_dto);        
//        Assert.assertTrue(list_dto.size() == 2);
//        
//        Assert.assertNotNull(list_dto.get(0));
//        Assert.assertEquals("56,32", list_dto.get(0).getAmount());
//        Assert.assertTrue(Util.convertDate(t2.getDatetime()).compareTo(list_dto.get(0).getDate()) == 0);
//        
//        Assert.assertNotNull(list_dto.get(1));        
//        Assert.assertEquals("1,23", list_dto.get(1).getAmount());
//        Assert.assertTrue(Util.convertDate(t1.getDatetime()).compareTo(list_dto.get(1).getDate()) == 0);
//    }
//    
//     @Test
//    public void testArredondadmento(){
//        
//        
//        CardTransaction t1 = new CardTransaction();
//        t1.setTransaction_type(TransactionType.WITHDRAW);
//        t1.setDatetime(new Date());
//        t1.setResult_code(TransactionResultCode.APPROVED);
//        t1.setAmount(BigDecimal.valueOf(1.234));        
//        
//        CardTransactionDTO dto1 = cardDomain.getCardTransactionDTO(t1);
//        Assert.assertNotNull(dto1);
//        Assert.assertEquals("1,23", dto1.getAmount());
//                
//        //=====
//        
//        CardTransaction t2 = new CardTransaction();
//        t2.setTransaction_type(TransactionType.WITHDRAW);
//        t2.setDatetime(new Date());
//        t2.setResult_code(TransactionResultCode.INVALID_ACCOUNT);
//        t2.setAmount(BigDecimal.valueOf(56.326));       
//        
//        CardTransactionDTO dto2 = cardDomain.getCardTransactionDTO(t2);
//        Assert.assertNotNull(dto2);
//        Assert.assertEquals("56,33", dto2.getAmount());
//    }
//    
//}
