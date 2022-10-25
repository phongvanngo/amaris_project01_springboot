package com.example.novapo_practice05.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.novapo_practice05.domain.Catalog;

public interface CatalogRepository extends JpaRepository<Catalog,Long> {
}
