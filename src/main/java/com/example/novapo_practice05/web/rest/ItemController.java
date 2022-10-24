package com.example.novapo_practice05.web.rest;

import com.example.novapo_practice05.service.ItemService;
import com.example.novapo_practice05.service.dto.Item.GetItemDTO;
import com.example.novapo_practice05.service.dto.Item.ItemDTO;
import com.example.novapo_practice05.service.dto.Item.ResponseItemDTO;
import com.example.novapo_practice05.service.dto.Pagination.ResponsePaginationDTO;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @PostMapping()
    @RolesAllowed("ROLE_ADMIN")
    public ResponseItemDTO createItem(@RequestBody ItemDTO itemDTO) {
        return itemService.createItem(itemDTO);
    }

    @GetMapping()
    public ResponsePaginationDTO<ResponseItemDTO> getItems(@Valid GetItemDTO getItemDTO) {
        System.out.println(getItemDTO);
        return itemService.getItems(getItemDTO);
    }

    @PutMapping("/{itemID}")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseItemDTO updateItem (@RequestBody ItemDTO itemDTO, @PathVariable long itemID) {
        return itemService.updateItem(itemDTO,itemID);
    }

    @DeleteMapping("/{itemID}")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseItemDTO deleteItem (@PathVariable long itemID) {
        return itemService.deleteItem(itemID);
    }

}
