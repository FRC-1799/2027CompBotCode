package first.fms;

import org.wpilib.driverstation.DriverStation;
import org.wpilib.driverstation.MatchState;
import org.wpilib.driverstation.MatchType;
import org.wpilib.driverstation.RobotState;
import org.wpilib.hardware.hal.AllianceStationID;
import org.wpilib.hardware.hal.RobotMode;
import org.wpilib.simulation.DriverStationSim;
import org.wpilib.system.Timer;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;
import java.util.function.Function;

public class SimFms {
    private boolean lastFmsState = false;

    private Queue<FmsTransition> fmsTransitions = new ArrayDeque<>();

    public SimFms() {
        fmsTransitions.add(
                new FmsTransition(
                        0.0,
                        () -> {
                            DriverStationSim.setRobotMode(RobotMode.AUTONOMOUS);
                            DriverStationSim.setEnabled(true);
                        }
                )
        );

        fmsTransitions.add(
                new FmsTransition(
                        10.0,
                        () -> {
                            DriverStationSim.setRobotMode(RobotMode.TELEOPERATED);
                            DriverStationSim.setEnabled(true);
                        }
                )
        );

        fmsTransitions.add(
                new FmsTransition(
                        20.0,
                        () -> {
                            DriverStationSim.setRobotMode(RobotMode.AUTONOMOUS);
                            DriverStationSim.setEnabled(true);
                        }
                )
        );

        fmsTransitions.add(
                new FmsTransition(
                        30.0,
                        () -> {
                            DriverStationSim.setRobotMode(RobotMode.TELEOPERATED);
                            DriverStationSim.setEnabled(false);
                        }
                )
        );

        DriverStationSim.setDsAttached(true);
        DriverStationSim.setFmsAttached(true);
        DriverStationSim.setAllianceStationId(AllianceStationID.BLUE_2);
        DriverStationSim.setEventName("FunGame");
        DriverStationSim.setMatchNumber(1);
        DriverStationSim.setMatchType(MatchType.PRACTICE);
        DriverStationSim.notifyNewData();
    }

    public void updateFmsSimulation(double matchElapsedSec) {

//        DriverStationSim.setDsAttached(true);
//        DriverStationSim.setFmsAttached(true);
//        DriverStationSim.setAllianceStationId(AllianceStationID.BLUE_2);
//        DriverStationSim.notifyNewData();

        var nextTransition = fmsTransitions.peek();
        if(nextTransition != null) {
            if (matchElapsedSec > nextTransition.time) {
                fmsTransitions.remove();
                nextTransition.function.run();
            }
        }

        DriverStationSim.notifyNewData();

//        // Optional: Verify FMS attachment transition
//        boolean currentFms = RobotState.isFMSAttached();
//        if (currentFms != lastFmsState) {
//            System.out.println("FMS Attached changed to: " + currentFms);
//            lastFmsState = currentFms;
//        }
    }

    public class FmsTransition {
        public double time;
        public Runnable function;

        FmsTransition(double time, Runnable fun) {
            this.time = time;
            this.function = fun;
        }
    }
}
