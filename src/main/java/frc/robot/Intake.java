package frc.robot;

import com.fasterxml.jackson.databind.jsontype.impl.SubTypeValidator;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase{
    private final CANSparkMax m_motor = new CANSparkMax(8, MotorType.kBrushless);
    
    public Intake() {
        m_motor.setSmartCurrentLimit(40);
        m_motor.burnFlash();
    }

    public void run(double speed) {
        m_motor.set(speed);
    }

    public Command runHalf() {
        return new InstantCommand(() -> {
            run(0.5);
        });
    }
}
