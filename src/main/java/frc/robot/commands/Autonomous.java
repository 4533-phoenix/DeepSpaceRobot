package frc.team4533.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MiddleAuto extends CommandGroup {
   /**
    this moves the bot forward off the HAB platform
    */ 
    
    this.addSequential(new DrivePosition(95.28));
    public MiddleAuto(boolean left) {
        /**
        differentiates between starting on the left 2nd level or the right
         */
        if (boolean left == true) {
            /**
            turns the bot 90 degrees left
             */
            this.addSequential(new AngleTurn(-90));
            /**
            moves the bot 13 inches forward
             */
            this.addSequential(new DrivePosition(13));
            /**
            aligns the bot with the close side of the rocket ship
             */
            this.addSequential(new AngleTurn(52));
            /**
            moves forward into the rocket
             */
            this.addSequential(new DrivePosition(119));
            /**
            same thing but for right side
             */
        }else {
            this.addsequential(new AngleTurn(90));
            this.addSequential(new DrivePosition(13));
            this.addsequential(new AngleTurn(-52));
            this.addSequential(new DrivePosition(119));

        }

    }
}