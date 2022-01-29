package io.github.sergeivisotsky.metadata.preconfig.app.resolver;

import io.github.sergeivisotsky.metadata.engine.dao.LookupMetadataDao;
import io.github.sergeivisotsky.metadata.graphql.model.LookupHolderDto;
import io.github.sergeivisotsky.metadata.graphql.resolver.GetLookupMetadataQueryResolver;
import io.github.sergeivisotsky.metadata.preconfig.app.resolver.mapper.LookupMetadataMapper;

public class LookupMetadataQueryResolver implements GetLookupMetadataQueryResolver {

    private final LookupMetadataDao lookupMetadataDao;
    private final LookupMetadataMapper mapper;

    public LookupMetadataQueryResolver(LookupMetadataDao lookupMetadataDao, LookupMetadataMapper mapper) {
        this.lookupMetadataDao = lookupMetadataDao;
        this.mapper = mapper;
    }

    @Override
    public LookupHolderDto getLookupMetadata(String lookupName, String lang) {
        return mapper.map(lookupMetadataDao.getLookupMetadata(lookupName, lang));
    }
}
