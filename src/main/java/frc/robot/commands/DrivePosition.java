/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveSystem;
//Create DrivePosition Class
public class DrivePosition extends Command {
  private DriveSystem driveSystem=DriveSystem.getInstance();
  //creating distance variable
  private double distance;
  /** 
   * get the circumfrace of the wheels used on the robot
  */
  private static final double CIRCUMFRANCE=6*Math.PI;
  /**
   * set the coding units are on the wheel
   */
  private static final double UNITS_PER_INCH=4096/CIRCUMFRANCE;
  public DrivePosition(double distance) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);

    this.requires(DriveSystem.getInstance());
    /**
     * sets variable distance equal to parameter distance * UNITS_PER_INCH
     */
    this.distance=distance*UNITS_PER_INCH;
    /**
     * sets max speed to 1/2 the full capacity
     */
    DriveSystem.getInstance().setPeakOutput(.5);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override

  protected void execute() {
    /**
     * sets the proportional gain, integral gain,
     * derivative gain, feed forward gain
     */
    driveSystem.setPIDFValues(0.1, 0.0001, 0, 0);
    /**
     * sets the double variable distance to an int variable
     */
    driveSystem.drivePosition((int)(-distance));
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    /**
     * returns the absolute value of (driveSystem's left position >= distance) 
     * and the absolute value of (driveSystem's right position >= distance)
     */
    return(Math.abs(driveSystem.getLeftPosition())>=distance)&&(Math.abs(driveSystem.getRightPosition())>=distance);
  }

  // Called once after isFinished returns true
  @Override
  /**
   * tells code what to do when finished
   */
  protected void end() {
    /**
     * sets the maximum amount of speed
     */
    driveSystem.setPeakOutput(1);
    /**
     * Stops the robot
     */
    driveSystem.stop();
    /**
     * resets the position to 0
     */
    driveSystem.setPosition(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
