package org.usfirst.frc.team1619.robot2016;

import org.usfirst.frc.team1619.robot2016.util.logger.Logger.LoggingLevel;

public class Constants {

  // Motor IDs
  public static final int DRIVE_LEFT_1_ID = 1;
  public static final int DRIVE_LEFT_2_ID = 2;
  public static final int DRIVE_RIGHT_1_ID = 3;
  public static final int DRIVE_RIGHT_2_ID = 4;
  public static final int DART_MOTOR_ID = 5;

  // Joysticks
  public static final int JOYSTICK_RIGHT_ID = 0;
  public static final int JOYSTICK_LEFT_ID = 1;
  
  // Joystick Buttons
  public static final int BUTTON_DRIVE_PID_TURN = 1;
  public static final int BUTTON_DRIVE_PID_RESET = 2;

  // Encoders
  public static final int DRIVE_ENC_TICKS_PER_INCH = 1;
  public static final double DART_ENC_TICKS_PER_INCH = 1024.0;
  
  // Limit Switches
  public static final int UPPER_HALLEFFECT_ID = 0;
  public static final int LOWER_HALLEFFECT_ID = 1;
  
  // Properties/configurations
  public static final boolean TEST_MODE = false;
  public static final boolean COMPETITION_MODE = false;
  
  // Logger
  public static final int MAX_LOGS = 20;
  public static final int QUEUE_SIZE = 32;
  public static final LoggingLevel LOGGING_LEVEL = LoggingLevel.WARNING;
  public static final String LOG_FOLDER_PATH = "/home/lvuser/log/";
  
  // Drive Train
  public static final double DRIVE_PID_ROTATION_P = 0.025;
  public static final double DRIVE_PID_ROTATION_I = 0.0;
  public static final double DRIVE_PID_ROTATION_D = 0;

  public static final double DRIVE_PID_TRANSLATION_P = 0;
  public static final double DRIVE_PID_TRANSLATION_I = 0;
  public static final double DRIVE_PID_TRANSLATION_D = 0;
  
  // Utility Arm
  public static final double DART_SPEED_FACTOR = 1.0;
  
  // Utility Arm Angle Constants
  public static final double ARM_LENGTH = 5.08725;
  public static final double ARM_TO_DART_BASE_VERTICAL = 9.3073849;
  public static final double ARM_TO_DART_BASE_HORIZONTAL = 13.47087978;
  public static final double DART_WIDTH = 3.0;
  public static final double DART_HEIGHT = 12.0375-0.4423281995303814;
  public static final double ARM_TO_DART_LENGTH = Math.hypot(ARM_TO_DART_BASE_HORIZONTAL, ARM_TO_DART_BASE_VERTICAL);
  public static final double ARM_TO_DART_DECLINATION_ANGLE = Math.toDegrees(Math.atan(ARM_TO_DART_BASE_VERTICAL/ARM_TO_DART_BASE_HORIZONTAL));
  public static final double ARM_PIVOTS_TO_TOP_SURFACE_ANGLE = 9.27;
}
