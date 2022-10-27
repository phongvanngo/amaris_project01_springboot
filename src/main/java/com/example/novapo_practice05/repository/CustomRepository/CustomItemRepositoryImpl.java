package com.example.novapo_practice05.repository.CustomRepository;

import com.example.novapo_practice05.domain.QCatalog;
import com.example.novapo_practice05.domain.QItem;
import com.example.novapo_practice05.service.dto.Item.ResponseItemDTO;
import com.example.novapo_practice05.service.dto.Item.SearchItemDTO;
import com.example.novapo_practice05.service.dto.Pagination.ResponsePaginationDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class CustomItemRepositoryImpl implements CustomItemRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public ResponsePaginationDTO<ResponseItemDTO> searchByCriterias(SearchItemDTO searchItemDTO) {
        JPAQuery<ResponseItemDTO> query = new JPAQuery<>(entityManager);
        QItem item = QItem.item;
        QCatalog catalog = QCatalog.catalog;

        query
            .from(item)
            .join(catalog)
            .on(item.catalogID.eq(catalog.id))
            .orderBy(item.name.asc())
        ;

        if (Optional.ofNullable(searchItemDTO.getName()).isPresent()) {
            query.where(item.name.like('%'+searchItemDTO.getName()+'%'));
        }

        if (Optional.ofNullable(searchItemDTO.getDescription()).isPresent()) {
            query.where(item.description.like('%'+searchItemDTO.getDescription()+'%'));
        }

        if (Optional.ofNullable(searchItemDTO.getCatalogID()).isPresent()) {
            query.where(item.catalogID.eq(searchItemDTO.getCatalogID()));
        }

        int page = searchItemDTO.getPage();
        int limit = searchItemDTO.getLimit();


        int count = (int)query.stream().count();

        int totalPages =  count / limit + (count % limit == 0 ? 0: 1);

        if (page > totalPages - 1) {
            page = totalPages - 1;
        }

        if (page < 0) {
            page = 0;
        }

        query.offset(page).limit(limit);
        List<ResponseItemDTO> items = query.select(
                Projections.fields(ResponseItemDTO.class, item.id, catalog.id.as("catalogID"), item.name.as("name"),
                    catalog.name.as("catalogName")))
            .fetch();

        ResponsePaginationDTO<ResponseItemDTO> response = new ResponsePaginationDTO<>();

        return response.setPage(page).setLimt(limit).setTotalPage(totalPages)
            .setData(items).setCount(count);

    }
}
