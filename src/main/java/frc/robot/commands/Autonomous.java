package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.*;
public class Autonomous extends CommandGroup {

    /**
    this moves the bot forward off the HAB platform
    */ 

    
    public Autonomous(boolean left) {
        this.addSequential(new DrivePosition(95));
        /**
        differentiates between starting on the left 2nd level or the right
         */
        if (left == true) {
            /**
            turns the bot 90 degrees left
             */
            this.addSequential(new AngleTurn(-90));
            /**
            moves the bot 13 inches forward
            actual:13.00189572 
             */
            this.addSequential(new DrivePosition(13));
            /**
            aligns the bot with the close side of the rocket ship
             */
            this.addSequential(new AngleTurn(52));
            /**
            moves forward into the rocket
            Actual: 119.0695697
             */
            this.addSequential(new DrivePosition(116));
            this.addSequential(new ElevatorCommand(19));
            this.addSequential(new DrivePosition(3));
            this.addSequential(new HatchExit());
            /**
            same thing but for right side
             */
        }else {
            this.addSequential(new AngleTurn(90));
            this.addSequential(new DrivePosition(13));
            this.addSequential(new AngleTurn(-52));
            this.addSequential(new DrivePosition(116));
            this.addSequential(new ElevatorCommand(19));
            this.addSequential(new DrivePosition(3));
            this.addSequential(new HatchExit());

        }

    }
}