public record Transaction(
        int step, Type type, double amount, String nameOrig, double oldbalanceOrg, double newbalanceOrig,
        String nameDest, double oldbalanceDest, double newbalanceDest, int isFraud, int isFlaggedFraud
) { }
