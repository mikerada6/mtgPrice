package com.rezatron.mtgprice.entity.user;

import com.rezatron.mtgprice.entity.CardCollection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document( collection = "users" )
public
class User {

    private String userName;
    private String password;
    private List<CardCollection> cardCollection;
    @CreatedDate
    public LocalDateTime createDate;
    @LastModifiedDate
    public LocalDateTime lastModifiedDate;
}
