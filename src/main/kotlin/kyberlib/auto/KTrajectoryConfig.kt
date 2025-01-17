package kyberlib.auto

import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig
import edu.wpi.first.wpilibj.trajectory.constraint.TrajectoryConstraint
import kyberlib.math.units.extensions.LinearVelocity
import kyberlib.math.units.extensions.feetPerSecond
import kyberlib.math.units.extensions.metersPerSecond

data class KTrajectoryConfig(val maxVelocity: LinearVelocity, val maxAcceleration: LinearVelocity, val constraints: List<TrajectoryConstraint> = listOf(), val startVelocity: LinearVelocity = 0.feetPerSecond, val endVelocity: LinearVelocity = 0.feetPerSecond, val reversed: Boolean = false) {
    val wpiConfig = TrajectoryConfig(maxVelocity.metersPerSecond, maxAcceleration.metersPerSecond).setReversed(reversed).setStartVelocity(startVelocity.metersPerSecond).setEndVelocity(endVelocity.metersPerSecond)
}
