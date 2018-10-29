//package br.com.hubfintech.desafio.model.repository;
//
//import br.com.hubfintech.desafio.model.entity.Card;
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Optional;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
///**
// * Teste do Repositorio de Cartoes
// * @author andre
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class CardRepositoryTest {
//    
//    @Autowired
//    CardRepository cardRepository;
//    
//    //==========================================================================
//    
//    @Test
//    public void test() {
//        
//         for(int i=3; i<=10; i++){
//             
//            Card c = new Card();
//            c.setCardnumber("1234567890123456" + i);
//            c.setAvailableAmount(BigDecimal.valueOf(i));
//            
//            Card c_saved = cardRepository.saveCard(c);
//            
//            assertEquals(c_saved.getCardnumber(), c.getCardnumber());
//            assertEquals(c_saved.getAvailableAmount(), c.getAvailableAmount());
//        }
//         
//        List<Card> cards = cardRepository.getAllCards();
//         
//        assertNotNull(cards);
//        
////        System.out.println("===============================");
////        for(Card cd : cards) 
////            System.out.println(cd);        
////        System.out.println("===============================");
//        
//        Optional<Card> c_id_1 = cardRepository.getCardById(3L);
//        
//        assertTrue(c_id_1.isPresent());
//        assertEquals("12345678901234563", c_id_1.get().getCardnumber());
//        assertEquals(new BigDecimal("3.00"), c_id_1.get().getAvailableAmount());
//    }
//    
//     @Test
//    public void test2() {
//        
//         for(int i=0; i<10; i++){
//             
//            Card c = new Card();
//            c.setCardnumber("1234567890123456" + i);
//            c.setAvailableAmount(BigDecimal.valueOf(i));
//            
////            LinkedList<CardTransaction> transactions = new LinkedList<>();
////            
////            CardTransaction t1 = new CardTransaction();
////            t1.setTransaction_type(TransactionType.WITHDRAW);
////            t1.setDatetime(new Date());
////            t1.setResult_code(TransactionResultCode.APROVADA);
////            t1.setAmount(new BigDecimal(1.99));
////            transactions.add(t1);
////            
////            CardTransaction t2 = new CardTransaction();
////            t2.setTransaction_type(TransactionType.WITHDRAW);
////            t2.setDatetime(new Date());
////            t2.setResult_code(TransactionResultCode.ERRO_PROCESSAMENTO);
////            t2.setAmount(new BigDecimal(400.78));
////            transactions.add(t2);
//            
////            c.setTransactions(transactions);
//            
//            Card c_saved = cardRepository.saveCard(c);
//            
//            assertEquals(c_saved.getCardnumber(), c.getCardnumber());
//            assertEquals(c_saved.getAvailableAmount(), c.getAvailableAmount());
//            
//            
////             assertNotNull(c_saved.getTransactions());
////            assertTrue(c_saved.getTransactions().size() == 2);
////            
////            assertEquals(TransactionResultCode.APROVADA, c_saved.getTransactions().get(0).getResult_code());
////            assertEquals(TransactionType.WITHDRAW, c_saved.getTransactions().get(0).getTransaction_type());
////            
////            assertEquals(TransactionResultCode.ERRO_PROCESSAMENTO, c_saved.getTransactions().get(1).getResult_code());
////            assertEquals(TransactionType.DEPOSIT, c_saved.getTransactions().get(1).getTransaction_type());
//        }
//         
//        List<Card> cards = cardRepository.getAllCards();
//         
//        assertNotNull(cards);
//        assertEquals(20, cards.size());
//        
////        System.out.println("===============================");
////        for(Card cd : cards) 
////            System.out.println(cd);        
////        System.out.println("===============================");
//        
//        Optional<Card> c_id_1 = cardRepository.getCardById(3L);
//        
//        assertTrue(c_id_1.isPresent());
//        assertEquals("12345678901234563", c_id_1.get().getCardnumber());
//        assertEquals(new BigDecimal("3.00"), c_id_1.get().getAvailableAmount());
//    }
//}
