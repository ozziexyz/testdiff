// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.Constants.OI;

public class Robot extends TimedRobot {
  private Drive m_drive = new Drive();
  private Pivot m_pivot = new Pivot();
  private Intake m_intake = new Intake();
  private Shooter m_shooter = new Shooter();

  private Command m_auto = new RunCommand(
    () -> m_drive.tankDrive(-Constants.driveInputMax, -Constants.driveInputMax)
  ).withTimeout(2);
  
  public void configureButtonBindings() {
    SmartDashboard.putBoolean("Button bindings", true);

    OI.controller.leftTrigger(0.3).onTrue(m_pivot.rotateCommand(Constants.pivotSpeed));
    OI.controller.leftTrigger(0.3).onFalse(m_pivot.rotateCommand(0));
    OI.controller.rightTrigger(0.3).onTrue(m_pivot.rotateCommand(-Constants.pivotSpeed));
    OI.controller.rightTrigger(0.3).onFalse(m_pivot.rotateCommand(0));

    OI.controller.leftBumper().onTrue(m_shooter.runCommand(Constants.shooterSpeed));
    OI.controller.leftBumper().onFalse(m_shooter.runCommand(0));
    OI.controller.rightBumper().onTrue(m_intake.runCommand(Constants.intakeSpeed));
    OI.controller.rightBumper().onFalse(m_intake.runCommand(0));

    OI.controller.a().onTrue(m_intake.runCommand(-Constants.intakeSpeed));
    OI.controller.a().onFalse(m_intake.runCommand(0));
  }

  @Override
  public void robotInit() {
    configureButtonBindings();
  }

  @Override
  public void teleopPeriodic() {
    double leftY = MathUtil.clamp(OI.controller.getLeftY(), Constants.driveInputMin, Constants.driveInputMax);
    double rightY = MathUtil.clamp(OI.controller.getRightY(), Constants.driveInputMin, Constants.driveInputMax);
    m_drive.tankDrive(leftY, rightY);
  }

  @Override
  public void autonomousInit() {
    m_auto.schedule();
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }
}
