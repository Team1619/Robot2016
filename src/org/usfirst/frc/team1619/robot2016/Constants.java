package org.usfirst.frc.team1619.robot2016;

import org.usfirst.frc.team1619.robot2016.util.logger.Logger.LoggingLevel;

public class Constants {

  // Motor IDs
  public static final int DRIVE_LEFT_1_ID = 1;
  public static final int DRIVE_LEFT_2_ID = 2;
  public static final int DRIVE_RIGHT_1_ID = 3;
  public static final int DRIVE_RIGHT_2_ID = 4;
  public static final int TEST_MOTOR_ID = 10;

  //Joysticks
  public static final int JOYSTICK_RIGHT_ID = 0;
  public static final int JOYSTICK_LEFT_ID = 1;
  
  //Buttons
  public static final int TEST_FORWARD_BUTTON = 1;
  public static final int TEST_BACKWARD_BUTTON = 2;

  // Encoders
  public static final int DRIVE_ENC_TICKS_PER_INCH = 1;
  
  // Properties/configurations
  public static final boolean TEST_MODE = false;
  public static final boolean COMPETITION_MODE = false;
  public static final LoggingLevel LOGGING_LEVEL = LoggingLevel.WARNING;
  public static final String LOG_FOLDER_PATH = "/home/lvuser/log/";
}
