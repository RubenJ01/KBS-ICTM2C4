package gui.model;

import java.util.ArrayList;
import java.util.List;

public class RobotArmQueueBinPacking {

    private List<PackageBinPacking> robotArmQueuePackages;


    public RobotArmQueueBinPacking() {
        this.robotArmQueuePackages = new ArrayList<>();

    }

    public List<PackageBinPacking> getRobotArmQueuePackages() {
        return robotArmQueuePackages;
    }

    @Override
    public String toString() {
        String toString = "";
        for (int i = 0; i < robotArmQueuePackages.size(); i++) {
            toString += "Pakket " + i + ": " + robotArmQueuePackages.get(i) + "\n";
        }
        return toString;
    }
}
