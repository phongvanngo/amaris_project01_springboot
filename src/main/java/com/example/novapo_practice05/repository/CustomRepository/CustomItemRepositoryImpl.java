package com.example.novapo_practice05.repository.CustomRepository;

import com.example.novapo_practice05.domain.Item;
import com.example.novapo_practice05.domain.QItem;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class CustomItemRepositoryImpl implements  CustomItemRepository {


    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<Item> searchByCriterias() {
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        QItem item = QItem.item;
        return (List<Item>) query.from(item).fetch();
    }
}
