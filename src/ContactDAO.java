import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) for the Contact entity.
 * This class handles all the database operations (CRUD - Create, Read, Update, Delete)
 * related to contacts. It isolates the data access logic from the main application logic.
 */
public class ContactDAO {

    /**
     * 1. Adds a new contact to the database.
     * @param contact The Contact object containing the details to be saved.
     */
    public void addContact(Contact contact) {
        // The SQL query for inserting a new record.
        // The '?' are placeholders for the values that will be set later. This is part of a PreparedStatement.
        String sql = "INSERT INTO contacts (name, phone_number, email, address) VALUES (?, ?, ?, ?)";

        // 'try-with-resources' statement ensures that the Connection and PreparedStatement are automatically closed.
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Bind the values from the contact object to the placeholders in the SQL statement.
            // This is safer than string concatenation as it prevents SQL injection.
            stmt.setString(1, contact.getName());       // Binds name to the first '?'
            stmt.setString(2, contact.getPhoneNumber()); // Binds phone number to the second '?'
            stmt.setString(3, contact.getEmail());       // Binds email to the third '?'
            stmt.setString(4, contact.getAddress());     // Binds address to the fourth '?'

            // Execute the query. executeUpdate() is used for INSERT, UPDATE, or DELETE statements.
            stmt.executeUpdate();
            System.out.println("✅ Contact added successfully!");

        } catch (SQLException e) {
            System.out.println("Error adding contact: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 2. Retrieves a list of all contacts from the database.
     * @return A List of Contact objects. The list will be empty if no contacts are found.
     */
    public List<Contact> getAllContacts() {
        // Create an empty list to store the contacts that are retrieved.
        List<Contact> contacts = new ArrayList<>();
        // SQL query to select all records from the contacts table.
        String sql = "SELECT * FROM contacts";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             // The ResultSet object holds the data returned by a SELECT query.
             ResultSet rs = stmt.executeQuery(sql)) {

            // Loop through each row in the ResultSet.
            // rs.next() moves the cursor to the next row and returns false when there are no more rows.
            while (rs.next()) {
                // Create a new Contact object for each row.
                Contact contact = new Contact(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone_number"),
                        rs.getString("email"),
                        rs.getString("address")
                );
                // Add the newly created Contact object to the list.
                contacts.add(contact);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving contacts: " + e.getMessage());
            e.printStackTrace();
        }
        // Return the list of contacts.
        return contacts;
    }

    /**
     * 3. Searches for contacts by name.
     * @param name The name (or part of the name) to search for.
     * @return A List of contacts that match the search criteria.
     */
    public List<Contact> searchContacts(String name) {
        List<Contact> contacts = new ArrayList<>();
        // SQL query to find contacts where the name is 'LIKE' the search term.
        String sql = "SELECT * FROM contacts WHERE name LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the search term for the 'LIKE' clause.
            // The '%' are wildcard characters, so this will find any name that CONTAINS the search string.
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Create a Contact object for each matching record and add it to the list.
                Contact contact = new Contact(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone_number"),
                        rs.getString("email"),
                        rs.getString("address")
                );
                contacts.add(contact);
            }
        } catch (SQLException e) {
            System.out.println("Error searching contacts: " + e.getMessage());
            e.printStackTrace();
        }
        return contacts;
    }

    /**
     * 4. Updates an existing contact in the database.
     * @param contact The Contact object containing the updated details (must include the ID).
     */
    public void updateContact(Contact contact) {
        // SQL query to update a record. The WHERE clause is crucial to ensure only the correct record is updated.
        String sql = "UPDATE contacts SET name = ?, phone_number = ?, email = ?, address = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Bind the new values to the placeholders.
            stmt.setString(1, contact.getName());
            stmt.setString(2, contact.getPhoneNumber());
            stmt.setString(3, contact.getEmail());
            stmt.setString(4, contact.getAddress());
            stmt.setInt(5, contact.getId()); // Bind the ID for the WHERE clause.

            // executeUpdate() returns the number of rows affected by the query.
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Contact updated successfully!");
            } else {
                System.out.println("❌ Contact not found. Update failed.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating contact: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 5. Deletes a contact from the database by its ID.
     * @param id The ID of the contact to delete.
     */
    public void deleteContact(int id) {
        // SQL query to delete a record based on its ID.
        String sql = "DELETE FROM contacts WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Bind the ID to the placeholder in the WHERE clause.
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            // Check if a row was actually deleted.
            if (rowsAffected > 0) {
                System.out.println("🗑️ Contact deleted successfully!");
            } else {
                System.out.println("❌ Contact not found. Deletion failed.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting contact: " + e.getMessage());
            e.printStackTrace();
        }
    }
}