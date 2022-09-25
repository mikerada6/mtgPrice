package com.rezatron.mtgprice.objectmother;

import com.rezatron.mtgprice.entity.wizards.Card;

public
class CardMother {

    static
    Card.CardBuilder singleSided()
    {
        return Card.builder().id( "0000579f-7b35-4ed3-b44c-db2a538066fe" )
                   .oracleId( "44623693-51d6-49ad-8cd7-140505caf02f" ).name( "Fury Sliver" ).language( "en" );
    }
}
