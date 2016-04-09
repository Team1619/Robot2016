package org.usfirst.frc.team1619.robot2016.IO.SocketTables;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.usfirst.frc.team1619.robot2016.Constants;

public class SocketServer extends Thread {

  private ServerSocket serverSocket;
  private int port;
  private boolean open;

  private SmashBoardValues smashBoardValues;

  public SocketServer() {
    this(Constants.SMASH_BOARD_PORT);
  }

  public SocketServer(int port) {
    this.port = port;
    open = false;

    smashBoardValues = SmashBoardValues.getInstance();
    SocketHandler.initialize();
  }

  public void startServer() {
    try {
      serverSocket = new ServerSocket(port);
      start();
    }
    catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  public void stopServer() {
    open = false;
    interrupt();
  }

  @Override
  public void run() {
    open = true;

    while (open) {
      try {
        Socket socket = serverSocket.accept();

        SocketHandler messageHandler = new SocketHandler(socket);
        messageHandler.start();
      }
      catch (IOException exception) {
        exception.printStackTrace();
      }
    }
  }

  public long getLong(String key, long defaultValue) {
    try {
      long value = smashBoardValues.getLong(key);
      return value;
    }
    catch (NullPointerException exception) {
      return defaultValue;
    }
  }

  public double getDouble(String key, double defaultValue) {
    try {
      double value = smashBoardValues.getDouble(key);
      return value;
    }
    catch (NullPointerException exception) {
      return defaultValue;
    }
  }

  public String getString(String key, String defaultValue) {
    try {
      String value = smashBoardValues.getString(key);
      return value;
    }
    catch (NullPointerException exception) {
      return defaultValue;
    }
  }

  public void setLong(String key, long value) {
    try {
      SocketHandler.updateLong(key, value);
    }
    catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  public void setDouble(String key, double value) {
    try {
      SocketHandler.updateDouble(key, value);
    }
    catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  public void setString(String key, String value) {
    try {
      SocketHandler.updateString(key, value);
    }
    catch (IOException exception) {
      exception.printStackTrace();
    }
  }

}
