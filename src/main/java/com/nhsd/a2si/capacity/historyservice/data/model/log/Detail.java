package com.nhsd.a2si.capacity.historyservice.data.model.log;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * CREATE TABLE log_detail (
 *   id BIGSERIAL PRIMARY KEY,
 *   header_id BIGINT REFERENCES log_header(id),
 *   service_id VARCHAR(30),
 *   timestamp TIMESTAMP DEFAULT NOW(),
 *   wait_time_in_minutes INT,
 *   age_in_minutes INT
 * );
 *
 */
@Entity
@Table(name="log_detail")
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "header_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Header header;

    @Column(name = "service_id")
    private String serviceId;

    @Column(name = "timestamp")
    private Date timestamp;

    @Column(name = "wait_time_in_minutes")
    private Integer waitTimeInMinutes;

    @Column(name = "age_in_minutes")
    private Integer ageInMinutes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getWaitTimeInMinutes() {
        return waitTimeInMinutes;
    }

    public void setWaitTimeInMinutes(Integer waitTimeInMinutes) {
        this.waitTimeInMinutes = waitTimeInMinutes;
    }

    public Integer getAgeInMinutes() {
        return ageInMinutes;
    }

    public void setAgeInMinutes(Integer ageInMinutes) {
        this.ageInMinutes = ageInMinutes;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }
}