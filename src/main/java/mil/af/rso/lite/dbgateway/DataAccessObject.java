package mil.af.rso.lite.dbgateway;

import java.util.List;
import java.util.Map;

public interface DataAccessObject {
        int save(String sqlQuery);

        int update(String sqlQuery);

        int delete( String sqlQuery);

        List<Map<String,Object>> getAll( String sqlQuery);
}
