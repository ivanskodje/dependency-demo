package com.ivanskodje.dependency.demo.dependencydemo.service;

import com.ivanskodje.dependency.demo.dependencydemo.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void saveItem(Item item) {
        itemRepository.saveItem(item);
    }

    public List<Item> findItems() {
        return itemRepository.findItems();
    }
}
