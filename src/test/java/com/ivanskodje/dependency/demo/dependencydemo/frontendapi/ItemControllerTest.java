package com.ivanskodje.dependency.demo.dependencydemo.frontendapi;

import com.ivanskodje.dependency.demo.dependencydemo.domain.Item;
import com.ivanskodje.dependency.demo.dependencydemo.repository.printer.ItemPrinterAdapter;
import com.ivanskodje.dependency.demo.dependencydemo.service.ItemRepository;
import com.ivanskodje.dependency.demo.dependencydemo.service.ItemService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.AfterTestExecution;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(ItemRepositoryTestConfig.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItemRepository itemRepository;

    @BeforeEach
    public void beforeAll() {
        itemRepository.deleteAll();
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
                .andExpect(status().isOk()).andReturn();


        List<Item> items = itemRepository.findItems().stream().filter(item -> "First Item".equals(item.getName())).toList();
        assertThat(items).isNotEmpty();
        assertThat(items).hasSize(1);
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
                .andExpect(jsonPath("$[?(@.name == 'Second Item (unique)')]", is(notNullValue())))
                .andExpect(jsonPath("$[?(@.description == 'My second description')]", is(notNullValue())));
    }
}