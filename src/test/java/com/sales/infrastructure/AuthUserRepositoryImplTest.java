package com.sales.infrastructure;

import com.sales.domain.user.AuthUser;
import com.sales.domain.user.AuthUserRepository;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@PropertySource(value = "classpath:properties/sql.properties")
@DisplayName("認証用リポジトリテストクラス")
class AuthUserRepositoryImplTest {

    private AuthUser authUser;
    private DataSource dataSourceTest;
    private AuthUserRepository authUserRepositoryTest;
    private PlatformTransactionManager sysTransactionManager;

    @Autowired
    public AuthUserRepositoryImplTest(AuthUser authUser,
                                      @Qualifier("applds01") DataSource dataSourceTest,
                                      AuthUserRepository authUserRepositoryTest,
                                      @Qualifier("sysTransactionManager") PlatformTransactionManager sysTransactionManager) {
        this.authUser = authUser;
        this.dataSourceTest = dataSourceTest;
        this.authUserRepositoryTest = authUserRepositoryTest;
        this.sysTransactionManager = sysTransactionManager;
    }

    @Test
    @Transactional(transactionManager = "applTransactionManager")
    @DisplayName("正常系 ユーザー取得")
    public void testNomal01() {
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        this.sysTransactionManager.getTransaction(transactionDefinition);

        try {
            Connection connectionTest = DataSourceUtils.getConnection(this.dataSourceTest);
            IDatabaseConnection iDatabaseConnectionTest= new DatabaseConnection(connectionTest);
            IDataSet dataset = new XlsDataSet(new File("./src/test/resources/" +
                    Thread.currentThread().getStackTrace()[1].getClassName().replaceAll("\\.", "/") + "/" +
                    Thread.currentThread().getStackTrace()[1].getMethodName() + "/" +
                    "init.xlsx"));
            DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnectionTest,dataset);

            Map<String, Object> expectMap = new HashMap<>();
            expectMap.put("ID", "root");
            expectMap.put("PASSWORD", "$2a$08$uGieQ2B6N9Ic7666NIpgI.7I70oits21rmd3D1M1nhBWnG8Zsko7C");
            expectMap.put("AUTHORITY", "0001");
            expectMap.put("AUTHORITY_VALUE", "ROLE_USER");
            expectMap.put("ENABLED", true);
            List<Map<String, Object>> expectList = new ArrayList<Map<String, Object>>();
            expectList.add(expectMap);

            this.authUser.setId("root");
            List<Map<String, Object>> resultList = this.authUserRepositoryTest.findByUserId(this.authUser);

            assertEquals(expectList, resultList);
        } catch (Exception e) {
            assertTrue(false);
            e.printStackTrace();
        }
    }
}