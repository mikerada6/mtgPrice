package com.rezatron.mtgprice.repository;

import com.rezatron.mtgprice.entity.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public
interface InventoryRepository extends MongoRepository<Inventory, String> {
    List<Inventory> findByUser_Id(String id);


}
