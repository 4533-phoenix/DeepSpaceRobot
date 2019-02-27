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
    // actual 46.28
    this.addSequential(new DrivePosition(46.28));
    if(left == true) {
      //actual 31.0695
      this.addSequential(new AngleTurn(31.0695));
      //actual 58.373
      this.addSequential(new DrivePosition(58.373));
      //actual 148.9305
      this.addSequential(new AngleTurn(-180+148.9305));
      //actual 73.97
      this.addSequential(new DrivePosition(70.97));
      this.addSequential(new ElevatorCommand(19));
      this.addSequential(new DrivePosition(3));
      // this.addSequential(new HatchExit());
      this.addSequential(new DrivePosition(-12));
      this.addSequential(new AngleTurn(-149.72));
      this.addSequential(new DrivePosition(182.09));
      this.addSequential(new AngleTurn(-30.28));
      this.addSequential(new DrivePosition(95.28));
    }
    else {
      //actual 31.0695
      this.addSequential(new AngleTurn(-31.0695));
      //actual 58.373
      this.addSequential(new DrivePosition(58.373));
      //actual 148.9305
      this.addSequential(new AngleTurn(148.9305));
      //actual 73.97
      this.addSequential(new DrivePosition(70.97));
      this.addSequential(new ElevatorCommand(19));
      this.addSequential(new DrivePosition(3));
      // this.addSequential(new HatchExit());
      this.addSequential(new DrivePosition(-12));
      this.addSequential(new AngleTurn(149.72));
      this.addSequential(new DrivePosition(182.09));
      this.addSequential(new AngleTurn(30.28));
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
