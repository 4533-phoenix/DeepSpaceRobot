/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.ElevatorSystem;

public class ElevatorDownCommand extends Command {
  private static final double INCHES_PER_REVOLUTION = 4096 / 3.875;
  double distance = 0;
  ElevatorSystem elevatorDown;

  public ElevatorDownCommand() {
    // Sets distance equal to the postion - 19 inches * inches per revolution to get the distance in encoder ticks
    this.distance = elevatorDown.getPosition() - 19 * INCHES_PER_REVOLUTION;
    elevatorDown = ElevatorSystem.getInstance();
    this.requires(ElevatorSystem.getInstance());
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
    elevatorDown.elevatorMovement((int) distance);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(elevatorDown.getPosition()) <= distance;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    elevatorDown.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}