/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.ElevatorSystem;
public class StringElevator extends Command {
  int target;
  boolean up;
  double curPos;
  public StringElevator(int tar) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(ElevatorSystem.getInstance());
    target = tar;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    ElevatorSystem.getInstance().enable();
    curPos = ElevatorSystem.getInstance().position();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    ElevatorSystem.getInstance().setPosition(target);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (Math.abs(ElevatorSystem.getInstance().position()-target)>= 0);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    ElevatorSystem.getInstance().disable();
    ElevatorSystem.getInstance().stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
