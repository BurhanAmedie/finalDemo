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
    
        // SQL command to query records in a table
        if(sqlQuery.startsWith("SELECT")) {
            return jdbcTemplate.queryForList(sqlQuery);

        } else {
            throw new InputValiadationException(sqlQuery);
        }   
     
}

@Override
public int save( String sqlQuery ) {
    // SQL command to insert new records in a table
    if(sqlQuery.startsWith("INSERT")) {
        return jdbcTemplate.update(sqlQuery);
    } else {
        throw new InputValiadationException(sqlQuery);
    }
   
}


@Override
public int update(String sqlQuery) {
    // SQL command to update existing records in a table
    if(sqlQuery.startsWith("UPDATE")) {
        return jdbcTemplate.update(sqlQuery);
    } else {
        throw new InputValiadationException(sqlQuery);
    }
}

@Override
public int delete(String sqlQuery) {
    // SQL command to delete  existing records in a table
    if(sqlQuery.startsWith("DELETE")) {
        return jdbcTemplate.update(sqlQuery);
    } else {
        throw new InputValiadationException(sqlQuery);
    }
}
    
}
