/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.RobotMap;


/**
 * Add your docs here.
 */
public class Turbo extends Subsystem {
  
  private DoubleSolenoid s_gearshift;

  @Override
  public void initDefaultCommand() {
    
  }
  
  public Turbo(){
    s_gearshift = new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.TURBO_FORWARD, RobotMap.TURBO_REVERSE);
  }

  public void Turbo_Off(){
    s_gearshift.set(Value.kOff);
  }
  public void setForward(){
    s_gearshift.set(Value.kForward);
  }
  public void setReverse(){
    s_gearshift.set(Value.kReverse);
  }
  public void Toggle(){
    if (s_gearshift.get() != Value.kForward){
      s_gearshift.set(Value.kForward);
    }
    else {
      s_gearshift.set(Value.kReverse);
    }

  }
}
