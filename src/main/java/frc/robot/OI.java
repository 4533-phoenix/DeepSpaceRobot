/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;
import frc.robot.subsystems.ElevatorSystem;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
/**
 * create OI Class
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
  /**
   * create controller object
   */
  /**
   * create constructor for OI class
   */
  Joystick controller = new Joystick(RobotMap.JOYSTICK_PORT);
  /**
   * add buttons start and select by creating objects
   */
  JoystickButton select = new JoystickButton(controller,RobotMap.SELECT);
  JoystickButton start = new JoystickButton(controller, RobotMap.START);
  JoystickButton aButton = new JoystickButton(controller, RobotMap.A_BUTTON);
  JoystickButton leftBumperButton = new JoystickButton(controller, RobotMap.LEFT_BUMPER);
  JoystickButton rightBumperButton = new JoystickButton(controller, RobotMap.RIGHT_BUMPER);
  JoystickButton bButton = new JoystickButton(controller, RobotMap.B_BUTTON);
  JoystickButton yButton = new JoystickButton(controller, RobotMap.Y_BUTTON);
  JoystickButton xButton = new JoystickButton(controller, RobotMap.X_BUTTON);
  JoystickButton rightTrigger = new JoystickButton(controller, RobotMap.RIGHT_TRIGGER);
  JoystickButton leftTrigger = new JoystickButton(controller, RobotMap.LEFT_TRIGGER);
  public OI() {
    boolean percentOutput = false;
    if(percentOutput) {
      bButton.whileHeld(new ElevatorPercentOutput(.5));
      aButton.whileHeld(new ElevatorPercentOutput(-.25));
    }
    else {
      bButton.whenPressed(new ElevatorPercentDown(-.25));
      /*
      if(controller.getRawButton(RobotMap.RIGHT_TRIGGER)) {
        //When pressed elevator goes to first cargo level
        aButton.whenPressed(new ElevatorCommand(27.5));
        // When pressed elevator goes to second cargo level
        xButton.whenPressed(new ElevatorCommand(55.5));
        // When pressed elevator goes to third cargo level
        yButton.whenPressed(new ElevatorCommand(83.5));   
      }
      */
      //When pressed elevator goes to first hatch level
      aButton.whenPressed(new ElevatorCommand(19-15));
      //When pressed elevator goes to second hatch level
      xButton.whenPressed(new ElevatorCommand(47-15));
      //When pressed elevator goes to third hatch level
      yButton.whenPressed(new ElevatorCommand(75-15));
      select.whenPressed(new ElevatorPercentOutput(-.25));
      start.whileHeld(new ElevatorPercentOutput(.5));
    }
    leftBumperButton.whileHeld(new IntakeIn());
    rightBumperButton.whileHeld(new IntakeOut());
    
    //Buttons that are currently free
    /*
    Start
    RightJoystickButton
    LeftJoystickButton
    */
  }

}

