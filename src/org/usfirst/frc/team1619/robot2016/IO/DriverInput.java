package org.usfirst.frc.team1619.robot2016.IO;

import org.usfirst.frc.team1619.robot2016.Constants;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Joysticks and JoystickButtons from the driver station
 */
public class DriverInput {

  private static DriverInput instance;

  static {
    try {
      instance = new DriverInput();
    }
    catch (Throwable exception) {
      exception.printStackTrace();
      throw exception;
    }
  }

  public static DriverInput getInstance() {
    return instance;
  }

  // Declare joysticks
  private Joystick driverStick;
  private Joystick operatorStick;

  private JoystickButton[] driverStickButtons;
  private JoystickButton[] operatorStickButtons;

  private DriverInput() {
    // Initialize joysticks
    driverStick = new Joystick(Constants.JOYSTICK_RIGHT_ID);
    operatorStick = new Joystick(Constants.JOYSTICK_LEFT_ID);

    driverStickButtons = new JoystickButton[15];
    operatorStickButtons = new JoystickButton[15];

    for (int i = 0; i < 15; i++) {
      driverStickButtons[i] = new JoystickButton(driverStick, i + 1);
      operatorStickButtons[i] = new JoystickButton(operatorStick, i + 1);
    }
  }

  // Buttons
  public boolean getDriverButton(int buttonID) {
    return driverStickButtons[buttonID - 1].get();
  }

  public boolean getOperatorButton(int buttonID) {
    return operatorStickButtons[buttonID - 1].get();
  }

  // Driver
  public double getDriverX() {
    return driverStick.getX();
  }

  public double getDriverY() {
    return driverStick.getY();
  }

  public double getDriverTwist() {
    return driverStick.getTwist();
  }

  public double getDriverThrottle() {
    return driverStick.getThrottle();
  }

  // Operator
  public double getOperatorX() {
    return operatorStick.getX();
  }

  public double getOperatorY() {
    return operatorStick.getY();
  }

  public double getOperatorTwist() {
    return operatorStick.getTwist();
  }

  public double getOperatorThrottle() {
    return operatorStick.getThrottle();
  }

}
