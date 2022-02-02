package com.thesamoanppprogrammer.graphql.datasource.todos.repository;

import com.thesamoanppprogrammer.graphql.datasource.todos.entity.Todoz;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TodozRepository extends CrudRepository<Todoz, UUID> {

    List<Todoz> findAll();

    @Query(nativeQuery = true, value = "select * from todoz t "
            + "where upper(todo_title) like upper(:keyword)")
    List<Todoz> findByKeyword(@Param("keyword") String keyword);

}
