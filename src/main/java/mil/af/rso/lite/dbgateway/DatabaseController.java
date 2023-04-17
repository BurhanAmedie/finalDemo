package mil.af.rso.lite.dbgateway;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatabaseController {
    
    @Autowired
    private DataAccessObject DAO;

    // SQL queries to read from the database will be mapped to this endpoint
    @Operation(summary = "SQL queries should start with SELECT", description="SQL queries to read from the database will be mapped to this endpoint", tags = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Data successfully retrieved from database", content = {@Content(mediaType = "application/json", schema = @Schema())}),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)

    })
    @GetMapping("/select")
    public ResponseEntity<Object> select(@RequestParam(value = "sqlQuery", required = true) String sqlQuery) {
        try {
            List<Map<String,Object>> results = DAO.getAll(sqlQuery);
            return ResponseHandler.generateResponse(HttpStatus.OK, results);
        } catch (Exception e) {
            return ErrorHandler.generateResponse(ErrorHandler.CreateResponse(e.getMessage(), "1190"), HttpStatus.BAD_REQUEST );
        }
    }
    // SQL queries to create new entry in the database will be mapped to the endpoint

    @Operation(summary = "SQL queries should start with CREATE", description="SQL queries to create new entry in the database will be mapped to this endpoint", tags = "POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully created entry in database!", content = {@Content(mediaType = "application/json", schema = @Schema())}),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)

    })
    @PostMapping("/create")
    public ResponseEntity<Object>  save(@RequestParam(value = "sqlQuery", required = true) String sqlQuery) {
        try {
            int NumberofEntryAffected = DAO.save(sqlQuery);
            if (NumberofEntryAffected == 0 ){
                return ErrorHandler.generateResponse(ErrorHandler.CreateResponse("No change was made to the database. Please check your SQL command", "1190"), HttpStatus.BAD_REQUEST);

            } else{
                return ResponseHandler.generateResponse(HttpStatus.CREATED, NumberofEntryAffected);
            }
        } catch (Exception e) {
            return ErrorHandler.generateResponse(ErrorHandler.CreateResponse(e.getMessage(), "1190"), HttpStatus.BAD_REQUEST );
        }        
    }
    // SQL queries to update entry in the database will be mapped to the endpoint

    @Operation(summary = "SQL queries should start with UPDATE", description="SQL queries to update entry in the database will be mapped to this endpoint", tags = "POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated entry in the database", content = {@Content(mediaType = "application/json", schema = @Schema())}),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)

    })

    @PatchMapping("/update")
    public ResponseEntity<Object> Update(@RequestParam(value = "sqlQuery", required = true) String sqlQuery) {
        try {
            int NumberofEntryAffected = DAO.update(sqlQuery);
            if (NumberofEntryAffected == 0 ){
                return ErrorHandler.generateResponse(ErrorHandler.CreateResponse("No change was made to the database. Please check your SQL command", "1190"), HttpStatus.FORBIDDEN);

            } else{
                return ResponseHandler.generateResponse(HttpStatus.OK, NumberofEntryAffected);
            }
            
        } catch (Exception e) {
            return ErrorHandler.generateResponse(ErrorHandler.CreateResponse(e.getMessage(), "1190"), HttpStatus.BAD_REQUEST );
        }        
    }
     // SQL queries to delete entry in the database will be mapped to the endpoint
     @Operation(summary = "SQL queries should start with DELETE", description="SQL queries to delete entry in the database will be mapped to this endpoint", tags = "DELETE")
     @ApiResponses(value = {
         @ApiResponse(responseCode = "204", description = "Successfully deleted entry in the database"),
         @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
 
     })
    @DeleteMapping("/delete")
    public ResponseEntity<Object> delete(@RequestParam(value = "sqlQuery", required = true) String sqlQuery) {
        try {
            int NumberofEntryAffected = DAO.delete(sqlQuery);
            if (NumberofEntryAffected == 0 ){
                return ErrorHandler.generateResponse(ErrorHandler.CreateResponse("No change was made to the database. Please check your SQL command", "1190"), HttpStatus.BAD_REQUEST);

            } else{
                return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT, NumberofEntryAffected);
            }
        } catch (Exception e) {
            return ErrorHandler.generateResponse(ErrorHandler.CreateResponse(e.getMessage(), "1190"), HttpStatus.BAD_REQUEST );
        }
    }


}
