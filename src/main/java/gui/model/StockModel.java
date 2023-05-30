package gui.model;
//
//
//
public class StockModel {
    boolean noodstop = true;
    int rood;
    int HIGH;
    int LOW;
    int groen;
    int geel;
    boolean joystickmode;
    public void digitalWrite(int rood, int HIGH){

    }
    void stopLichtStatus() {
        if(noodstop){
            digitalWrite(rood, HIGH);
        }
        else{
            digitalWrite(rood, LOW);
        }
        if(joystickmode){
            digitalWrite(groen, LOW);
            digitalWrite(geel, HIGH);
        }
        else{
            digitalWrite(groen, HIGH);
            digitalWrite(geel, LOW);
        }
    }
}