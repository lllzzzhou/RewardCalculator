# RewardsCalculator

## Description
A retailer offers a rewards program to its customers, awarding points based on each recorded purchase. 
 
A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction 
(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
 
Given a record of every transaction during a three-month period, calculate the reward points earned for each customer per month and total.


## Instructions
This project is of jdk17, SpringBoot 3

Download and run the project
Open a web browser and 
- type "http://localhost:8080/customers/1", you would see a page showing {"customerId":1,"lastFirstMonthRewards":72,"lastSecondMonthRewards":38,"lastThirdMonthRewards":0,"totalRewards":110}
- you can try with "http://localhost:8080/customers/2" or "http://localhost:8080/customers/3"
- if you type "http://localhost:8080/customers/4", it will be showing a msg with all rewards equal to 0
- if you type "http://localhost:8080/customers/5" or any number other than 1 to 4, you will get a message saying customer not found

Also, you can type in the browser "http://localhost:8080/h2-console" to check the schema of the database, be sure to input like below in the fields:

![Screenshot 2023-01-12 at 22 31 51](https://user-images.githubusercontent.com/122585123/212230895-554d2572-7268-409f-93c9-fe9e2f6c79e8.png)
- username is sa
- password just leave it blank
