package frc.robot;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase{
    private final CANSparkMax m_motor = new CANSparkMax(8, MotorType.kBrushless);
    private final double m_motorSpeed = 0.5;

    public Intake() {
        m_motor.setSmartCurrentLimit(60);
        m_motor.burnFlash();
    }

    public void run(double speed) {
        SmartDashboard.putBoolean("intake running", true);
        m_motor.set(speed);
    }

    public Command runCommand(double speed) {
        return new InstantCommand(() -> {
            SmartDashboard.putBoolean("intake command", true);
            run(speed);
        });
    }
}
