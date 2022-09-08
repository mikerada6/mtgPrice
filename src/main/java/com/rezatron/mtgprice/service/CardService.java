package com.rezatron.mtgprice.service;

import com.rezatron.mtgprice.dto.magic.Card;
import com.rezatron.mtgprice.dto.magic.CardFace;
import com.rezatron.mtgprice.dto.magic.CardFaceImages;
import com.rezatron.mtgprice.dto.magic.Images;
import com.rezatron.mtgprice.dto.magic.Price;
import com.rezatron.mtgprice.dto.magic.scryfall.ImageUris;
import com.rezatron.mtgprice.dto.magic.scryfall.ScryfallCard;
import com.rezatron.mtgprice.dto.magic.scryfall.ScryfallCardFace;
import com.rezatron.mtgprice.dto.magic.wizards.Color;
import com.rezatron.mtgprice.dto.magic.wizards.Rarity;
import com.rezatron.mtgprice.repository.CardFaceRepository;
import com.rezatron.mtgprice.repository.CardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public
class CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CardFaceRepository cardFaceRepository;

    @Transactional( readOnly = true )
    public
    Card findById(String id) {
        log.info("findById {}.",
                 id);
        return cardRepository.findById(id).orElse(null);
    }

    public
    Card updateCard(ScryfallCard scryfallCard, Optional<LocalDateTime> optionalTimestamp)
    {
        if (!scryfallCard.getGames().contains( "paper" )) {
            log.trace( "{} is not a paper card so it was not saved.",
                       scryfallCard.getId() );
            return null;
        }
        if (!scryfallCard.getLangauage().equalsIgnoreCase( "en" )) {
            log.trace( "{} is not an english card so it was not saved.",
                       scryfallCard.getId() );
            return null;
        }
        LocalDateTime timestamp = optionalTimestamp.orElse( LocalDateTime.now() );
        Card card = cardRepository.findById( scryfallCard.getId() ).orElse( new Card() );
        card.setId( scryfallCard.getId() );
        card.setName( scryfallCard.getName() );
        card.setMtgSet( scryfallCard.getSet() );
        card.setMtgSetName( scryfallCard.getSetName() );
        card.setCollectorNumber( scryfallCard.getCollectorNumber() );
        card.setCmc( scryfallCard.getCmc() );
        card.setReleasedAt( LocalDate.parse( scryfallCard.getReleasedAt() ) );
        card.setTypeLine( scryfallCard.getTypeLine() );
        card.setRarity( Rarity.fromShortName( scryfallCard.getRarity() ) );
        card.setLanguage( scryfallCard.getLangauage() );
        card.setOracleId( scryfallCard.getOracleId() );
        card.setOracleText( scryfallCard.getOracleText() );
        if (scryfallCard.getColors() != null) {
            card.setColors( scryfallCard.getColors().stream().map( c -> Color.getFromLabel( c ) )
                                        .collect( Collectors.toSet() ) );
        }
        if (scryfallCard.getColorIdentity() != null) {
            card.setColorIdentity( scryfallCard.getColorIdentity().stream().map( c -> Color.getFromLabel( c ) )
                                               .collect( Collectors.toSet() ) );
        }
        Set<Price> prices = card.getPrices();
        if (prices == null) {
            prices = new HashSet<>();
        }

        prices.add( Price.builder().usd( scryfallCard.getPrices().getUsd() )
                         .usdFoil( scryfallCard.getPrices().getUsdFoil() )
                         .usdEtched( scryfallCard.getPrices().getUsdEtched() ).eur( scryfallCard.getPrices().getEur() )
                         .eurFoil( scryfallCard.getPrices().getEurFoil() )

                         .tix( scryfallCard.getPrices().getTix() ).timestamp( timestamp ).card( card ).build() );
        ImageUris temp = scryfallCard.getImageUris();
        if (temp != null) {
            card.setImages( Images.builder().artCrop( temp.getArtCrop() ).borderCrop( temp.getBorderCrop() )
                                  .large( temp.getLarge() ).normal( temp.getNormal() ).png( temp.getPng() )
                                  .small( temp.getSmall() ).card( card ).build() );
        }


        if (scryfallCard.getCardFaces() != null) {
            Set<CardFace> cardFaces = new HashSet();
            List<ScryfallCardFace> scryfallCardFaces = scryfallCard.getCardFaces().stream().sorted()
                                                                   .collect( Collectors.toList() );
            CardFace cardFaceA = cardFaceRepository.findById( card.getId() + "-A" ).orElse( new CardFace() );
            CardFace cardFaceB = cardFaceRepository.findById( card.getId() + "-B" ).orElse( new CardFace() );

            cardFaceA.setId( card.getId() + "-A" );
            cardFaceA.setName( scryfallCardFaces.get( 0 ).getName() );
            cardFaceA.setManaCost( scryfallCardFaces.get( 0 ).getManaCost() );
            cardFaceA.setTypeLine( scryfallCardFaces.get( 0 ).getTypeLine() );
            if (scryfallCardFaces.get( 0 ).getColors() != null) {
                cardFaceA.setColors( scryfallCardFaces.get( 0 ).getColors().stream().map( c -> Color.getFromLabel( c ) )
                                                      .collect( Collectors.toSet() ) );
            }
            cardFaceA.setCard( card );
            ImageUris temp1 = scryfallCardFaces.get( 0 ).getImageUris();
            if (temp1 != null) {
                cardFaceA.setImages( CardFaceImages.builder().artCrop( temp1.getArtCrop() )
                                                   .borderCrop( temp1.getBorderCrop() ).large( temp1.getLarge() )
                                                   .normal( temp1.getNormal() ).png( temp1.getPng() )
                                                   .small( temp1.getSmall() ).cardFace( cardFaceA ).build() );
            }

            cardFaceB.setId( card.getId() + "-B" );
            cardFaceB.setName( scryfallCardFaces.get( 1 ).getName() );
            cardFaceB.setManaCost( scryfallCardFaces.get( 1 ).getManaCost() );
            cardFaceB.setTypeLine( scryfallCardFaces.get( 1 ).getTypeLine() );
            if (scryfallCardFaces.get( 1 ).getColors() != null) {
                cardFaceB.setColors( scryfallCardFaces.get( 1 ).getColors().stream().map( c -> Color.getFromLabel( c ) )
                                                      .collect( Collectors.toSet() ) );
            }

            ImageUris temp2 = scryfallCardFaces.get( 1 ).getImageUris();
            if (temp2 != null) {
                cardFaceB.setImages( CardFaceImages.builder().artCrop( temp2.getArtCrop() )
                                                   .borderCrop( temp2.getBorderCrop() ).large( temp2.getLarge() )
                                                   .normal( temp2.getNormal() ).png( temp2.getPng() )
                                                   .small( temp2.getSmall() ).cardFace( cardFaceB ).build() );
            }
            cardFaceA.setCard( card );
            cardFaceB.setCard( card );
            //TODO take care of flip side images
            cardFaces.add( cardFaceA );
            cardFaces.add( cardFaceB );
            card.setCardFaces( cardFaces );
        }

        return card;
    }

    public
    List<Card> saveAll(List<Card> cardsToSave) {
        return cardRepository.saveAllAndFlush( cardsToSave );
    }

    public
    List<String> getSuperTypes() {
        List<String> typeLines = cardRepository.findDistinctTypeLines().stream().filter( t -> t != null )
                                               .collect( Collectors.toList() );
        Set<String> superTypes = new HashSet<>();
        for (String typeLine : typeLines) {
            String[] split = typeLine.split( "//" );
            for (String splitTypeLine : split) {
                String[] superType = splitTypeLine.split( " — " );
                superTypes.add( superType[0].trim() );
            }
       }
        return superTypes.stream().sorted().collect( Collectors.toList() );
    }
}
