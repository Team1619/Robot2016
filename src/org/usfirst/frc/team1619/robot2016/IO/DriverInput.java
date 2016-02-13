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
    instance = new DriverInput();
  }

  public static DriverInput getInstance() {
    return instance;
  }

  //Declare joysticks
  private Joystick rightStick;
  private Joystick leftStick;
  
  private JoystickButton testForwardButton;
  private JoystickButton testBackwardButton;

  //Declare buttons
  private JoystickButton turnPID;
  private JoystickButton resetPID;

  private DriverInput() {
    //Init joysticks
    rightStick = new Joystick(Constants.JOYSTICK_RIGHT_ID);
    leftStick = new Joystick(Constants.JOYSTICK_LEFT_ID);
    
    testForwardButton = new JoystickButton(rightStick, Constants.TEST_FORWARD_BUTTON );
    testBackwardButton = new JoystickButton(rightStick, Constants.TEST_BACKWARD_BUTTON);

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
  public boolean getTestForwardButton() {
	  return this.testForwardButton.get();
  }
  
  public boolean getTestBackwardButton() {
	  return this.testBackwardButton.get();
  }
  
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
