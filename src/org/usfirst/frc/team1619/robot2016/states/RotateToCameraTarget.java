package org.usfirst.frc.team1619.robot2016.states;

public class RotateToCameraTarget extends RotateToAngle {

  @Override
  protected double getRotationTarget() {
    return (sensorInput.getNavXHeading()
      - smashBoard.getRotationOffsetToAligned() + 360) % 360;
  }

}