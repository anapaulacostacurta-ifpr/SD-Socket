import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

  private Socket client;
  private ObjectOutputStream output;
  private ObjectInputStream input;
  private String message = "";

  public void runClient() {
    try {
      System.out.println("Step 1: Create a Socket to make connection.");
      System.out.println("Attempting connection...");
      client = new Socket(InetAddress.getByName("localhost"), 5050);

      System.out.println("Connected to: " + client.getInetAddress().getHostName());

      System.out.println("Step 2: Get the output streams.");
      output = new ObjectOutputStream(client.getOutputStream());
      System.out.println("Step 2: output.flush()");
      output.flush();
      System.out.println("Step 2: Get the input streams.");
      input = new ObjectInputStream(client.getInputStream());
      System.out.println("Got I/O streams\n");

      // Step 3: Process connection.
      Scanner scanner = new Scanner(System.in);  // Read user input from terminal
        
      do {
        System.out.print("CLIENT>>> ");
        message = scanner.nextLine();
        output.writeObject("CLIENT>>> " + message);
        output.flush();
        try {
          message = (String) input.readObject();
          System.out.println("\n" + message);
        } catch (ClassNotFoundException cnfex) {
          System.out.println("\nUnknown object type received");
        }
        
      } while (!message.equalsIgnoreCase("SERVER>>> SAIR"));

      // Step 4: Close connection.
      System.out.println("Closing connection.");
      output.close();
      input.close();
      client.close();
    } catch (EOFException eof) {
      System.out.println("Server terminated connection");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String args[]) {
    Client app = new Client();
    app.runClient();
  }
}