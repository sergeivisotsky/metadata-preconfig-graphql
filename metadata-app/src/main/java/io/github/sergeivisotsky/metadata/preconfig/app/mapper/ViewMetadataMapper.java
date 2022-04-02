package io.github.sergeivisotsky.metadata.preconfig.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import io.github.sergeivisotsky.metadata.engine.domain.Language;
import io.github.sergeivisotsky.metadata.engine.domain.ViewMetadata;
import io.github.sergeivisotsky.metadata.engine.exception.MetadataStorageException;
import io.github.sergeivisotsky.metadata.engine.mapper.MetadataMapper;
import io.github.sergeivisotsky.metadata.preconfig.app.domain.ExtendedViewMetadata;

public class ViewMetadataMapper implements MetadataMapper<ViewMetadata> {

    @Override
    public String getSql() {
        return "SELECT fm.id,\n" +
                "       fm.view_name,\n" +
                "       fm.definition,\n" +
                "       fm.language,\n" +
                "       fm.offset,\n" +
                "       fm.padding,\n" +
                "       fm.font,\n" +
                "       fm.font_size,\n" +
                "       fm.description,\n" +
                "       fm.facet\n" +
                "FROM view_metadata fm\n" +
                "WHERE fm.view_name = :viewName\n" +
                "  AND fm.language = :lang";
    }

    @Override
    public ExtendedViewMetadata map(ResultSet rs) {
        try {
            ExtendedViewMetadata metadata = new ExtendedViewMetadata();
            metadata.setViewName(rs.getString("view_name"));
            metadata.setDefinition(rs.getString("definition"));
            metadata.setLang(Language.valueOf(rs.getString("language")
                    .toUpperCase(Locale.ROOT)));
            metadata.setOffset(rs.getInt("offset"));
            metadata.setPadding(rs.getInt("padding"));
            metadata.setFont(rs.getString("font"));
            metadata.setFontSize(rs.getInt("font_size"));
            metadata.setDescription(rs.getString("description"));
            return metadata;
        } catch (SQLException e) {
            throw new MetadataStorageException("Unable to get value from ResultSet for Mapper: {}" +
                    ViewMetadataMapper.class.getSimpleName(), e);
        }
    }
}
