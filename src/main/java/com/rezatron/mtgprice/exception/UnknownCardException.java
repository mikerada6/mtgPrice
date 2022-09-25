package com.rezatron.mtgprice.exception;

public
class UnknownCardException extends DeckException {
    public
    UnknownCardException(String message) {
        super( message );
    }
}
