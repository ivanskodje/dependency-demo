package com.ivanskodje.dependency.demo.dependencydemo.frontendapi;

import com.ivanskodje.dependency.demo.dependencydemo.domain.Item;
import com.ivanskodje.dependency.demo.dependencydemo.service.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItemRepository itemRepository;

    @BeforeEach
    public void beforeEach() {

    }

    @Test
    void saveItem_isOk() throws Exception {
        String jsonBody = """
                {
                    "name": "First Item",
                    "description": "My first description"
                }
                """;


        mockMvc.perform(MockMvcRequestBuilders.post("/api/item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk());


        List<Item> items = itemRepository.findItems();

        assertThat(items).isNotEmpty().hasSize(1);
        assertThat(items.get(0).getName()).isEqualTo("First Item");
        assertThat(items.get(0).getDescription()).isEqualTo("My first description");
    }

    @Test
    void findItems_oneResult_isOk() throws Exception {
        Item item = new Item();
        item.setName("Second Item (unique)");
        item.setDescription("My second description");

        itemRepository.saveItem(item);


        mockMvc.perform(MockMvcRequestBuilders.get("/api/item")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("First Item")))
                .andExpect(jsonPath("$[0].description", is("My first description")));
    }
}