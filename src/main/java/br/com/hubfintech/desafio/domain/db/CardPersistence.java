package br.com.hubfintech.desafio.domain.db;

import br.com.hubfintech.desafio.domain.CardDomain;
import br.com.hubfintech.desafio.domain.CardTransactionDomain;
import br.com.hubfintech.desafio.model.entity.Card;
import br.com.hubfintech.desafio.model.entity.CardTransaction;
import java.util.LinkedList;

/**
 * Gerenciador Assincrono de Persistencia no DB
 * @author andre
 */
public class CardPersistence extends Thread{
    
    CardDomain cardDomain;
    
    CardTransactionDomain cardTransactionDomain;

    /** Fila para Processamento do Salvamento no DB */
    static public LinkedList<Object> PROCESSING_QUEUE = new LinkedList<>();
    
    //==========================================================================
    
    public CardPersistence(CardDomain cardDomain, CardTransactionDomain cardTransactionDomain){
        
        this.cardDomain = cardDomain;
        
        this.cardTransactionDomain = cardTransactionDomain;
        
        System.out.println("============================================");
        System.out.println("Inicializando Processamento de Persistencia...");
    }

    /***
     * Adiciona Objeto para Processamento na Fila
     * @param obj Objeto a ser processado
     */
    static public void addToPresistence(Object obj){
        
        PROCESSING_QUEUE.add(obj);
    }

    @Override
    public void run() {
        
        while(true){

            if(PROCESSING_QUEUE.size() > 0){

                Object obj = PROCESSING_QUEUE.poll();

                if(obj != null){

                    //Aguarda Salvamento no DB
                    while(!saveDB(obj)){                

                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException ex) {
                            System.out.println("Erro na espera do salvamento no DB.");
                        }
                    }
                }

            }else{

                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    System.out.println("Erro na espera do salvamento no DB.");                    
                }
            }
        }
    }

    /***
     * Salva Cartao no DB
     * @param obj Objeto a ser salvo
     * @return Flag de sucesso
     */
    private boolean saveDB(Object obj){

        try{
            if(obj instanceof Card) cardDomain.saveCard((Card) obj);
            else if(obj instanceof CardTransaction) cardTransactionDomain.saveCardTransaction((CardTransaction) obj);
            
            return true;

        }catch(Exception e){}
        
        return false;
    }
}
