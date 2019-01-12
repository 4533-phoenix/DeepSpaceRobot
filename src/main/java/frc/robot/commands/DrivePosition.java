/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveSystem;

public class DrivePosition extends Command {
  private DriveSystem driveSystem=DriveSystem.getInstance();
  private double distance;
  private static final double CIRCUMFRANCE=6*Math.PI;
  private static final double UNITS_PER_INCH=4096/CIRCUMFRANCE;
  public DrivePosition(int distance) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.requires(DriveSystem.getInstance());
    this.distance=distance*UNITS_PER_INCH;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    driveSystem.setPIDFValues(0.1, 0.0001, 0, 0);
    driveSystem.drivePosition((int)(-distance));
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return(Math.abs(driveSystem.getLeftPosition())>=distance)&&(Math.abs(driveSystem.getRightPosition())>=distance);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    driveSystem.setPosition(0);
    driveSystem.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
