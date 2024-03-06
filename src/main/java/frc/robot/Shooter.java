package frc.robot;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    private final CANSparkMax m_leftMotor = new CANSparkMax(7, MotorType.kBrushless);
    private final CANSparkMax m_rightMotor = new CANSparkMax(6, MotorType.kBrushless);

    public Shooter() {
        m_leftMotor.setSmartCurrentLimit(Constants.shooterMaxCurrent);
        m_rightMotor.setSmartCurrentLimit(Constants.shooterMaxCurrent);
        m_leftMotor.setIdleMode(IdleMode.kBrake);
        m_rightMotor.setIdleMode(IdleMode.kBrake);
        m_leftMotor.setInverted(true);
        m_leftMotor.burnFlash();
        m_rightMotor.burnFlash();
    }

    private void set(double speed) {
        SmartDashboard.putNumber("Shooter speed", speed);
        m_leftMotor.set(speed);
        m_rightMotor.set(speed);
    }

    public Command runCommand(double speed) {
        SmartDashboard.putBoolean("Shooter command", true);
        return Commands.startEnd(
            () -> {
                set(speed);
            },
            () -> {
                set(0);
            }
        );
    }

    public Command setCommand(double speed) {
        return Commands.run(
            () -> set(speed)
        );
    }
}
