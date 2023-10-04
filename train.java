import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class Train {
    private String name;
    private int capacity;
    private int availableSeats;

    public Train(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.availableSeats = capacity;
    }

    public String getName() {
        return name;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public boolean reserveSeats(int numSeats) {
        if (numSeats <= availableSeats) {
            availableSeats -= numSeats;
            return true;
        } else {
            return false;
        }
    }

    public void cancelReservation(int numSeats) {
        availableSeats += numSeats;
    }

    @Override
    public String toString() {
        return "Train: " + name + ", Capacity: " + capacity + ", Available Seats: " + availableSeats;
    }
}

class ReservationSystem {
    private List<Train> trains;

    public ReservationSystem() {
        trains = new ArrayList<>();
    }

    public void addTrain(String name, int capacity) {
        trains.add(new Train(name, capacity));
    }

    public void displayTrains() {
        System.out.println("Available Trains:");
        for (Train train : trains) {
            System.out.println(train);
        }
    }

    public void makeReservation(String trainName, int numSeats) {
        for (Train train : trains) {
            if (train.getName().equalsIgnoreCase(trainName)) {
                if (train.reserveSeats(numSeats)) {
                    System.out.println("Reservation successful!");
                } else {
                    System.out.println("Sorry, there are not enough available seats.");
                }
                return;
            }
        }
        System.out.println("Train not found.");
    }

    public void cancelReservation(String trainName, int numSeats) {
        for (Train train : trains) {
            if (train.getName().equalsIgnoreCase(trainName)) {
                train.cancelReservation(numSeats);
                System.out.println("Reservation canceled.");
                return;
            }

        }
        System.out.println("Train not found.");
    }
}

public class TrainReservationSystem {
    public static void main(String[] args) {
        ReservationSystem reservationSystem = new ReservationSystem();
        reservationSystem.addTrain("Express 1", 100);
        reservationSystem.addTrain("Local 1", 50);
        reservationSystem.addTrain("Express 2", 120);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nTrain Reservation System");
            System.out.println("1. Display Available Trains");
            System.out.println("2. Make a Reservation");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    reservationSystem.displayTrains();
                    break;
                case 2:
                    System.out.print("Enter the train name: ");
                    String trainName = scanner.next();
                    System.out.print("Enter the number of seats to reserve: ");
                    int numSeats = scanner.nextInt();
                    reservationSystem.makeReservation(trainName, numSeats);
                    break;
                case 3:
                    System.out.print("Enter the train name: ");
                    trainName = scanner.next();
                    System.out.print("Enter the number of seats to cancel: ");
                    numSeats = scanner.nextInt();
                    reservationSystem.cancelReservation(trainName, numSeats);
                    break;
                case 4:
                    System.out.println("Thank you for using the Train Reservation System!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

