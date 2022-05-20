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
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMap;
import io.github.sergeivisotsky.metadata.engine.dao.ViewMetadataDao;
import io.github.sergeivisotsky.metadata.engine.dao.ViewQueryDao;
import io.github.sergeivisotsky.metadata.engine.domain.Paging;
import io.github.sergeivisotsky.metadata.engine.domain.ViewField;
import io.github.sergeivisotsky.metadata.engine.domain.ViewMetadata;
import io.github.sergeivisotsky.metadata.engine.domain.ViewQueryResult;
import io.github.sergeivisotsky.metadata.engine.exception.UrlParseException;
import io.github.sergeivisotsky.metadata.engine.filtering.dto.ViewQuery;
import io.github.sergeivisotsky.metadata.engine.graphql.PageableGraphQLParser;
import io.github.sergeivisotsky.metadata.graphql.model.OrderDto;
import io.github.sergeivisotsky.metadata.graphql.model.PagingDto;
import io.github.sergeivisotsky.metadata.graphql.model.SortDirectionDto;
import io.github.sergeivisotsky.metadata.graphql.model.ViewQueryResponseDto;
import io.github.sergeivisotsky.metadata.graphql.resolver.ViewQueryQueryResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: not the best naming convention. Think about it.
 *
 * @author Sergejs Visockis
 */
public class ViewQueryGraphQLQueryResolver implements ViewQueryQueryResolver {

    private static final Logger LOG = LoggerFactory.getLogger(ViewQueryGraphQLQueryResolver.class);

    private final ViewQueryDao queryDao;
    private final ViewMetadataDao metadataDao;
    private final PageableGraphQLParser pageableGraphQLParser;

    public ViewQueryGraphQLQueryResolver(ViewQueryDao queryDao, ViewMetadataDao metadataDao,
                                         PageableGraphQLParser pageableGraphQLParser) {
        this.queryDao = queryDao;
        this.metadataDao = metadataDao;
        this.pageableGraphQLParser = pageableGraphQLParser;
    }

    @Override
    public ViewQueryResponseDto viewQuery(String viewName, String lang, Long offset, Integer limit) {

        // TODO: Bad code practice. Refactor this.
        Map<String, String[]> params = ImmutableMap.<String, String[]>builder()
                .put("_offset", new String[]{offset.toString()})
                .put("_limit", new String[]{limit.toString()})
                .build();

        ViewMetadata metadata = metadataDao.getViewMetadata(viewName, lang);

        try {
            ViewQuery query = pageableGraphQLParser.constructViewQuery(metadata, params);
            ViewQueryResult queryResult = queryDao.query(metadata, query);

            // TODO: This looks weird! Refactor...
            ViewQueryResponseDto response = new ViewQueryResponseDto();
            response.setContent(queryResult.getRowList());
            response.setFieldNames(queryResult.getFieldList()
                    .stream()
                    .map(ViewField::getName)
                    .collect(Collectors.toList()));
            Paging paging = queryResult.getPaging();
            response.setPaging(new PagingDto(paging.getTotalElements(), paging.getOffset(), paging.getHasMoreElements()));
            response.setOrder(queryResult.getOrderList()
                    .stream()
                    .map(order -> {
                        OrderDto orderDto = new OrderDto();
                        orderDto.setDirection(SortDirectionDto.valueOf(order.getDirection().name()));
                        orderDto.setFieldName(order.getFieldName());
                        return orderDto;
                    }).collect(Collectors.toList()));

            return response;
        } catch (UrlParseException e) {
            // TODO: Handle an exception here properly
            throw new RuntimeException();
        }

    }
}
