package org.usfirst.frc.team1619.robot2016;

import org.usfirst.frc.team1619.robot2016.util.PID.PIDValues;
import org.usfirst.frc.team1619.robot2016.util.logger.Logger.LoggingLevel;

public class Constants {
  // Joysticks
  public static final int JOYSTICK_RIGHT_ID = 0;
  public static final int JOYSTICK_LEFT_ID = 1;
  public static final double JOYSTICK_DEADZONE = 0.05;

  // Driver buttons
  public static final int DRIVER_BUTTON_SHOOT = 4;
  public static final int DRIVER_BUTTON_DRIVE_HOLD_HEADING = 2;
  public static final int DRIVER_BUTTON_DRIVE_TURN_TO_PRESET = 3;
  public static final int DRIVER_BUTTON_DRIVE_TURN_TO_CAMERA = 4;
  public static final int DRIVER_BUTTON_SHOOT_HIGH = 1;
  public static final int DRIVER_BUTTON_SHOOT_LOW = 3;
  public static final int DRIVER_BUTTON_DRIVE_INVERT = 8;

  // Operator buttons
  public static final int OPERATOR_BUTTON_SHOOT = 1;
  // Spool up shooter motor out, intake out when shoot is at the right speed
  public static final int OPERATOR_BUTTON_SHOOT_RELEASE = 2;
  // Manual intake out while in operator shoot
  public static final int OPERATOR_BUTTON_INTAKE = 2;
  // Spool shooter motor in, intake in
  public static final int OPERATOR_BUTTON_ZERO_DART = 4;
  // Zero dart to the top
  public static final int OPERATOR_BUTTON_ARM_SHOOT = 5;
  public static final int OPERATOR_BUTTON_ARM_NEUTRAL = 6;
  public static final int OPERATOR_BUTTON_ARM_INTAKE = 7;
  public static final int OPERATOR_BUTTON_SCALER_TIMER_OVERIDE = 11;
  public static final int OPERATOR_BUTTON_MANUAL_SHOOTER_OUT = 13;
  public static final int OPERATOR_BUTTON_MANUAL_SHOOTER_IN = 14;
  public static final int OPERATOR_BUTTON_MANUAL_INTAKE_OUT = 12;
  public static final int OPERATOR_BUTTON_MANUAL_INTAKE_IN = 15;
  public static final int OPERATOR_BUTTON_MANUAL_SCALER_RETRACT = 16;
  public static final int OPERATOR_BUTTON_SCALER_SCALE = 9;
  public static final int OPERATOR_BUTTON_MANUAL_SCALER_EXTEND = 10;

  // Autonomous
  public static final int AUTO_DRIVE_TRANSLATION_DEADTIME = 200;
  public static final double AUTO_DRIVE_TRANSLATION_MAX_OUTPUT = 0.85;
  public static final int AUTO_DRIVE_ROTATION_DEADTIME = 200;
  public static final double AUTO_DRIVE_ROTATION_MAX_OUTPUT = 0.85;
  public static final double AUTO_DRIVE_ROTATION_TOLERANCE = 0.5;
  public static final double AUTO_ARM_TOLERANCE = 0.15;
  
  public static final double AUTO_LOW_BAR_DRIVE_DISTANCE = 125.0;

  // Drive Train
  public static final PIDValues DRIVE_PID_ROTATION =
    new PIDValues(0.08, 0.01, 0.20);
  public static final double DRIVE_PID_ROTATION_IRANGE = 5;
  public static final double DRIVE_PID_ROTATION_IMAX =
    0.60 / DRIVE_PID_ROTATION.i;
  public static final double DRIVE_PID_ROTATION_DEADZONE = 0.0;

  public static final PIDValues DRIVE_PID_TRANSLATION =
    new PIDValues(0.03, 0.005, 0.02);
  public static final double DRIVE_PID_TRANSLATION_IRANGE = 5;
  public static final double DRIVE_PID_TRANSLATION_IMAX =
    0.35 / DRIVE_PID_TRANSLATION.i;
  public static final double DRIVE_PID_TRANSLATION_DEADZONE = 0.01;
  public static final double DRIVE_PID_TRANSLATION_MINIMUM_OUTPUT = 0.3;

  // Intake
  public static final double INTAKE_INTAKE_SPEED = -1.0;
  public static final double INTAKE_SHOOT_SPEED = 1.0;
  public static final int SHOOTING_INTAKE_OUT_TIME = 750;

  // Shooter
  public static final double SHOOTER_INTAKE_SPEED = -1.0;
  public static final double SHOOTER_SHOOT_SPEED = 1.0;
  public static final int SHOOTER_SHOOT_SPEED_TARGET_HIGH = 27000;
  public static final int SHOOTER_SHOOT_SPEED_TARGET = 25500;
  public static final int SHOOTER_SHOOT_SPEED_TARGET_LOW = 24500;
  public static final int SHOOTER_SHOOT_SPEED_TARGET_BATTER = 17500;
  public static final double SHOOTER_SPOOL_UP_SPEED = 0.75;

  // Scaler
  public static final double SCALER_EXTEND_SPEED = 1.0;
  public static final double SCALER_RETRACT_SPEED = -1.0;
  public static final double SCALER_MANUAL_TIMER_START = 25;
  public static final double SCALER_SCALE_SPEED = -0.5;
  public static final int SCALER_SCALE_TIME = 200;

  // Utility Arm
  public static final double ARM_MAX_SPEED = 0.75;
  public static final double ARM_BOTTOM_THROTTLE_POSITION = -5.0;
  public static final double ARM_TOP_THROTTLE_POSITION = -0.5;
  public static final double ARM_THROTTLE_SPEED = 0.5;

  public static final double ARM_SCALE_SPEED = -0.5;
  public static final int ARM_SCALE_TIME = 500;

  public static final PIDValues ARM_PID = new PIDValues(0.4, 0.1, 0.0);
  public static final double ARM_PID_IRANGE = 0.0;
  public static final double ARM_PID_IMAX = 0.3 / ARM_PID.i;
  public static final double ARM_PID_DEADZONE = 0.05;
  public static final double ARM_PID_MINIMUM = 0.1;
  public static final int ARM_AUTO_DEADTIME = 500;

  public static final double ARM_ZERO_SPEED = 0.75;

  public static final double ARM_POSITION_DEFAULT = -3.5;
  public static final double ARM_POSITION_INTAKE = -5.6;
  public static final double ARM_POSITION_LOW_BAR = -5.0;
  public static final double ARM_POSITION_SHOOT_OUTERWORKS = -0.4;
  public static final double ARM_POSITION_SHOOT_NEAR_BATTER = -0.0;
  public static final double ARM_POSITION_VISION = 0.0;

  // Arm Hall Effects
  public static final int ARM_UPPER_HALLEFFECT_ID = 0;
  public static final int ARM_LOWER_HALLEFFECT_ID = 1;

  // Utility Arm Encoder Stuff
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
  public static final int SCALER_MOTOR_ID = 8;

  // Digital IO
  public static final int BALL_PRESENCE_SENSOR_REAR_ID = 3;
  public static final int BALL_PRESENCE_SENSOR_FRONT_ID = 2;

  // Relays
  public static final int RELAY_BALL_DETECTED_REAR_ID = 0;
  public static final int RELAY_BALL_DETECTED_FRONT_ID = 1;
  public static final int RELAY_VISION_RING_LIGHT_ID = 2;

  // Encoders
  public static final double DRIVE_ENC_TICKS_PER_INCH = 83.07625;
  public static final double DART_ENC_TICKS_PER_INCH = 1024.0;

  // Properties/configurations
  public static final boolean PROPERTIES_TEST_MODE = false;
  public static final boolean PROPERTIES_COMPETITION_MODE = false;

  // Logger
  public static final int LOGGER_MAX_LOGS = 20;
  public static final int LOGGER_QUEUE_SIZE = 32;
  public static final LoggingLevel LOGGER_LOGGING_LEVEL = LoggingLevel.WARNING;
  public static final String LOGGER_LOG_FOLDER_PATH = "/home/lvuser/log/";

  // Target and Robot Positions after crossing defenses
  public static final int AUTO_SHOT_DISTANCE_FROM_GOAL = 30;
  public static final double LEFT_GOAL_TARGET_X =
    149.36 - Math.sin(Math.toRadians(60.0)) * AUTO_SHOT_DISTANCE_FROM_GOAL;
  public static final double LEFT_GOAL_TARGET_Y =
    179.0 - Math.cos(Math.toRadians(60.0)) * AUTO_SHOT_DISTANCE_FROM_GOAL;
  public static final int LEFT_GOAL_OFFSET_ANGLE = 60;
  public static final double MIDDLE_GOAL_TARGET_X = 171.86;
  public static final double MIDDLE_GOAL_TARGET_Y =
    166.1 - AUTO_SHOT_DISTANCE_FROM_GOAL;
  public static final int MIDDLE_GOAL_OFFSET_ANGLE = 0;
  public static final double RIGHT_GOAL_TARGET_X =
    194.36 + Math.sin(Math.toRadians(60.0)) * AUTO_SHOT_DISTANCE_FROM_GOAL;
  public static final double RIGHT_GOAL_TARGET_Y =
    179.0 - Math.cos(Math.toRadians(60.0)) * AUTO_SHOT_DISTANCE_FROM_GOAL;
  public static final int RIGHT_GOAL_OFFSET_ANGLE = -60;
  public static final double AUTO_CAMERA_BIAS_OFFSET = -2.5;

  public static final double DEFENSE_1_X = 53.4 * 0 + 25.7;
  public static final double DEFENSE_1_Y = 0;
  public static final double DEFENSE_2_X = 53.4 * 1 + 25.7;
  public static final double DEFENSE_2_Y = 0;
  public static final double DEFENSE_3_X = 53.4 * 2 + 25.7;
  public static final double DEFENSE_3_Y = 0;
  public static final double DEFENSE_4_X = 53.4 * 3 + 25.7;
  public static final double DEFENSE_4_Y = 0;
  public static final double DEFENSE_5_X = 53.4 * 4 + 25.7;
  public static final double DEFENSE_5_Y = 0;

  public static final int SMASH_BOARD_PORT = 5801;
}
