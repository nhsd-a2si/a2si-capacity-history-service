package com.nhsd.a2si.capacity.reportingservice.data.model.waittime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="wait_times")
public final class WaitTime {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "wait_time_id")
    private long id;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated")
    private Date lastUpdated;

    @Column(name = "provider")
    @Size(max = 100)
    private String provider;

    @Column(name = "wait_time_in_minutes")
    private long waitTimeInMinutes;

    @ManyToOne
    @JoinColumn(name="service_id")
    private Service service;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }


    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public long getWaitTimeInMinutes() {
        return waitTimeInMinutes;
    }

    public void setWaitTimeInMinutes(long waitTimeInMinutes) {
        this.waitTimeInMinutes = waitTimeInMinutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WaitTime waitTime = (WaitTime) o;

        if (id != waitTime.id) return false;
        if (waitTimeInMinutes != waitTime.waitTimeInMinutes) return false;
        if (lastUpdated != null ? !lastUpdated.equals(waitTime.lastUpdated) : waitTime.lastUpdated != null)
            return false;
        if (provider != null ? !provider.equals(waitTime.provider) : waitTime.provider != null) return false;
        return service != null ? service.equals(waitTime.service) : waitTime.service == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (lastUpdated != null ? lastUpdated.hashCode() : 0);
        result = 31 * result + (provider != null ? provider.hashCode() : 0);
        result = 31 * result + (int) (waitTimeInMinutes ^ (waitTimeInMinutes >>> 32));
        result = 31 * result + (service != null ? service.hashCode() : 0);
        return result;
    }
}
