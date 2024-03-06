package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.OI;

public class Autos {
    private Drive drive;
    private Shooter shooter;
    private Intake intake;
    private Pivot pivot;

    public Autos(Drive drive, Shooter shooter, Pivot pivot, Intake intake) {
        this.drive = drive;
        this.shooter = shooter;
        this.pivot = pivot;
        this.intake = intake;
    }

    public Command twoNote() {
        return new SequentialCommandGroup(
            shooter.runCommand(Constants.shooterSpeed).withTimeout(1),
            drive.driveCommand(1, 0).withTimeout(2),
            pivot.rotateCommand(-Constants.pivotSpeed).withTimeout(1),
            intake.runCommand(Constants.intakeSpeed).withTimeout(1),
            drive.driveCommand(-1, 0).withTimeout(2),
            pivot.rotateCommand(Constants.pivotSpeed).withTimeout(1),
            shooter.runCommand(Constants.shooterSpeed)
        );
    }

    public Command oneNote() {
        return Commands.sequence(
            Commands.race(
                shooter.runCommand(1),
                Commands.sequence(
                    drive.driveCommand(.5, 0).withTimeout(0.35),
                    Commands.waitSeconds(1),
                    intake.runCommand(1).withTimeout(3)
                )
            ),
            drive.driveCommand(.5, 0).withTimeout(0.5)
        );
    }

    public Command oneNoteMiddle() {
        return new SequentialCommandGroup(
            drive.driveCommand(OI.defaultDriveInputLimit, 0).withTimeout(1),
            shooter.setCommand(1).withTimeout(5),
            intake.runCommand(1).withTimeout(5),
            shooter.setCommand(0)
        );
    }

    public Command oneNoteLeft() {
        return new SequentialCommandGroup(
            drive.driveCommand(-OI.defaultDriveInputLimit, 0).withTimeout(0.2),
            drive.driveCommand(0, 1).withTimeout(0.2),
            shooter.setCommand(1),
            intake.runCommand(1).withTimeout(1),
            shooter.setCommand(0)
        );
    }
    
    public Command oneNoteRight() {
        return new SequentialCommandGroup(
            drive.driveCommand(-OI.defaultDriveInputLimit, 0).withTimeout(0.2),
            drive.driveCommand(0,1).withTimeout(0.2),
            shooter.setCommand(1),
            intake.runCommand(1).withTimeout(1),
            shooter.setCommand(0)
        );
    }

    public Command taxi() {
        return new SequentialCommandGroup(
            drive.driveCommand(-OI.defaultDriveInputLimit, 0).withTimeout(.75),
            drive.driveCommand(0, 0)
        );
    }

    public Command justShoot() {
        return new SequentialCommandGroup(
            shooter.runCommand(Constants.shooterSpeed).withTimeout(2),
            intake.runCommand(1).withTimeout(1),
            shooter.runCommand(0).withTimeout(1),
            intake.runCommand(0).withTimeout(1)
        );
    }
}                                                                                                                                                                                                                                                                                                                                                                                                                      
