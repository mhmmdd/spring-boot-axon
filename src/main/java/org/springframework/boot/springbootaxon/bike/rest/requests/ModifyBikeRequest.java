package org.springframework.boot.springbootaxon.bike.rest.requests;

import javax.validation.constraints.NotNull;

public class ModifyBikeRequest {
    @NotNull
    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}