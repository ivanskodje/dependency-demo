package com.ivanskodje.dependency.demo.dependencydemo.repository.jpa.converter;

import com.ivanskodje.dependency.demo.dependencydemo.domain.Item;
import com.ivanskodje.dependency.demo.dependencydemo.frontendapi.dto.FindItemResponseDto;
import com.ivanskodje.dependency.demo.dependencydemo.repository.jpa.ItemJpaEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ItemJpaToItemConverter implements Converter<ItemJpaEntity, Item> {

    @Override
    public Item convert(ItemJpaEntity source) {
        return new Item(source.getName(), source.getDescription());
    }
}
