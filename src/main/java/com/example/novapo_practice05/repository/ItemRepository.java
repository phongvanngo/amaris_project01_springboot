package com.example.novapo_practice05.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.novapo_practice05.domain.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>,
        JpaSpecificationExecutor<Item> {

}
