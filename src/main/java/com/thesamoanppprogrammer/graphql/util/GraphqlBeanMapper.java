package com.thesamoanppprogrammer.graphql.util;


import com.thesamoanppprogrammer.graphql.datasource.todos.entity.Todoz;
import com.thesamoanppprogrammer.graphql.generated.types.TodoItem;
import com.thesamoanppprogrammer.graphql.generated.types.TodoItemCreateInput;

import java.util.UUID;

public class GraphqlBeanMapper {

//    private static final PrettyTime PRETTY_TIME = new PrettyTime();
//
//    private static final ZoneOffset ZONE_OFFSET = ZoneOffset.ofHours(7);

    public static TodoItem mapToGraphql(Todoz original) {
        var result = new TodoItem();

        //id, todoTitle, completed
        result.setId(original.getId().toString());
        result.setTodoTitle(original.getTodoTitle());
        result.setCompleted(original.getCompleted());

        return result;
    }

    public static Todoz mapToEntity(TodoItemCreateInput original) {
        var result = new Todoz();

        result.setId(UUID.randomUUID());
        result.setTodoTitle(original.getTodoTitle());

        //Start completed for todoItem as false
        result.setCompleted(false);

        return result;
    }

}

