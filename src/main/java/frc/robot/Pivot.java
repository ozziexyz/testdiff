package frc.robot;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pivot extends SubsystemBase {
    private final CANSparkMax m_motor = new CANSparkMax(5, MotorType.kBrushless);

    public Pivot() {
        m_motor.setSmartCurrentLimit(Constants.pivotMaxCurrent);
        m_motor.setIdleMode(IdleMode.kBrake);
        m_motor.burnFlash();
    }

    private void rotate(double speed) {
        SmartDashboard.putNumber("Pivot speed", speed);
        m_motor.set(speed);
    }

    public Command rotateCommand(double speed) {
        SmartDashboard.putBoolean("Pivot command", true);
        return Commands.startEnd(
            () -> rotate(speed),
            () -> rotate(0)
        );
    }
}
