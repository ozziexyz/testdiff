// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.Constants.OI;

public class Robot extends TimedRobot {
  private Drive m_drive = new Drive();
  private Pivot m_pivot = new Pivot();
  private Intake m_intake = new Intake();
  private Shooter m_shooter = new Shooter();

  private Command m_auto = new RunCommand(
    () -> m_drive.tankDrive(-OI.driveInputMax, -OI.driveInputMax)
  ).withTimeout(2);
  
  public void configureButtonBindings() {
    SmartDashboard.putBoolean("Button bindings", true);

    OI.opController.leftTrigger(0.3).whileTrue(m_pivot.rotateCommand(Constants.pivotSpeed));
    OI.opController.rightTrigger(0.3).whileTrue(m_pivot.rotateCommand(-Constants.pivotSpeed));


    OI.opController.leftBumper().whileTrue(m_shooter.runCommand(Constants.shooterSpeed));

    OI.opController.rightBumper().whileTrue(m_intake.runCommand(Constants.intakeSpeed));

    OI.opController.a().whileTrue(m_intake.runCommand(-Constants.intakeSpeed));
  }

  @Override
  public void robotInit() {
    configureButtonBindings();
  }

  @Override
  public void teleopPeriodic() {
    double leftY = MathUtil.clamp(OI.driveController.getLeftY(), OI.driveInputMin, OI.driveInputMax);
    double rightY = MathUtil.clamp(OI.driveController.getRightY(), OI.driveInputMin, OI.driveInputMax);
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
