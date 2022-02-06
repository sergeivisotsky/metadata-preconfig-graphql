package io.github.sergeivisotsky.metadata.preconfig.app.resolver.mapper;

import io.github.sergeivisotsky.metadata.engine.domain.ViewMetadata;
import io.github.sergeivisotsky.metadata.graphql.model.ViewMetadataDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ViewMetadataMapper {

    ViewMetadataDto map(ViewMetadata metadata);

}
