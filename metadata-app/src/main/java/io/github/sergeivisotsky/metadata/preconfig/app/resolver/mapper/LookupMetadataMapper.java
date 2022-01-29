package io.github.sergeivisotsky.metadata.preconfig.app.resolver.mapper;

import io.github.sergeivisotsky.metadata.engine.domain.LookupHolder;
import io.github.sergeivisotsky.metadata.graphql.model.LookupHolderDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LookupMetadataMapper {

    LookupHolderDto map(LookupHolder lookupHolder);

}
