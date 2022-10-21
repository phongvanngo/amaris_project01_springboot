package com.example.novapo_practice05.repository.CustomRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UserNoVaPoRepositoryImpl implements UserNoVaPoRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public String generateUserId() {
        return "ABC";
    }
}
