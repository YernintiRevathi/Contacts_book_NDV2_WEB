// Import the Scanner for user input and List/ArrayList for handling collections of contacts.
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * The main class for the Contact Book application.
 * This class contains the main method, which is the entry point of the program.
 * It handles the user interface (console menu) and orchestrates the calls to the ContactDAO.
 */
public class Main {
    public static void main(String[] args) {
        // Create a Scanner object to read input from the console.
        Scanner scanner = new Scanner(System.in);
        // Create an instance of the ContactDAO to interact with the database.
        ContactDAO dao = new ContactDAO();

        // An infinite loop to keep the application running until the user decides to exit.
        while (true) {
            // Display the main menu to the user.
            System.out.println("\n====== Contact Book Menu ======");
            System.out.println("1. Add Contact");
            System.out.println("2. View All Contacts");
            System.out.println("3. Search by Name");
            System.out.println("4. Update Contact");
            System.out.println("5. Delete Contact");
            System.out.println("6. Exit");
            System.out.print("Choose an option (1-6): ");

            try {
                // Read the user's integer choice.
                int choice = scanner.nextInt();
                // Consume the leftover newline character from the input buffer.
                // This is crucial to prevent issues with subsequent nextLine() calls.
                scanner.nextLine();

                // A switch statement to perform an action based on the user's choice.
                switch (choice) {
                    case 1: // Add Contact
                        System.out.print("Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Phone Number: ");
                        String phone = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Address: ");
                        String address = scanner.nextLine();

                        // Create a new Contact object with the user's input.
                        Contact newContact = new Contact(name, phone, email, address);
                        // Call the DAO method to add the contact to the database.
                        dao.addContact(newContact);
                        break;

                    case 2: // View All Contacts
                        // Get the list of all contacts from the DAO.
                        List<Contact> allContacts = dao.getAllContacts();
                        if (allContacts.isEmpty()) {
                            System.out.println("No contacts found.");
                        } else {
                            System.out.println("\n--- All Contacts ---");
                            // Iterate through the list and print each contact using its toString() method.
                            for (Contact c : allContacts) {
                                System.out.println(c);
                            }
                        }
                        break;

                    case 3: // Search by Name
                        System.out.print("Enter name to search: ");
                        String searchName = scanner.nextLine();
                        // Get the list of contacts that match the search name.
                        List<Contact> results = dao.searchContacts(searchName);
                        if (results.isEmpty()) {
                            System.out.println("No contacts found with that name.");
                        } else {
                            System.out.println("\n--- Search Results ---");
                            for (Contact c : results) {
                                System.out.println(c);
                            }
                        }
                        break;

                    case 4: // Update Contact
                        System.out.print("Enter ID of contact to update: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("New Name: ");
                        String newName = scanner.nextLine();
                        System.out.print("New Phone Number: ");
                        String newPhone = scanner.nextLine();
                        System.out.print("New Email: ");
                        String newEmail = scanner.nextLine();
                        System.out.print("New Address: ");
                        String newAddress = scanner.nextLine();

                        // Create a Contact object with the new details and the original ID.
                        Contact updatedContact = new Contact(updateId, newName, newPhone, newEmail, newAddress);
                        // Call the DAO to update the contact in the database.
                        dao.updateContact(updatedContact);
                        break;

                    case 5: // Delete Contact
                        System.out.print("Enter ID of contact to delete: ");
                        int deleteId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        // Call the DAO to delete the contact by its ID.
                        dao.deleteContact(deleteId);
                        break;

                    case 6: // Exit
                        System.out.println("👋 Exiting Contact Book. Goodbye!");
                        // Close the scanner to release system resources.
                        scanner.close();
                        // Terminate the application.
                        return;

                    default: // Invalid option
                        System.out.println(" Invalid option. Please choose a number between 1 and 6.");
                }
            } catch (InputMismatchException e) {
                // Catch the exception if the user enters non-integer input for the menu choice.
                System.out.println(" Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input from the scanner.
            }
        }
    }
}