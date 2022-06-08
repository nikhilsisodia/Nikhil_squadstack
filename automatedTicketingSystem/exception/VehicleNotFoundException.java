package automatedTicketingSystem.exception;

public class VehicleNotFoundException extends Exception {

  public VehicleNotFoundException(String registrationNumber) {
    super("The car with the registration number " + registrationNumber + " could not be found");
  }
}