package com.ivanskodje.dependency.demo.dependencydemo.frontendapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivanskodje.dependency.demo.dependencydemo.domain.Item;
import com.ivanskodje.dependency.demo.dependencydemo.repository.ItemRepository;
import com.ivanskodje.dependency.demo.dependencydemo.service.ItemService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItemRepository itemRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void beforeEach() {
        itemRepository.deleteAll();
    }


    @Test
    void saveItem_isOk() throws Exception {
        Item item = Item.builder()
                .name("First Item")
                .description("My first description")
                .build();
        String jsonBody = objectMapper.writeValueAsString(item);


        mockMvc.perform(MockMvcRequestBuilders.post("/api/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andExpect(MockMvcResultMatchers.status().isOk());


        List<Item> items = itemRepository.findAll();

        assertThat(items).isNotEmpty().hasSize(1);
        assertThat(items.get(0).getId()).isPositive();
        assertThat(items.get(0).getName()).isEqualTo("First Item");
        assertThat(items.get(0).getDescription()).isEqualTo("My first description");
    }

    @Test
    void findItems_oneResult_isOk() throws Exception {
        Item item = Item.builder()
                .name("First Item")
                .description("My first description")
                .build();
        itemRepository.save(item);


        String jsonResponse = mockMvc.perform(MockMvcRequestBuilders.get("/api/item")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();


        List<Item> items = objectMapper.readValue(jsonResponse, new TypeReference<List<Item>>() {        });

        assertThat(items).isNotEmpty().hasSize(1);
        assertThat(items.get(0).getId()).isPositive();
        assertThat(items.get(0).getName()).isEqualTo("First Item");
        assertThat(items.get(0).getDescription()).isEqualTo("My first description");
    }

}