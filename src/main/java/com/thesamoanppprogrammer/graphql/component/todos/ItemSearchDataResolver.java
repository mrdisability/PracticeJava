package com.thesamoanppprogrammer.graphql.component.todos;

import com.netflix.graphql.dgs.DgsComponent;

@DgsComponent
public class ItemSearchDataResolver {

//    @Autowired
//    private ProblemzQueryService problemzQueryService;
//
//    @Autowired
//    private SolutionzQueryService solutionzQueryService;
//
//    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.ItemSearch)
//    public List<SearchableItem> searchItems(
//            @InputArgument(name = "filter", collectionType = SearchItemFilter.class) SearchItemFilter filter) {
//        var result = new ArrayList<SearchableItem>();
//        var keyword = filter.getKeyword();
//
//        var problemzByKeyword = problemzQueryService.problemzByKeyword(keyword)
//                .stream().map(GraphqlBeanMapper::mapToGraphql).collect(Collectors.toList());
//        result.addAll(problemzByKeyword);
//
//        var solutionzByKeyword = solutionzQueryService.solutionzByKeyword(keyword)
//                .stream().map(GraphqlBeanMapper::mapToGraphql).collect(Collectors.toList());
//        result.addAll(solutionzByKeyword);
//
//        if (result.isEmpty()) {
//            throw new DgsEntityNotFoundException("No item with keyword " + keyword);
//        }
//
//        result.sort(Comparator.comparing(SearchableItem::getCreateDateTime).reversed());
//
//        return result;
//    }

}

