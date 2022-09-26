package com.rezatron.mtgprice.repository;

import com.rezatron.mtgprice.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public
interface UserRepository extends MongoRepository<User, String> {
}
