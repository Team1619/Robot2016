package org.usfirst.frc.team1619.robot2016;

import org.usfirst.frc.team1619.robot2016.util.PID.PIDValues;
import org.usfirst.frc.team1619.robot2016.util.logger.Logger.LoggingLevel;

public class Constants {
  // Joysticks
  public static final int JOYSTICK_RIGHT_ID = 0;
  public static final int JOYSTICK_LEFT_ID = 1;
  public static final double JOYSTICK_DEADZONE = 0.05;

  // Driver buttons
  public static final int DRIVER_BUTTON_SHOOT_FIND_LEFT = 1;
  public static final int DRIVER_BUTTON_SHOOT_FIND_RIGHT = 4;
  public static final int DRIVER_BUTTON_DRIVE_HOLD_HEADING = 2;
  public static final int DRIVER_BUTTON_DRIVE_TURN_TO_PRESET = 9;
  public static final int DRIVER_BUTTON_DRIVE_TURN_TO_CAMERA = 6;
  public static final int DRIVER_BUTTON_DRIVE_INVERT = 8;
  public static final int DRIVER_BUTTON_SHOOT_OFFSET_INCREASE = 7;
  public static final int DRIVER_BUTTON_SHOOT_OFFSET_DECREASE = 13;
  public static final int DRIVER_BUTTON_SHOOT_OFFSET_RESET = 14;

  // Operator buttons
  public static final int OPERATOR_BUTTON_SHOOT = 1;
  // Spool up shooter motor out, intake out when shoot is at the right speed
  public static final int OPERATOR_BUTTON_SHOOT_RELEASE = 2;
  // Manual intake out while in operator shoot
  // Spool shooter motor in, intake in
  public static final int OPERATOR_BUTTON_ZERO_DART = 4;
  // Zero dart to the top
  public static final int OPERATOR_BUTTON_ARM_SHOOT = 5;
  public static final int OPERATOR_BUTTON_ARM_DEFAULT = 6;
  public static final int OPERATOR_BUTTON_INTAKE = 7;
  public static final int OPERATOR_BUTTON_INTAKE_MANUAL = 2;
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
  public static final double AUTO_DRIVE_TRANSLATION_STOP_SPEED = 20;
  public static final double AUTO_DRIVE_TRANSLATION_MAX_OUTPUT = 0.85;
  public static final int AUTO_DRIVE_ROTATION_DEADTIME = 200;
  public static final double AUTO_DRIVE_ROTATION_MAX_OUTPUT = 0.85;
  public static final double AUTO_DRIVE_ROTATION_TOLERANCE = 0.7;
  public static final double AUTO_ARM_TOLERANCE = 0.1;
  public static final double AUTO_ARM_STOP_SPEED = 0.2;

  public static final double AUTO_LANE_1_DISTANCE = 90.0;
  public static final double AUTO_LANE_2_DISTANCE = 75.0;
  public static final double AUTO_LANE_3_DISTANCE = 35.0;
  public static final double AUTO_LANE_4_DISTANCE = 35.0;
  public static final double AUTO_LANE_5_DISTANCE = 75.0;

  public static final double AUTO_DISTANCE_LINE_TO_PLATFORM = 50.0;
  public static final double AUTO_DISTANCE_LOW_BAR = 85.0;
  public static final double AUTO_DISTANCE_ROCK_WALL = 115.0;
  public static final double AUTO_DISTANCE_ROUGH_TERRAIN = 85.0;
  public static final double AUTO_DISTANCE_MOAT = 110.0;
  public static final double AUTO_DISTANCE_RAMPARTS = 105.0;
  public static final double AUTO_DISTANCE_CHEVALLE = 5.0;

  public static final double AUTO_RETURN_OFFSET_LOW_BAR = 0.0;
  public static final double AUTO_RETURN_OFFSET_ROCK_WALL = 15.0;
  public static final double AUTO_RETURN_OFFSET_ROUGH_TERRAIN = 5.0;
  public static final double AUTO_RETURN_OFFSET_MOAT = 15.0;
  public static final double AUTO_RETURN_OFFSET_RAMPARTS = 15.0;
  public static final double AUTO_RETURN_OFFSET_CHEVALLE = 15.0;

  public static final double AUTO_STATIC_DEFENSES_SPEED = 0.9;
  public static final double AUTO_DEFENSE_TRANSLATE_TOLERANCE = 12.0;

  // Drive Train
  public static final PIDValues DRIVE_PID_ROTATION =
    new PIDValues(0.08, 0.015, 0.22);
  public static final double DRIVE_PID_ROTATION_IRANGE = 3;
  public static final double DRIVE_PID_ROTATION_IMAX =
    0.40 / DRIVE_PID_ROTATION.i;
  public static final double DRIVE_PID_ROTATION_DEADZONE = 0.0;

  public static final PIDValues DRIVE_PID_TRANSLATION =
    new PIDValues(0.03, 0.005, 0.02);
  public static final double DRIVE_PID_TRANSLATION_IRANGE = 5;
  public static final double DRIVE_PID_TRANSLATION_IMAX =
    0.35 / DRIVE_PID_TRANSLATION.i;
  public static final double DRIVE_PID_TRANSLATION_DEADZONE = 0.01;
  public static final double DRIVE_PID_TRANSLATION_MINIMUM_OUTPUT = 0.3;

  public static final int DRIVE_ROTATE_TO_TARGET_TIMEOUT = 5000;
  public static final double DRIVE_ROTATE_TO_TARGET_TOLERANCE = 1.0;
  public static final double DRIVE_ROTATE_UPDATE_VISION_ANGLE_SPEED = 0.75;

  public static final double DRIVE_FIND_CONTOUR_ROTATE_SPEED = 0.8;
  public static final double DRIVE_FIND_GOOD_CONTOUR_ROTATE_SPEED = 0.7;

  // Intake
  public static final double INTAKE_INTAKE_SPEED = -1.0;
  public static final double INTAKE_SHOOT_SPEED = 1.0;
  public static final int SHOOTING_INTAKE_OUT_TIME = 500;
  public static final double INTAKE_STALL_SPEED = 100;
  public static final int INTAKE_STALL_TIME_BEFORE_STOP = 750;

  // Shooter
  public static final double SHOOTER_PASSIVE_SPEED = 0.0;
  public static final double SHOOTER_INTAKE_SPEED = -1.0;
  public static final double SHOOTER_SHOOT_SPEED = 1.0;
  public static final double SHOOTER_SPOOL_UP_SPEED = 0.15;
  public static final double SHOOTER_BATTER_SPOOL_UP_SPEED = 0.15;
  public static final double SHOOTER_OFFSET_INCREMENT = 1.0;
  public static final double SHOOTER_INITIAL_OFFSET_ANGLE = -4.5;

  // Scaler
  public static final double SCALER_EXTEND_SPEED = 1.0;
  public static final double SCALER_RETRACT_SPEED = -1.0;
  public static final double SCALER_MANUAL_TIMER_START = 25;
  public static final double SCALER_SCALE_SPEED = -0.5;
  public static final int SCALER_SCALE_TIME = 200;

  // Utility Arm
  public static final double ARM_MAX_SPEED = 0.8;
  public static final double ARM_BOTTOM_THROTTLE_POSITION = -6.0;
  public static final double ARM_TOP_THROTTLE_POSITION = 1.0;
  public static final double ARM_THROTTLE_SPEED = 0.625;

  public static final double ARM_SCALE_SPEED = -0.5;
  public static final int ARM_SCALE_TIME = 500;

  public static final PIDValues ARM_PID = new PIDValues(1.2, 0.0, 0.0);
  public static final double ARM_PID_IRANGE = 0.0;
  public static final double ARM_PID_IMAX = 0.3 / ARM_PID.i;
  public static final double ARM_PID_DEADZONE = 0.05;
  public static final double ARM_PID_MINIMUM = 0.1;
  public static final int ARM_AUTO_DEADTIME = 500;

  public static final double ARM_ZERO_SPEED = 0.75;

  public static final double ARM_POSITION_DEFAULT = -3.5;
  public static final double ARM_POSITION_VISION = 0.0;

  // Shot configurations
  public static final double SHOT_BATTER_CUT_OFF = 110.0;
  public static final double SHOT_MID_RANGE_CUT_OFF = 137.5;
  public static final double SHOT_LONG_RANGE_CUT_OFF = 160.0;

  public static final int SHOT_BATTER_SHOOT_SPEED = 5000;
  public static final int SHOT_MID_RANGE_SHOOT_SPEED = 15000;
  public static final int SHOT_LONG_RANGE_SHOOT_SPEED = 32000;
  public static final int SHOT_LONGEST_RANGE_SHOOT_SPEED = 32000;

  public static final double SHOT_BATTER_ARM_POSITION = 0.0;
  public static final double SHOT_MID_RANGE_ARM_POSITION = -0.4;
  public static final double SHOT_LONG_RANGE_ARM_POSITION = -0.4;
  public static final double SHOT_LONGEST_RANGE_ARM_POSITION = -0.4;

  // Arm Hall Effects
  public static final int ARM_UPPER_HALLEFFECT_ID = 0;
  public static final int ARM_LOWER_HALLEFFECT_ID = 1;

  // Motor IDs
  public static final int DRIVE_LEFT_1_ID = 1;
  public static final int DRIVE_LEFT_2_ID = 2;
  public static final int DRIVE_RIGHT_1_ID = 3;
  public static final int DRIVE_RIGHT_2_ID = 4;
  public static final int DART_MOTOR_ID = 5;
  public static final int INTAKE_MOTOR_ID = 6;
  public static final int SHOOTER_MOTOR_ID = 7;
  public static final int SHOOTER_MOTOR_2_ID = 9;
  public static final int SCALER_MOTOR_ID = 8;

  // Digital IO
  public static final int BALL_PRESENCE_SENSOR_REAR_ID = 2;
  public static final int BALL_PRESENCE_SENSOR_FRONT_ID = 3;

  // Relays
  public static final int RELAY_BALL_DETECTED_REAR_ID = 1;
  public static final int RELAY_BALL_DETECTED_FRONT_ID = 0;
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

  public static final int SMASH_BOARD_PORT = 5801;
}
