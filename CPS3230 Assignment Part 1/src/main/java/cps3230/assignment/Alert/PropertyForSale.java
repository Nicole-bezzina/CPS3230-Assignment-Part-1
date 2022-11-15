package cps3230.assignment.Alert;

public class PropertyForSale extends Alert {
    public PropertyForSale (){

    }

    //Max alerts
    public int getMaxAlerts() {
        return 5;
    }

    //Alert Type
    public AlertTypes getAlertType() {
        return AlertTypes.PropertyForSaleType;
    }
}
