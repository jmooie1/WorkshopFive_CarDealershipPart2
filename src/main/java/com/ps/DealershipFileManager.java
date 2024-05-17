package com.ps;

import java.io.*;

class DealershipFileManager {
    private String filename; // Filename of data file.

    // Constructor to initialize DealerShipFileManager with a filename

    public DealershipFileManager(String filename) {
        this.filename = filename;
    }

    // Reads dealership data from a file and returns a Dealership object.
    // Dealership object reads from the file or null if any kind of error occurs.
    public Dealership getDealership() {
        // Reads the dealership information from the first line of the file.
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) { // reads the first line containing dealership info.
            String dealershipInfo = reader.readLine(); // Read the first line containing dealership info
            String[] dealershipData = dealershipInfo.split("\\|"); // Splits the line into parts using "|" as a delimiter.
            String name = dealershipData[0]; // This extracts the dealership name
            String address = dealershipData[1]; // This extract dealership address
            String phone = dealershipData[2]; // This extracts the dealership phone numbers.

            Dealership dealership = new Dealership(name, address, phone); // Creates new dealership object with the read information.

            // Read vehicle information from subsequent lines
            String line; // Variable to store each line read from the file.
            while ((line = reader.readLine()) != null) {
                String[] vehicleData = line.split("\\|"); // Splits the line into parts using "|" as delimiter
                int vin = Integer.parseInt(vehicleData[0]); // Extracts vehicle VIN and convert to integer
                int year = Integer.parseInt(vehicleData[1]); // Extracts vehicle year and converts it to an integer.
                String make = vehicleData[2]; // Extracts vehicle make
                String model = vehicleData[3]; // Extract vehicle model
                String vehicleType = vehicleData[4]; // Extracts vehicle type
                String color = vehicleData[5]; // Extracts vehicle color
                int odometer = Integer.parseInt(vehicleData[6]); // Extracts vehicle odometer reading and converts it to an integer.
                double price = Double.parseDouble(vehicleData[7]); // Extracts vehicle price and converts it to a double.

                // Creates a new vehicle object with the read information and adds it to the dealership.
                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                dealership.addVehicle(vehicle);
            }

            return dealership; // Returns the constructed dealership object.
        } catch (IOException e) {
            // Prints error message if an IOException occurs
            System.out.println("Error occurred while reading dealership data: " + e.getMessage());
            return null; // Returns null to show failure.
        }
    }

    // Writes dealership data to the file.
    public void saveDealership(Dealership dealership) {
        try (PrintWriter write = new PrintWriter(new FileWriter(filename))) {
            // Writes dealership information to the file
            write.println(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());

            // Writes vehicle information to the file
            for (Vehicle vehicle : dealership.getAllVehicles()) {
                // Writes each vehicle's data in this type of format: VIN|Year|Make|Model|Type|Color|Mileage|Price
                write.println(vehicle.getVin() + "|" + vehicle.getYear() + "|" + vehicle.getMake() + "|" +
                        vehicle.getModel() + "|" + vehicle.getVehicleType() + "|" + vehicle.getColor() + "|" +
                        vehicle.getOdometer() + "|" + vehicle.getPrice());
            }
            // Prints the success message
            System.out.println("Dealership data saved successfully.");
        } catch (IOException e) {
            // Prints the error message if an IOException occurs.
            System.out.println("Error occurred while saving dealership data: " + e.getMessage());
        }
    }
}