package org.usfirst.frc.team1619.robot2016.IO.SocketTables;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SocketHandler extends Thread {

  private static HashSet<Socket> sockets;
  private static SmashBoardValues smashBoardValues;

  static {
    sockets = new HashSet<>();
  }

  private Socket socket;

  public static void initialize() {
    smashBoardValues = SmashBoardValues.getInstance();
  }

  private static void addSocket(Socket socket) {
    sockets.add(socket);
  }

  private static void removeSocket(Socket socket) {
    sockets.remove(socket);
  }

  private static void blast(JSONObject message) throws IOException {
    for (Socket socket : sockets) {
      if (!socket.isClosed()) {
        PrintWriter output = new PrintWriter(socket.getOutputStream());
        output.println(message);
        output.flush();
      }
    }
  }

  public SocketHandler(Socket socket) {
    addSocket(socket);
    this.socket = socket;

    try {
      sendCurrentValues();
    }
    catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  private void sendCurrentValues() throws IOException {
    JSONObject currentValuesMessage = new JSONObject();
    currentValuesMessage.put("type", "currentValues");
    currentValuesMessage.put("longs", smashBoardValues.getLongs());
    currentValuesMessage.put("doubles", smashBoardValues.getDoubles());
    currentValuesMessage.put("strings", smashBoardValues.getStrings());

    PrintWriter output = new PrintWriter(socket.getOutputStream());
    output.println(currentValuesMessage);
    output.flush();
  }

  @Override
  public void run() {
    try {
      BufferedReader input =
        new BufferedReader(new InputStreamReader(socket.getInputStream()));

      JSONParser jsonParser = new JSONParser();

      while (!socket.isClosed()) {
        String line = input.readLine();

        if (line != null && line.length() > 0) {
          try {
            JSONObject message = (JSONObject)jsonParser.parse(line);

            switch ((String)message.get("type")) {
              case "setLong": {
                String key = (String)message.get("key");
                long value = (long)message.get("value");

                updateLong(key, value);
                break;
              }
              case "setDouble": {
                String key = (String)message.get("key");
                double value = (double)message.get("value");

                updateDouble(key, value);
                break;
              }
              case "setString": {
                String key = (String)message.get("key");
                String value = (String)message.get("value");

                updateString(key, value);
                break;
              }
              case "disconnect":
                socket.close();
                removeSocket(socket);
                break;
            }
          }
          catch (ParseException exception) {
            exception.printStackTrace();
          }
        }
      }
    }
    catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  public static void updateLong(String key, long value) throws IOException {
    smashBoardValues.setLong(key, value);

    JSONObject updateMessage = new JSONObject();
    updateMessage.put("type", "updateLong");
    updateMessage.put("key", key);
    updateMessage.put("value", value);

    blast(updateMessage);
  }

  @SuppressWarnings("unchecked")
  public static void updateDouble(String key, double value) throws IOException {
    smashBoardValues.setDouble(key, value);

    JSONObject updateMessage = new JSONObject();
    updateMessage.put("type", "updateDouble");
    updateMessage.put("key", key);
    updateMessage.put("value", value);

    blast(updateMessage);
  }

  @SuppressWarnings("unchecked")
  public static void updateString(String key, String value) throws IOException {
    smashBoardValues.setString(key, value);

    JSONObject updateMessage = new JSONObject();
    updateMessage.put("type", "updateString");
    updateMessage.put("key", key);
    updateMessage.put("value", value);

    blast(updateMessage);
  }

}
