package org.usfirst.frc.team1619.robot2016;

import edu.wpi.first.wpilibj.CANTalon;

class RobotOutput {
  private static RobotOutput instance;

  private CANTalon leftDrive1;
  private CANTalon leftDrive2;
  private CANTalon rightDrive1;
  private CANTalon rightDrive2;

  private RobotOutput() {
    leftDrive1 = new CANTalon(-0);
    leftDrive2 = new CANTalon(-0);
    rightDrive1 = new CANTalon(-0);
    rightDrive2 = new CANTalon(-0);
  }

  public static RobotOutput getInstance() {
    if(instance == null) {
      instance = new RobotOutput();
    }
    return instance;
  }

  /**
   * Don't use this method. Use SensorInput instead.
   * @return
   */
  public int getLeftEncoderPosition() {
    return leftDrive1.getEncPosition();
  }

  /**
   * Don't use this method. Use SensorInput instead.
   * @return
   */
  public int getRightEncoderPosition() {
    return rightDrive1.getEncPosition();
  }
}
