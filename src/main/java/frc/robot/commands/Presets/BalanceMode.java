// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Presets;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.OneBar.PID2;
import frc.robot.commands.Wrist.AutoAlignCmd;
import frc.robot.subsystems.OnebarSubsystem;
import frc.robot.subsystems.WristSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class BalanceMode extends SequentialCommandGroup {
  /** Creates a new ArmStraightUp. */
  public BalanceMode(OnebarSubsystem onebar, WristSubsystem wrists) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new PID2(onebar, 0), new AutoAlignCmd(wrists, 0));
  }
}
