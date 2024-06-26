package com.ps;


// Abstract class and holds common information for all contracts.
public abstract class Contract {
    // Fields to store all the contract information.
    private String date;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSold;
    protected double totalPrice;
    protected double monthlyPayment;

    // Constructor to initialize contract details
    public Contract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    // abstract methods that are to be implemented.
    public abstract double getTotalPrice();

    public abstract double getMonthlyPayment();

    // Getters and setters
    public Vehicle getVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(Vehicle vehicleSold) {
        this.vehicleSold = vehicleSold;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}

