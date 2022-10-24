package com.example.novapo_practice05.repository;

import com.example.novapo_practice05.domain.Catalog;
import com.example.novapo_practice05.domain.Item;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CatalogRepository extends PagingAndSortingRepository<Catalog,Long>, JpaRepository<Catalog,Long> {
//    List<Catalog> findAllByPrice(double price, Pageable pageable);
}
