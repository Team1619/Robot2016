package org.usfirst.frc.team1619.robot2016.IO;

import org.usfirst.frc.team1619.robot2016.framework.RobotState;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

/**
 * The network table represented by the smart dashboard.
 * 
 * Values from robot are written to dashboard, and values from dashboard are
 * listened for and responded to on the robot.
 * 
 * TODO: Implement this in a TimerTask which is not called as often as the more
 * important looping code.
 * 
 * @author DanielHathcock
 *
 */
public class SmashBoard {

  private static SmashBoard instance;

  private double angleError;

  static {
    try {
      instance = new SmashBoard();
    }
    catch (Throwable e) {
      e.printStackTrace();
      throw e;
    }
  }

  public static SmashBoard getInstance() {
    return instance;
  }

  // SmashBoard
  private NetworkTable smashBoard;

  // Listener for value changes
  private ITableListener tableListener = new ITableListener() {

    /**
     * Used to update values or preferences in real time from the SmartDashboard
     * on the driverstation. Called automatically on value change.
     * 
     * Double click on value in smashBoard (while not in editable mode) to
     * change a value.
     */
    @Override
    public void valueChanged(ITable source, String key, Object value,
      boolean isNew) {

      switch (key) {
        // here are all of the cases for updating a value (like PID)
        default:
          break;
      }

    }
  };

  // References for getting and putting data
  private SensorInput sensorInput;
  private RobotState robotState;

  private SmashBoard() {
    smashBoard = NetworkTable.getTable("SmashBoard");
    deleteAllKeys();

    smashBoard.addTableListener(tableListener);

    sensorInput = SensorInput.getInstance();
    robotState = RobotState.getInstance();

    angleError = 1000.0;

    addDefaults();
  }

  private void deleteAllKeys() {
    for (String key : smashBoard.getKeys()) {
      smashBoard.delete(key);
    }
  }

  /**
   * Called once during construction. Adds defaults to the smashBoard which will
   * be listened for.
   */
  private void addDefaults() {
    // Value defaults
  }

  /**
   * Called to update any choosers in smashBoard. Likey to only be called in
   * Teleop and Autonomous init.
   */
  public void updateChoosers() {
  }

  /**
   * Write values from robot to smashBoard
   * 
   * Use only for writing, reading values should be done with the table
   * listener.
   * 
   * TODO: decide whether names are too long, and a convention to shorten them
   */
  public void update() {
    // Encoders
    smashBoard.putNumber("rightDriveEncoderPosition",
      sensorInput.getDriveRightEncoderPosition());
    smashBoard.putNumber("leftDriveEncoderPosition",
      sensorInput.getDriveLeftEncoderPosition());
    smashBoard.putNumber("dartEncoderPosition", sensorInput.getDartPosition());
    smashBoard.putNumber("speed", sensorInput.getShooterEncoderVelocity());
    smashBoard.putNumber("angleError", angleError);

    // NavX
    smashBoard.putNumber("angle", sensorInput.getNavXHeading());
    smashBoard.putNumber("pitch", sensorInput.getNavXPitch());
    smashBoard.putNumber("roll", sensorInput.getNavXRoll());

    // Dart
    smashBoard.putNumber("upperHallEffect",
      sensorInput.getUpperHallEffect() ? 1.0 : 0.0);
    smashBoard.putNumber("lowerHallEffect",
      sensorInput.getLowerHallEffect() ? 1.0 : 0.0);

    // DIO
    smashBoard.putNumber("ballPresenceSensor",
      sensorInput.getBallPresenceSensor() ? 1.0 : 0.0);
    smashBoard.putNumber("ballPresenceEdge",
      robotState.getBallPresenceRisingEdge() ? 1.0 : 0.0);
  }

  public void setAngleError(double error) {
    angleError = error;
  }

  public double getRotationOffsetToAligned() {
    return smashBoard.getNumber("angleOffsetToAligned", 0.0);
  }

  public double getDistance() {
    return smashBoard.getNumber("distance", 100.0);
  }
}
