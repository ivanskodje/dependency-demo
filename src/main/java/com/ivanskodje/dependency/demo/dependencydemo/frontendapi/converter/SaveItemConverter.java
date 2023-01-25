package com.ivanskodje.dependency.demo.dependencydemo.frontendapi.converter;

import com.ivanskodje.dependency.demo.dependencydemo.domain.Item;
import com.ivanskodje.dependency.demo.dependencydemo.frontendapi.dto.FindItemResponseDto;
import com.ivanskodje.dependency.demo.dependencydemo.frontendapi.dto.SaveItemRequestDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SaveItemConverter implements Converter<SaveItemRequestDto, Item> {

    @Override
    public Item convert(SaveItemRequestDto source) {
        return new Item(source.name(), source.description());
    }
}
