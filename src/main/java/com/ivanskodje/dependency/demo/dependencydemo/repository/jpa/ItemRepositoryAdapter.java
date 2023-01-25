package com.ivanskodje.dependency.demo.dependencydemo.repository.jpa;

import com.ivanskodje.dependency.demo.dependencydemo.domain.Item;
import com.ivanskodje.dependency.demo.dependencydemo.repository.jpa.converter.ItemJpaToItemConverter;
import com.ivanskodje.dependency.demo.dependencydemo.repository.jpa.converter.ItemToItemJpaConverter;
import com.ivanskodje.dependency.demo.dependencydemo.service.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ItemRepositoryAdapter implements ItemRepository {

    private final ItemToItemJpaConverter itemToItemJpaConverter;
    private final ItemJpaToItemConverter itemJpaToItemConverter;
    private final ItemJpaRepository itemJpaRepository;

    @Override
    public List<Item> findItems() {
        List<ItemJpaEntity> itemEntities = itemJpaRepository.findAll();
        return itemEntities.stream()
                .map(itemJpaToItemConverter::convert)
                .toList();
    }

    @Override
    public void saveItem(Item item) {
        throwExceptionIfItemAlreadyExist(item);
        ItemJpaEntity itemJpaEntity = itemToItemJpaConverter.convert(item);
        assert itemJpaEntity != null;
        itemJpaRepository.save(itemJpaEntity);
    }

    private void throwExceptionIfItemAlreadyExist(Item item) {
        itemJpaRepository.findFirstByName(item.getName()).ifPresent(itemJpaEntity -> new RuntimeException("Cannot save, item already exist"));
    }
}
