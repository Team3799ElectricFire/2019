/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IntakeToggle extends Command {
  /* Note for John
   * This command wasn't working b/c there was a file with the same name 
   * under frc.robot, so the whenPressed function for the START button
   * in the OI.java file was defaulting to that (which was an empty command
   * which did nothing!) You can check that by mousing over the "IntakeToggle()"
   * in the whenPressed function in OI.java and seeing what that name is pointing to
   * 
   * -Dave
   * Oh wow. Oh boy. I appologizebut man oh man that's pretty funny.
   * 
   * -John
   */
  double servoDown = 0.0;
  double servoUp = 0.8;

  public IntakeToggle() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_ball_intake);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("Initial Value: " + Robot.m_ball_intake.getLeftPosition());

    if (Robot.m_ball_intake.getLeftPosition() != false) {
      Robot.m_ball_intake.setIntakePosition(false);
    } else {
      Robot.m_ball_intake.setIntakePosition(true);
    }

    System.out.println("Final Value: " + Robot.m_ball_intake.getLeftPosition());
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
