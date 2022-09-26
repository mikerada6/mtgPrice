package com.rezatron.mtgprice.service;

import com.rezatron.mtgprice.dto.LegalStatus;
import com.rezatron.mtgprice.dto.magic.scryfall.ImageUris;
import com.rezatron.mtgprice.dto.magic.scryfall.ScryfallCard;
import com.rezatron.mtgprice.dto.magic.scryfall.ScryfallCardFace;
import com.rezatron.mtgprice.dto.magic.wizards.Color;
import com.rezatron.mtgprice.dto.magic.wizards.Rarity;
import com.rezatron.mtgprice.entity.Legalities;
import com.rezatron.mtgprice.entity.wizards.Card;
import com.rezatron.mtgprice.entity.wizards.CardFace;
import com.rezatron.mtgprice.entity.wizards.CardFaceImages;
import com.rezatron.mtgprice.entity.wizards.Images;
import com.rezatron.mtgprice.repository.CardFaceRepository;
import com.rezatron.mtgprice.repository.CardRepository;
import com.rezatron.mtgprice.repository.OracleCardRepository;
import com.rezatron.mtgprice.repository.PriceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
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



    @Autowired
    private PriceRepository priceRepository;

    @Transactional( readOnly = true )
    public
    Card findById(String id) {
        log.info( "findById {}.",
                  id );
        return cardRepository.findById( id ).orElse( null );
    }

    @Transactional( readOnly = true )
    public
    List<Card> findByIdIn(Collection<String> ids) {
        log.info( "cardfindByIdIn {}.",
                  ids.size() );
        return cardRepository.findByIdIn( ids );
    }


    public
    Card updateCard(ScryfallCard scryfallCard, Optional<LocalDateTime> optionalTimestamp, Optional<Card> optionalCard)
    {
        LocalDateTime timestamp = optionalTimestamp.orElse( LocalDateTime.now() );
        Card card = optionalCard.orElse( new Card() );
        card.setId( scryfallCard.getId() );
        card.setName( scryfallCard.getName() );
        card.setMtgSet( scryfallCard.getSet() );
        card.setMtgSetName( scryfallCard.getSetName() );
        card.setCollectorNumber( scryfallCard.getCollectorNumber() );
        card.setCmc( scryfallCard.getCmc() );
        card.setReleasedAt( LocalDate.parse( scryfallCard.getReleasedAt() ) );
        card.setTypeLine( scryfallCard.getTypeLine() );
        card.setRarity( Rarity.fromShortName( scryfallCard.getRarity() ) );
        card.setLanguage( scryfallCard.getLanguage() );
        card.setOracleId( scryfallCard.getOracleId() );
        card.setOracleText( scryfallCard.getOracleText() );
        card.setLanguage( scryfallCard.getLanguage() );
        com.rezatron.mtgprice.dto.magic.scryfall.Legalities tempLegalities = scryfallCard.getLegalities();
        Legalities newLegalities = Legalities.builder().brawl( LegalStatus.getFromLabel( tempLegalities.getBrawl() ) )
                                             .alchemy( LegalStatus.getFromLabel( tempLegalities.getAlchemy() ) )
                                             .commander( LegalStatus.getFromLabel( tempLegalities.getCommander() ) )
                                             .duel( LegalStatus.getFromLabel( tempLegalities.getDuel() ) )
                                             .explorer( LegalStatus.getFromLabel( tempLegalities.getExplorer() ) )
                                             .future( LegalStatus.getFromLabel( tempLegalities.getFuture() ) )
                                             .gladiator( LegalStatus.getFromLabel( tempLegalities.getGladiator() ) )
                                             .historic( LegalStatus.getFromLabel( tempLegalities.getHistoric() ) )
                                             .historicbrawl( LegalStatus.getFromLabel( tempLegalities.getHistoricbrawl() ) )
                                             .legacy( LegalStatus.getFromLabel( tempLegalities.getLegacy() ) )
                                             .modern( LegalStatus.getFromLabel( tempLegalities.getModern() ) )
                                             .oldschool( LegalStatus.getFromLabel( tempLegalities.getOldschool() ) )
                                             .pauper( LegalStatus.getFromLabel( tempLegalities.getPauper() ) )
                                             .paupercommander( LegalStatus.getFromLabel( tempLegalities.getPaupercommander() ) )
                                             .penny( LegalStatus.getFromLabel( tempLegalities.getPenny() ) )
                                             .pioneer( LegalStatus.getFromLabel( tempLegalities.getPioneer() ) )
                                             .premodern( LegalStatus.getFromLabel( tempLegalities.getPremodern() ) )
                                             .standard( LegalStatus.getFromLabel( tempLegalities.getStandard() ) )
                                             .vintage( LegalStatus.getFromLabel( tempLegalities.getVintage() ) )
                                             .build();
//        card.setLegalities( newLegalities );
        if (scryfallCard.getColors() != null) {
            card.setColors( scryfallCard.getColors().stream().map( c -> Color.getFromLabel( c ) )
                                        .collect( Collectors.toSet() ) );
        }
        if (scryfallCard.getColorIdentity() != null) {
            card.setColorIdentity( scryfallCard.getColorIdentity().stream().map( c -> Color.getFromLabel( c ) )
                                               .collect( Collectors.toSet() ) );
        }

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
            cardFaces.add( cardFaceA );
            cardFaces.add( cardFaceB );
            card.setCardFaces( cardFaces );
        }

        return card;
    }

    @Transactional
    public
    List<Card> saveAll(List<Card> cardsToSave) {
        return cardRepository.saveAll( cardsToSave );
    }

    @Transactional
    public
    String getOracleIdFromName(String cardName) {
        Optional<Card> card = cardRepository.findFirstByName( cardName );
        if (card.isPresent()) {
            return card.get().getOracleId();
        } else {
            Optional<CardFace> cardFace = cardFaceRepository.findFirstByName( cardName );
            if (cardFace.isPresent()) {
                return cardFace.get().getCard().getOracleId();
            }
        }
        return null;
    }

//    public
//    List<String> getSuperTypes() {
//        List<String> typeLines = cardRepository.findDistinctTypeLines().stream().filter( t -> t != null )
//                                               .collect( Collectors.toList() );
//        Set<String> superTypes = new HashSet<>();
//        for (String typeLine : typeLines) {
//            String[] split = typeLine.split( "//" );
//            for (String splitTypeLine : split) {
//                String[] superType = splitTypeLine.split( " — " );
//                superTypes.add( superType[0].trim() );
//            }
//        }
//        return superTypes.stream().sorted().collect( Collectors.toList() );
//    }

    public
    Card save(Card tempCard) {
        return cardRepository.save( tempCard );
    }
}
