package com.thesamoanppprogrammer.graphql.datasource.fake;

import com.github.javafaker.Faker;
import com.thesamoanppprogrammer.graphql.generated.types.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
public class FakeTodosDataSource {

    @Autowired
    private Faker faker;

    public static final List<TodoItem> TODOS_LIST = new ArrayList<>();

    @PostConstruct
    private void postConstruct() {
        for (int i = 0; i < 20; i++) {
            var todo = TodoItem.newBuilder()
                    .id(UUID.randomUUID().toString())
                    .todoTitle(faker.job().title())
                    .completed(faker.bool().bool())
                    .build();

            TODOS_LIST.add(todo);
        }
    }

}


