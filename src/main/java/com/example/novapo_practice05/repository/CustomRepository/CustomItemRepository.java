package com.example.novapo_practice05.repository.CustomRepository;

import com.example.novapo_practice05.domain.Item;
import java.util.List;

public interface CustomItemRepository {
    List<Item> searchByCriterias();
}
