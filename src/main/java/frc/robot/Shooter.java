package frc.robot;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    private final CANSparkMax m_leftMotor = new CANSparkMax(7, MotorType.kBrushless);
    private final CANSparkMax m_rightMotor = new CANSparkMax(6, MotorType.kBrushless);


    public Shooter() {
        m_rightMotor.follow(m_leftMotor);
        m_leftMotor.setSmartCurrentLimit(40);
        m_rightMotor.setSmartCurrentLimit(40);
        m_leftMotor.burnFlash();
        m_rightMotor.burnFlash();
    }

    public void run(double speed) {
        m_leftMotor.set(speed);
    }

    public Command runHalf() {
        return new InstantCommand(() -> {
            run(0.5);
        });
    }
}
