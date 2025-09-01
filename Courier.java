public class Courier implements CourierService {
    private int courierId;
    private String sender;
    private String receiver;
    private double weight;
    private TransportMode mode;
    private double charges;
    private CourierStatus status;

    public Courier(int courierId, String sender, String receiver, double weight, String modeStr)
            throws InvalidTransportException, InvalidWeightException {
        this.courierId = courierId;
        this.sender = sender;
        this.receiver = receiver;
        setWeight(weight);
        setTransportMode(modeStr);
        this.charges = calculateCharges();
        this.status = CourierStatus.BOOKED; // default
    }

    private void setTransportMode(String modeStr) throws InvalidTransportException {
        try {
            this.mode = TransportMode.valueOf(modeStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidTransportException("Transport Mode " + modeStr + " is not available!");
        }
    }

    private void setWeight(double weight) throws InvalidWeightException {
        if (weight <= 0) {
            throw new InvalidWeightException("Weight must be greater than 0!");
        } else if (weight > 100) {
            throw new InvalidWeightException("Weight exceeds maximum limit (100kg)!");
        }
        this.weight = weight;
    }

    @Override
    public double calculateCharges() {
        double rate;
        switch (mode) {
            case TRAIN: rate = 10; break;
            case CAR: rate = 15; break;
            case FLIGHT: rate = 30; break;
            case SHIP: rate = 8; break;
            default: rate = 0;
        }
        return weight * rate;
    }

    @Override
    public void updateStatus(String status) throws InvalidStatusException {
        try {
            this.status = CourierStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidStatusException("Invalid Status: " + status + ". Valid: BOOKED, IN_TRANSIT, DELIVERED");
        }
    }

    @Override
    public void displayDetails() {
        System.out.println("Courier ID: " + courierId);
        System.out.println("Sender: " + sender);
        System.out.println("Receiver: " + receiver);
        System.out.println("Weight: " + weight + "kg");
        System.out.println("Transport: " + mode);
        System.out.println("Charges: ₹" + charges);
        System.out.println("Status: " + status);
        System.out.println("-----------------------------------");
    }
}
