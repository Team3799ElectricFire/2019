/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class ThePlunger extends Subsystem {

  private TalonSRX m_plunger;
  private AnalogPotentiometer m_pot;

  public final double upPosition = 14.7;
  public final double downPosition = 60;

  public ThePlunger() {
    m_plunger = new TalonSRX(RobotMap.PLUNGER_MOTOR);
    m_plunger.configFactoryDefault();
    
    m_plunger.setInverted(true);

    m_pot = new AnalogPotentiometer(RobotMap.ANALOG_POTENTIOMETER, 100);
  }

  @Override
  public void initDefaultCommand() {
    // None
  }

  public void setMotorsPlunger(double speed) {
    try {
      m_plunger.set(ControlMode.PercentOutput, speed);
    } catch (Exception e) {
      DriverStation.reportError("Exception in setMotorsPlunger, speed was " + speed + ", message was " + e.toString(),
          true);
    }
  }

  public double getPot() {
    return m_pot.get();
  }
}
