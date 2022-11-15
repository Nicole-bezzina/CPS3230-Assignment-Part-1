package cps3230.assignment.Alert;

public class Cars extends Alert{

    public Cars (){

    }

    //Max alerts
    public int getMaxAlerts() {
        return 5;
    }

    //Alert Type
    public AlertTypes getAlertType() {
        return AlertTypes.CarType;
    }
}

