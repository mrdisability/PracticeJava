package com.thesamoanppprogrammer.graphql.service.query;

import com.thesamoanppprogrammer.graphql.datasource.todos.entity.Todoz;
import com.thesamoanppprogrammer.graphql.datasource.todos.repository.TodozRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TodozQueryService {

    @Autowired
    private TodozRepository repository;

    public List<Todoz> todozList() {
        var todoz = repository.findAll();
        return todoz;
    }

    public Optional<Todoz> todozDetail(UUID todozId) {
        return repository.findById(todozId);
    }

    public List<Todoz> todozByKeyword(String keyword) {
        return repository.findByKeyword("%" + keyword + "%");
    }

}

