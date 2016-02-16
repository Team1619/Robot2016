package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.IO.RobotOutput;
import org.usfirst.frc.team1619.robot2016.IO.SensorInput;
import org.usfirst.frc.team1619.robot2016.subsystems.SubsystemID;

public class AutoShoot extends State {

  private static final SubsystemID[] subsystems;

  private RobotOutput robotOutput;
  private SensorInput sensorInput;

  private int extraTime;

  static {
    subsystems = new SubsystemID[] {SubsystemID.SHOOTER_INTAKE};
  }

  public AutoShoot() {
    super(subsystems);

    robotOutput = RobotOutput.getInstance();
    sensorInput = SensorInput.getInstance();
  }

  @Override
  protected void setup() {
    extraTime = 0;
  }

  @Override
  protected void execute() {
    robotOutput.setShooterMotor(1.0);

    if (sensorInput.getShooterEncoderVelocity() >= 29000) {
      robotOutput.setIntakeMotor(1.0);
      extraTime++;
    }
    else {
      robotOutput.setIntakeMotor(0.0);
    }

    if (extraTime > 5) {
      setFinished();
    }
  }

  @Override
  protected void pause() {
    robotOutput.setShooterMotor(0.0);
    robotOutput.setIntakeMotor(0.0);
  }

  @Override
  protected void destroy() {
    robotOutput.setShooterMotor(0.0);
    robotOutput.setIntakeMotor(0.0);
    setFinished();
  }

  @Override
  public boolean isReadyForActive() {
    return !getFinished();
  }

}
