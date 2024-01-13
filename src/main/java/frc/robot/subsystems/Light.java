/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/*
 * Add your docs here.
 */
public class Light extends Subsystem {
  private Relay m_light;

  @Override
  public void initDefaultCommand() {
    // none
  }

  public Light() {
    m_light = new Relay(RobotMap.Relay);
  }

  public void LightOn() {
    m_light.set(Value.kReverse);
  }

  public void LightOff(){
    m_light.set(Value.kOff);
  }
}