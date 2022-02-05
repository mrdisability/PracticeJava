package com.thesamoanppprogrammer.graphql.component.todos;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import com.thesamoanppprogrammer.graphql.datasource.todos.entity.Todoz;
import com.thesamoanppprogrammer.graphql.generated.DgsConstants;
import com.thesamoanppprogrammer.graphql.generated.types.TodoItem;
import com.thesamoanppprogrammer.graphql.generated.types.TodoItemCreateInput;
import com.thesamoanppprogrammer.graphql.generated.types.TodoItemResponse;
import com.thesamoanppprogrammer.graphql.service.command.TodozCommandService;
import com.thesamoanppprogrammer.graphql.service.query.TodozQueryService;
import com.thesamoanppprogrammer.graphql.util.GraphqlBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@DgsComponent
public class TodoDataResolver {

    @Autowired
    private TodozQueryService queryService;

    @Autowired
    private TodozCommandService commandService;

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.AllTodos)
    public List<TodoItem> getTodosList() {
        return queryService.todozList().stream().map(GraphqlBeanMapper::mapToGraphql)
                .collect(Collectors.toList());
    }

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.TodoDetail)
    public TodoItem getTodoDetail(@InputArgument(name = "id") String todoId) {
        var todozId = UUID.fromString(todoId);
        var todoz = queryService.todozDetail(todozId)
                .orElseThrow(DgsEntityNotFoundException::new);

        return GraphqlBeanMapper.mapToGraphql(todoz);
    }

    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.CreateTodo)
    public TodoItemResponse createTodo(
            @InputArgument(name = "todo") TodoItemCreateInput todoCreateInput) {
        var todoz = GraphqlBeanMapper.mapToEntity(todoCreateInput);
        var created = commandService.createTodoz(todoz);

        return TodoItemResponse.newBuilder().todo(GraphqlBeanMapper.mapToGraphql(created)).build();
    }

    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.DeleteTodo)
    public Boolean deleteTodo(@InputArgument(name = "id") String todoId) {
        commandService.deleteTodo(UUID.fromString(todoId));

        return true;
    }

    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.UpdateTodo)
    public Todoz updateTodo(@InputArgument(name = "id") String todoId, @InputArgument(name = "todoTitle") String todoTitle,
                            @InputArgument(name = "completed") Boolean completed) {
        var todozId = UUID.fromString(todoId);

        var todo = commandService.updateTodo(todozId, todoTitle, completed);

        return todo;
    }

    @DgsData(parentType = DgsConstants.SUBSCRIPTION_TYPE, field = DgsConstants.SUBSCRIPTION.TodoItemAdded)
    public Flux<TodoItem> subscribeTodoAdded() {
        return commandService.todozFlux().map(GraphqlBeanMapper::mapToGraphql);
    }

}

