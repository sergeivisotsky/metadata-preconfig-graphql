package io.github.sergeivisotsky.metadata.preconfig.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import io.github.sergeivisotsky.metadata.preconfig.app.domain.ExtendedLayout;
import io.github.sergeivisotsky.metadata.engine.domain.Area;
import io.github.sergeivisotsky.metadata.engine.domain.Layout;
import io.github.sergeivisotsky.metadata.engine.mapper.MetadataMapper;

public class LayoutMapper implements MetadataMapper<Layout> {

    @Override
    public String getSql() {
        return "SELECT l.area,\n" +
                "       l.weight,\n" +
                "       l.height,\n" +
                "       l.font,\n" +
                "       l.font_size\n" +
                "FROM layout l\n" +
                "WHERE l.view_name = :viewName";
    }

    @Override
    public ExtendedLayout map(ResultSet rs) {
        try {
            ExtendedLayout layout = new ExtendedLayout();
            layout.setFont(rs.getString("font"));
            layout.setFontSize(rs.getInt("font_size"));
            layout.setArea(Area.valueOf(rs.getString("area")));
            layout.setWeight(rs.getInt("weight"));
            layout.setHeight(rs.getInt("height"));
            return layout;
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get value from ResultSet for Mapper: {}" +
                    LayoutMapper.class.getSimpleName(), e);
        }
    }
}
