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
package io.github.sergeivisotsky.metadata.preconfig.app.config;

import io.github.sergeivisotsky.metadata.engine.dao.FormMetadataDao;
import io.github.sergeivisotsky.metadata.engine.dao.LookupMetadataDao;
import io.github.sergeivisotsky.metadata.engine.dao.ViewMetadataDao;
import io.github.sergeivisotsky.metadata.engine.dao.ViewQueryDao;
import io.github.sergeivisotsky.metadata.engine.graphql.filtering.GraphQLViewQueryParser;
import io.github.sergeivisotsky.metadata.preconfig.app.resolver.ChartMetadataQueryResolver;
import io.github.sergeivisotsky.metadata.preconfig.app.resolver.FormMetadataQueryResolver;
import io.github.sergeivisotsky.metadata.preconfig.app.resolver.LookupMetadataQueryResolver;
import io.github.sergeivisotsky.metadata.preconfig.app.resolver.ViewMetadataQueryResolver;
import io.github.sergeivisotsky.metadata.preconfig.app.resolver.ViewQueryGraphQLQueryResolver;
import io.github.sergeivisotsky.metadata.preconfig.app.resolver.mapper.FormMetadataMapper;
import io.github.sergeivisotsky.metadata.preconfig.app.resolver.mapper.LookupMetadataMapper;
import io.github.sergeivisotsky.metadata.preconfig.app.resolver.mapper.ViewMetadataMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Sergei Visotsky
 */
@Configuration
public class ResolverConfig {

    @Bean
    public ViewMetadataQueryResolver viewMetadataQueryResolver(ViewMetadataDao viewMetadataDao, ViewMetadataMapper mapper) {
        return new ViewMetadataQueryResolver(viewMetadataDao, mapper);
    }

    @Bean
    public LookupMetadataQueryResolver lookupMetadataQueryResolver(LookupMetadataDao lookupMetadataDao, LookupMetadataMapper mapper) {
        return new LookupMetadataQueryResolver(lookupMetadataDao, mapper);
    }

    @Bean
    public FormMetadataQueryResolver formMetadataQueryResolver(FormMetadataDao formMetadataDao, FormMetadataMapper mapper) {
        return new FormMetadataQueryResolver(formMetadataDao, mapper);
    }

    @Bean
    public ChartMetadataQueryResolver chartMetadataQueryResolver() {
        return new ChartMetadataQueryResolver();
    }

    @Bean
    public ViewQueryGraphQLQueryResolver viewQueryGraphQLQueryResolver(ViewQueryDao queryDao, ViewMetadataDao metadataDao,
                                                                       GraphQLViewQueryParser viewQueryParser) {
        return new ViewQueryGraphQLQueryResolver(queryDao, metadataDao, viewQueryParser);
    }
}
