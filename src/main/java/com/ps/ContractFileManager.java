package com.ps;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// This class manages all the saving of contracts to a file
public class ContractFileManager {
    private static final String CONTRACTS_FILE = "contracts.txt";

    // A method to append the contact data into the file.
    public void appendContract(String contractData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONTRACTS_FILE, true))) {
            writer.write(contractData); // Writes a contact data to the file.
            writer.newLine(); // Adds a newline separator.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // A method to save a contract object to the file.
    public void saveContract(Contract contract) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONTRACTS_FILE, true))) {
            writer.write(contract.toString()); // Write contract details to the file
            writer.newLine(); // Add a newline separator
            System.out.println("Contract saved successfully."); // Prints a success message and vice versa.
        } catch (IOException e) {
            System.err.println("Error saving contract: " + e.getMessage());
        }
    }
}

