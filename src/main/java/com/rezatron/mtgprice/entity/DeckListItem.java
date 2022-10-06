package com.rezatron.mtgprice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Table( name = "DeckListItem" )
@ToString( exclude = {"deck"} )
@EqualsAndHashCode( exclude = {"id", "deck"} )
//@Entity
public
class DeckListItem {
    @Id
    private String id;
    @NotBlank
    @NotEmpty
    @NotNull
    private String cardName;
    @NotBlank
    @NotEmpty
    @NotNull
    private String oracle_id;

}
