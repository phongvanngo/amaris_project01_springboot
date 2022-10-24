package com.example.novapo_practice05.repository;

import com.example.novapo_practice05.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long>, PagingAndSortingRepository<Item,Long>,
    JpaSpecificationExecutor<Item> {

}
