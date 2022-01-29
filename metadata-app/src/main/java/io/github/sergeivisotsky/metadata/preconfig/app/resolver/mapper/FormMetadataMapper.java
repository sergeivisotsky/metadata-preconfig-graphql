package io.github.sergeivisotsky.metadata.preconfig.app.resolver.mapper;

import io.github.sergeivisotsky.metadata.engine.domain.form.FormMetadata;
import io.github.sergeivisotsky.metadata.graphql.model.FormMetadataDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FormMetadataMapper {

    FormMetadataDto map(FormMetadata metadata);
}
