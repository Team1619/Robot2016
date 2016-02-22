package org.usfirst.frc.team1619.robot2016;

import org.usfirst.frc.team1619.robot2016.util.PIDValues;
import org.usfirst.frc.team1619.robot2016.util.logger.Logger.LoggingLevel;

public class Constants {
  // Joysticks
  public static final int JOYSTICK_RIGHT_ID = 0;
  public static final int JOYSTICK_LEFT_ID = 1;

  // Driver buttons
  public static final int DRIVER_BUTTON_DRIVE_TURN_TO_CAMERA = 1;
  public static final int DRIVER_BUTTON_DRIVE_PID_RESET = 2;
  public static final int DRIVER_BUTTON_DRIVE_TURN_TO_PRESET = 3;
  

  // Operator buttons
  public static final int OPERATOR_BUTTON_INTAKE = 2;
  public static final int OPERATOR_BUTTON_SHOOT = 1;
  public static final int OPERATOR_BUTTON_MOVE_ARM_TO_INTAKE = 3;

  // Encoders
  public static final double DRIVE_ENC_TICKS_PER_INCH = 161.6;
  public static final double DART_ENC_TICKS_PER_INCH = 1024.0;

  // Drive Train
  public static final PIDValues DRIVE_PID_ROTATION =
    new PIDValues(0.04, 0.001, 0.15);
  public static final double DRIVE_PID_ROTATION_IRANGE = 25;
  public static final double DRIVE_PID_ROTATION_KACHIG_BAND = 10;
  public static final double DRIVE_PID_ROTATION_KACHIG_CONSTANT = 0.60;
  public static final double DRIVE_PID_ROTATION_DEADBAND = 0.25;
  public static final double DRIVE_PID_ROTATION_MINIMUM = 0.5;
  public static final int DRIVE_PID_ROTATION_KACHIG_ONTIME = 100;
  public static final int DRIVE_PID_ROTATION_KACHIG_OFFTIME = 200;

  public static final PIDValues DRIVE_PID_TRANSLATION =
    new PIDValues(0.04, 0.001, 0.15);
  public static final double DRIVE_PID_TRANSLATION_IRANGE = 2;

  // Utility Arm
  public static final PIDValues ARM_PID_DOWN =
    new PIDValues(0.625, 0.00075, 1.2);
  public static final PIDValues ARM_PID_UP = new PIDValues(1.0, 0.0005, 1.25);
  public static final double DART_SPEED_FACTOR = 1.0;

  public static final int ARM_UPPER_HALLEFFECT_ID = 0;
  public static final int ARM_LOWER_HALLEFFECT_ID = 1;

  //Utility Arm 
  public static final double ARM_LENGTH = 5.08725;
  public static final double ARM_TO_DART_BASE_VERTICAL = 9.3073849;
  public static final double ARM_TO_DART_BASE_HORIZONTAL = 13.47087978;
  public static final double ARM_DART_WIDTH = 3.0;
  public static final double ARM_DART_HEIGHT = 12.0375 - 0.4423281995303814;
  public static final double ARM_TO_DART_LENGTH =
    Math.hypot(ARM_TO_DART_BASE_HORIZONTAL, ARM_TO_DART_BASE_VERTICAL);
  public static final double ARM_TO_DART_DECLINATION_ANGLE = Math.toDegrees(
    Math.atan(ARM_TO_DART_BASE_VERTICAL / ARM_TO_DART_BASE_HORIZONTAL));
  public static final double ARM_PIVOTS_TO_TOP_SURFACE_ANGLE = 9.27;

  // Motor IDs
  public static final int DRIVE_LEFT_1_ID = 1;
  public static final int DRIVE_LEFT_2_ID = 2;
  public static final int DRIVE_RIGHT_1_ID = 3;
  public static final int DRIVE_RIGHT_2_ID = 4;
  public static final int DART_MOTOR_ID = 5;
  public static final int INTAKE_MOTOR_ID = 6;
  public static final int SHOOTER_MOTOR_ID = 7;

  // Properties/configurations
  public static final boolean PROPERTIES_TEST_MODE = false;
  public static final boolean PROPERTIES_COMPETITION_MODE = false;

  // Logger
  public static final int LOGGER_MAX_LOGS = 20;
  public static final int LOGGER_QUEUE_SIZE = 32;
  public static final LoggingLevel LOGGER_LOGGING_LEVEL = LoggingLevel.WARNING;
  public static final String LOGGER_LOG_FOLDER_PATH = "/home/lvuser/log/";
}