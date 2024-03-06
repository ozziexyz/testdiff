// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Constants.OI;

public class Robot extends TimedRobot {
  private Drive m_drive = new Drive();
  private Pivot m_pivot = new Pivot();
  private Intake m_intake = new Intake();
  private Shooter m_shooter = new Shooter();
  private double driveInputLimit = OI.defaultDriveInputLimit;
  private Autos m_autos = new Autos(m_drive, m_shooter, m_pivot, m_intake);

  public void configureButtonBindings() {
    SmartDashboard.putBoolean("Button bindings", true);

    OI.opController.leftTrigger(0.3).whileTrue(m_pivot.rotateCommand(Constants.pivotSpeed));
    // OI.opController.rightTrigger(0.3).whileTrue(m_pivot.rotateCommand(-Constants.pivotSpeed));

    OI.opController.rightBumper().whileTrue(m_intake.runCommand(Constants.intakeSpeed));
    OI.opController.a().whileTrue(m_intake.runCommand(-Constants.intakeSpeed));

    //Intake

    OI.opController.povDown().whileTrue(m_shooter.runCommand(Constants.shooterReverseSpeed));

    //Shoot 
    OI.opController.leftBumper().whileTrue(m_shooter.runCommand(Constants.shooterSpeed));

    OI.driveController.leftBumper().whileTrue(Commands.startEnd(
      () -> {
        driveInputLimit = OI.slowModeInputLimit;
      },
      () -> {
        driveInputLimit = OI.defaultDriveInputLimit;
      }
    ));
  }

  @Override
    public void robotInit() {
    configureButtonBindings();
  }

  @Override
  public void teleopPeriodic() {
    double leftY = MathUtil.clamp(OI.driveController.getLeftY(), -driveInputLimit, driveInputLimit);
    double rightY = MathUtil.clamp(OI.driveController.getRightY(), -driveInputLimit, driveInputLimit);
    m_drive.tankDrive(leftY, rightY);
    // doueble speed = OI.driveController.getLeftY();
    // double rotation = OI.driveController.getRightX();
    // m_drive.arcadeDrive(speed, rotation);
  }

  @Override
  public void autonomousInit() {
    m_autos.oneNote().schedule();
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }
}
