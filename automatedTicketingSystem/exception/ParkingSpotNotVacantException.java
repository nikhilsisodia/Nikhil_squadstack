package automatedTicketingSystem.exception;

public class ParkingSpotNotVacantException extends Exception {

  public ParkingSpotNotVacantException(int parkingSpot) {
    super("The spot " + parkingSpot + " is not vacant");
  }
}