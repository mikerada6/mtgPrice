package com.rezatron.mtgprice.repository;

import com.rezatron.mtgprice.inventory.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public
interface InventoryRepository extends JpaRepository<Inventory, String> {
}
