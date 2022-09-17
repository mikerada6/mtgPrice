package com.rezatron.mtgprice.repository;

import com.rezatron.mtgprice.inventory.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public
interface InventoryRepository extends JpaRepository<Inventory, String> {
    List<Inventory> findByUser_Id(String id);


}
