package frc.robot;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
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

        m_leftBackMotor.setIdleMode(IdleMode.kCoast);
        m_rightBackMotor.setIdleMode(IdleMode.kCoast);
        m_leftFrontMotor.setIdleMode(IdleMode.kCoast);
        m_rightFrontMotor.setIdleMode(IdleMode.kCoast);

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
        SmartDashboard.putNumber("Right drive speed", rightSpeed);
        SmartDashboard.putNumber("Left drive speed", leftSpeed);
        m_drive.tankDrive(leftSpeed, rightSpeed);
    }

    public Command driveCommand(double forwardSpeed, double rotationSpeed) {
        return Commands.startEnd(
            () -> {
                m_drive.arcadeDrive(forwardSpeed, rotationSpeed);
            },
            () -> {
                m_drive.arcadeDrive(0, 0);
            }
        );
    }
}
