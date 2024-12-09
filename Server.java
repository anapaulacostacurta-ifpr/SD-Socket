import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

  private ServerSocket server;
  private Socket connection;
  private ObjectOutputStream output;
  private ObjectInputStream input;
  private String message = "";

  public void runServer() {
    try {
      // Step 1: Create a ServerSocket.
      server = new ServerSocket(5050, 100);

      while (true) {
        // Step 2: Wait for a connection.
        System.out.println("Waiting for connection...");
        connection = server.accept();

        System.out.println("Connection received from: " + connection.getInetAddress().getHostName());

        System.out.println("Step 3: Get output streams.");
        output = new ObjectOutputStream(connection.getOutputStream());
        System.out.println("Step 3: output.flush();");
        output.flush();
        System.out.println("Step 3: Get input streams.");
        input = new ObjectInputStream(connection.getInputStream());
        System.out.println("Got I/O streams\n");
        
        // Step 3: Process connection.
        Scanner scanner = new Scanner(System.in);  // Read user input from terminal

        do {
          try {
            message = (String) input.readObject();
            System.out.println("\n" + message);
          } catch (ClassNotFoundException cnfex) {
            System.out.println("\nUnknown object type received");
          }
          System.out.print("SERVER>>> ");
          message = scanner.nextLine();
          output.writeObject("SERVER>>> " + message);
          output.flush();
        } while (!message.equalsIgnoreCase("CLIENT>>> SAIR"));

        // Step 5: Close connection.
        System.out.println("\nUser terminated connection");
        output.close();
        input.close();
        connection.close();
      }
    } catch (EOFException eof) {
      System.out.println("Client terminated connection");
    } catch (IOException io) {
      io.printStackTrace();
    } finally {
      try {
        server.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String args[]) {
    Server app = new Server();
    app.runServer();
  }
}