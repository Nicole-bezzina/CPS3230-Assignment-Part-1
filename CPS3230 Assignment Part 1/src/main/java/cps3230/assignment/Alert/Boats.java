package cps3230.assignment.Alert;

public class Boats extends Alert{

    public Boats (){

    }

    //Max alerts
    public int getMaxAlerts() {
        return 5;
    }

    //Alert Type
    public AlertTypes getAlertType() {
        return AlertTypes.BoatType;
    }
}