public class Client {

    private int idClient;
    private String name;
    private String surname;
    private String address;
    private String phoneNumber;

    public Client(int idClient, String name, String surname, String address, String phoneNumber) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Imię nie może być puste.");
        }
        if (!name.matches("[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ]+")) {
            throw new IllegalArgumentException("Imię może zawierać tylko litery.");
        }
        if (surname == null || surname.trim().isEmpty()) {
            throw new IllegalArgumentException("Nazwisko nie może być puste.");
        }
        if (!surname.matches("[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ]+")) {
            throw new IllegalArgumentException("Nazwisko może zawierać tylko litery.");
        }
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Adres nie może być pusty.");
        }
        String phonePattern = "\\+\\d{2} \\d{3} \\d{3} \\d{3}";
        if (phoneNumber == null || !phoneNumber.matches(phonePattern)) {
            throw new IllegalArgumentException("Numer telefonu musi mieć format: +xx xxx xxx xxx");
        }
        this.idClient = idClient;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }


    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }


    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
