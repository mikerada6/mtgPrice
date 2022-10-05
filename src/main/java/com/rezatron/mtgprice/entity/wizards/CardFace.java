package com.rezatron.mtgprice.entity.wizards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.rezatron.mtgprice.dto.magic.wizards.Color;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Table( name = "Card_Face" )
@ToString( exclude = {"card"} )
@EqualsAndHashCode( exclude = {"card"} )
//@Entity
@Slf4j
@JsonPropertyOrder( alphabetic = true )
public
class CardFace {


    Set<Color> colors;
    @Id
    @JsonIgnore
    private String id;
    private String manaCost;
    private String name;
    private String typeLine;

}
