package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class Constants {
    // Current
    public static final double shooterSpeed = 1;
    public static final double intakeSpeed = 1;
    public static final double pivotSpeed = 0.5;

    // Current limits
    public static final int intakeMaxCurrent = 60;
    public static final int shooterMaxCurrent = 40;
    public static final int pivotMaxCurrent = 40;

    // Controller constants
    public class OI {
        public static final double rumble = 0.2;
        public static final RumbleType rumbleType = RumbleType.kBothRumble;
        public static final double driveInputMax = .75;
        public static final double driveInputMin = -.75;
        public static final CommandXboxController opController = new CommandXboxController(0);
        public static final CommandXboxController driveController = new CommandXboxController(1);
    }
}
