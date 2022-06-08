package automatedTicketingSystem.exception;

public class ParkingSpotAlreadyVacantException extends Exception {

  public ParkingSpotAlreadyVacantException(int parkingSpot) {
    super("The spot " + parkingSpot + " is already vacant");
  }
}