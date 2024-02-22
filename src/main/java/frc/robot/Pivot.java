package frc.robot;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pivot extends SubsystemBase {
    private final CANSparkMax m_motor = new CANSparkMax(5, MotorType.kBrushless);

    public Pivot() {
        m_motor.setSmartCurrentLimit(40);
        m_motor.setIdleMode(IdleMode.kBrake);
        m_motor.burnFlash();
    }

    private void rotate(double speed) {
        SmartDashboard.putBoolean("pivot rotating", true);
        m_motor.set(speed);
    }

    public Command rotateCommand(double speed) {
        return new InstantCommand(() -> {
            SmartDashboard.putBoolean("pivot command", true);
            rotate(speed);
        });
    }
}
