package com.ps;

import java.util.ArrayList;
import java.util.List;

// Represents car dealership
class Dealership {
    private String name;
    private String address;
    private String phone;
    private List<Vehicle> inventory; // List of vehicles in the inventory.

    // Constructor to initialize the dealership with name, address and phone.
    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    // Getter method for dealership name.
    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPhone() {
        return this.phone;
    }


    public Vehicle findVehicleByVin(int vin){
        for(Vehicle vehicle : inventory) {
            if(vehicle.getVin() == vin) {
                return vehicle;
            }
        }
        return null;
    }

    //  Method to get vehicles within a specified price range
    public List<Vehicle> getVehiclesByPrice(double minPrice, double maxPrice) {
        // takes minPrice and maxPrice as parameters representing the minimum and maximum prices.
        List<Vehicle> vehiclesByPrice = new ArrayList<>(); // Creates list to store vehicles within price range
        for (Vehicle vehicle : inventory) { // Iterates through vehicles in inventory
            if (vehicle.getPrice() >= minPrice && vehicle.getPrice() <= maxPrice) { // Checks if price falls within range.
                vehiclesByPrice.add(vehicle); // // Add vehicles to list if price is within range
            }
        }
        return vehiclesByPrice; // Returns list of vehicles within price range.
    }

    // Method to get vehicles by make and model
    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        List<Vehicle> vehiclesByMakeModel = new ArrayList<>(); // Creates a new list to store vehicles that match the make and model
        for (Vehicle vehicle : inventory) { // iterates through the inventory of vehicles
            if (vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)) { // Checks if the vehicle make and model matches the specified make and model
                vehiclesByMakeModel.add(vehicle); // If vehicle matches add it to list.
            }
        }
        return vehiclesByMakeModel; // Returns list of vehicles that match the specified make and model.
    }

    // Method to get vehicles within a specified year range.
    public List<Vehicle> getVehiclesByYear(int minYear, int maxYear) {
        List<Vehicle> vehiclesByYear = new ArrayList<>(); // Create a new list to store vehicles within the specified year range.
        for (Vehicle vehicle : inventory) { // Iterate through the inventory of vehicles.
            if (vehicle.getYear() >= minYear && vehicle.getYear() <= maxYear) { // This checks if the vehicle's year falls within the specified range.
                vehiclesByYear.add(vehicle); // If the vehicle's year falls within the range, add it to the list.
            }
        }
        return vehiclesByYear;
    }

    // Method to get vehicles by color
    public List<Vehicle> getVehiclesByColor(String color) {
        List<Vehicle> vehiclesByColor = new ArrayList<>(); //Create a new list to store vehicles that match the specified color
        for (Vehicle vehicle : inventory) { // Iterate through inventory of vehicle
            if (vehicle.getColor().equalsIgnoreCase(color)) { // Checks if the vehicle's color matches the specified color(case-insensitive)
                vehiclesByColor.add(vehicle); // If the vehicle matches, add it to the list
            }
        }
        return vehiclesByColor;
    }

    // Method to get vehicles within a specified mileage range
    public List<Vehicle> getVehiclesByMileage(int minMileage, int maxMileage) {
        List<Vehicle> vehiclesByMileage = new ArrayList<>(); // Create a new list to store vehicles within the specified mileage range
        for (Vehicle vehicle : inventory) { // Iterate through the inventory of vehicles
            if (vehicle.getOdometer() >= minMileage && vehicle.getOdometer() <= maxMileage) { // Checks if the vehicle's mileage falls within the specified range.
                vehiclesByMileage.add(vehicle);
            }
        }
        return vehiclesByMileage; // Returns the list of vehicles within the specified mileage range.
    }

    // Method to get vehicles by type.
    public List<Vehicle> getVehiclesByType(String vehicleType) {
        List<Vehicle> vehiclesByType = new ArrayList<>(); // Creates a new list to store vehicles by type.
        for (Vehicle vehicle : inventory) { // Iterates through the inventory of vehicles.
            if (vehicle.getVehicleType().equalsIgnoreCase(vehicleType)) { // Checks if the vehicle matches(case-insensitive)
                vehiclesByType.add(vehicle); // If it matches then add it to the list.
            }
        }
        return vehiclesByType; // Then return the list of vehicles of the specified type.
    }

    public List<Vehicle> getAllVehicles() { // A method to get all vehicles into the inventory
        return this.inventory;
    }

    public void addVehicle(Vehicle vehicle) { // A method to add vehicles into the inventory
        this.inventory.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) { // A method to remove vehicles into the inventory
        this.inventory.remove(vehicle);
    }
}
