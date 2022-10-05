package com.rezatron.mtgprice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Builder
@ToString()
@Document( collection = "prices" )
@Slf4j
@JsonInclude( JsonInclude.Include.NON_NULL)
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
