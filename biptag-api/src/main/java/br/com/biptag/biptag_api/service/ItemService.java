package br.com.biptag.biptag_api.service;

import br.com.biptag.biptag_api.model.Item;
import br.com.biptag.biptag_api.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItemService {

    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    // Equivale a police de SELECT do Supabase
    public List<Item> findAllItems() {
        return repository.findAll();
    }

    // Equivale a police de SELECT por usuario logado
    public List<Item> findItemsByUserId(UUID userId) {
        return repository.findByUserId(userId);
    }

    // Equivale a police de SELECT por ID do Supabase
    public Item findItemById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado com o ID: " + id));
    }

    // Equivale a police de INSERT do Supabase
    public Item createItem(Item item) {
        return repository.save(item);
    }

    // Equivale a police de UPDATE do Supabase
    public Item updateItem(Long id, Item itemDetails) {
        Item item = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado com o ID: " + id));

        item.setName(itemDetails.getName());
        item.setCategory(itemDetails.getCategory());
        item.setDescription(itemDetails.getDescription());
        item.setTagId(itemDetails.getTagId());
        item.setAttachTagDate(itemDetails.getAttachTagDate());
        item.setNfKey(itemDetails.getNfKey());
        item.setNfCreateData(itemDetails.getNfCreateData());
        item.setNfPhotoUrl(itemDetails.getNfPhotoUrl());
        item.setImage(itemDetails.getImage());
        item.setStatus(itemDetails.getStatus());

        return repository.save(item);
    }

    // Equivale a police de DELETE do Supabase
    public void deleteItem(Long id) {
        repository.deleteById(id);
    }

    public void updateStatus(Long id, String status) {
        Item item = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado com ID: " + id));
        item.setStatus(status);
        repository.save(item);
    }
}