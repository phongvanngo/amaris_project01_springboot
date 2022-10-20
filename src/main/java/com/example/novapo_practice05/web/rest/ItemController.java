package com.example.novapo_practice05.web.rest;

import com.example.novapo_practice05.service.ItemService;
import com.example.novapo_practice05.service.dto.Item.ItemDTO;
import com.example.novapo_practice05.service.dto.Item.ResponseItemDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @PostMapping()
    public ResponseItemDTO createItem(@RequestBody ItemDTO itemDTO) {
        return itemService.createItem(itemDTO);
    }

    @GetMapping()
    public List<ResponseItemDTO> getAllItems() {
        List<ResponseItemDTO> results = itemService.getAllItems();
        System.out.println("hello");
        return results;
//        return itemService.getAllItems();
    }

    @PutMapping("/{itemID}")
    public ResponseItemDTO updateItem (@RequestBody ItemDTO itemDTO, @PathVariable long itemID) {
        return itemService.updateItem(itemDTO,itemID);
    }

    @DeleteMapping("/{itemID}")
    public ResponseItemDTO deleteItem (@PathVariable long itemID) {
        return itemService.deleteItem(itemID);
    }

}