/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
  public final int RIGHT_TRIGGER = 8;
  public final int RIGHT_BUMPER = 6;
  public final int LEFT_TRIGGER = 7;
  public final int LEFT_BUMPER = 5;
  public final int Y_BUTTON = 4;
  public final int X_BUTTON = 1;
  public final int A_BUTTON = 2;
  public final int B_BUTTON = 3;
  public final int SELECT = 9;
  public final int START = 10;
  public final int LEFT_JOYSTICK_BUTTON = 11;
  public final int RIGHT_JOYSTICK_BUTTON = 12;
  public final int LEFT_SLAVE_MOTOR = 1;
  public final int LEFT_MASTER_MOTOR = 0;
  public final int RIGHT_SLAVE_MOTOR = 3;
  public final int RIGHT_MASTER_MOTOR = 2;
  public final int PID_INDEX = 0;
  public final int TIMEOUT = 100;
}

