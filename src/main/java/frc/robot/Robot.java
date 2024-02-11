// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private DifferentialDrive m_robotDrive;
  private CommandXboxController m_controller = new CommandXboxController(0);

  private final CANSparkMax m_leftFrontMotor = new CANSparkMax(1, MotorType.kBrushless);
  private final CANSparkMax m_rightFrontMotor = new CANSparkMax(2, MotorType.kBrushless);
  private final CANSparkMax m_leftBackMotor = new CANSparkMax(3, MotorType.kBrushless);
  private final CANSparkMax m_rightBackMotor = new CANSparkMax(4, MotorType.kBrushless);

  @Override
  public void robotInit() {
    m_leftBackMotor.follow(m_leftFrontMotor);
    m_rightFrontMotor.follow(m_rightFrontMotor);

    SendableRegistry.addChild(m_robotDrive, m_leftFrontMotor);
    SendableRegistry.addChild(m_robotDrive, m_rightFrontMotor);

    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_rightFrontMotor.setInverted(true);
    m_rightBackMotor.setInverted(true);

    m_robotDrive = new DifferentialDrive(m_leftFrontMotor::set, m_rightFrontMotor::set);
  }

  @Override
  public void teleopPeriodic() {
    m_robotDrive.tankDrive(-m_controller.getLeftY(), -m_controller.getRightY());
  }
}
