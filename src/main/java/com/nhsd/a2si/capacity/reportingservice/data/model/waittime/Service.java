package com.nhsd.a2si.capacity.reportingservice.data.model.waittime;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="service")
public final class Service {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Service service = (Service) o;

        if (id != null ? !id.equals(service.id) : service.id != null) return false;
        return name != null ? name.equals(service.name) : service.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
