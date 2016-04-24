package org.usfirst.frc.team1619.robot2016.states;

import org.usfirst.frc.team1619.robot2016.Constants;
import org.usfirst.frc.team1619.robot2016.SubsystemID;
import org.usfirst.frc.team1619.robot2016.commands.ArmMoveToPositionCommand;
import org.usfirst.frc.team1619.robot2016.commands.ControlFrontLEDs;
import org.usfirst.frc.team1619.robot2016.commands.ControlRearLEDs;
import org.usfirst.frc.team1619.robot2016.commands.FindContourCommand;
import org.usfirst.frc.team1619.robot2016.commands.FlashFrontLEDs;
import org.usfirst.frc.team1619.robot2016.commands.ShootFromAnywhereCommand;
import org.usfirst.frc.team1619.robot2016.framework.CommandSequence;
import org.usfirst.frc.team1619.robot2016.framework.SequencerState;

public class MultiShootFromAnywhere extends SequencerState {

  private int driverButton;
  private boolean left;

  private static SubsystemID[] subsystems;

  static {
    subsystems = new SubsystemID[] {SubsystemID.DRIVE_TRAIN,
      SubsystemID.UTILITY_ARM, SubsystemID.INTAKE, SubsystemID.SHOOTER,
      SubsystemID.SERVO, SubsystemID.LEDS};
  }

  public MultiShootFromAnywhere(int driverButton, boolean left) {
    super(subsystems);

    this.driverButton = driverButton;
    this.left = left;
  }

  @Override
  protected void addCommands() {
    CommandSequence align = new CommandSequence();
    align.add(new FindContourCommand(left));
    align.addPassive(new FlashFrontLEDs(5));

    CommandSequence shoot = new CommandSequence();
    shoot.add(new ShootFromAnywhereCommand());
    shoot.addPassive(new ControlRearLEDs() {
      @Override
      protected void updateLEDs() {
        setLEDs(true);
      }
    });
    shoot.addPassive(new FlashFrontLEDs(5));

    CommandSequence finish = new CommandSequence();

    finish.add(new ArmMoveToPositionCommand(Constants.ARM_POSITION_DEFAULT));
    finish.addPassive(new ControlRearLEDs() {
      @Override
      protected void updateLEDs() {
        setLEDs(true);
      }
    });
    finish.addPassive(new ControlFrontLEDs() {
      @Override
      protected void updateLEDs() {
        setLEDs(true);
      }
    });

    add(align);
    add(shoot);
    add(finish);
  }

  @Override
  protected void update() {
    robotOutput.setExtensionServoPWM(Constants.SERVO_OUT_POSITION);

    super.update();
  }

  @Override
  protected void pause() {
    robotOutput.setExtensionServoPWM(Constants.SERVO_IN_POSITION);

    super.pause();
  }

  @Override
  protected void destruct() {
    robotOutput.setExtensionServoPWM(Constants.SERVO_IN_POSITION);

    super.destruct();
  }

  @Override
  public boolean isReadyForActive() {
    return driverInput.getDriverButton(driverButton);
  }

}
