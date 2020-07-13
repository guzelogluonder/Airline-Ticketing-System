 # Airline Ticketing System

The aim of the program is airline creating and searching,airport creating and searching,route creating and searching,creating flight to airline and searching flight,buying flight ticket, searching ticket with ticket number and canceling ticket with ticket number.

 ## Getting Started

To build such system, spring boot framework v2.3.1 used. 

Prerequisites
To run the program, a maven is needed. Intellij has all premise installation requirements

## Logic

1. Create the airport with the name and iata code.   
2. Create the airline with name and two letter code.   
3. Create the route with origin and destination.  
4. Create the flight with two letter code, origin and destination.  
5. Create the ticket with origin, destination, two letter code and credit card number.

# Sample Calls

##### Request

http://localhost:8080/airports

##### Response
{
    "id": 1,
    "name": "Hatay Havalimani",
    "iataCode": "HTY",
    "createdAt": "2020-07-12T20:15:56.088+00:00",
    "updateAt": "2020-07-12T20:15:56.088+00:00"
}
##### Request
http://localhost:8080/airports
##### Response
{
    "id": 2,
    "name": "Esenboğa Havalimani",
    "iataCode": "ESB",
    "createdAt": "2020-07-12T20:16:55.948+00:00",
    "updateAt": "2020-07-12T20:16:55.948+00:00"
}
##### Request
http://localhost:8080/airlines
##### Response
{
    "id": 3,
    "name": "Pegasus",
    "twoLetterCode": "PG",
    "createdAt": "2020-07-12T19:58:07.499+00:00",
    "updateAt": "2020-07-12T19:58:07.499+00:00"
}
##### Request
http://localhost:8080/routes
##### Response
{
    "id": 4,
    "origin": "HTY",
    "destination": "ESB",
    "createdAt": "2020-07-12T19:58:09.532+00:00",
    "updateAt": "2020-07-12T19:58:09.532+00:00"
}
##### Request
http://localhost:8080/flights
##### Response
{
    "id": 5,
    "twoLetterCode": "PG",
    "originIataCode": "HTY",
    "destinationIataCode": "ESB",
    "ticketPrice": 795,
    "contingent": 10,
    "createdAt": "2020-07-12T20:20:04.057+00:00",
    "updateAt": "2020-07-12T20:20:04.057+00:00"
}
##### Request
http://localhost:8080/tickets
##### Response
{
    "id": 6,
    "originIataCode": "HTY",
    "destinationIataCode": "ESB",
    "airlineTwoLetterCode": "PG",
    "ticketNumber": 1857161794682,
    "creditCardNumber": "365478*****72654",
    "ticketPrice": 795,
    "createdAt": "2020-07-12T20:21:17.880+00:00",
    "updateAt": "2020-07-12T20:21:17.880+00:00"
}
## Built With

Maven - Dependency Management

## Versioning
v0.1

## Authors

Önder Guzeloglu - Initial work
