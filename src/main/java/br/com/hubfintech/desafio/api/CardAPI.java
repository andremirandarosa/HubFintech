package br.com.hubfintech.desafio.api;

import br.com.hubfintech.desafio.api.dto.CardDTO;
import br.com.hubfintech.desafio.config.Config;
import br.com.hubfintech.desafio.domain.CardDomain;
import br.com.hubfintech.desafio.domain.cache.CardCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Servico REST para Usuarios
 * @author andre.rosa
 */
@Api(value = Config.PATH_API + "/card", tags = "Card")
@RestController
public class CardAPI{
    
    @Autowired
    CardDomain cardDomain;
    
    //==========================================================================
    //==========================================================================
    
    @ApiOperation(
        value = "Card List",
        notes = "List of Cards.",
        response = CardDTO.class, 
        responseContainer = "List")
    @RequestMapping(method = RequestMethod.GET, 
                    value = Config.PATH_API + "/card/list", 
                    produces = "application/json")
    public ResponseEntity<List<CardDTO>> getAllCards(){
        
        List<CardDTO> cards = cardDomain.getListCardDTO(CardCache.getAllCards());
        
        if(cards != null) return new ResponseEntity(cards, HttpStatus.ACCEPTED);
        else return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
     @ApiOperation(
        value = "Card",
        notes = "Card info.",
        response = CardDTO.class)
    @RequestMapping(method = RequestMethod.GET, 
                    value = Config.PATH_API + "/card/{cardnumber}", 
                    produces = "application/json")
    public ResponseEntity<CardDTO> getCardByCardnumber(@ApiParam(value = "Card Number", required = true) @PathVariable("cardnumber") String cardnumber){
        
        CardDTO card = cardDomain.getCardDTO(CardCache.getCard(cardnumber));
        
        if(card != null) return new ResponseEntity(card, HttpStatus.ACCEPTED);
        else return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
