package com.ivanskodje.dependency.demo.dependencydemo.frontendapi.converter;

import com.ivanskodje.dependency.demo.dependencydemo.domain.Item;
import com.ivanskodje.dependency.demo.dependencydemo.frontendapi.dto.FindItemResponseDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Component
public class FindItemConverter implements Converter<Item, FindItemResponseDto> {

    @Override
    public FindItemResponseDto convert(Item source) {
        return new FindItemResponseDto(source.getName(), source.getDescription());
    }
}
