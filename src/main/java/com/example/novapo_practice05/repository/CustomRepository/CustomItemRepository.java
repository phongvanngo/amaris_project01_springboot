package com.example.novapo_practice05.repository.CustomRepository;

import com.example.novapo_practice05.domain.Item;
import com.example.novapo_practice05.service.dto.Item.ResponseItemDTO;
import com.example.novapo_practice05.service.dto.Item.SearchItemDTO;
import com.example.novapo_practice05.service.dto.Pagination.ResponsePaginationDTO;
import java.util.List;

public interface CustomItemRepository {
    ResponsePaginationDTO<ResponseItemDTO> searchByCriterias(SearchItemDTO searchItemDTO);
}
