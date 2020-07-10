package com.onder.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "flights ")

public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "twoLetterCode")
    private String twoLetterCode;

    @Column(name = "originIataCode")
    private String originIataCode;

    @Column(name = "destinationIataCode")
    private String destinationIataCode;
    @CreationTimestamp

    private Date createdAt;

    @CreationTimestamp
    private Date updateAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOriginIataCode() {
        return originIataCode;
    }

    public void setOriginIataCode(String originIataCode) {
        this.originIataCode = originIataCode;
    }

    public String getDestinationIataCode() {
        return destinationIataCode;
    }

    public void setDestinationIataCode(String destinationIataCode) {
        this.destinationIataCode = destinationIataCode;
    }

    public String getTwoLetterCode() {
        return twoLetterCode;
    }

    public void setTwoLetterCode(String twoLetterCode) {
        this.twoLetterCode = twoLetterCode;
    }
}
