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
  }

  public void initialize() {
    socketServer.startServer();
  }

  public void update() {
  }

  public void setAngleError(double error) {
    socketServer.setDouble("angleError", error);
  }

  public void setWaypointPosition(double x, double y) {
    socketServer.setDouble("waypointX", x);
    socketServer.setDouble("waypointY", y);
  }

  public void setWaypointOffsets(double angle, double distance) {
    socketServer.setDouble("waypointAngle", angle);
    socketServer.setDouble("waypointDistance", distance);
  }

  public double getRotationOffsetToAligned() {
    return socketServer.getDouble("angleOffsetToAligned", 0.0);
  }

  public double getDistance() {
    return socketServer.getDouble("distance", 100.0);
  }
}
