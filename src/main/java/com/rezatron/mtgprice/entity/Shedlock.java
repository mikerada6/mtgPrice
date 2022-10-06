package com.rezatron.mtgprice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document( collection = "shedlock" )
public
class Shedlock {
    @Id
    @Size( max = 64 )
    private String id;
    private Instant lockUntil;
    private Instant lockedAt;
    @Size( max = 255 )
    private String lockedBy;

}
