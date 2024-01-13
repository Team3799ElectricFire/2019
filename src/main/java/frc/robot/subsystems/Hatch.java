/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Hatch extends Subsystem {
  private Solenoid m_hatch;

  public Hatch() {
    m_hatch= new Solenoid(RobotMap.HATCH_MANIPULATOR_SOLENOID);
  }

  @Override
  public void initDefaultCommand() {
    // none
  }

  public boolean getPosition() {
    return m_hatch.get();
  }

  public void setPosition(boolean hatchPosition) {
    m_hatch.set(hatchPosition);
  }
}
