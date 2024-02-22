// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class Robot extends TimedRobot {
  private CommandXboxController m_controller = new CommandXboxController(0);

  private Drive m_drive = new Drive();
  private Pivot m_pivot = new Pivot();
  private Intake m_intake = new Intake();
  private Shooter m_shooter = new Shooter();
  
  private final double driveInputMin = -0.5;
  private final double driveInputMax = 0.5;

  public void configureButtonBindings() {
    SmartDashboard.putBoolean("Button bindings", true);
    m_controller.leftBumper().whileTrue(m_pivot.rotateBackward());
    m_controller.rightBumper().whileTrue(m_pivot.rotateForward());
    m_controller.leftTrigger().onTrue(m_shooter.runCommand(0.5));
    m_controller.a().onTrue(m_shooter.runCommand(0));
    m_controller.rightTrigger().onTrue(m_intake.runCommand(0.9));
    m_controller.b().onTrue(m_intake.runCommand(0));
  }

  @Override
  public void robotInit() {
    configureButtonBindings();
  }

  @Override
  public void teleopPeriodic() {
    double leftY = MathUtil.clamp(m_controller.getLeftY(), driveInputMin, driveInputMax);
    double rightY = MathUtil.clamp(m_controller.getRightY(), driveInputMin, driveInputMax);
    m_drive.tankDrive(leftY, rightY);
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run(); // start command scheduler
  }
}
