package com.rezatron.mtgprice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Table( name = "Inventory" )
@ToString( exclude = {"user"} )
@EqualsAndHashCode( exclude = {"user"} )
//@Entity
@Slf4j
//@JsonPropertyOrder({ "userName", "firstName", "lastName" })
public
class Inventory {
    @Id
    @JsonIgnore
    private String id;


}
