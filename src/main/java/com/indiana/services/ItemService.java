package com.indiana.services;

import com.indiana.models.Item;
import com.indiana.models.User;
import com.indiana.repository.ItemRepository;
import com.indiana.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

    public Page<Item> getAll(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }
    public Page<Item> getAllItemsByUserId(Long id, Pageable pageable) {
        Page<Item> userItems = null;
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " + id));
        userItems = itemRepository.findAllByUser(user, pageable);

        return userItems;
    }

    public void addNewItem(Item item){
        itemRepository.save(item);
    }
    public void addItem(String name, String description, Long id, Date dateFound, MultipartFile image){
        byte [] byteImage= new byte[0];
        try {
            byteImage = image.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " + id));

        Item item = new Item(name,description,user,dateFound,byteImage);

        itemRepository.save(item);
    }

    public List<Item> getAllByUser(Long id) {
        List<Item> userItems = null;
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " + id));
        userItems = itemRepository.findAllByUserOrderByDateFound(user);

        return userItems;
    }

}
