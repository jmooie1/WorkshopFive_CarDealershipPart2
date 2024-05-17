package com.ps;

public class SalesContract extends Contract {
    // Constant variables
    private static final double salesTaxRate = 0.05;
    private static final double recordingFee = 100.0;
    private static final double processingFeeUnder10000 = 295.0;
    private static final double processingFeeOver10000 = 495.0;
    private static final double loanInterestRateUnder10000 = 0.0525;
    private static final double loanInterestRateOver10000 = 0.0425;
    private static final int loanMonthsUnder10000 = 25;
    private static final int loanMonthsOver10000 = 48;

    private boolean financeOption; // This indicates if the customer opts for financing.

    // Constructor with parameters to initialize contract details including
    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, boolean financeOption) {
        super(date, customerName, customerEmail, vehicleSold);
        this.financeOption = financeOption;
    }

    // Additional constructor without the finance option paramter.
    public SalesContract (String contractDate, String customerName, String customerEmail, Vehicle vehicle) {
        super(contractDate,customerName,customerEmail,vehicle);
    }

    // Getter and setter for the finance option.
    public boolean isFinanceOption() {
        return financeOption;
    }

    public void setFinanceOption(boolean financeOption) {
        this.financeOption = financeOption;
    }

    // Override methods to calculate the total prices and monthly payments.
    @Override
    public double getTotalPrice() {
        double totalPrice = getVehicleSold().getPrice() * (1 + salesTaxRate) + recordingFee;
        if (getVehicleSold().getPrice() < 10000) {
            totalPrice += processingFeeUnder10000;
        } else {
            totalPrice += processingFeeOver10000;
        }
        if (financeOption) {
            if (getVehicleSold().getPrice() >= 10000) {
                totalPrice += getVehicleSold().getPrice() * loanInterestRateOver10000 * loanMonthsUnder10000;
            } else {
                totalPrice += getVehicleSold().getPrice() * loanInterestRateUnder10000 * loanMonthsUnder10000;
            }
        }
        return totalPrice;
    }

    @Override
    public double getMonthlyPayment() {
        if (financeOption) {
            if (getVehicleSold().getPrice() >= 10000) {
                return getTotalPrice() / loanMonthsOver10000;
            } else {
                return getTotalPrice() / loanMonthsOver10000;
            }
        } else {
            return 0.0;
        }
    }
}
