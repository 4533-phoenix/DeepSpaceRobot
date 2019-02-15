/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveSystem;

public class AngleTurn extends Command {
  double angle;

  public AngleTurn(double angle) {
    this.requires(DriveSystem.getInstance());
    this.angle = angle;
    DriveSystem.getInstance().resetAngle();
    
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    DriveSystem.getInstance().setPIDFValues(0.25, 0, 2.5, 0.243);
    if (angle < 0 ) {
      DriveSystem.getInstance().driveVelocity(0.35, -0.35);
    }else{
      DriveSystem.getInstance().driveVelocity(-0.35, 0.35);
    }

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(DriveSystem.getInstance().getAngle()) >= Math.abs(angle);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    DriveSystem.getInstance().setPosition(0);
    DriveSystem.getInstance().resetAngle();
    DriveSystem.getInstance().stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
