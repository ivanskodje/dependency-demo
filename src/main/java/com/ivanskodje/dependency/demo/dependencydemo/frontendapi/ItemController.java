package com.ivanskodje.dependency.demo.dependencydemo.frontendapi;


import com.ivanskodje.dependency.demo.dependencydemo.domain.Item;
import com.ivanskodje.dependency.demo.dependencydemo.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/item")
    public ResponseEntity<Void> saveItem(@RequestBody Item item) {
        itemService.saveItem(item);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/item")
    public ResponseEntity<List<Item>> findItems() {
        List<Item> items = itemService.findAll();
        return ResponseEntity.ok(items);
    }
}
