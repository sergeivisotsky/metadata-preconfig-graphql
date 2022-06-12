/*
 * Copyright 2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.sergeivisotsky.metadata.preconfig.app.resolver;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import io.github.sergeivisotsky.metadata.engine.dao.ViewMetadataDao;
import io.github.sergeivisotsky.metadata.engine.dao.ViewQueryDao;
import io.github.sergeivisotsky.metadata.engine.domain.ViewMetadata;
import io.github.sergeivisotsky.metadata.engine.domain.ViewQueryResult;
import io.github.sergeivisotsky.metadata.engine.exception.ViewQueryParseException;
import io.github.sergeivisotsky.metadata.engine.filtering.dto.ViewQuery;
import io.github.sergeivisotsky.metadata.engine.graphql.filtering.GraphQLViewQueryParser;
import io.github.sergeivisotsky.metadata.graphql.model.ViewQueryResponseDto;
import io.github.sergeivisotsky.metadata.graphql.resolver.ViewQueryQueryResolver;
import io.github.sergeivisotsky.metadata.preconfig.app.resolver.mapper.ViewQueryResponseMapper;

import static io.github.sergeivisotsky.metadata.engine.filtering.FilteringConstants.LIMIT;
import static io.github.sergeivisotsky.metadata.engine.filtering.FilteringConstants.OFFSET;
import static io.github.sergeivisotsky.metadata.engine.filtering.FilteringConstants.SORT;

public class ViewQueryGraphQLResolver implements ViewQueryQueryResolver {

    private final ViewQueryDao queryDao;
    private final ViewMetadataDao metadataDao;
    private final GraphQLViewQueryParser viewQueryParser;
    private final ViewQueryResponseMapper viewQueryResponseMapper;

    public ViewQueryGraphQLResolver(ViewQueryDao queryDao, ViewMetadataDao metadataDao,
                                    GraphQLViewQueryParser pageableGraphQLParser,
                                    ViewQueryResponseMapper viewQueryResponseMapper) {
        this.queryDao = queryDao;
        this.metadataDao = metadataDao;
        this.viewQueryParser = pageableGraphQLParser;
        this.viewQueryResponseMapper = viewQueryResponseMapper;
    }

    @Override
    public ViewQueryResponseDto viewQuery(String viewName, String lang, Long offset, Integer limit, String sort) {

        Map<String, String[]> params = ImmutableMap.<String, String[]>builder()
                .put(OFFSET, new String[]{offset.toString()})
                .put(LIMIT, new String[]{limit.toString()})
                .put(SORT, new String[]{sort})
                .build();

        ViewMetadata metadata = metadataDao.getViewMetadata(viewName, lang);

        try {
            ViewQuery query = viewQueryParser.constructViewQuery(metadata, params);
            ViewQueryResult queryResult = queryDao.query(metadata, query);

            return viewQueryResponseMapper.map(queryResult);
        } catch (ViewQueryParseException e) {
            throw new IllegalStateException(
                    "Unable to parse query parameters for a view with viewName=" + viewName + " lang=" + lang, e);
        }

    }
}
