package io.github.sergeivisotsky.metadata.preconfig.app.resolver;

import io.github.sergeivisotsky.metadata.engine.dao.ViewMetadataDao;
import io.github.sergeivisotsky.metadata.engine.dao.ViewQueryDao;
import io.github.sergeivisotsky.metadata.graphql.model.ViewQueryResultResponseDto;
import io.github.sergeivisotsky.metadata.graphql.resolver.QueryQueryResolver;
import io.github.sergeivisotsky.metadata.preconfig.app.resolver.mapper.ViewQueryMapper;

public class ViewQueryGraphQLQueryResolver implements QueryQueryResolver {

    private final ViewMetadataDao metadataDao;
    private final ViewQueryDao queryDao;
    private final ViewQueryMapper mapper;

    public ViewQueryGraphQLQueryResolver(ViewMetadataDao metadataDao, ViewQueryDao queryDao, ViewQueryMapper mapper) {
        this.metadataDao = metadataDao;
        this.queryDao = queryDao;
        this.mapper = mapper;
    }

    @Override
    public ViewQueryResultResponseDto query(String viewName, String lang) {
        // TODO: Need to decide what to do w/ params that are got from Http servlet request in case of rest
//        return mapper.map(queryDao.query());
        return null;
    }
}
