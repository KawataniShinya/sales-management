package com.sales.infrastructure;

import com.sales.common.DataSourceForApplication01ConfigurationTestMock;
import com.sales.domain.user.AuthUser;
import com.sales.domain.user.AuthUserRepository;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
@Import(DataSourceForApplication01ConfigurationTestMock.class)
@PropertySource(value = "classpath:properties/sql.properties")
@DisplayName("認証用リポジトリテストクラス")
class AuthUserRepositoryImplTest {

    private AuthUser authUser;
    private DataSource dataSource;
    private AuthUserRepository authUserRepository;

    @Autowired
    public AuthUserRepositoryImplTest(AuthUser authUser,
                                      @Qualifier("applds01TestMock") DataSource dataSource,
                                      @Qualifier("appljdbc01TestMock") JdbcTemplate jdbcTemplate,
                                      @Qualifier("applNpjdbc01TestMock") NamedParameterJdbcTemplate npJdbcTemplate,
                                      @Value("${APPLSQL001}") String AUTH_SQL) {
        this.authUser = authUser;
        this.dataSource = dataSource;
        this.authUserRepository = new AuthUserRepositoryImpl(jdbcTemplate, npJdbcTemplate, AUTH_SQL);
    }

    @Test
    @DisplayName("正常系 ユーザー取得")
    public void testNomal01() {
        Connection connection = null;
        IDatabaseConnection iDatabaseConnection = null;

        try {
            connection = this.dataSource.getConnection();
            connection.setAutoCommit(false);

            iDatabaseConnection= new DatabaseConnection(connection);
            IDataSet dataset = new XlsDataSet(new File("./src/test/resources/test.xlsx"));
            DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection,dataset);

            Map<String, Object> expectMap = new HashMap<>();
            expectMap.put("ID", "root");
            expectMap.put("PASSWORD", "$2a$08$uGieQ2B6N9Ic7666NIpgI.7I70oits21rmd3D1M1nhBWnG8Zsko7C");
            expectMap.put("AUTHORITY", "0001");
            expectMap.put("AUTHORITY_VALUE", "ROLE_USER");
            expectMap.put("ENABLED", true);
            List<Map<String, Object>> expectList = new ArrayList<Map<String, Object>>();
            expectList.add(expectMap);

            this.authUser.setId("root");
            List<Map<String, Object>> resultList = this.authUserRepository.findByUserId(this.authUser);

            assertEquals(expectList, resultList);
        } catch (Exception e) {
            assertTrue(false);
            e.printStackTrace();
        } finally {
            try{
                if(connection!=null) {
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}