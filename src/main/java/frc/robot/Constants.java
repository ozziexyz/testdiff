package frc.robot;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class Constants {
    // Speeds

    public static final double shooterSpeed = 1;
    public static final double intakeSpeed = .3;
    public static final double pivotSpeed = 0.5;
    public static final double shooterReverseSpeed = -0.5 ;

    // Current limits
    public static final int intakeMaxCurrent = 60;
    public static final int shooterMaxCurrent = 40;
    public static final int pivotMaxCurrent = 40;


    // Controller constants
    public class OI {
        public static final double rumble = 0.2;
        public static final double defaultDriveInputLimit = 0.75;
        public static final double slowModeInputLimit = .35;
        public static final CommandXboxController opController = new CommandXboxController(0);
        public static final CommandXboxController driveController = new CommandXboxController(1);
    }
}
