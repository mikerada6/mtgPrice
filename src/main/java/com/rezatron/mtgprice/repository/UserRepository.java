package com.rezatron.mtgprice.repository;

import com.rezatron.mtgprice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public
interface UserRepository extends JpaRepository<User, String> {
}
