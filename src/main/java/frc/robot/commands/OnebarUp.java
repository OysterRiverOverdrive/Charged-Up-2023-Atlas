// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.OnebarSubsystem;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import frc.robot.Constants;

public class OnebarUp extends CommandBase {
  private final OnebarSubsystem onebarsubsystem;

  /** Creates a new OneBarUp. */
  public OnebarUp(OnebarSubsystem subsystem) {
    onebarsubsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    onebarsubsystem.InverseMotor();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    onebarsubsystem.armUp();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}