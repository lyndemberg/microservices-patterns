package com.h3.portal.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Source {
    private String id;
    private String name;

    public Source() {
    }

    public Source(String id, String name) {
        this.id = id;
        this.name = name;
    }

}
