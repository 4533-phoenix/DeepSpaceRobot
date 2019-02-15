/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.ElevatorSystem;
import edu.wpi.first.wpilibj.DigitalInput;

public class ElevatorCommand extends Command {

  private static final double INCHES_PER_REVOLUTION = 4096 / 3.875 ;
  double distance = 0 ;
  ElevatorSystem elevator; 
  DigitalInput limitSwitch = new DigitalInput(1);
  DigitalInput limitSwitchTwo = new DigitalInput(0);

  public ElevatorCommand(double distance) {
    this.distance = distance * INCHES_PER_REVOLUTION;
    elevator = ElevatorSystem.getInstance();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.requires(ElevatorSystem.getInstance());
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
   elevator.elevatorMovement((int) -distance);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(limitSwitch.get()) {
      return true;
    }
    if(limitSwitchTwo.get()) {
      return true;
    }
    return Math.abs(elevator.getPosition()) >= distance;

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
