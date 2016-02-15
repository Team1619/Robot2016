package org.usfirst.frc.team1619.robot2016.IO;

import org.usfirst.frc.team1619.robot2016.Constants;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Joysticks and JoystickButtons from the driver station
 * @author Tim
 */
public class DriverInput {
  //Singleton stuff
  private static DriverInput instance;
  static {
    try {
      instance = new DriverInput();
    }
    catch (Throwable e) {
      e.printStackTrace();
      throw e;
    }
  }

  public static DriverInput getInstance() {
    return instance;
  }

  //Declare joysticks
  private Joystick rightStick;
  private Joystick leftStick;

  //Declare buttons
  private JoystickButton turnPID;
  private JoystickButton resetPID;

  private DriverInput() {
    //Init joysticks
    rightStick = new Joystick(Constants.JOYSTICK_RIGHT_ID);
    leftStick = new Joystick(Constants.JOYSTICK_LEFT_ID);

    //Init buttons
    turnPID = new JoystickButton(rightStick, Constants.BUTTON_DRIVE_PID_TURN);
    resetPID = new JoystickButton(rightStick, Constants.BUTTON_DRIVE_PID_RESET);
  }

  //Buttons
  public boolean getTurnPID() {
    return turnPID.get();
  }

  public boolean getResetPID() {
    return resetPID.get();
  }

  // Driver
  public GenericHID getDriverStick() {
    return rightStick;
  }

  public double getDriverX() {
    return rightStick.getX();
  }

  public double getDriverY() {
    return rightStick.getY();
  }

  public double getDriverTwist() {
    return rightStick.getTwist();
  }

  public double getDriverThrottle() {
    return rightStick.getThrottle();
  }

  // Operator
  public GenericHID getOperatorStick() {
    return leftStick;
  }

  public double getOperatorX() {
    return leftStick.getX();
  }

  public double getOperatorY() {
    return leftStick.getY() * -1.0;
  }

  public double getOperatorTwist() {
    return leftStick.getTwist();
  }

  public double getOperatorThrottle() {
    return leftStick.getThrottle();
  }
}
