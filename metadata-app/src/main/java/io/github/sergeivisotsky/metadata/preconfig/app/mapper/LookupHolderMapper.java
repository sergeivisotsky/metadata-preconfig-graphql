package io.github.sergeivisotsky.metadata.preconfig.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import io.github.sergeivisotsky.metadata.preconfig.app.domain.ExtendedLookupHolder;
import io.github.sergeivisotsky.metadata.engine.domain.LookupHolder;
import io.github.sergeivisotsky.metadata.engine.mapper.MetadataMapper;

public class LookupHolderMapper implements MetadataMapper<LookupHolder> {

    @Override
    public String getSql() {
        return "SELECT l.id,\n" +
                "      l.name,\n" +
                "      l.weight,\n" +
                "      l.height\n" +
                "FROM lookup_holder l\n" +
                "WHERE l.name = :lookupName";
    }

    @Override
    public ExtendedLookupHolder map(ResultSet rs) {
        try {
            ExtendedLookupHolder holder = new ExtendedLookupHolder();

            holder.setName(rs.getString("name"));
            holder.setWeight(rs.getInt("weight"));
            holder.setHeight(rs.getInt("height"));

            return holder;
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get value from ResultSet for Mapper: {}" +
                    LookupHolderMapper.class.getSimpleName(), e);
        }
    }
}
