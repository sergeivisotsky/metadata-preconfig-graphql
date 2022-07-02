package io.github.sergeivisotsky.metadata.preconfig.app.mapper.model;

import io.github.sergeivisotsky.metadata.engine.domain.form.FormSection;
import io.github.sergeivisotsky.metadata.engine.mapper.ModelMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FormSectionModelMapper extends ModelMapper<FormSection, FormSection> {
}