package com.ps;

// This class represents the LeaseContract which is a type of contract where a vehicle is leased to a customer.
public class LeaseContract extends Contract {
    // Constants for specific
    private static final double leaseFeeRate = 0.07;
    private static final double financeRate = 0.04;
    private static final int financeMonths = 36;

    private double expectedEndingValue; // Expected value of the vehicle at the end of the lease term.

    // Constructor with parameters to initalize the lease contract details.
    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, double expectedEndingValue) {
        super(date, customerName, customerEmail, vehicleSold);
        this.expectedEndingValue = expectedEndingValue;
    }

    // Another constructor with additional lease contract details.
    public LeaseContract(String contractDate, String customerName, String customerEmail, Vehicle vehicle, double totalPrice, double monthlyPayment, double expectedEndingValue, double leaseFee, int leasingTerm) {
        super(contractDate,customerName,customerEmail,vehicle);
    }

    // Getter and setter for expected ending value
    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }


    // Override methods that calculate the total price nad the monthly payment
    @Override
    public double getTotalPrice() {
        return getVehicleSold().getPrice() * (1 + leaseFeeRate);
    }

    @Override
    public double getMonthlyPayment() {
        double residualValue = getVehicleSold().getPrice() * expectedEndingValue;
        double leaseAmount = getTotalPrice() - residualValue;
        double interest = (leaseAmount * financeRate) / financeMonths;
        return (leaseAmount + interest) / financeMonths;
    }
}

