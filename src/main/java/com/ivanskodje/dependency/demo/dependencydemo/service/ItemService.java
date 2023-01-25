package com.ivanskodje.dependency.demo.dependencydemo.service;

import com.ivanskodje.dependency.demo.dependencydemo.domain.Item;
import com.ivanskodje.dependency.demo.dependencydemo.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void saveItem(Item item){
        itemRepository.save(item);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }
}
