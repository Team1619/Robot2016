package org.usfirst.frc.team1619.robot2016.states;

public class RotateToPresetTarget extends RotateToAngle {
  
  @Override
  protected double getRotationTarget() {
    return robotState.getRotateTarget();
  }
  
}