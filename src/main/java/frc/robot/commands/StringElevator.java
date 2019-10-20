/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.ElevatorSystem;
import edu.wpi.first.wpilibj.Joystick;
public class StringElevator extends Command {
  int target;
  boolean up;
  double curPos;
  Joystick controller;
  public StringElevator(int tar) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(ElevatorSystem.getInstance());
    target = tar;
    controller = new Joystick(0);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    curPos = ElevatorSystem.getInstance().position();
    if(target >= curPos) {
      ElevatorSystem.getInstance().enable();
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(target >= curPos) {
      ElevatorSystem.getInstance().setPosition(target);
    }
    else {
      ElevatorSystem.getInstance().elevatorPercentOutput(.25);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(target <= curPos) {
      return (Math.abs(ElevatorSystem.getInstance().position())<= target);
    }
    return (Math.abs(ElevatorSystem.getInstance().position())>= target);
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
