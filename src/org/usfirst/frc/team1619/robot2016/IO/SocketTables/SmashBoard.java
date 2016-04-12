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
    socketServer.setDouble("heading", sensorInput.getNavXHeading());
    socketServer.setDouble("shooterSpeed",
      sensorInput.getShooterEncoderVelocity());
    socketServer.setLong("innerBallSensor",
      sensorInput.getBallPresenceSensorFront() ? 1 : 0);
    socketServer.setLong("outerBallSensor",
      sensorInput.getBallPresenceSensorRear() ? 1 : 0);
    socketServer.setLong("upperHallEffect",
      sensorInput.getUpperHallEffect() ? 1 : 0);
    socketServer.setLong("lowerHallEffect",
      sensorInput.getLowerHallEffect() ? 1 : 0);
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

  // public static void main(String[] args) {
  // SmashBoard smashBoard = new SmashBoard(5000);
  // }

}
