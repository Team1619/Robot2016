package org.usfirst.frc.team1619.robot2016.IO.SocketTables;

public class SmashBoard {

  private static SmashBoard instance;

  private SocketServer socketServer;

  static {
    instance = new SmashBoard();
  }

  public static SmashBoard getInstance() {
    return instance;
  }

  public SmashBoard() {
    socketServer = new SocketServer();
    socketServer.startServer();
  }

  public SmashBoard(int port) {
    socketServer = new SocketServer(port);
    socketServer.startServer();
  }
  
  public void update() {
  }

  public void setShootOffset(double offset) {
    socketServer.setDouble("shootOffset", offset);
  }

  public void setAngleError(double error) {
    socketServer.setDouble("angleError", error);
  }

  public double getRotationOffsetToAligned() {
    return socketServer.getDouble("angleOffsetToAligned", 0.0);
  }

  public double getDistance() {
    return socketServer.getDouble("distance", 100.0);
  }

  public int getAutoDefense() {
    return (int)socketServer.getLong("autoDefense", 1);
  }

  public int getAutoLane() {
    return (int)socketServer.getLong("autoLane", 1);
  }

  public int getAutoTargetGoal() {
    return (int)socketServer.getLong("autoTargetGoal", 1);
  }
  
//  public static void main(String[] args) {
//    SmashBoard smashBoard = new SmashBoard(5000);
//  }
  
}
