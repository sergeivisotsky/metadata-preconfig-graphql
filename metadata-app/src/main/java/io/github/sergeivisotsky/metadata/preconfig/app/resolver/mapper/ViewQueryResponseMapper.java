package io.github.sergeivisotsky.metadata.preconfig.app.resolver.mapper;

import java.util.stream.Collectors;

import io.github.sergeivisotsky.metadata.engine.domain.ViewField;
import io.github.sergeivisotsky.metadata.engine.domain.ViewQueryResult;
import io.github.sergeivisotsky.metadata.graphql.model.ViewQueryResponseDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ViewQueryResponseMapper {

    @Mapping(source = "rowList", target = "content")
    @Mapping(source = "orderList", target = "order")
    ViewQueryResponseDto map(ViewQueryResult queryResult);

    @AfterMapping
    default void mapFieldNames(@MappingTarget ViewQueryResponseDto.Builder response, ViewQueryResult queryResult) {
        response.setFieldNames(queryResult
                .getFieldList()
                .stream()
                .map(ViewField::getName)
                .collect(Collectors.toList()));
    }

}
