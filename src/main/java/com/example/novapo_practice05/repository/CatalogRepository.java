package com.example.novapo_practice05.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.novapo_practice05.domain.Catalog;

public interface CatalogRepository extends PagingAndSortingRepository<Catalog,Long>, JpaRepository<Catalog,Long> {
//    List<Catalog> findAllByPrice(double price, Pageable pageable);
}
