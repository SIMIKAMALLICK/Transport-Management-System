public interface CourierService {
    double calculateCharges();
    void displayDetails();
    void updateStatus(String status) throws InvalidStatusException;
}
