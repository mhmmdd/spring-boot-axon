package org.springframework.boot.springbootaxon.bike.query;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BikeEntry {
    @Id
    private String id;

    private String title;

    public BikeEntry(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public BikeEntry() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
