package com.rezatron.mtgprice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Table( name = "Deck" )
@ToString
@EqualsAndHashCode( exclude = {"id"} )
//@Entity
public
class Deck {
    @Id
    private String id;
    @NotEmpty
    @NotNull
    private String name;


}
