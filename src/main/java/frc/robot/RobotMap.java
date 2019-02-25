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
  /**
   * This variable allows use of RIGHT_TRIGGER in the code
   */
  public static final int RIGHT_TRIGGER = 8;
  /**
   * This variable allows use of RIGHT_BUMBER in the code
   */
  public static final int RIGHT_BUMPER = 6;
  /**
   * This variable allows use of LEFT_TRIGGER in the code
   */
  public static final int LEFT_TRIGGER = 7;
  /**
   * This variable allows use of LEFT_BUMPER in the code
   */
  public static final int LEFT_BUMPER = 5;
  /**
   * This variable allows use of Y_BUTTON in the code
   */
  public static final int Y_BUTTON = 4;
  /**
   * This variable allows use of X_BUTTON in the code
   */
  public static final int X_BUTTON = 1;
  /**
   * This variable allows use of A_BUTTON in the code
   */
  public static final int A_BUTTON = 2;
  /**
   * This variable allows use of B_BUTTON in the code
   */
  public static final int B_BUTTON = 3;
  /**
   * This variable allows use of SELECT in the code
   */
  public static final int SELECT = 9;
  /**
   * This variable allows use of START in the code
   */
  public static final int START = 10;
  /**
   * This variable allows use of LEFT_JOYSTICK_BUTTON in the code
   */
  public static final int LEFT_JOYSTICK_BUTTON = 11;
  /**
   * This variable allows use of RIGHT_JOYSTICK_BUTTON in the code
   */
  public static final int RIGHT_JOYSTICK_BUTTON = 12;
  /**
   * This variable allows use of LEFT_SLAVE_MOTOR
   */
  public static final int LEFT_SLAVE_MOTOR = 1;
  /**
   * This variable allows use of LEFT_MASTER_MOTOR in the code
   */
  public static final int LEFT_MASTER_MOTOR = 0;
  /**
   * This variable allows use of RIGHT_SLAVE_MOTOR in the code
   */
  public static final int RIGHT_SLAVE_MOTOR = 3;
  /**
   * This variable allows use of RIGHT_MASTER_MOTOR
   */
  public static final int RIGHT_MASTER_MOTOR = 2;
  /**
   * This variable allows use of PID_INDEX in the code
   */
  public static final int PID_INDEX = 0;
  /**
   * This variable allows use of TIMEOUT in the code
   */
  public static final int TIMEOUT = 100;
  /**
   * This variable allows use of the JOYSTICK_PORT in the code
   */
  public static final int INTAKE_MOTOR = 4;
  public static final int ELEVATOR_MOTOR = 5;
  public static final int JOYSTICK_PORT = 0;
}

