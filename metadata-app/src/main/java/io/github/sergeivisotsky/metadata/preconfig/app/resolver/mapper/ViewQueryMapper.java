package io.github.sergeivisotsky.metadata.preconfig.app.resolver.mapper;

import io.github.sergeivisotsky.metadata.engine.domain.ViewQueryResult;
import io.github.sergeivisotsky.metadata.graphql.model.ViewQueryResultResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ViewQueryMapper {

    ViewQueryResultResponseDto map(ViewQueryResult result);

}
