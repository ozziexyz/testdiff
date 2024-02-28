package frc.robot;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    private final CANSparkMax m_motor = new CANSparkMax(8, MotorType.kBrushless);
    // private final DigitalInput m_sensor = new DigitalInput(0);

    public Intake() {
        m_motor.setSmartCurrentLimit(Constants.intakeMaxCurrent);
        m_motor.setIdleMode(IdleMode.kBrake);
        m_motor.burnFlash();
    }

    private void set(double speed) {
        m_motor.set(speed);
        SmartDashboard.putNumber("Intake speed", speed);
    }

    public Command runCommand(double speed) {
        return Commands.startEnd(
            () -> {
                SmartDashboard.putBoolean("Intake command", true);
                // if(isStoring()){
                //     OI.opController.getHID().setRumble(OI.rumbleType, OI.rumble);
                // }
                // else {
                //     OI.opController.getHID().setRumble(OI.rumbleType, 0);
                // } 
                // System.out.println("running intake");
                set(speed);
            },
            () -> {
                // OI.opController.getHID().setRumble(OI.rumbleType, 0);
                set(0);
            }
        );
    }

    // public boolean isStoring() {
    //     return m_sensor.get();
    // }
}
