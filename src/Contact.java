/**
 * This is a model class, also known as a POJO (Plain Old Java Object).
 * Its purpose is to represent a single contact and hold its data.
 * It encapsulates the properties of a contact: id, name, phone number, email, and address.
 */
public class Contact {
    // Private fields to store the attributes of a contact.
    // They are private to ensure data is accessed only through controlled methods (getters/setters).
    private int id;
    private String name;
    private String phoneNumber;
    private String email;
    private String address;

    /**
     * Constructor for creating a Contact object from data retrieved from the database.
     * This constructor includes the 'id' because the database has already assigned it.
     */
    public Contact(int id, String name, String phoneNumber, String email, String address) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    /**
     * Constructor for creating a new Contact object before saving it to the database.
     * This constructor does not include an 'id' because the database will generate it automatically.
     */
    public Contact(String name, String phoneNumber, String email, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    // --- Getters and Setters ---
    // Public methods to provide controlled access to the private fields.
    // Getters retrieve the value of a field.
    // Setters modify the value of a field.

    public int getId() { return id; }
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setEmail(String email) { this.email = email; }
    public void setAddress(String address) { this.address = address; }

    /**
     * Overrides the default toString() method from the Object class.
     * This provides a custom, human-readable string representation of the Contact object,
     * which is useful for printing contact details to the console.
     * @return A formatted string with the contact's details.
     */
    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Phone: %s | Email: %s | Address: %s",
            id, name, phoneNumber, email, address);
    }
}