package mil.af.rso.lite.dbgateway;

public class InputValiadationException extends RuntimeException {

    // reposne to invlaid inputs 

    public InputValiadationException(String sqlStatement ) {
        super("SQL command can not be used for this path: "+ sqlStatement);
    }
    
}
