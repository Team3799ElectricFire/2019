/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class FollowVisionTarget extends Command {

  private double target;
  private double sensor;
  private double output;
  private double pValue = -0.05;
  private double offset = 0.5;
  private boolean targetSeen = true;


  public FollowVisionTarget() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_tankdrive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Robot.m_table.getEntry("tapeDetected").getBoolean(false)) {
      //tape seen, turn toward tape
      target = -.5;
      sensor = Robot.m_table.getEntry("tapeYaw").getDouble(0.0);
      targetSeen = true;
    } else {
      //i don't see anything so i'm driving foward
      if (targetSeen) {
        Robot.m_tankdrive.zeroYaw();
      }
      
      target = 0;
      sensor = Robot.m_tankdrive.getYaw();
      targetSeen = false;
    }

    output = (sensor-target)*pValue;

    Robot.m_tankdrive.setMotors(-offset-output, -offset+output);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    //don't need to stope motors, because 
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}