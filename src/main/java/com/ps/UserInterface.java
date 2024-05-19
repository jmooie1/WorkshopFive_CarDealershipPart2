package com.ps;

import java.util.List;
import java.util.Scanner;

// This is a user interface class that manages the user interactions and commands.
class UserInterface {
    private Dealership dealership; // This references to the dealership.
    private Scanner scanner; // Scanner for user input.
    private ContractFileManager contractFileManager; // Manages contract files.

    // Constructor to initialize the scanner and loads the dealership.
    public UserInterface() {
        this.scanner = new Scanner(System.in); // Creates scanner object.
        this.contractFileManager = new ContractFileManager(); // This creates the contract file manager object.
        loadDealership(); // Loads dealership from a file.
    }

    // A method that loads the dealership from the file.
    private void loadDealership() {
        DealershipFileManager fileManager = new DealershipFileManager("dealership.txt");
        this.dealership = fileManager.getDealership();
    }

    // A method to display the user interface and handles the user commands.
    public void display() {
        boolean quit = false; // Variable to control the loop
        while (!quit) {
            // Displays the menu and process user input.

            System.out.println("1 - Find vehicles within a price range");
            System.out.println("2 - Find vehicles by make/model");
            System.out.println("3 - Find vehicles by year range");
            System.out.println("4 - Find vehicles by color");
            System.out.println("5 - Find vehicles by mileage range");
            System.out.println("6 - Find vehicles by type (car, truck, SUV, van)");
            System.out.println("7 - List ALL vehicles");
            System.out.println("8 - Add a vehicle");
            System.out.println("9 - Remove a vehicle");
            System.out.println("10 - Sell a Vehicle or Lease a Vehicle");
            System.out.println("99 - Quit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    processGetByPriceRequest(); // Find vehicles within a price range.
                    break;
                case 2:
                    processGetByMakeModelRequest(); // Finds vehicle by make/model
                    break;
                case 3:
                    processGetByYearRequest(); // Finds vehicle by year range.
                    break;
                case 4:
                    processGetByColorRequest(); // Finds vehicle by color
                    break;
                case 5:
                    processGetByMileageRequest(); // Finds vehicles by mileage range.
                    break;
                case 6:
                    processGetByVehicleTypeRequest(); // Finds vehicles by vehicle type.
                    break;
                case 7:
                    processAllVehiclesRequest(); // Lists all the vehicles in the dealership.
                    break;
                case 8:
                    processAddVehicleRequest(); // Adds a new vehicle to the dealership.
                    saveDealershipToFile(); // Saves info after adding a vehicle
                    break;
                case 9:
                    processRemoveVehicleRequest(); // Removes a vehicle form the dealership
                    saveDealershipToFile(); // Saves info after removing a vehicle
                    break;
                case 10:
                    processSellOrLeaseRequest(); // Checks to see if it is a sell or lease request
                    break;
                case 99:
                    quit = true; // Quits the program.
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid. Try again.");
            }
        }
    }

    // A method to handle the selling or leasing a vehicle.
    private void processSellOrLeaseRequest() {
        // Prompt the user for VIN #.
        System.out.println("Enter VIN of the vehicle to sell or lease: ");
        int vin = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Find the vehicle in dealership inventory
        Vehicle vehicle = dealership.findVehicleByVin(vin);
        if (vehicle != null) {
            // Prompt user to choose sale or lease
            System.out.println("Is this a sale or lease? Enter 'sale' or 'lease': ");
            String saleOrLease = scanner.nextLine();

            Contract contract;
            if ("sale".equalsIgnoreCase(saleOrLease)) {
                contract = createSalesContract(vehicle);
            } else if ("lease".equalsIgnoreCase(saleOrLease)) {
                contract = createLeaseContract(vehicle);
            } else {
                System.out.println("Invalid option.");
                return;
            }

            // Save contract and remove vehicle from inventory
            saveContract(contract);
            dealership.removeVehicle(vehicle);
            System.out.println("Vehicle sold or leased successfully.");
        } else {
            System.out.println("Vehicle not found in inventory.");
        }
    }

    // A method to create a sales contract.
    private SalesContract createSalesContract(Vehicle vehicle) {
        // Gather sales information from user
        Scanner scanner = new Scanner(System.in);

        // Get date of the contract (assuming it's a string)
        System.out.println("Enter date of the contract (YYYY:MM:DD): ");
        String contractDate = scanner.nextLine();

        // Get customer name
        System.out.println("Enter customer name: ");
        String customerName = scanner.nextLine();

        // Get customer email
        System.out.println("Enter customer email: ");
        String customerEmail = scanner.nextLine();

        // Get financing option
        System.out.println("Do they want to finance? (yes/no): ");
        String financeOption = scanner.nextLine();

        // Initialize monthly payment if financed
        double financedMonthlyPayment = 0.0;

        // If they want to finance, get the monthly payment
        if (financeOption.equalsIgnoreCase("yes")) {
            System.out.println("Enter monthly payment if financed: ");
            financedMonthlyPayment = scanner.nextDouble();
            scanner.nextLine(); // consume newline
        }

        // Now, create the SalesContract object
        return new SalesContract(contractDate, customerName, customerEmail, vehicle);

    }

    // A method to create a lease contract.
    private LeaseContract createLeaseContract(Vehicle vehicle) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter date of the contract (YYYY:MM:DD): ");
        String contractDate = scanner.nextLine();

        System.out.println("Enter customer name: ");
        String customerName = scanner.nextLine();

        System.out.println("Enter customer email: ");
        String customerEmail = scanner.nextLine();

        System.out.println("Enter total price: ");
        double totalPrice = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter monthly payment: ");
        double monthlyPayment = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter expected ending value: ");
        double expectedEndingValue = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter lease fee: ");
        double leaseFee = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter leasing term (in months): ");
        int leasingTerm = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        return new LeaseContract(contractDate, customerName, customerEmail, vehicle, totalPrice,
                monthlyPayment, expectedEndingValue, leaseFee, leasingTerm);
    }

    //A method to save a contract to the file.
    private void saveContract(Contract contract) {
        ContractFileManager fileManager = new ContractFileManager();
        fileManager.saveContract(contract);
    }

    // Method to handle finding vehicles within a price range
    private void processGetByPriceRequest() {
        // Inputs the minimum and maximum price
        System.out.print("Enter minimum price: ");
        double minPrice = scanner.nextDouble();
        System.out.print("Enter maximum price: ");
        double maxPrice = scanner.nextDouble();

        // Get vehicles by price range and display
        List<Vehicle> vehicles = dealership.getVehiclesByPrice(minPrice, maxPrice);
        displayVehicles(vehicles);
    }

    // A method to handle finding vehicles by make/model
    private void processGetByMakeModelRequest() {
        // Inputs make and model
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        // Get vehicles by make/model and display
        List<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(vehicles);
    }

    // Method to handle finding vehicles by year range
    private void processGetByYearRequest() {
        // Input minimum and maximum year
        System.out.print("Enter minimum year: ");
        int minYear = scanner.nextInt();
        System.out.print("Enter maximum year: ");
        int maxYear = scanner.nextInt();
        scanner.nextLine(); // Skips a line.

        // Get vehicles by year range and display
        List<Vehicle> vehicles = dealership.getVehiclesByYear(minYear, maxYear);
        displayVehicles(vehicles);
    }

    // Method to handle finding vehicles by color
    private void processGetByColorRequest() {
        // Inputs a color.
        System.out.print("Enter color: ");
        String color = scanner.nextLine().toLowerCase();

        // Get vehicles by color and display
        List<Vehicle> vehicles = dealership.getVehiclesByColor(color);
        displayVehicles(vehicles);
    }

    // Method to handle finding vehicles by mileage range
    private void processGetByMileageRequest() {
        // Inputs minimum and maximum mileage
        System.out.print("Enter minimum mileage: ");
        int minMileage = scanner.nextInt();
        System.out.print("Enter maximum mileage: ");
        int maxMileage = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Gets the vehicles by mileage range and display
        List<Vehicle> vehicles = dealership.getVehiclesByMileage(minMileage, maxMileage);
        displayVehicles(vehicles);
    }

    // Method to handle finding vehicles by type
    private void processGetByVehicleTypeRequest() {
        // Input vehicle type
        System.out.print("Enter vehicle type: ");
        String vehicleType = scanner.nextLine();

        // Get vehicles by type and display
        List<Vehicle> vehicles = dealership.getVehiclesByType(vehicleType);
        displayVehicles(vehicles);
    }

    // A method to display vehicles
    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles != null && !vehicles.isEmpty()) {
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle.getVin() + " - " + vehicle.getYear() + " " +
                        vehicle.getMake() + " " + vehicle.getModel());
            }
        } else {
            System.out.println("No vehicles found with the specified criteria.");
        }
    }

    // method to list all the vehicles
    private void processAllVehiclesRequest() {
        List<Vehicle> allVehicles = dealership.getAllVehicles();
        displayVehicles(allVehicles);
    }

    // Method to add a vehicle
    private void processAddVehicleRequest() {
        // Input vehicle details
        System.out.println("Enter vehicle details:");
        System.out.print("VIN: ");
        int vin = scanner.nextInt();
        System.out.print("Year: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Skips next line.
        System.out.print("Make: ");
        String make = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Vehicle Type: ");
        String vehicleType = scanner.nextLine();
        System.out.print("Color: ");
        String color = scanner.nextLine();
        System.out.print("Odometer: ");
        int odometer = scanner.nextInt();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Skips to next line.

        // Create new vehicle and add to dealership
        Vehicle newVehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        dealership.addVehicle(newVehicle);
        System.out.println("Vehicle added successfully.");
    }

    // Method to remove a vehicle
    private void processRemoveVehicleRequest() {
        // Input VIN of vehicle to remove
        System.out.print("Enter VIN of vehicle to remove: ");
        int vinToRemove = scanner.nextInt();
        scanner.nextLine(); // Skips to next line.

        List<Vehicle> vehicles = dealership.getAllVehicles();
        boolean removedVehicle = false;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVin() == vinToRemove) {
                dealership.removeVehicle(vehicle);
                saveDealershipToFile(); // Saves the dealership to a file
                removedVehicle = true;
                break;
            }
        }
        if (removedVehicle) {
            System.out.println("Vehicle removed successfully.");
        } else {
            System.out.println("Vehicle with VIN " + vinToRemove + " not found.");
        }
    }

    // Method to save the dealership to a file
    private void saveDealershipToFile() {
        DealershipFileManager fileManager = new DealershipFileManager("dealership.txt");
        fileManager.saveDealership(dealership);
    }
}




