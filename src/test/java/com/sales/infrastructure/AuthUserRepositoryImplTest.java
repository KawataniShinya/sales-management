package com.sales.infrastructure;

import com.sales.domain.auth.AuthUser;
import com.sales.domain.auth.AuthUserRepository;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("認証用リポジトリテストクラス")
class AuthUserRepositoryImplTest {

    private final AuthUser authUser;
    private final DataSource dataSourceTest;
    private final AuthUserRepository authUserRepositoryTest;
    private final PlatformTransactionManager sysTransactionManager;

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

        this.setLogNoCommit();

        if (!this.initDbData(
                Thread.currentThread().getStackTrace()[1].getClassName(),
                Thread.currentThread().getStackTrace()[1].getMethodName())) {
            fail();
            return;
        }

        Map<String, Object> expectMap = new HashMap<>();
        expectMap.put("ID", "root");
        expectMap.put("PASSWORD", "$2a$08$uGieQ2B6N9Ic7666NIpgI.7I70oits21rmd3D1M1nhBWnG8Zsko7C");
        expectMap.put("AUTHORITY", "0001");
        expectMap.put("AUTHORITY_VALUE", "ROLE_USER");
        expectMap.put("ENABLED", true);
        List<Map<String, Object>> expectList = new ArrayList<>();
        expectList.add(expectMap);

        this.authUser.setUserId("root");
        List<Map<String, Object>> resultList = this.authUserRepositoryTest.findEnableUserByUserId(this.authUser);

        assertEquals(expectList, resultList);
    }

    private void setLogNoCommit() {
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        this.sysTransactionManager.getTransaction(transactionDefinition);
    }

    private boolean initDbData(String className, String methodName) {
        boolean successed = false;

        try {
            Connection connectionTest = DataSourceUtils.getConnection(this.dataSourceTest);
            IDatabaseConnection iDatabaseConnectionTest= new DatabaseConnection(connectionTest);
            IDataSet dataset = new XlsDataSet(new File("./src/test/resources/" +
                className.replaceAll("\\.", "/") + "/" + methodName + "/" +
                "init.xlsx"));
            DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnectionTest,dataset);
            successed = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return successed;
    }
}