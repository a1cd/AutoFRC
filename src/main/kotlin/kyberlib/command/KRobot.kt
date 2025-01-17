package kyberlib.command

import edu.wpi.first.hal.FRCNetComm
import edu.wpi.first.hal.HAL
import edu.wpi.first.wpilibj.RobotBase
import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.livewindow.LiveWindow
import edu.wpi.first.wpilibj2.command.CommandScheduler

open class KRobot(private val period: Double = 0.02) {

    var enabled = false
        private set

    private inner class KRobotInternal : TimedRobot(period) {

        override fun robotInit() {
            HAL.report(FRCNetComm.tResourceType.kResourceType_Language, FRCNetComm.tInstances.kLanguage_Kotlin)
            LiveWindow.disableAllTelemetry()
            this@KRobot.robotInit()
        }

        override fun robotPeriodic() {
            CommandScheduler.getInstance().run()
            this@KRobot.robotPeriodic()
        }

        override fun disabledInit() {
            enabled = false
            this@KRobot.disabledInit()
        }

        override fun disabledPeriodic() {
            this@KRobot.disabledPeriodic()
        }

        override fun autonomousInit() {
            if (!enabled) enabledInit()
            enabled = true
            this@KRobot.autonomousInit()
        }

        override fun autonomousPeriodic() {
            this@KRobot.autonomousPeriodic()
        }

        override fun teleopInit() {
            if (!enabled) enabledInit()
            enabled = true
            this@KRobot.teleopInit()
        }

        override fun teleopPeriodic() {
            this@KRobot.teleopPeriodic()
        }

        override fun testInit() {
//            LiveWindow.setEnabled(false)
        }
    }

    /**
     * The value being wrapped by KRobot
     */
    private val internalRobot = KRobotInternal()

    /**
     * Ran once when the robot starts up
     */
    open fun robotInit() {}

    /**
     * Ran continuously at all times.
     * CommandScheduler is being ran automatically, so no need to call it here.
     */
    open fun robotPeriodic() {}

    /**
     * Ran once when the robot is disabled
     */
    open fun disabledInit() {}

    /**
     * Ran continuously when the robot is disabled
     */
    open fun disabledPeriodic() {}

    /**
     * Ran once when robot enters auto or teleop, but not both
     */
    open fun enabledInit() {}

    /**
     * Ran once when the robot enters autonomous mode
     */
    open fun autonomousInit() {}

    /**
     * Ran continuously when the robot is in autonomous mode
     */
    open fun autonomousPeriodic() {}

    /**
     * Ran once when the robot enters teleoperated mode
     */
    open fun teleopInit() {}

    /**
     * Ran continuously when the robot is in teleoperated mode
     */
    open fun teleopPeriodic() {}

    /**
     * Runs the underlying WPILib startup methods. Run this in Main.kt
     */
    fun initialize() {
        RobotBase.startRobot { internalRobot }
    }
}
