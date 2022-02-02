package com.thesamoanppprogrammer.graphql.component.todos;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import com.thesamoanppprogrammer.graphql.generated.DgsConstants;
import com.thesamoanppprogrammer.graphql.generated.types.SearchItemFilter;
import com.thesamoanppprogrammer.graphql.generated.types.SearchableItem;
import com.thesamoanppprogrammer.graphql.util.GraphqlBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.thesamoanppprogrammer.graphql.service.query.TodozQueryService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@DgsComponent
public class ItemSearchDataResolver {

    @Autowired
    private TodozQueryService todozQueryService;

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.ItemSearch)
    public List<SearchableItem> searchItems(
            @InputArgument(name = "filter", collectionType = SearchItemFilter.class) SearchItemFilter filter) {
        var result = new ArrayList<SearchableItem>();
        var keyword = filter.getKeyword();

        var todozByKeyword = todozQueryService.todozByKeyword(keyword)
                .stream().map(GraphqlBeanMapper::mapToGraphql).collect(Collectors.toList());
        result.addAll(todozByKeyword);

        if (result.isEmpty()) {
            throw new DgsEntityNotFoundException("No item with keyword " + keyword);
        }

        return result;
    }

}

