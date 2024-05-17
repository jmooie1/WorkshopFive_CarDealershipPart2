package com.ps;


// This class represents a vehicle with various attributes such as VIN, year , make, model, and more.
// This class provides a getter and setter methods for accessing and modifying all of these attributes.
public class Vehicle {
    // All the attributes of the vehicle.
    private int vin; // Vehicle Identification Number AKA VIN
    private int year; // Year it was made.
    private String make; // The make of the vehicle such as Toyota or Honda.
    private String model; // Model of the vehicle such as the Camry or the Accord
    private String vehicleType; // Type of the vehicle (car, truck, or SUV)
    private String color; // Color of the vehicle.
    private int odometer; // Mileage or how many miles can it clock up to.
    private double price; // Price of the vehicle.
    private String VIN;
    private String type;

    // Constructor to initialize the vehicle object with the specified attributes from above.
    public Vehicle(int vin, int year, String make, String model, String vehicleType, String color, int odometer, double price) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vehicleType = vehicleType;
        this.color = color;
        this.odometer = odometer;
        this.price = price;

    }


    // All the getter and setter methods for accessing and modifying all of these attributes below.
    public int getVin() {
        return vin;
    }

    public void setVin(int vin) {
        this.vin = vin;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
