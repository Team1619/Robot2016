package org.usfirst.frc.team1619.robot2016;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;

/**
 * Joysticks and JoystickButtons from the driver station
 * 
 * @author Tim
 */
public class DriverInput {
  private static DriverInput instance;
  static {
    instance = new DriverInput();
  }

  private Joystick rightStick;
  private Joystick leftStick;

  private DriverInput() {
    rightStick = new Joystick(Constants.JOYSTICK_RIGHT_ID);
    leftStick = new Joystick(Constants.JOYSTICK_LEFT_ID);
  }

  public static DriverInput getInstance() {
    return instance;
  }

  // Driver
  public GenericHID getDriverStick() {
    return this.rightStick;
  }

  public double getDriverX() {
    return this.rightStick.getX();
  }

  public double getDriverY() {
    return this.rightStick.getY();
  }

  public double getDriverTwist() {
    return this.rightStick.getTwist();
  }

  public double getDriverThrottle() {
    return this.rightStick.getThrottle();
  }

  // Operator
  public GenericHID getOperatorStick() {
    return this.leftStick;
  }

  public double getOperatorX() {
    return this.leftStick.getX();
  }

  public double getOperatorY() {
    return this.leftStick.getY();
  }

  public double getOperatorTwist() {
    return this.leftStick.getTwist();
  }

  public double getOperatorThrottle() {
    return this.leftStick.getThrottle();
  }
}
