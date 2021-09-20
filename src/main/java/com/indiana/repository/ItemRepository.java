package com.indiana.repository;

import com.indiana.models.Item;
import com.indiana.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Page<Item> findAllByUser(User user, Pageable pageable);

    List<Item> findAllByUserOrderByDateFound(User user);
}
