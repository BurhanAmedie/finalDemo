package mil.af.rso.lite.dbgateway;

public class InputValidationException extends RuntimeException {

    // reposne to invlaid inputs 

    public InputValidationException(String sqlStatement ) {
        super("SQL command can not be used for this path: "+ sqlStatement);
    }
    
}
