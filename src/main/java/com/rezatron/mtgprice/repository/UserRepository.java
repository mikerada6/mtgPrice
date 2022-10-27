package com.rezatron.mtgprice.repository;

import com.rezatron.mtgprice.entity.user.User;
import com.rezatron.mtgprice.entity.wizards.Card;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public
interface UserRepository  extends MongoRepository<User, String> {


}
