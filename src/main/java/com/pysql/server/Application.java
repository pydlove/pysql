package com.pysql.server;

import com.pysql.server.system.SystemDatabase;

/**
 *
 * @description: This is the startup entry for the pysql database
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2024-09-11 16:42
 */
public class Application {

    public static void main(String[] args) {

        // Initialize and create system database
        SystemDatabase.init();
    }
}
