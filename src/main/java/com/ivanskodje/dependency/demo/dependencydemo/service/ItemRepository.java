package com.ivanskodje.dependency.demo.dependencydemo.service;

import com.ivanskodje.dependency.demo.dependencydemo.domain.Item;

import java.util.List;

public interface ItemRepository {

    List<Item> findItems();

    void saveItem(Item item);
}
