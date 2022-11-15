package cps3230.assignment.Alert;

public class PropertyForRent extends Alert {
    public PropertyForRent (){

    }

    //Max alerts
    public int getMaxAlerts() {
        return 5;
    }

    //Alert Type
    public AlertTypes getAlertType() {
        return AlertTypes.PropertyForRentType;
    }
}
