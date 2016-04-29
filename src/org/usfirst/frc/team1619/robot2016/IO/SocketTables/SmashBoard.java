package org.usfirst.frc.team1619.robot2016.IO.SocketTables;

import org.usfirst.frc.team1619.robot2016.RobotState;
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
  private RobotState robotState;

  public SmashBoard() {
    socketServer = new SocketServer();
    socketServer.startServer();
  }

  public void initialize() {
    sensorInput = SensorInput.getInstance();
    robotState = RobotState.getInstance();
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
    socketServer.setDouble("adjustedAngleOffsetToAligned",
      getRotationOffsetToAligned()
        - RobotState.getInstance().getShootAlignOffset());
    socketServer.setLong("driveVelocity",
      (int)(sensorInput.getDriveLeftEncoderVelocity()
        + sensorInput.getDriveRightEncoderVelocity()) / 2);
    socketServer.setDouble("dartPosition", sensorInput.getDartPosition());
    socketServer.setDouble("dartVelocity", sensorInput.getDartVelocity());
    socketServer.setDouble("rotationVelocity",
      sensorInput.getRotationVelocity());
    socketServer.setDouble("shootSpeedPercent",
      robotState.getShootSpeedPercent() * 100.0);
    socketServer.setDouble("navXPitch", sensorInput.getNavXPitch());
    socketServer.setDouble("navXRoll", sensorInput.getNavXRoll());
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
    return (int)socketServer.getLong("autoDefense", 0);
  }

  public int getAutoLane() {
    return (int)socketServer.getLong("autoLane", 0);
  }

  public boolean getContourFound() {
    return socketServer.getLong("contourFound", 0) == 1;
  }

  public boolean getContourEdge() {
    return socketServer.getLong("contourLeft", 0) == 1
      || socketServer.getLong("contourTop", 0) == 1
      || socketServer.getLong("contourRight", 0) == 1;
  }

  public boolean getGoodContourFound() {
    return socketServer.getLong("contourGood", 0) == 1;
  }

  public int getShotRange() {
    return (int)socketServer.getLong("shotRange", 0);
  }

  public void setShotRange(int shotRange) {
    socketServer.setLong("shotRange", shotRange);
  }

}
