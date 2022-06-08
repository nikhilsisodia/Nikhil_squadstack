package automatedTicketingSystem;

import java.util.ArrayList;
import java.util.stream.Collectors;
import automatedTicketingSystem.exception.ParkingSpotAlreadyVacantException;
import automatedTicketingSystem.exception.ParkingSpotNotVacantException;
import automatedTicketingSystem.exception.ParkingFullException;
import automatedTicketingSystem.exception.InvalidParkingSpotException;
import automatedTicketingSystem.exception.VehicleNotFoundException;

public class AutomatedTicketingSystem {
  private ParkingLot parkingLot;

  private String createParkingLot(String[] tokens) {
    Integer capacity = Integer.parseInt(tokens[1]);
    this.parkingLot = new ParkingLot(capacity);
    return "Created parking of " + capacity + " slots";
  }

  private String park(String[] tokens)
      throws ParkingFullException, InvalidParkingSpotException, ParkingSpotNotVacantException {
    String registrationNumber = tokens[1];
    Integer driverAge = Integer.parseInt(tokens[3]);
    ParkedCar car = new ParkedCar(registrationNumber, driverAge);
    Integer slotNumber = this.parkingLot.getNearestVacantPosition();
    this.parkingLot.parkCar(car, slotNumber);
    return "Car with vehicle registration number \"" + registrationNumber + "\" has been parked at slot number "
        + slotNumber;
  }

  private String slotNumbersForDriverOfAge(String[] tokens) {
    Integer driverAge = Integer.parseInt(tokens[1]);
    ArrayList<Integer> slotNumbers = this.parkingLot.getSlotNumbersByDriverAge(driverAge);
    return slotNumbers.stream().map(String::valueOf).collect(Collectors.joining(","));
  }

  private String slotNumberForCarWithNumber(String[] tokens) throws VehicleNotFoundException {
    String registrationNumber = tokens[1];
    Integer slotNumber = this.parkingLot.getSlotNumberFromRegistrationNumber(registrationNumber);
    return "Car with vehicle registration number \"" + registrationNumber + "\" has been parked at slot number "
        + slotNumber;
  }

  private String leave(String[] tokens) throws InvalidParkingSpotException, ParkingSpotAlreadyVacantException {
    Integer slotNumber = Integer.parseInt(tokens[1]);
    this.parkingLot.validateParkingSpot(slotNumber);
    if (this.parkingLot.isParkingSpotEmpty(slotNumber)) {
      return "Slot already vacant";
    }
    ParkedCar car = this.parkingLot.vacateParkingSpot(slotNumber);
    return "Slot number " + slotNumber + " vacated, the car with vehicle registration number \""
        + car.registrationNumber + "\" left the space, the driver of the car was of age " +
        car.driverAge;
  }

  private String vehicleRegistrationNumberForDriverOfAge(String[] tokens) {
    Integer driverAge = Integer.parseInt(tokens[1]);
    ArrayList<String> registrationNumbers = this.parkingLot.getRegistrationNumbersByDriverAge(driverAge);
    return registrationNumbers.stream().collect(Collectors.joining(","));
  }

  public String processCommand(String command) {
    String[] tokens = command.split(" ");
    String commandName = tokens[0];
    try {
      switch (commandName) {
        case "Create_parking_lot":
          return this.createParkingLot(tokens);
        case "Park":
          return this.park(tokens);
        case "Slot_numbers_for_driver_of_age":
          return this.slotNumbersForDriverOfAge(tokens);
        case "Slot_number_for_car_with_number":
          return this.slotNumberForCarWithNumber(tokens);
        case "Leave":
          return this.leave(tokens);
        case "Vehicle_registration_number_for_driver_of_age":
          return this.vehicleRegistrationNumberForDriverOfAge(tokens);
        default:
          return "Command name \"" + commandName + "\" invalid";
      }
    } catch (Exception e) {
      return "Something went wrong: " + e.getMessage();
    }
  }
}
