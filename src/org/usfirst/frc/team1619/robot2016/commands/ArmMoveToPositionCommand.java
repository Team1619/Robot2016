package org.usfirst.frc.team1619.robot2016.commands;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.framework.Command;
import org.usfirst.frc.team1619.robot2016.util.GenericTimer;
import org.usfirst.frc.team1619.robot2016.util.PID.ArmPID;

public class ArmMoveToPositionCommand extends Command {

  private double position;
  private GenericTimer endTimer;
  private ArmPID armPID;

  public ArmMoveToPositionCommand(double position) {
    this(position, 0);
  }

  public ArmMoveToPositionCommand(double position, int timeout) {
    super(timeout);

    this.position = position;
    endTimer = new GenericTimer();
    armPID = new ArmPID();
  }

  @Override
  protected void initialize() {
    endTimer.setDuration(Constants.ARM_AUTO_DEADTIME);
    endTimer.start();

    armPID.reset();
    armPID.setTarget(position);
  }

  @Override
  protected void update() {
    armPID.calculate();

    robotOutput.setDartMotor(armPID.get());

    if (Math.abs(armPID.getError()) <= Constants.AUTO_ARM_TOLERANCE) {
      if (endTimer.isFinished()) {
        setFinished();
      }
    }
    else {
      endTimer.start();
    }
  }

  @Override
  public void pause() {
    robotOutput.setDartMotor(0.0);
  }

  @Override
  public void destruct() {
    robotOutput.setDartMotor(0.0);
    endTimer.reset();
  }

}
