package frc.robot;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pivot extends SubsystemBase{
    private final CANSparkMax m_motor = new CANSparkMax(5, MotorType.kBrushless);
    private final double forwardSpeed = 0.5;
    private final double backwardSpeed = -0.5;

    public Pivot() {
        m_motor.setSmartCurrentLimit(40);
        m_motor.burnFlash();
    }

    public void rotate(double speed) {
       m_motor.set(speed);
    }

    public Command rotateForward() {
        return new InstantCommand(() -> {
            rotate(forwardSpeed);
        });
    }

    public Command rotateBackward() {
        return new InstantCommand(() -> {
            rotate(backwardSpeed);
        });
    }
}
