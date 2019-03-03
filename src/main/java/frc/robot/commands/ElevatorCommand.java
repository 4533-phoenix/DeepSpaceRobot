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
import edu.wpi.first.wpilibj.DigitalInput;
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
    ElevatorSystem.getInstance().setPIDFValues(0.1, 0.0001, 0, 0);
    controller = new Joystick(RobotMap.JOYSTICK_PORT);
    
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    ball = controller.getRawButton(RobotMap.RIGHT_TRIGGER);
    if(ball){
      elevator.elevatorMovement((int) (distance + 23.5));
    }
    else {
      elevator.elevatorMovement((int) (distance));
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
