package com.example.novapo_practice05.repository;

import com.example.novapo_practice05.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {

}
