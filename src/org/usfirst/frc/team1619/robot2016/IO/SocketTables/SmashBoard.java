package org.usfirst.frc.team1619.robot2016.IO.SocketTables;

import org.usfirst.frc.team1619.robot2016.IO.SensorInput;

public class SmashBoard {

  private static SmashBoard instance;

  private SocketServer socketServer;

  static {
    instance = new SmashBoard();
  }

  public static SmashBoard getInstance() {
    return instance;
  }

  private SensorInput sensorInput;
  
  public SmashBoard() {
    socketServer = new SocketServer();
    socketServer.startServer();
    sensorInput = SensorInput.getInstance();
  }

  public SmashBoard(int port) {
    socketServer = new SocketServer(port);
    socketServer.startServer();
    sensorInput = SensorInput.getInstance();
  }
  
  public void update() {
    socketServer.setDouble("NavX", sensorInput.getNavXHeading());
    socketServer.setDouble("Shooter Encoder Speed", sensorInput.getShooterEncoderVelocity());
  }

  public void setString(String key, String text) {
    socketServer.setString(key, text);
  }
  
  public void setShootOffset(double offset) {
    socketServer.setDouble("shootOffset", offset);
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

  public int getAutoDefense() {
    return (int)socketServer.getLong("autoDefense", 1);
  }

  public int getAutoLane() {
    return (int)socketServer.getLong("autoLane", 1);
  }

  public int getAutoTargetGoal() {
    return (int)socketServer.getLong("autoTargetGoal", 1);
  }
  
  public int getDesiredDistance() {
    return (int)socketServer.getLong("desiredDistance", 30);
  }
  
//  public static void main(String[] args) {
//    SmashBoard smashBoard = new SmashBoard(5000);
//  }
}
