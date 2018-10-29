
package br.com.hubfintech.desafio.model.crud;

import br.com.hubfintech.desafio.model.entity.CardTransaction;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface para o CRUD de Transacoes de Cartao
 * @author andre.rosa
 */
@Transactional
public interface CardTransactionCRUD extends CrudRepository<CardTransaction, Long>{
}
