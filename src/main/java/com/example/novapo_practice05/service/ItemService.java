package com.example.novapo_practice05.service;

import com.example.novapo_practice05.domain.Catalog;
import com.example.novapo_practice05.domain.Item;
import com.example.novapo_practice05.exception.ItemNotFoundException;
import com.example.novapo_practice05.repository.CatalogRepository;
import com.example.novapo_practice05.repository.ItemRepository;
import com.example.novapo_practice05.service.dto.Item.ItemDTO;
import com.example.novapo_practice05.service.dto.Item.ResponseItemDTO;
import com.example.novapo_practice05.service.mapper.ItemMapper;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    public final ItemRepository itemRepository;
    public final CatalogRepository catalogRepository;


    public final ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository, CatalogRepository catalogRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.catalogRepository = catalogRepository;
        this.itemMapper = itemMapper;
    }


    public ResponseItemDTO createItem(ItemDTO item) {

        Long catalogID = item.getCatalogID();

        Optional<Catalog> catalog = catalogRepository.findById(catalogID);

        if (catalog.isPresent()) {
            Item itemToCreate = itemMapper.toEntity(item);
            itemToCreate.setCatalog(catalog.get());
            Item newItem = itemRepository.save(itemToCreate);
            return itemMapper.toResponseDTO(newItem);
        }
        return null;
    }

    public ResponseItemDTO updateItem(ItemDTO item, Long itemID) {

        Optional<Item> existingItem = itemRepository.findById(itemID);

//        item don't exist
        if (existingItem.isEmpty()) {
            return null;
        }

//        item exists
        Item itemToUpdate = itemMapper.toEntity(item);
        itemToUpdate.setId(itemID);

        Optional<Long> catalogID = Optional.ofNullable(item.getCatalogID());

//        if update catalogID
        if (catalogID.isPresent()) {
            Optional<Catalog> catalog = catalogRepository.findById(catalogID.get());
            catalog.ifPresent(catalog1 -> itemToUpdate.setCatalog(catalog1));
        }
        else {
            itemToUpdate.setCatalog(existingItem.get().getCatalog());
        }

        Item newItem = itemRepository.save(itemToUpdate);
        return itemMapper.toResponseDTO(newItem);

    }

    public ResponseItemDTO deleteItem(long itemID) {
        Optional<Item> itemToDelete = itemRepository.findById(itemID);
        if (itemToDelete.isPresent()) {
            itemToDelete.get().setDeletedAt(LocalDateTime.now());
            Item item = itemRepository.save(itemToDelete.get());
            return itemMapper.toResponseDTO(item);
        }

        return null;
    }

    public List<ResponseItemDTO> getAllItems() {
        List<Item> items = itemRepository.findAll();
        System.out.println(items);
        List<ResponseItemDTO> results = new ArrayList<ResponseItemDTO>();
        for (Item item : items) {
            System.out.println(item);
            results.add(itemMapper.toResponseDTO(item));
        }
        System.out.println("done");
        return results;
    }



    public Item getbByID(long id){
        Optional<Item> item = itemRepository.findById(id);
        if(item.isPresent()) {
            return item.get();
        }
        else throw new ItemNotFoundException(id);

    }

}
