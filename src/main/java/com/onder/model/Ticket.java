package com.onder.model;

import com.onder.repository.TicketRepository;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;
import java.util.Random;

@Entity
@Table(name = "tickets")
@NamedQuery(name = "Ticket.getTicketsByFlight",
        query = "SELECT t FROM Ticket t WHERE t.airlineTwoLetterCode = ?1 AND t.destinationIataCode = ?2 AND t.originIataCode = ?3")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    long min = 1000000000000L;
    long max = 10000000000000L;
    Random random = new Random();
    long number = min + ((long) (random.nextDouble() * (max - min)));

    @Column(name = "originIataCode")
    private String originIataCode;

    @Column(name = "destinationIataCode")
    private String destinationIataCode;

    @Column(name = "airlineTwoLetterCode")
    private String airlineTwoLetterCode;

    @Column(name = "ticketNumber")
    private long ticketNumber;

    @Column(name = "creditCardNumber")
    private String creditCardNumber;

    @Column(name = "ticketPrice")
    private int ticketPrice;

    @CreationTimestamp
    private Date createdAt;

    @CreationTimestamp
    private Date updateAt;

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

    public String getAirlineTwoLetterCode() {
        return airlineTwoLetterCode;
    }

    public void setAirlineTwoLetterCode(String airlineTwoLetterCode) {
        this.airlineTwoLetterCode = airlineTwoLetterCode;
    }

    public long getTicketNumber() {
        ticketNumber = number;
        return ticketNumber;
    }

    public void setTicketNumber(long ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        String cleanedCreditCardNumber = creditCardNumber.replaceAll("[^0-9]", "");
        this.creditCardNumber = maskCardNumber(cleanedCreditCardNumber,"xxxxxx*****xxxxx");
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public static String maskCardNumber(String cardNumber, String mask) {

        // format the number
        int index = 0;
        StringBuilder maskedNumber = new StringBuilder();
        for (int i = 0; i < mask.length(); i++) {
            char c = mask.charAt(i);
            if (c == 'x') {
                maskedNumber.append(cardNumber.charAt(index));
                index++;
            } else if (c == '*') {
                maskedNumber.append(c);
                index++;
            } else {
                maskedNumber.append(c);
            }
        }

        // return the masked number
        return maskedNumber.toString();
    }
}
