package com.ps;

public class SalesContract extends Contract {
    private static final double SALES_TAX_RATE = 0.05;
    private static final double RECORDING_FEE = 100.0;
    private static final double PROCESSING_FEE_UNDER_10000 = 295.0;
    private static final double PROCESSING_FEE_OVER_10000 = 495.0;
    private static final double LOAN_INTEREST_RATE_UNDER_10000 = 0.0525;
    private static final double LOAN_INTEREST_RATE_OVER_10000 = 0.0425;
    private static final int LOAN_MONTHS_UNDER_10000 = 25;
    private static final int LOAN_MONTHS_OVER_10000 = 48;

    private boolean financeOption;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, boolean financeOption) {
        super(date, customerName, customerEmail, vehicleSold);
        this.financeOption = financeOption;
    }

    public SalesContract (String contractDate, String customerName, String customerEmail, Vehicle vehicle) {
        super(contractDate,customerName,customerEmail,vehicle);
    }


    public boolean isFinanceOption() {
        return financeOption;
    }

    public void setFinanceOption(boolean financeOption) {
        this.financeOption = financeOption;
    }

    @Override
    public double getTotalPrice() {
        double totalPrice = getVehicleSold().getPrice() * (1 + SALES_TAX_RATE) + RECORDING_FEE;
        if (getVehicleSold().getPrice() < 10000) {
            totalPrice += PROCESSING_FEE_UNDER_10000;
        } else {
            totalPrice += PROCESSING_FEE_OVER_10000;
        }
        if (financeOption) {
            if (getVehicleSold().getPrice() >= 10000) {
                totalPrice += getVehicleSold().getPrice() * LOAN_INTEREST_RATE_OVER_10000 * LOAN_MONTHS_OVER_10000;
            } else {
                totalPrice += getVehicleSold().getPrice() * LOAN_INTEREST_RATE_UNDER_10000 * LOAN_MONTHS_UNDER_10000;
            }
        }
        return totalPrice;
    }

    @Override
    public double getMonthlyPayment() {
        if (financeOption) {
            if (getVehicleSold().getPrice() >= 10000) {
                return getTotalPrice() / LOAN_MONTHS_OVER_10000;
            } else {
                return getTotalPrice() / LOAN_MONTHS_UNDER_10000;
            }
        } else {
            return 0.0;
        }
    }
}
