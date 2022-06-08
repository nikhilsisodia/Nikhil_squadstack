package automatedTicketingSystem.exception;

public class InvalidParkingSpotException extends Exception {

  public InvalidParkingSpotException(int parkingSpot) {
    super("The spot " + parkingSpot + " is not valid");
  }
}