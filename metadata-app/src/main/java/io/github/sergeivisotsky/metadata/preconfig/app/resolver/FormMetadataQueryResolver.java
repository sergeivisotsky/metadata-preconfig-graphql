package io.github.sergeivisotsky.metadata.preconfig.app.resolver;

import io.github.sergeivisotsky.metadata.engine.dao.FormMetadataDao;
import io.github.sergeivisotsky.metadata.graphql.model.FormMetadataDto;
import io.github.sergeivisotsky.metadata.graphql.resolver.GetFormMetadataQueryResolver;
import io.github.sergeivisotsky.metadata.preconfig.app.resolver.mapper.FormMetadataMapper;

public class FormMetadataQueryResolver implements GetFormMetadataQueryResolver {

    private final FormMetadataDao formMetadataDao;
    private final FormMetadataMapper mapper;

    public FormMetadataQueryResolver(FormMetadataDao formMetadataDao, FormMetadataMapper mapper) {
        this.formMetadataDao = formMetadataDao;
        this.mapper = mapper;
    }

    @Override
    public FormMetadataDto getFormMetadata(String lang, String formName) {
        return mapper.map(formMetadataDao.getFormMetadata(lang, formName));
    }
}
