package com.onder.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "routes")

    //query for checking origin and destination
@NamedQuery(name = "Route.getByOriginDestination",
        query = "SELECT c FROM Route c WHERE c.origin = ?1 AND c.destination = ?2")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    // route's origin
    @Column(name = "origin")
    private String origin;

    //route's destination
    @Column(name = "destination")
    private String destination;

    // date of creting route
    @CreationTimestamp
    private Date createdAt;

    // date of updating route
    @CreationTimestamp
    private Date updateAt;

    // getters and setters
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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
