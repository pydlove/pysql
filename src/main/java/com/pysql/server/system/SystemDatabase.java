package com.pysql.server.system;

import com.pysql.server.domain.DatabaseNode;
import com.pysql.server.parser.ddl.CreateDatabaseTokenization;
import com.pysql.server.storage.metadata.CreateDatabaseStore;

import java.util.Objects;

/**
 *
 * @description: SystemDatabase
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2024-09-11 16:45
 */
public class SystemDatabase {
    
    /**
     * init
     * 
     * @since 1.0.0
     * 
     * @return: void 
     * @author: panyong 
     * @version: 1.0.0 
     * @createTime: 2024-09-11 16:45 
     */  
    public static void init() {
        String sql = "CREATE DATABASE system";

        CreateDatabaseTokenization createDatabaseTokenization = new CreateDatabaseTokenization();
        DatabaseNode databaseNode = createDatabaseTokenization.analyzer(sql);
        if (Objects.nonNull(databaseNode)) {
            CreateDatabaseStore createDatabaseStore = new CreateDatabaseStore();
            createDatabaseStore.store(databaseNode);
        }
    }
}
