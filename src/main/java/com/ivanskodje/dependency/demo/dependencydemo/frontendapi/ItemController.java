package com.ivanskodje.dependency.demo.dependencydemo.frontendapi;


import com.ivanskodje.dependency.demo.dependencydemo.domain.Item;
import com.ivanskodje.dependency.demo.dependencydemo.frontendapi.dto.FindItemResponseDto;
import com.ivanskodje.dependency.demo.dependencydemo.frontendapi.dto.SaveItemRequestDto;
import com.ivanskodje.dependency.demo.dependencydemo.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final Converter<Item, FindItemResponseDto> findItemConverter;
    private final Converter<SaveItemRequestDto, Item> saveItemConverter;

    @PostMapping("/item")
    public ResponseEntity<Void> saveItem(@RequestBody SaveItemRequestDto saveItemRequestDto) {
        Item item = saveItemConverter.convert(saveItemRequestDto);
        itemService.saveItem(item);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/item")
    public ResponseEntity<List<FindItemResponseDto>> findItems() {
        List<Item> items = itemService.findItems();
        List<FindItemResponseDto> findItemResponseDtoList = items.stream().map(findItemConverter::convert).toList();
        return ResponseEntity.ok(findItemResponseDtoList);
    }
}
