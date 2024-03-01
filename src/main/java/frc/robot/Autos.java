package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
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

    public Command oneNoteMiddle() {
        return new SequentialCommandGroup(
            drive.driveCommand(-OI.defaultDriveInputLimit, 0).withTimeout(0.2),
            shooter.setCommand(1),
            intake.runCommand(1).withTimeout(1),
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
            drive.driveCommand(0, 1).withTimeout(0.2),
            shooter.setCommand(1),
            intake.runCommand(1).withTimeout(1),
            shooter.setCommand(0)
        );
    }

    public Command taxi() {
        return drive.driveCommand(-OI.defaultDriveInputLimit, 0).withTimeout(1);
    }
}
