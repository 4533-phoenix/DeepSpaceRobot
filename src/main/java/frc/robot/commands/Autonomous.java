package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous extends CommandGroup {

    private double angleWait = 1;

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
            this.addSequential(new AutoDrive(1, -1), angleWait);
            /**
            moves the bot 13 inches forward
            actual:13.00189572 
             */
            this.addSequential(new DrivePosition(13));
            /**
            aligns the bot with the close side of the rocket ship
             */
            this.addSequential(new AutoDrive(-1, 1), (angleWait/90) * 52);
            /**
            moves forward into the rocket
            Actual: 119.0695697
             */
            this.addSequential(new DrivePosition(119));
            /**
            same thing but for right side
             */
        }else {
            this.addSequential(new AutoDrive(-1, 1), angleWait);
            this.addSequential(new DrivePosition(13));
            this.addSequential(new AutoDrive(1, -1), (angleWait/90) * 52);
            this.addSequential(new DrivePosition(119));

        }

    }
}