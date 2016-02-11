# Exercises

## Scenario: Loyalty Club Tracking

Our client, owning a chain of stores in many locations around the country, have a 
loyalty club which offers discounts for members when they purchase over a certain
amount per year.

Currently, they are tracking this manually by way of a paper card, on which
purchases are recorded in the form of amount and date of purchase. This card is valid
in all stores belonging to the chain.

When a customer has purchased goods for the amount of €100, they are offered a 
10% discount on further purchases up until a year from the first purchase.

Example:
I purchase goods for €50 on 2015-03-01, and another €50 worth on 2015-08-15. 
From then on, and until 2016-02-29, I get 10% discount on all purchases. After that,
the card is invalid, and my next purchase will start a new card.

This calculation is straigthforward once a customer is over the threshold, but in the
case where the customer crosses the threshold, the part of that purchase that exceeds
 €100 should be discounted.
 
Example:
I purchase goods for €50 on 2015-03-01, and pay the full €50 for them.
I then purchase for €100 on 2015-04-25. €50 of those takes me to the limit, and
on the remaining €50 I get 10% discount, so I pay €50+€45 = €95 for that purchase.

