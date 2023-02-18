// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.robot.commands.*;
import frc.robot.commands.LowerCmd;
import frc.robot.commands.OneBar.*;
import frc.robot.commands.RaiseCmd;
import frc.robot.commands.RotLeftCmd;
import frc.robot.commands.RotRightCmd;
import frc.robot.commands.StopRaiseCmd;
import frc.robot.commands.StopRotCmd;
import frc.robot.subsystems.*;
import frc.robot.subsystems.WristSubsystem;
import java.util.function.BooleanSupplier;
import frc.robot.commands.ClampCmd;
import frc.robot.commands.ReleaseCmd;
import frc.robot.commands.ShiftLeftCmd;
import frc.robot.commands.ShiftRightCmd;
import frc.robot.commands.StopClawCmd;
import frc.robot.subsystems.ClawSubsystem;

public class RobotContainer {
  // Defining Controllers
  private final Joystick driver1 = new Joystick(Controllers.DRIVER_ONE_PORT);
  private final Joystick driver2 = new Joystick(Controllers.DRIVER_SEC_PORT);
  private final Joystick operator = new Joystick(Controllers.OPER_PORT);

  // Defining Subsystems
  private final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
  private final ControllerSubsystem controls = new ControllerSubsystem();
  private final OnebarSubsystem onebar = new OnebarSubsystem();

  // Defining Commands
  private final AutoCmd m_autoCommand = new AutoCmd();
  private final TeleopCmd teleopCmd = new TeleopCmd(drivetrain);
  private final ShiftdownCmd shiftdown = new ShiftdownCmd(drivetrain);
  private final ShiftupCmd shiftup = new ShiftupCmd(drivetrain);
  private final OnebarDown armDown = new OnebarDown(onebar);
  private final OnebarUp armUp = new OnebarUp(onebar);
  private final OnebarOut armOut = new OnebarOut(onebar);
  private final OnebarIn armIn = new OnebarIn(onebar);
  private final ArmExtStop armExtStop = new ArmExtStop(onebar);
  private final ArmRotStop armRotStop = new ArmRotStop(onebar);
  private final WristSubsystem wristSubsystem = new WristSubsystem();
  private final LowerCmd lowerCmd = new LowerCmd(wristSubsystem);
  private final RaiseCmd raiseCmd = new RaiseCmd(wristSubsystem);
  private final RotLeftCmd rotLeftCmd = new RotLeftCmd(wristSubsystem);
  private final RotRightCmd rotRightCmd = new RotRightCmd(wristSubsystem);
  private final StopRaiseCmd stopRaiseCmd = new StopRaiseCmd(wristSubsystem);
  private final StopRotCmd stopRotCmd = new StopRotCmd(wristSubsystem);
  private final ClawSubsystem clawSubsystem = new ClawSubsystem();
  private final ClampCmd clampCmd = new ClampCmd(clawSubsystem);
  private final ReleaseCmd releaseCmd = new ReleaseCmd(clawSubsystem);
  private final ShiftLeftCmd shiftLeftCmd = new ShiftLeftCmd(clawSubsystem);
  private final ShiftRightCmd shiftRightCmd = new ShiftRightCmd(clawSubsystem);
  private final StopClawCmd stopClawCmd = new StopClawCmd(clawSubsystem);

  public Trigger supplier(int buttonID) {
    BooleanSupplier bsup = () -> driver1.getRawButton(buttonID);
    Trigger mybutton = new Trigger(bsup);
    return mybutton;
  }
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    // Configure the button bindings
    configureButtonBindings();
    drivetrain.setDefaultCommand(teleopCmd);
    controls.setup();
    onebar.setup();
  }

  private void configureButtonBindings() {

    Trigger lowerbutton = supplier(1);
    lowerbutton.onTrue(raiseCmd);
    lowerbutton.onFalse(stopRaiseCmd);

    Trigger raisebutton = supplier(2);
    raisebutton.onTrue(raiseCmd);
    raisebutton.onFalse(stopRaiseCmd);

    Trigger rotleftbutton = supplier(3);
    rotleftbutton.onTrue(rotLeftCmd);
    rotleftbutton.onFalse(stopRotCmd);

    Trigger rotrightbutton = supplier(4);
    rotrightbutton.onTrue(rotRightCmd);
    rotrightbutton.onFalse(stopRotCmd);

    // // Arm Extension In
    // supplier(5).onTrue(armIn).onFalse(armExtStop);
    // // Arm Extension Out
    // supplier(3).onTrue(armOut).onFalse(armExtStop);
    // // Arm Rotation Up
    // supplier(6).onTrue(armUp).onFalse(armRotStop);
    // // Arm Rotation Down
    // supplier(4).onTrue(armDown).onFalse(armRotStop);

    // Shift Up
    supplier(Controllers.xbox_rbutton).onTrue(shiftup);
    // Shift Down
    supplier(Controllers.xbox_lbutton).onTrue(shiftdown);

    Trigger clampbutton = supplier(5);
    clampbutton.onTrue(clampCmd);
    clampbutton.onFalse(stopClawCmd);
    
    Trigger releasebutton = supplier(6);
    releasebutton.onTrue(releaseCmd);
    releasebutton.onFalse(stopClawCmd);

    Trigger shiftleftbutton = supplier(7);
    shiftleftbutton.onTrue(shiftLeftCmd);
    shiftleftbutton.onFalse(stopClawCmd);

    Trigger shiftrightbutton = supplier(8);
    shiftrightbutton.onTrue(shiftRightCmd);
    shiftrightbutton.onFalse(stopClawCmd);
  }

  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
