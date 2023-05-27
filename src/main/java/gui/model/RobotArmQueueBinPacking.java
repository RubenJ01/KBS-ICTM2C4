package gui.model;

import java.util.ArrayList;
import java.util.List;

public class RobotArmQueueBinPacking {
    //Op de robot arm zitten 3 pakketten
    //Deze moeten toegevoegd kunnen worden door een functie. Hierbij moet het product ID en het gewicht worden meegegeven.
    private List<PackageBinPacking> robotArmQueuePackages;


    public RobotArmQueueBinPacking(){
        this.robotArmQueuePackages = new ArrayList<>();

    }

    public List<PackageBinPacking> getRobotArmQueuePackages(){
        return robotArmQueuePackages;
    }

    public void setRobotArmQueuePackages(List<PackageBinPacking> robotArmQueuePackages) {
        this.robotArmQueuePackages = robotArmQueuePackages;
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
