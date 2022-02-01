package com.thesamoanppprogrammer.graphql.component.fake;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.thesamoanppprogrammer.graphql.datasource.fake.FakeTodosDataSource;
import com.thesamoanppprogrammer.graphql.generated.types.TodoItem;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@DgsComponent
public class FakeTodosDataResolver {

    @DgsQuery
    public List<TodoItem> allTodos() {
        return FakeTodosDataSource.TODOS_LIST;
    }

    @DgsQuery
    public List<TodoItem> todos(@InputArgument(name = "isCompleted") Boolean isCompleted) {
        return FakeTodosDataSource.TODOS_LIST.stream()
                .filter(t -> t.getCompleted() == isCompleted)
                .collect(Collectors.toList());
    }

    @DgsQuery
    public TodoItem oneTodo() {
        return FakeTodosDataSource.TODOS_LIST.get(
                ThreadLocalRandom.current().nextInt(FakeTodosDataSource.TODOS_LIST.size())
        );
    }

    @DgsQuery
    public TodoItem showTodo(@InputArgument(name = "id") String id) {
        return FakeTodosDataSource.TODOS_LIST.stream().filter(todoItem -> todoItem.getId().equals(id))
                .findAny()
                .orElse(null);
    }

}
