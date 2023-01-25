package com.ivanskodje.dependency.demo.dependencydemo.repository.printer;

import com.ivanskodje.dependency.demo.dependencydemo.domain.Item;
import com.ivanskodje.dependency.demo.dependencydemo.service.ItemRepository;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class ItemPrinterAdapter implements ItemRepository {
    public static final String SEPARATOR = "<!>";
    private final String fileName = "text-database.txt";
    private final File lockFile = new File("text-database.lock");
    private boolean lock = false;

    @Override
    public List<Item> findItems() {
        while(lock) {
            waitForUnlock();
        }
        lock = true;
        List<Item> items = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(SEPARATOR);
                items.add(new Item(parts[0], parts[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        lock = false;
        return items;
    }

    @Override
    public void saveItem(Item item) {
        while(lock) {
            waitForUnlock();
        }
        lock = true;
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.println(item.getName() + SEPARATOR + item.getDescription());
        } catch (IOException e) {
            e.printStackTrace();
        }
        lock = false;
    }

    @Override
    public void deleteAll() {
        while(lock) {
            waitForUnlock();
        }
        lock = true;
        try {

            if (Files.deleteIfExists(Paths.get(fileName))) {
                System.out.println("Deleted Successfully");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        lock = false;
    }

    private void waitForUnlock(){
        while(lock){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
