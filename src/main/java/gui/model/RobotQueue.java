package gui.model;

import gui.view.LoadView;

import java.util.ArrayList;

public class RobotQueue {
    public static ArrayList<LoadModel> queue=new ArrayList<>();

    public RobotQueue(){

    }


    public static void printQueue() {
        if(queue.size()>0){
            for(int i = 0; i < queue.size(); i++) {
                System.out.println(i+": "+queue.get(i));
            }
        }
    }

    public static void addQueue(int Y, int X, int Nummer){
        queue.add(new LoadModel(Y,X,Nummer));
        printQueue();
        LoadView.model.removeAllElements();
        int i=0;
        for (LoadModel item : queue) {
            i++;
            LoadView.model.addElement(i+": "+item);
        }


    }

    public static void removeQueue(){
        LoadView.model.removeAllElements();
        queue.clear();
    }




}
