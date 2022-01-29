package io.github.sergeivisotsky.metadata.preconfig.app.domain;

import io.github.sergeivisotsky.metadata.engine.domain.ViewMetadata;

public class ExtendedViewMetadata extends ViewMetadata {

    private String facet;

    public String getFacet() {
        return facet;
    }

    public void setFacet(String facet) {
        this.facet = facet;
    }
}
