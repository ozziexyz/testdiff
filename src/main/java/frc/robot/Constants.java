package frc.robot;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class Constants {
    public static final double driveInputMax = 0.5;
    public static final double driveInputMin = -0.5;
    public static final double shooterSpeed = 1;
    public static final double intakeSpeed = 1;
    public static final double pivotSpeed = 0.5;
    public static final double rumble = 0.2;

    public class OI {
        public static CommandXboxController controller = new CommandXboxController(0);
    }
}
