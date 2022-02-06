package io.github.sergeivisotsky.metadata.preconfig.app.resolver;

import io.github.sergeivisotsky.metadata.graphql.model.ChartMetadataDto;
import io.github.sergeivisotsky.metadata.graphql.resolver.GetChartMetadataQueryResolver;

public class ChartMetadataQueryResolver implements GetChartMetadataQueryResolver {

    @Override
    public ChartMetadataDto getChartMetadata(String chartName) {
        return null;
    }
}
