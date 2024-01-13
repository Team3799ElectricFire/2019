/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class BallIntake extends Subsystem {
// "BallIntake extends".... That's hot.- From Tim
  private TalonSRX m_ball_intake;
  private Solenoid m_intake;
  

  public BallIntake() {
    m_ball_intake = new TalonSRX(RobotMap.INTAKE_MOTOR);
    m_ball_intake.configFactoryDefault();

    m_intake = new Solenoid(RobotMap.INTAKE_SOLENOID);
    
  }

  @Override
  public void initDefaultCommand() {
    // None
  }

  public void setMotorsIntake(double intakeSpeed) {
    m_ball_intake.set(ControlMode.PercentOutput, intakeSpeed);
  }

  public boolean getLeftPosition() {
    return m_intake.get();
  }



  public void setIntakePosition(boolean position) {
    m_intake.set(position);

  }

}