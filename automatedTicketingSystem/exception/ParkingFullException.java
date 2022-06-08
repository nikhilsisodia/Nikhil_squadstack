package automatedTicketingSystem.exception;

public class ParkingFullException extends Exception {

  public ParkingFullException() {
    super("There are no vacant parking slots available");
  }
}