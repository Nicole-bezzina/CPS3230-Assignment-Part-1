package cps3230.assignment.Alert;

public class Electronics extends Alert{

    public Electronics (){

    }

    //Max alerts
    public int getMaxAlerts() {
        return 5;
    }

    //Alert Type
    public AlertTypes getAlertType() {
        return AlertTypes.ElectronicType;
    }
}
