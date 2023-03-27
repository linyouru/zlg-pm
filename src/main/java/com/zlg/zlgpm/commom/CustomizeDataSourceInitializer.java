package com.zlg.zlgpm.commom;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import org.springframework.core.io.Resource;

import javax.sql.DataSource;

/**
 * mysql初始化
 *
 * @author linyouru
 */
@Configuration
public class CustomizeDataSourceInitializer {


    @Value("classpath:sql/userTable.sql")
    private Resource userTable;
    @Value("classpath:sql/roleTable.sql")
    private Resource roleTable;
    @Value("classpath:sql/permissionTable.sql")
    private Resource permissionTable;
    @Value("classpath:sql/userRoleTable.sql")
    private Resource userRoleTable;
    @Value("classpath:sql/rolePermissionTable.sql")
    private Resource rolePermissionTable;
    @Value("classpath:sql/projectTable.sql")
    private Resource projectTable;
    @Value("classpath:sql/projectTaskTable.sql")
    private Resource projectTaskTable;
    @Value("classpath:sql/operationLogTable.sql")
    private Resource operationLogTable;
    @Value("classpath:sql/taskChangeTable.sql")
    private Resource taskChangeTable;
    @Value("classpath:sql/taskLogTable.sql")
    private Resource taskLogTable;
    @Value("classpath:sql/userProjectTable.sql")
    private Resource userProjectTable;
    @Value("classpath:sql/taskFeedbackTable.sql")
    private Resource feedbackTable;
    @Value("classpath:sql/projectModulesTable.sql")
    private Resource projectModulesTable;
    @Value("classpath:sql/projectVersionTable.sql")
    private Resource projectVersionTable;


    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(databasePopulator());
        return dataSourceInitializer;
    }

    private DatabasePopulator databasePopulator() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        //在这里添加初始化sql脚本
        populator.addScript(userTable);
        populator.addScript(roleTable);
        populator.addScript(permissionTable);
        populator.addScript(userRoleTable);
        populator.addScript(rolePermissionTable);
        populator.addScript(projectTable);
        populator.addScript(projectTaskTable);
        populator.addScript(operationLogTable);
        populator.addScript(taskChangeTable);
        populator.addScript(taskLogTable);
        populator.addScript(userProjectTable);
        populator.addScript(feedbackTable);
        populator.addScript(projectModulesTable);
        populator.addScript(projectVersionTable);
        return populator;
    }


}
