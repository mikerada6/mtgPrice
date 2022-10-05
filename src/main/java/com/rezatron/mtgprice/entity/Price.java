package com.rezatron.mtgprice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document( collection = "prices" )
public
class Price {

    @Id
    private String id;
    private LocalDateTime timestamp;
    private Double eur;
    private Double eurFoil;
    private Double tix;
    private Double usd;
    private Double usdEtched;
    private Double usdFoil;

    public
    boolean worthSaving() {
        return eur != null || eurFoil != null || tix != null || usd != null || usdEtched != null || usdFoil != null;
    }
}
