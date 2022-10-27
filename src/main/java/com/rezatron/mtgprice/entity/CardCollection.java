package com.rezatron.mtgprice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document( collection = "cardCollections" )
public
class CardCollection {
    private String id;
    private String name;
    private String mtgSet;
    private String mtgSetName;
    private String inDeck;
    private boolean isFoil;
    private String language;
    private String tags;
    private String collectorNumber;
    @CreatedDate
    public LocalDateTime createDate;
    @LastModifiedDate
    public LocalDateTime lastModifiedDate;
}
