package com.ivanskodje.dependency.demo.dependencydemo.frontendapi;

import com.ivanskodje.dependency.demo.dependencydemo.repository.printer.ItemPrinterAdapter;
import com.ivanskodje.dependency.demo.dependencydemo.service.ItemRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ItemRepositoryTestConfig {

    @Bean
    @Qualifier("itemPrinterAdapter")
    public ItemRepository itemRepository() {
        return new ItemPrinterAdapter();
    }
}