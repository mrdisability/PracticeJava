package com.thesamoanppprogrammer.graphql.service.command;

import com.thesamoanppprogrammer.graphql.datasource.todos.entity.Todoz;
import com.thesamoanppprogrammer.graphql.datasource.todos.repository.TodozRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Service
public class TodozCommandService {

    private Sinks.Many<Todoz> todozSink = Sinks.many().multicast().onBackpressureBuffer();

    @Autowired
    private TodozRepository repository;

    public Todoz createTodoz(Todoz t) {
        var created = repository.save(t);

        todozSink.tryEmitNext(created);

        return created;
    }

    public Flux<Todoz> todozFlux() {
        return todozSink.asFlux();
    }

}

