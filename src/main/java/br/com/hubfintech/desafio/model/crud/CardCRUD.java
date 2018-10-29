
package br.com.hubfintech.desafio.model.crud;

import br.com.hubfintech.desafio.model.entity.Card;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface para o CRUD de Cartao
 * @author andre.rosa
 */
@Transactional
public interface CardCRUD extends CrudRepository<Card, Long>{
}
