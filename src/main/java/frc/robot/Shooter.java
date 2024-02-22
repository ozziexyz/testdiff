package frc.robot;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    private final CANSparkMax m_leftMotor = new CANSparkMax(7, MotorType.kBrushless);
    private final CANSparkMax m_rightMotor = new CANSparkMax(6, MotorType.kBrushless);

    public Shooter() {
        m_leftMotor.setSmartCurrentLimit(40);
        m_rightMotor.setSmartCurrentLimit(40);
        m_leftMotor.setInverted(true);
        m_leftMotor.burnFlash();
        m_rightMotor.burnFlash();
    }

    private void run(double speed) {
        SmartDashboard.putBoolean("shooter running", true);
        m_leftMotor.set(speed);
        m_rightMotor.set(speed);
    }

    public Command runCommand(double speed) {
        SmartDashboard.putBoolean("shooter command", true);
        return new InstantCommand(() -> {
            run(speed);
        });
    }
}
