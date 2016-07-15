package org.usfirst.frc.team1619.robot2016.IO.SocketTables;

import java.net.InetSocketAddress;

import org.usfirst.frc.team1619.robot2016.RobotState;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;

public class SmashBoard {

  private static SmashBoard instance;

  private NewSocketServer socketServer;

  static {
    instance = new SmashBoard();
  }

  public static SmashBoard getInstance() {
    return instance;
  }

  private SensorInput sensorInput;
  private RobotState robotState;

  public SmashBoard() {
    socketServer = new NewSocketServer(new InetSocketAddress("0.0.0.0", 5802));
    socketServer.start();
  }

  public void initialize() {
    sensorInput = SensorInput.getInstance();
    robotState = RobotState.getInstance();
  }

  public void update() {
    socketServer.set("heading", sensorInput.getNavXHeading());
    socketServer.set("shooterSpeed", sensorInput.getShooterEncoderVelocity());
    socketServer.set("innerBallSensor",
      sensorInput.getBallPresenceSensorFront() ? 1 : 0);
    socketServer.set("outerBallSensor",
      sensorInput.getBallPresenceSensorRear() ? 1 : 0);
    socketServer.set("upperHallEffect",
      sensorInput.getUpperHallEffect() ? 1 : 0);
    socketServer.set("lowerHallEffect",
      sensorInput.getLowerHallEffect() ? 1 : 0);
    socketServer.set("adjustedAngleOffsetToAligned",
      getRotationOffsetToAligned()
        - RobotState.getInstance().getShootAlignOffset());
    socketServer.set("driveVelocity",
      (int)(sensorInput.getDriveLeftEncoderVelocity()
        + sensorInput.getDriveRightEncoderVelocity()) / 2);
    socketServer.set("dartPosition", sensorInput.getDartPosition());
    socketServer.set("dartVelocity", sensorInput.getDartVelocity());
    socketServer.set("rotationVelocity", sensorInput.getRotationVelocity());
    socketServer.set("shootSpeedPercent",
      robotState.getShootSpeedPercent() * 100.0);
    socketServer.set("navXPitch", sensorInput.getNavXPitch());
    socketServer.set("navXRoll", sensorInput.getNavXRoll());
    socketServer.flush();
  }

  public void setString(String key, String text) {
    socketServer.set(key, text);
  }

  public void setShootOffset(double offset) {
    socketServer.set("shootOffset", offset);
  }

  public void setAngleError(double error) {
    socketServer.set("angleError", error);
  }

  public double getRotationOffsetToAligned() {
    return socketServer.getDouble("angleOffsetToAligned", 0.0);
  }

  public double getDistance() {
    return socketServer.getDouble("distance", 100.0);
  }

  public int getAutoDefense() {
    return socketServer.getInt("autoDefense", 0);
  }

  public int getAutoLane() {
    return socketServer.getInt("autoLane", 0);
  }

  public boolean getContourFound() {
    return socketServer.getInt("contourFound", 0) == 1;
  }

  public boolean getContourEdge() {
    return socketServer.getInt("contourLeft", 0) == 1
      || socketServer.getInt("contourTop", 0) == 1
      || socketServer.getInt("contourRight", 0) == 1;
  }

  public boolean getGoodContourFound() {
    return socketServer.getInt("contourGood", 0) == 1;
  }

  public int getShotRange() {
    return socketServer.getInt("shotRange", 0);
  }

  public void setShotRange(int shotRange) {
    socketServer.set("shotRange", shotRange);
  }

}
