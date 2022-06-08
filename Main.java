import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

import automatedTicketingSystem.AutomatedTicketingSystem;

class Main {

  public static void main(String[] args) {

    AutomatedTicketingSystem system = new AutomatedTicketingSystem();

    File fp = new File("input.txt");
    try {
      FileReader fr = new FileReader(fp);
      BufferedReader br = new BufferedReader(fr);
      String command;
      while ((command = br.readLine()) != null) {
        String output = system.processCommand(command);
        System.out.println(output);
      }
      fr.close();
    } catch (Exception e) {
      System.out.println("Something went wrong: " + e.getMessage());
    }
  }
}