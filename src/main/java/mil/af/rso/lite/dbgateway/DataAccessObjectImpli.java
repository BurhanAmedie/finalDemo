package mil.af.rso.lite.dbgateway;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;




@Repository
public class DataAccessObjectImpli implements DataAccessObject   {
    
@Autowired
JdbcTemplate jdbcTemplate ;



@Override
public List <Map<String,Object>> getAll( String sqlQuery) {
        String sqlCommand = sqlQuery.trim() ; 

    
        // SQL command to query records in a table
        if(sqlCommand.toUpperCase().startsWith("SELECT")) {
            return jdbcTemplate.queryForList(sqlCommand);

        } else {
            throw new InputValidationException(sqlQuery);
        }   
     
}

@Override
public int save( String sqlQuery ) {
    String sqlCommand = sqlQuery.trim() ; 

    // SQL command to insert new records in a table
    if(sqlCommand.toUpperCase().startsWith("INSERT")) {
        return jdbcTemplate.update(sqlCommand);
    } else {
        throw new InputValidationException(sqlQuery);
    }
   
}


@Override
public int update(String sqlQuery) {
    String sqlCommand = sqlQuery.trim() ; 

    // SQL command to update existing records in a table
    if(sqlCommand.toUpperCase().startsWith("UPDATE")) {
        return jdbcTemplate.update(sqlCommand);
    } else {
        throw new InputValidationException(sqlQuery);
    }
}

@Override
public int delete(String sqlQuery) {
    String sqlCommand = sqlQuery.trim() ; 
    // SQL command to delete  existing records in a table
    if(sqlCommand.toUpperCase().startsWith("DELETE")) {
        return jdbcTemplate.update(sqlCommand);
    } else {
        throw new InputValidationException(sqlQuery);
    }
}
    
}
