package org.usfirst.frc.team1619.robot2016;

import org.usfirst.frc.team1619.robot2016.util.logger.Logger.LoggingLevel;

public class Constants {

  //Joysticks
  public static final int JOYSTICK_RIGHT_ID = 0;
  public static final int JOYSTICK_LEFT_ID = 1;

  //Joystick Buttons
  public static final int BUTTON_DRIVE_PID_TURN = 1;
  public static final int BUTTON_DRIVE_PID_RESET = 2;

  // Motor IDs
  public static final int DRIVE_LEFT_1_ID = 1;
  public static final int DRIVE_LEFT_2_ID = 2;
  public static final int DRIVE_RIGHT_1_ID = 3;
  public static final int DRIVE_RIGHT_2_ID = 4;

  // Encoders
  public static final int DRIVE_ENC_TICKS_PER_INCH = 1;
  
  // Properties/configurations
  public static final boolean TEST_MODE = false;
  public static final boolean COMPETITION_MODE = false;
  public static final LoggingLevel LOGGING_LEVEL = LoggingLevel.WARNING;
  public static final String LOG_FOLDER_PATH = "/home/lvuser/log/";

  //DriveTrain
  public static final double DRIVE_PID_ROTATION_P = 0.04;
  public static final double DRIVE_PID_ROTATION_I = 0.001;
  public static final double DRIVE_PID_ROTATION_D = 0.15;
  public static final double DRIVE_PID_ROTATION_IRANGE = 25;

  public static final double DRIVE_PID_TRANSLATION_P = 0.001;
  public static final double DRIVE_PID_TRANSLATION_I = 0.0001;
  public static final double DRIVE_PID_TRANSLATION_D = 0.0;
  public static final double DRIVE_PID_TRANSLATION_IRANGE = 100;

  public static final double DRIVER_ROTATION_DEADZONE = 0.05;

  //Autonomous
  //  Test
  public static final double AUTO_TEST_ROTATION_TOLERANCE = 2.0;
  public static final double AUTO_TEST_TRANSLATION_TOLERANCE = 200;
}
