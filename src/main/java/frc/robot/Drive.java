package frc.robot;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {
    private final CANSparkMax m_leftFrontMotor = new CANSparkMax(1, MotorType.kBrushless);
    private final CANSparkMax m_rightFrontMotor = new CANSparkMax(2, MotorType.kBrushless);
    private final CANSparkMax m_leftBackMotor = new CANSparkMax(3, MotorType.kBrushless);
    private final CANSparkMax m_rightBackMotor = new CANSparkMax(4, MotorType.kBrushless);
    private DifferentialDrive m_drive;

    public Drive(){
        m_leftBackMotor.restoreFactoryDefaults();
        m_rightBackMotor.restoreFactoryDefaults();
        m_leftFrontMotor.restoreFactoryDefaults();
        m_rightFrontMotor.restoreFactoryDefaults();

        m_leftFrontMotor.follow(m_leftBackMotor);
        m_rightFrontMotor.follow(m_rightBackMotor);
        
        m_rightFrontMotor.setInverted(true);
        m_rightBackMotor.setInverted(true);

        m_leftBackMotor.burnFlash();
        m_leftFrontMotor.burnFlash();
        m_rightBackMotor.burnFlash();
        m_rightFrontMotor.burnFlash();


        m_drive = new DifferentialDrive(m_leftBackMotor::set, m_rightBackMotor::set);
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        m_drive.tankDrive(leftSpeed, rightSpeed);
    }
}
