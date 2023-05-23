package gui.model;

import gui.view.RackView;

import java.util.ArrayList;

public  class  RackModel  extends RackView {
    public static ArrayList<PackageModel> rack=new ArrayList<>();

    public static void printRack(){
        System.out.println("pakketten in rack:");
        if(rack.size()>0){
            for(int i = 0; i < rack.size(); i++) {
                System.out.println(i+": "+rack.get(i));
            }
        }
    }
    public boolean CheckLocationPossession(int x, int y){

        for (PackageModel item : rack) {
            if(x==item.getLocationX()&&y==item.getLocationY()){
                return false;
            }
        }
        for (PackageModel item : RobotQueue.queue) {
            if(x==item.getLocationX()&&y==item.getLocationY()){
                return false;
            }
        }
        return true;
    }

    public static void addToRack(PackageModel loadmodel){
        rack.add(loadmodel);
    }
    public static void removeFromRack(){

    }
}

