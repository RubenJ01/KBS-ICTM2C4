package gui.model;

import java.util.ArrayList;
import java.util.List;

public class RobotArmQueueBinPacking {

    private final List<PackageModel> robotArmQueuePackages;


    public RobotArmQueueBinPacking() {
        this.robotArmQueuePackages = new ArrayList<>();

    }

    public List<PackageModel> getRobotArmQueuePackages() {
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
