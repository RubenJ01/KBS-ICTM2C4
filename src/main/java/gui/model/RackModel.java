package gui.model;

import java.util.ArrayList;

public  class  RackModel {
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
            }else{
                return true;
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

