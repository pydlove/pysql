package com.pysql.server.storage.metadata;

import com.pysql.server.domain.DatabaseNode;
import com.pysql.server.tools.thread.ThreadPoolFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

import static com.pysql.server.tools.thread.ThreadName.CREATE_DATABASE_THREAD;

/**
 *
 * @description: CreateDatabaseStore
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2024-09-11 14:24
 */
@Slf4j
public class CreateDatabaseStore {

    /**
     * Perform database storage operations:
     * including creating file directories and creating database information in system tables
     * 
     * @since 1.0.0
     * 
     * @param: databaseNode
     * @return: void 
     * @author: panyong 
     * @version: 1.0.0 
     * @createTime: 2024-09-11 15:10 
     */  
    public void store(DatabaseNode databaseNode) {

       try {

           // This execution step is an asynchronous operation and requires the creation of a thread pool
           ThreadPoolFactory.register(CREATE_DATABASE_THREAD, 1);
           ThreadPoolFactory.submit(CREATE_DATABASE_THREAD, () -> {

               // Read the location information of the metadata store

               // Create database physical file address
               createDatabaseFile(databaseNode);

               // Store the database information in the system database
           });

       } catch (Exception ex) {
           log.error("store database error,  databaseNode: {}, cause by:", databaseNode, ex);
       }
    }

    /**
     * createDatabaseFile
     * 
     * @since 1.0.0
     * 
     * @param: databaseNode
     * @return: void 
     * @author: panyong 
     * @version: 1.0.0 
     * @createTime: 2024-09-11 15:11 
     */  
    private void createDatabaseFile(DatabaseNode databaseNode) {

        String folderPath = "D:\\software\\idea\\codeSpace\\sourceCode\\pysql\\src\\main\\resources/store/" + databaseNode.getDatabaseName();
        File folder = new File(folderPath);

        if (!folder.exists()) {
            try {
                folder.mkdirs();
            } catch (Exception ex) {
                log.error("create dir fail, folderPath: {}, caused by: ", folderPath, ex);
            }
        } else {
            log.warn("this dir is existed, please check folderPath: {}.", folderPath);
        }
    }
}
