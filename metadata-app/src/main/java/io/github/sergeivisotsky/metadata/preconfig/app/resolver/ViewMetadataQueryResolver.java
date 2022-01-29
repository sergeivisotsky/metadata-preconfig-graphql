package io.github.sergeivisotsky.metadata.preconfig.app.resolver;

import io.github.sergeivisotsky.metadata.engine.dao.ViewMetadataDao;
import io.github.sergeivisotsky.metadata.engine.domain.ViewMetadata;
import io.github.sergeivisotsky.metadata.graphql.model.ViewMetadataDto;
import io.github.sergeivisotsky.metadata.graphql.resolver.GetViewMetadataQueryResolver;
import io.github.sergeivisotsky.metadata.preconfig.app.resolver.mapper.ViewMetadataMapper;

public class ViewMetadataQueryResolver implements GetViewMetadataQueryResolver {

    private final ViewMetadataDao metadataDao;
    private final ViewMetadataMapper mapper;

    public ViewMetadataQueryResolver(ViewMetadataDao metadataDao, ViewMetadataMapper mapper) {
        this.metadataDao = metadataDao;
        this.mapper = mapper;
    }

    @Override
    public ViewMetadataDto getViewMetadata(String viewName, String lang) {
        return mapper.map(metadataDao.getViewMetadata(viewName, lang));
    }

}
