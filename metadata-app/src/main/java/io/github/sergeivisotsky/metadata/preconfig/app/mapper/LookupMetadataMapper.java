package io.github.sergeivisotsky.metadata.preconfig.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import io.github.sergeivisotsky.metadata.engine.domain.LookupMetadata;
import io.github.sergeivisotsky.metadata.engine.exception.MetadataStorageException;
import io.github.sergeivisotsky.metadata.engine.mapper.MetadataMapper;
import io.github.sergeivisotsky.metadata.preconfig.app.domain.ExtendedLookupMetadata;

public class LookupMetadataMapper implements MetadataMapper<LookupMetadata> {

    @Override
    public String getSql() {
        return "SELECT lm.code,\n" +
                "       lm.lang,\n" +
                "       lm.default_value,\n" +
                "       lm.display_value\n" +
                "FROM lookup_metadata lm\n" +
                "WHERE lm.lookup_holder_id = :holderId\n" +
                "  AND lm.lang = :lang";
    }

    @Override
    public LookupMetadata map(ResultSet rs) {
        try {
            ExtendedLookupMetadata metadata = new ExtendedLookupMetadata();

            metadata.setLang(rs.getString("lang"));
            metadata.setCode(rs.getString("code"));
            metadata.setDefaultValue(rs.getString("default_value"));
            metadata.setDisplayValue(rs.getString("display_value"));

            return metadata;
        } catch (SQLException e) {
            throw new MetadataStorageException("Unable to get value from ResultSet for Mapper: {}" +
                    LookupMetadataMapper.class.getSimpleName(), e);
        }
    }
}
