package com.rezatron.mtgprice.entity.wizards;

import com.rezatron.mtgprice.dto.magic.wizards.Rarity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Builder
@ToString()
@AllArgsConstructor
@NoArgsConstructor
//@Document( collection = "cards" )
@Slf4j
public
class Printing {
    @Id
    private String id;
    private String mtgSet;
    private String mtgSetName;
    private LocalDate releasedAt;
    private String collectorNumber;
    private Rarity rarity;
}
