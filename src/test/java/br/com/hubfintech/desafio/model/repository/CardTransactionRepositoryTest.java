//package br.com.hubfintech.desafio.model.repository;
//
//import br.com.hubfintech.desafio.model.entity.CardTransaction;
//import br.com.hubfintech.desafio.model.entity.util.TransactionResultCode;
//import br.com.hubfintech.desafio.model.entity.util.TransactionType;
//import java.math.BigDecimal;
//import java.util.Date;
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
// * Teste do Repositorio de Transacoes de Cartoes
// * @author andre
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class CardTransactionRepositoryTest {
//    
//    @Autowired
//    CardTransactionRepository cardTransactionRepository;
//    
//    //==========================================================================
//    
//    @Test
//    public void test() {
//        
//         for(int i=0; i<10; i++){
//             
//            CardTransaction t = new CardTransaction();
//            t.setId(new Long(i));
//            t.setTransaction_type(TransactionType.WITHDRAW);
//            t.setDatetime(new Date());
//            t.setResult_code(TransactionResultCode.APPROVED);
//            t.setAmount(BigDecimal.valueOf(i));
//            
//            CardTransaction t_saved = cardTransactionRepository.saveCardTransaction(t);
//            
//            assertEquals(TransactionResultCode.APPROVED, t_saved.getResult_code());
//            assertEquals(TransactionType.WITHDRAW, t_saved.getTransaction_type());
//        }
//         
//        List<CardTransaction> transactions = cardTransactionRepository.getAllCardTransactions();
//         
//        assertNotNull(transactions);
//        
////        System.out.println("===============================");
////        for(CardTransaction ct : transactions) 
////            System.out.println(ct);        
////        System.out.println("===============================");
//        
//        Optional<CardTransaction> t_id_1 = cardTransactionRepository.getCardTransactionById(3L);
//        
//        assertTrue(t_id_1.isPresent());
//        assertEquals(TransactionResultCode.APPROVED, t_id_1.get().getResult_code());
//        assertEquals(TransactionType.WITHDRAW, t_id_1.get().getTransaction_type());
//        assertEquals(new BigDecimal("3.00"), t_id_1.get().getAmount());
//    }
//}
