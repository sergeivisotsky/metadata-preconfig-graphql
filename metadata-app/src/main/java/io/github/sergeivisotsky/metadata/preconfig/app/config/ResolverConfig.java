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

import io.github.sergeivisotsky.metadata.engine.dao.ViewMetadataDao;
import io.github.sergeivisotsky.metadata.engine.graphql.resolver.ChartMetadataQueryResolver;
import io.github.sergeivisotsky.metadata.engine.graphql.resolver.FormMetadataQueryResolver;
import io.github.sergeivisotsky.metadata.engine.graphql.resolver.LookupMetadataQueryResolver;
import io.github.sergeivisotsky.metadata.engine.graphql.resolver.ViewMetadataQueryResolver;
import io.github.sergeivisotsky.metadata.engine.graphql.resolver.ViewQueryGraphQLQueryResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Sergei Visotsky
 */
@Configuration
public class ResolverConfig {

    @Bean
    public ViewMetadataQueryResolver viewMetadataQueryResolver(ViewMetadataDao viewMetadataDao) {
        return new ViewMetadataQueryResolver(viewMetadataDao);
    }

    @Bean
    public ViewQueryGraphQLQueryResolver viewQueryGraphQLQueryResolver() {
        return new ViewQueryGraphQLQueryResolver();
    }

    @Bean
    public LookupMetadataQueryResolver lookupMetadataQueryResolver() {
        return new LookupMetadataQueryResolver();
    }

    @Bean
    public FormMetadataQueryResolver formMetadataQueryResolver() {
        return new FormMetadataQueryResolver();
    }

    @Bean
    public ChartMetadataQueryResolver chartMetadataQueryResolver() {
        return new ChartMetadataQueryResolver();
    }
}
