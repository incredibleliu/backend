package db.migration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.transaction.annotation.Transactional;

public class V3__Insert_account extends BaseJavaMigration{
	
	private static Logger log = LoggerFactory.getLogger(V3__Insert_account.class);
    
	@Transactional
    public void migrate(Context context) {
    	
    	log.info("enter V3__Insert_account.migrate...");
		Connection conn = context.getConnection();

		try {
    		conn.setAutoCommit(true);
	    	InputStream in = new ClassPathResource(
	    		      "db/migration/account_number.csv").getInputStream();
	    	BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			for (String line; (line = reader.readLine()) != null; ) {
				if(!line.startsWith("account")) {
					
					log.info(line);
					try (Statement statement = context.getConnection().createStatement()) {
						try{
							String sql = "INSERT INTO EXCLUSION_ACCOUNTS (account_number) VALUES ('" + line + "')";
							log.info(sql);
							//statement.execute(sql);
							conn.commit();
						} catch (SQLException e) {
							e.printStackTrace();
						}
			
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error...", e);
		} finally {

			
		}

    }



}