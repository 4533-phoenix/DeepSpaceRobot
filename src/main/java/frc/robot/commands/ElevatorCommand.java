/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;
import frc.robot.subsystems.ElevatorSystem;
import edu.wpi.first.wpilibj.Joystick;

public class ElevatorCommand extends Command {

  private static final double INCHES_PER_REVOLUTION = 4096 / 4.33 ;
  double distance = 0 ;
  ElevatorSystem elevator; 
  boolean ball;
  Joystick controller;

  public ElevatorCommand(double distance) {
    this.distance = distance * INCHES_PER_REVOLUTION;
    elevator = ElevatorSystem.getInstance();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.requires(ElevatorSystem.getInstance());
    ElevatorSystem.getInstance().setPIDFValues(2, 0.0001, 0, 0);
    controller = new Joystick(RobotMap.JOYSTICK_PORT);
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if(controller.getRawButton(RobotMap.RIGHT_BUMPER)){
      distance+=21*INCHES_PER_REVOLUTION;
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    ball = controller.getRawButton(RobotMap.RIGHT_BUMPER);
    elevator.elevatorMovement((int) (distance));
    if(!elevator.getLimitSwitchIntake() && !elevator.getLimitSwitchMid()) {
      elevator.setPosition((int)(72*INCHES_PER_REVOLUTION));
    }
    else if(!elevator.getLimitSwitchBottom()) {
      elevator.setPosition((int)(0));
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(!elevator.getLimitSwitchIntake()&& !elevator.getLimitSwitchMid()) {
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
