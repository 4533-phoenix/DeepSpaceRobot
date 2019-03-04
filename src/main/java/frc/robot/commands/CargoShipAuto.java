/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.*;

public class CargoShipAuto extends CommandGroup {

  public CargoShipAuto(boolean left) {
    // go forward off the 2nd HAB level
    this.addSequential(new DrivePosition(46.28));
    if(left == true) {
      //Turn slightly right
      this.addSequential(new AngleTurn(31.0695));
      //go forward to align with cargo ship
      this.addSequential(new DrivePosition(58.373));
      //Turn so you are colinear to the cargoship
      this.addSequential(new AngleTurn(-180+148.9305));
      // go forward to the cargoship
      this.addSequential(new DrivePosition(70.97));
      //moves the elevator up to the hatch level on the cargoship
      this.addSequential(new ElevatorCommand(19));
      // moves forward to have the intake exactly on the cargoship
      this.addSequential(new DrivePosition(3));
      //makes the intake not hold onto the hatch
      this.addSequential(new ElevatorDownCommand());
      this.addSequential(new DrivePosition(-15));
      //turns left for going to the loading station
      this.addSequential(new AngleTurn(-149.72));
      //moves forward to align with the intake
      this.addSequential(new DrivePosition(182.09));
      //turns and becomes colinear to the loading station
      this.addSequential(new AngleTurn(-30.28));
      //goes up to the loading station
      this.addSequential(new DrivePosition(95.28));
    }
    else {
      //turns slightly left 
      this.addSequential(new AngleTurn(-31.0695));
      //moves forward to align with the cargo ship
      this.addSequential(new DrivePosition(58.373));
      //turns right to become colinear with the Cargo Ship
      this.addSequential(new AngleTurn(148.9305));
      //moves forward to align with the cargo ship
      this.addSequential(new DrivePosition(70.97));
      //moves the elevator up to the hatch level on the cargoship
      this.addSequential(new ElevatorCommand(19));
      this.addSequential(new DrivePosition(3));
      //makes the intake not hold onto the hatch
      this.addSequential(new ElevatorDownCommand());
      this.addSequential(new DrivePosition(-15));
      //turns right to go to the loading station
      this.addSequential(new AngleTurn(149.72));
      //moves forward to align with loading station
      this.addSequential(new DrivePosition(182.09));
      //turns to be colinear with loading station
      this.addSequential(new AngleTurn(30.28));
      //moves forward to loading station
      this.addSequential(new DrivePosition(95.28));
    }
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
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
