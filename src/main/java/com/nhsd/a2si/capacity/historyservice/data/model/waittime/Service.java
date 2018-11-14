package com.nhsd.a2si.capacity.historyservice.data.model.waittime;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="service")
public class Service {

    @Id
    @Column(name = "service_id")
    private String id;

    @Column(name = "service_name")
    @Size(max = 100)
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
