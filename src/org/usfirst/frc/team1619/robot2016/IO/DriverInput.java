package org.usfirst.frc.team1619.robot2016.IO;

import org.usfirst.frc.team1619.robot2016.Constants;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;

/**
 * Joysticks and JoystickButtons from the driver station
 * @author Tim
 */
public class DriverInput {
  //Singleton stuff
  private static DriverInput instance;
  static {
    instance = new DriverInput();
  }

  public static DriverInput getInstance() {
    return instance;
  }

  //Declare
  private Joystick rightStick;
  private Joystick leftStick;

  private DriverInput() {
    //Init
    rightStick = new Joystick(Constants.JOYSTICK_RIGHT_ID);
    leftStick = new Joystick(Constants.JOYSTICK_LEFT_ID);
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
