package org.usfirst.frc.team1619.robot2016.util;

public class GenericTimer {
  private long initialTime;
  private int duration;

  public GenericTimer() {
    initialTime = 0;
    duration = 0;
  }

  /**
   * Start the timer
   */
  public void reset() {
    initialTime = System.nanoTime();
  }

  /**
   * Set the duration of the timer
   * 
   * @param duration
   *          Timer length in milliseconds
   */
  public void set(int duration) {
    this.duration = duration;
  }

  /**
   * Return if the set time has elapsed
   * 
   * @return boolean: has timer duration elapsed
   */
  public boolean isFinished() {
    return initialTime + duration >= System.nanoTime();
  }

  /**
   * Get the amount of time that has passed since starting the timer
   * @return int: time elapsed in milliseconds
   */
  public int get() {
    return (int)(initialTime + duration - System.nanoTime());
  }
}
