/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.RobotDriveBase;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  
  Jaguar leftJaguar;
  Jaguar rightJaguar;
  Jaguar shooter;
  Joystick j;
  DoubleSolenoid launcher;
  
  SpeedControllerGroup leftSide;
  SpeedControllerGroup rightSide;

  DifferentialDrive robot;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    leftJaguar = new Jaguar(1);
    rightJaguar = new Jaguar(2);
    shooter = new Jaguar(0);
    j = new Joystick(1);
    launcher = new DoubleSolenoid(1,2);

    leftSide = new SpeedControllerGroup(leftSide);
    rightSide = new SpeedControllerGroup(rightSide);

    robot = new DifferentialDrive(leftSide, rightSide);
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    if(Math.abs(j.getRawAxis(0)) > 0.15 || Math.abs(j.getRawAxis(1)) > 0.15)
      robot.arcadeDrive(j.getRawAxis(1), j.getRawAxis(0));
    else
      robot.arcadeDrive(0, 0);

    if(j.getRawAxis(2) > 0.15)
      shooter.set(0.8);
    else
      shooter.set(0);

    if(j.getRawButton(1))
      launcher.set(DoubleSolenoid.Value.kForward);
    else
      launcher.set(DoubleSolenoid.Value.kReverse);
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
