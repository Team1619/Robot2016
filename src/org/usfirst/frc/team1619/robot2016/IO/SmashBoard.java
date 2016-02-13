package org.usfirst.frc.team1619.robot2016.IO;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.util.logger.Logger;
import org.usfirst.frc.team1619.robot2016.util.logger.Logger.LoggingLevel;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

/**
 * The network table represented by the smart dashboard. 
 * 
 * Values from robot are written to dashboard, and values from dashboard
 * are listened for and responded to on the robot.
 * 
 * TODO: Implement this in a TimerTask which is not called as often as the 
 * more important looping code.
 * @author DanielHathcock
 *
 */
public class SmashBoard {
  
  private static SmashBoard instance;
//  static {
//    instance = new SmashBoard();
//  }
  
  public static SmashBoard getInstance() {
    if (instance == null) {
      instance = new SmashBoard();
    }
    
    return instance;
  }
  
  // SmashBoard
  private NetworkTable smashBoard;
  
  // Listener for value changes
  private ITableListener tableListener = new ITableListener() {

    /**
     * Used to update values or preferences in real time from the 
     * SmartDashboard on the driverstation. Called automatically on value change.
     * 
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
  private DriverInput driverInput;
  private Logger logger;
  
  // Choosers
  private SendableChooser loggingLevelChooser;
  private SendableChooser autoModeChooser;
  
  private SmashBoard() {
    smashBoard = NetworkTable.getTable("SmartDashboard");
    smashBoard.addTableListener(tableListener);
    
    sensorInput = SensorInput.getInstance();
    driverInput = DriverInput.getInstance();
    // logger = Logger.getInstance();
    
    loggingLevelChooser = new SendableChooser();
    autoModeChooser = new SendableChooser();
    
    addDefaults();
  }
  
  /**
   * Called once during construction. Adds defaults to the 
   * smashBoard which will be listened for.
   */
  private void addDefaults() {
    // Choosers
    loggingLevelChooser.addObject("Error", LoggingLevel.ERROR);
    loggingLevelChooser.addObject("Warning", LoggingLevel.WARNING);
    loggingLevelChooser.addObject("Info", LoggingLevel.INFO);
    loggingLevelChooser.addObject("Debug", LoggingLevel.DEBUG);
    loggingLevelChooser.addDefault(Constants.LOGGING_LEVEL.toString(), Constants.LOGGING_LEVEL);
    loggingLevelChooser.initTable(smashBoard);
    
    // Place choosers
    SmartDashboard.putData("Logging Level", loggingLevelChooser);
    
    // Values
  }
  
  /**
   * Called to update any choosers in smashBoard. 
   * Likey to only be called in Teleop and Autonomous init.
   */
  public void updateChoosers() {
    logger.setLoggingLevel((LoggingLevel)loggingLevelChooser.getSelected());
  }
  
  /**
   * Write values from robot to smashBoard
   * 
   * Use only for writing, reading values should be done with 
   * the table listener.
   * 
   * TODO: decide whether names are too long, and a convention to shorten them
   */
  public void update() {
    // Encoders
    smashBoard.putNumber("Right Drive Encoder Position", sensorInput.getDriveRightEncoderPosition());
    smashBoard.putNumber("Left Drive Encoder Position", sensorInput.getDriveLeftEncoderPosition());
    smashBoard.putNumber("Right Drive Encoder Velocity", sensorInput.getDriveRightEncoderVelocity());
    smashBoard.putNumber("Left Drive Encoder Velocity", sensorInput.getDriveLeftEncoderVelocity());
    
    // NavX
    smashBoard.putNumber("Heading", sensorInput.getNavXHeading());
    
    //Dart
    smashBoard.putNumber("Upper Hall Effect", sensorInput.getUpperHallEffect() ? 1.0 : 0.0);
  }
  
}
