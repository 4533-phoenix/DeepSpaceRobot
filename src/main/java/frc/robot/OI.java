/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.ElevatorPercentOutput;
import frc.robot.commands.IntakeIn;
import frc.robot.commands.IntakeOut;
import frc.robot.commands.Invert;
import frc.robot.commands.Normal;
import frc.robot.commands.StringElevator;

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
  Joystick buttons = new Joystick(1);
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
  JoystickButton rightJoystickButton = new JoystickButton(controller, RobotMap.RIGHT_JOYSTICK_BUTTON);
  JoystickButton leftJoystickButton = new JoystickButton(controller, RobotMap.LEFT_JOYSTICK_BUTTON);
  JoystickButton cargo1 = new JoystickButton(buttons, 3);
  JoystickButton cargo2 = new JoystickButton(buttons, 2);
  JoystickButton cargo3 = new JoystickButton(buttons, 1);
  JoystickButton hatch1 = new JoystickButton(buttons, 4);
  JoystickButton hatch2 = new JoystickButton(buttons, 5);
  JoystickButton hatch3 = new JoystickButton(buttons, 7);
  JoystickButton zero = new JoystickButton(buttons, 8);
  JoystickButton manualUp = new JoystickButton(buttons, 10);
  JoystickButton manualDown = new JoystickButton(buttons, 9);

  public OI() {
    boolean percentOutput = false;
    if(percentOutput) {
      bButton.whileHeld(new ElevatorPercentOutput(.5));
      aButton.whileHeld(new ElevatorPercentOutput(-.25));
    }
    else {
      //aButton.whenPressed(new StringElevator(RobotMap.BOTTOM));
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
      //bButton.whenPressed(new ElevatorCommand(19-15));
      //bButton.whenPressed(new StringElevator(RobotMap.BOTTOM_HATCH));
      //When pressed elevator goes to second hatch level
      //xButton.whenPressed(new ElevatorCommand(47-15));
      //2278
      //xButton.whenPressed(new StringElevator(RobotMap.MID_HATCH));
      //When pressed elevator goes to third hatch level
      //yButton.whenPressed(new ElevatorCommand(75-15));
      //yButton.whenPressed(new StringElevator(RobotMap.TOP_HATCH));
      select.whenPressed(new Normal());
      start.whenPressed(new Invert());
      xButton.whileHeld(new ElevatorPercentOutput(.25));
      yButton.whileHeld(new ElevatorPercentOutput(-.5));
    }

    leftTrigger.whileHeld(new IntakeIn());
    rightTrigger.whileHeld(new IntakeOut());
    
    //2nd driver controller
    cargo1.whenPressed(new StringElevator(RobotMap.BOTTOM_CARGO));
    cargo2.whenPressed(new StringElevator(RobotMap.MID_CARGO));
    cargo3.whenPressed(new StringElevator(RobotMap.TOP_CARGO));
    hatch1.whenPressed(new StringElevator(RobotMap.BOTTOM_HATCH));
    hatch2.whenPressed(new StringElevator(RobotMap.MID_HATCH));
    hatch3.whenPressed(new StringElevator(RobotMap.TOP_HATCH));
    zero.whenPressed(new StringElevator(RobotMap.BOTTOM));
    manualDown.whileHeld(new ElevatorPercentOutput(.25));
    manualUp.whileHeld(new ElevatorPercentOutput(-.5));
    //Buttons that are currently free
    /*
    Start
    RightJoystickButton
    LeftJoystickButton
    */
  }
}

