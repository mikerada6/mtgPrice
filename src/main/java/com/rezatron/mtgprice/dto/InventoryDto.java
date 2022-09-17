package com.rezatron.mtgprice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public
class InventoryDto implements Serializable {
    @JsonIgnore
    private String id;
    private String cardId;
    private String userId;
    private boolean foil;
    @JsonIgnore
    private LocalDateTime createDateTime;
    @JsonIgnore
    private LocalDateTime updateDateTime;

    public
    boolean isFoil()
    {
        return foil;
    }

    public
    void setFoil(boolean foil) {
        this.foil = foil;
    }

}
