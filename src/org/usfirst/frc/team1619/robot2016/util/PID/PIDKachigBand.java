package org.usfirst.frc.team1619.robot2016.util.PID;

import org.usfirst.frc.team1619.robot2016.util.GenericTimer;
import org.usfirst.frc.team1619.robot2016.util.MathUtility;

public class PIDKachigBand extends GenericPID {
  private double kKachig;

  private double kachigBand;
  private int kachigOnTime;
  private int kachigOffTime;
  private double kachigMinimumOutput;

  private GenericTimer kachigTimer;

  public PIDKachigBand() {
    this(new PIDValues(0, 0, 0));
  }

  public PIDKachigBand(PIDValues values) {
    super(values);
    kKachig = 0;
    kachigBand = 0;
    kachigOnTime = 0;
    kachigOffTime = 0;
    kachigMinimumOutput = 0;

    kachigTimer = new GenericTimer();
    kachigTimer.start();
  }

  public void reset() {
    super.reset();
    kachigTimer.reset();
  }

  public void setKachigBand(double band) {
    kachigBand = band;
  }

  public void setKachigConstant(double constant) {
    kKachig = constant;
  }

  public void setKachigTime(int onTime, int offTime) {
    kachigOnTime = onTime;
    kachigOffTime = offTime;
  }

  public void setKachigMinumumOutput(double minimum) {
    kachigMinimumOutput = minimum;
  }

  public void calculate(double currentValue) {
    double error = setPoint - currentValue;
    // If outside deadband, do nothing
    if (Math.abs(error) > deadBand) {
      // If inside kachigband, kachig
      if (Math.abs(error) < kachigBand) {
        // If we should be kachiging, return the kachig value. Else, return 0
        if (kachigTimer.get() % (kachigOnTime + kachigOffTime) < kachigOnTime) {
          outputValue = MathUtility.absMax(kKachig * error, kachigMinimumOutput,
            -kachigMinimumOutput);
        }
        else {
          outputValue = kachigMinimumOutput;
        }
      }
      else {
        // Always restart the timer so we kachig right away when we're no longer
        // within the normal PID range
        kachigTimer.start();
        outputValue = calcPID(error);
      }
    }
    else {
      outputValue = 0;
    }
  }
}
