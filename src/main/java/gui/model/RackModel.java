package gui.model;

import database.dao.RackDao;
import database.util.DatabaseConnection;
import gui.view.RackView;

import java.sql.Connection;
import java.sql.SQLException;
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
    public static boolean CheckLocationPossession(int x, int y){

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

    public static boolean CheckPackage(int itemnummer){

        for (PackageModel item : rack) {
            if (item.getItemnummer()==itemnummer){
                return true;
            }
        }
        return false;
    }

    public static void addToRack(PackageModel loadmodel){
        rack.add(loadmodel);
        try (Connection con = DatabaseConnection.getConnection()) {
            RackDao.getInstance().addPackage(con, loadmodel.getItemnummer(), loadmodel.getWeight(), loadmodel.getLocationX(), loadmodel.getLocationY());
        } catch (SQLException e) {
            System.err.println("kon pakket niet toevoegen aan magazijn");
        }
    }
    public static void removeFromRack(PackageModel loadmodel){
        rack.remove(loadmodel);
        try (Connection con = DatabaseConnection.getConnection()) {
            RackDao.getInstance().removePackage(con, loadmodel.getLocationX(), loadmodel.getLocationY());
        } catch (SQLException e) {
            System.err.println("kon pakket niet verwijderen uit magazijn");
        }

    }

    public static void getRack(){
        try (Connection con = DatabaseConnection.getConnection()) {
            RackDao.getInstance().fillRackStartUp(con);
        } catch (SQLException e) {
            System.err.println("kon magazijn niet ophalen");
        }
    }

    public static int getXCoordinates(int itemNummer) {
        for (PackageModel item : rack) {
            if (item.getItemnummer()==itemNummer){
                System.out.println(item.getLocationX());
                return item.getLocationX();
            }
        }
        return 0;
    }

    public static int getYCoordinates(int itemNummer) {
        for (PackageModel item : rack) {
            if (item.getItemnummer()==itemNummer){
                System.out.println(item.getLocationY());
                return item.getLocationY();
            }
        }
        return 0;
    }

    public static PackageModel getPackageFromRack(int x, int y){
        for (PackageModel item : rack) {
            if (x==item.getLocationX()&&y== item.getLocationY()){
                return item;
            }
        }
        return null;
    }

}

