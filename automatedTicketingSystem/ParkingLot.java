package automatedTicketingSystem;

import java.util.*;
import automatedTicketingSystem.exception.InvalidParkingSpotException;
import automatedTicketingSystem.exception.ParkingFullException;
import automatedTicketingSystem.exception.ParkingSpotAlreadyVacantException;
import automatedTicketingSystem.exception.ParkingSpotNotVacantException;
import automatedTicketingSystem.exception.VehicleNotFoundException;

public class ParkingLot {

  ParkedCar cars[];

  ParkingLot(int capacity) {
    this.cars = new ParkedCar[capacity];
    for (int i = 0; i < capacity; i++) {
      this.cars[i] = null;
    }
  }

  public void validateParkingSpot(int parkingSpot) throws InvalidParkingSpotException {
    if (parkingSpot < 1 || parkingSpot > cars.length) {
      throw new InvalidParkingSpotException(parkingSpot);
    }
  }

  public boolean isParkingSpotEmpty(int parkingSpot) {
    return this.cars[parkingSpot - 1] == null;
  }

  public void parkCar(ParkedCar car, int parkingSpot)
      throws InvalidParkingSpotException, ParkingSpotNotVacantException {
    this.validateParkingSpot(parkingSpot);
    if (!this.isParkingSpotEmpty(parkingSpot)) {
      throw new ParkingSpotNotVacantException(parkingSpot);
    }
    cars[parkingSpot - 1] = car;
  }

  public int getNearestVacantPosition() throws ParkingFullException {
    for (int i = 1; i <= this.cars.length; i++) {
      if (this.isParkingSpotEmpty(i)) {
        return i;
      }
    }
    throw new ParkingFullException();
  }

  public ParkedCar vacateParkingSpot(Integer slotNumber)
      throws InvalidParkingSpotException, ParkingSpotAlreadyVacantException {
    this.validateParkingSpot(slotNumber);
    if (this.isParkingSpotEmpty(slotNumber)) {
      throw new ParkingSpotAlreadyVacantException(slotNumber);
    }
    ParkedCar leavingCar = this.cars[slotNumber - 1];
    this.cars[slotNumber - 1] = null;
    return leavingCar;
  }

  public ArrayList<String> getRegistrationNumbersByDriverAge(int driverAge) {
    ArrayList<String> registrationNumbers = new ArrayList<String>();
    for (ParkedCar car : this.cars) {
      if (car == null) {
        continue;
      }
      if (car.driverAge == driverAge) {
        registrationNumbers.add(car.registrationNumber);
      }
    }
    return registrationNumbers;
  }

  public Integer getSlotNumberFromRegistrationNumber(String registrationNumber) throws VehicleNotFoundException {
    for (int i = 0; i < this.cars.length; i++) {
      if (this.cars[i] == null) {
        continue;
      }
      if (this.cars[i].registrationNumber.equals(registrationNumber)) {
        return i + 1;
      }
    }
    throw new VehicleNotFoundException(registrationNumber);
  }

  public ArrayList<Integer> getSlotNumbersByDriverAge(int driverAge) {
    ArrayList<Integer> slotNumbers = new ArrayList<Integer>();
    for (int i = 0; i < this.cars.length; i++) {
      if (this.cars[i] == null) {
        continue;
      }
      if (this.cars[i].driverAge == driverAge) {
        slotNumbers.add(i + 1);
      }
    }
    return slotNumbers;
  }
}
