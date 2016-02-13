package org.usfirst.frc.team1619.robot2016;

import org.usfirst.frc.team1619.robot2016.util.logger.Logger.LoggingLevel;

public class Constants {

  // Motor IDs
  public static final int DRIVE_LEFT_1_ID = 1;
  public static final int DRIVE_LEFT_2_ID = 2;
  public static final int DRIVE_RIGHT_1_ID = 3;
  public static final int DRIVE_RIGHT_2_ID = 4;
  public static final int TEST_MOTOR_ID = -1;
  public static final int DART_MOTOR_ID = 5;

  //Joysticks
  public static final int JOYSTICK_RIGHT_ID = 0;
  public static final int JOYSTICK_LEFT_ID = 1;
  
  //Joystick Buttons
  public static final int BUTTON_DRIVE_PID_TURN = 1;
  public static final int BUTTON_DRIVE_PID_RESET = 2;
  
  //Buttons
  public static final int TEST_FORWARD_BUTTON = -1;
  public static final int TEST_BACKWARD_BUTTON = -1;

  // Encoders
  public static final int DRIVE_ENC_TICKS_PER_INCH = 1;
  public static final int DART_ENC_TICKS_PER_INCH = 5;
  
  // Properties/configurations
  public static final boolean TEST_MODE = false;
  public static final boolean COMPETITION_MODE = false;
  public static final LoggingLevel LOGGING_LEVEL = LoggingLevel.WARNING;
  public static final String LOG_FOLDER_PATH = "/home/lvuser/log/";
  public static final int UPPER_HALLEFFECT_ID = 0;
  public static final int LOWER_HALLEFFECT_ID = 1;
  public static final double DART_SPEED_FACTOR = 1.0;
  
//Drive Train
  public static final double DRIVE_PID_ROTATION_P = 0.025;
  public static final double DRIVE_PID_ROTATION_I = 0.0;
  public static final double DRIVE_PID_ROTATION_D = 0;

  public static final double DRIVE_PID_TRANSLATION_P = 0;
  public static final double DRIVE_PID_TRANSLATION_I = 0;
  public static final double DRIVE_PID_TRANSLATION_D = 0;
}
