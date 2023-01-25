package com.ivanskodje.dependency.demo.dependencydemo.repository.jpa.converter;

import com.ivanskodje.dependency.demo.dependencydemo.domain.Item;
import com.ivanskodje.dependency.demo.dependencydemo.repository.jpa.ItemJpaEntity;
import com.ivanskodje.dependency.demo.dependencydemo.repository.jpa.ItemJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ItemToItemJpaConverter implements Converter<Item, ItemJpaEntity> {
    @Override
    public ItemJpaEntity convert(Item source) {
        ItemJpaEntity itemJpaEntity = new ItemJpaEntity();
        itemJpaEntity.setName(source.getName());
        itemJpaEntity.setDescription(source.getDescription());
        return itemJpaEntity;
    }
}
