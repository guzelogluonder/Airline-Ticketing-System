package com.onder.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Random;

@Entity
@Table(name = "flights ")

//query check for ticket price
@NamedQuery(name = "Flight.getTicketPrice",
        query = "SELECT f FROM Flight f WHERE f.originIataCode = ?1 AND f.destinationIataCode = ?2 AND f.twoLetterCode = ?3")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    int min = 100;
    int max = 1000;

    // creates a random ticket price between 100 and 100
    Random random = new Random();
    int number = min + ((int) (random.nextDouble() * (max - min)));

    //airline's two letter code
    @Column(name = "twoLetterCode")
    private String twoLetterCode;

    //origin of route
    @Column(name = "originIataCode")
    private String originIataCode;

    //destination of route
    @Column(name = "destinationIataCode")
    private String destinationIataCode;

    //flight ticket price
    @Column(name = "ticketPrice")
    private int ticketPrice;

    //airplane contingent
    @Column(name = "contingent")
    private int contingent;

    // date of creating
    @CreationTimestamp
    private Date createdAt;

    // date of updating
    @CreationTimestamp
    private Date updateAt;
    //getters and setters
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

    public int getTicketPrice() {
        ticketPrice = number;
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getContingent() {
        return contingent;
    }

    public void setContingent(int contingent) {
        this.contingent = contingent;
    }
}
