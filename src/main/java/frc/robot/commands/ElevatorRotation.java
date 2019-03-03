/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.ElevatorSystem;

public class ElevatorRotation extends Command {
  ElevatorSystem elevator;
  double curdist;
  public ElevatorRotation() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    elevator = ElevatorSystem.getInstance();
    curdist = elevator.getPosition();
    this.requires(elevator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    elevator.elevatorMovement(4096);
    System.out.println(elevator.getPosition());
    System.out.println(curdist);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(elevator.getPosition()) >= curdist + 4096;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    elevator.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
