package frc.robot;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    private final CANSparkMax m_motor = new CANSparkMax(8, MotorType.kBrushless);
    private final DigitalInput m_sensor = new DigitalInput(0);

    public Intake() {
        m_motor.setSmartCurrentLimit(60);
        m_motor.setIdleMode(IdleMode.kBrake);
        m_motor.burnFlash();
    }

    private void run(double speed) {
        SmartDashboard.putBoolean("intake running", true);
        m_motor.set(speed);
    }

    public Command runCommand(double speed) {
        return new InstantCommand(() -> {
            SmartDashboard.putBoolean("intake command", true);
            // if (m_sensor.get() && OI.controller.rightTrigger().getAsBoolean()) {
            //     OI.controller.getHID().setRumble(RumbleType.kBothRumble, Constants.rumble);
            // }
            run(speed);
        });
    }

    public boolean isStowing() {
        return m_sensor.get();
    }
}
