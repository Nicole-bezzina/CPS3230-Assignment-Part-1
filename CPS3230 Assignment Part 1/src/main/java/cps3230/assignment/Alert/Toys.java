package cps3230.assignment.Alert;

public class Toys extends Alert {
    public Toys (){

    }

    //Max alerts
    public int getMaxAlerts() {
        return 5;
    }

    //Alert Type
    public AlertTypes getAlertType() {
        return AlertTypes.ToysType;
    }
}
