package com.rezatron.mtgprice.service;

import com.rezatron.mtgprice.dto.InventoryDto;
import com.rezatron.mtgprice.dto.magic.Card;
import com.rezatron.mtgprice.inventory.BulkInventory;
import com.rezatron.mtgprice.inventory.Inventory;
import com.rezatron.mtgprice.mapper.InventoryMapper;
import com.rezatron.mtgprice.repository.CardRepository;
import com.rezatron.mtgprice.repository.InventoryRepository;
import com.rezatron.mtgprice.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public
class InventoryService {

    @Autowired
    InventoryMapper inventoryMapper;
    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    CardRepository cardRepository;

    public
    InventoryDto addCard(InventoryDto dto) {
        Inventory inventory = inventoryMapper.inventoryDtoToInventory( dto );
        if (inventory.getCard() != null && inventory.getUser() != null) {
            inventory = inventoryRepository.saveAndFlush( inventory );
            return inventoryMapper.inventoryToInventoryDto( inventory );
        }
        return null;
    }

    public
    InventoryDto testing(String setName) {
        List<Card> cards = cardRepository.findByMtgSetOrderByCollectorNumberAsc( setName ).stream().sorted()
                                         .collect( Collectors.toList() );
        String ans = "Id\tName\tSet\tCollector Number\tNormal\tfoil\n";
        for (Card card : cards) {
            ans += card.getId() + "\t" + card.getName() + "\t" + card.getMtgSet() + "\t" + card.getCollectorNumber()
                   + "\t\t\n";
        }
        System.out.println( ans );
        return null;
    }

    public
    List<Inventory> addCards(List<BulkInventory> cards, User user) {
        List<BulkInventory> allCards = cards.stream().filter( c -> c.getNormal() > 0 || c.getFoil() > 0 )
                                            .collect( Collectors.toList() );
        ArrayList<Inventory> toSave = new ArrayList<>();
        for (BulkInventory bi : allCards) {
            Card c = cardRepository.findById( bi.getCardId() ).orElse( null );
            if (c == null) {
                log.error( "CardID {} was not found and will not be added.",
                           bi.getCardId() );
                continue;
            }
            for(int i=0;i<bi.getNormal();i++) {
                toSave.add(Inventory.builder().card( c ).foil( false ).user(user).build());
            }
            for(int i=0;i<bi.getFoil();i++) {
                toSave.add(Inventory.builder().card( c ).foil( true ).user(user).build());
            }
        }
        List<Inventory> saved = inventoryRepository.saveAll( toSave );
        return saved;
    }

    public
    ArrayList<BulkInventory> getAll() {
        List<Inventory> allCardsInIventory = inventoryRepository.findAll();
        Map<String, Long> normal = allCardsInIventory.stream().filter( i -> !i.isFoil() )
                                                     .collect( Collectors.groupingBy( Inventory::getCardId,
                                                                                      Collectors.counting() ) );
        Map<String, Long> foil = allCardsInIventory.stream().filter( i -> i.isFoil() )
                                                   .collect( Collectors.groupingBy( Inventory::getCardId,
                                                                                    Collectors.counting() ) );
        List<Card> cards = allCardsInIventory.stream().map( i -> i.getCard() ).distinct().sorted()
                                             .collect( Collectors.toList() );
        ArrayList<BulkInventory> ans = new ArrayList<>();
        for (Card c : cards) {
            BulkInventory bi = BulkInventory.builder().cardId( c.getId() ).cardName( c.getName() ).set( c.getMtgSet() )
                                            .colletorNumber( c.getCollectorNumber() ).build();
            if (normal.containsKey( c.getId() )) {
                bi.setNormal( normal.get( c.getId() ).intValue() );
            } else {
                bi.setNormal( 0 );
            }
            if (foil.containsKey( c.getId() )) {
                bi.setFoil( foil.get( c.getId() ).intValue() );
            } else {
                bi.setFoil( 0 );
            }
            ans.add( bi );
        }
        return ans;
    }
}
