package com.pysql.server.parser.ddl;

import com.pysql.server.domain.DatabaseNode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description: A custom lexical parser that creates a database
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2024-09-11 14:05
 */
public class CreateDatabaseTokenization {

    private static final Pattern TOKEN_PATTERN = Pattern.compile("(\\s+|CREATE|DATABASE|[a-zA-Z_][a-zA-Z0-9_]*)");

    /**
     * Because Apache Calcite does not support parsing SQL of Create Database,
     * we implemented a simple lexical parser ourselves.
     * 
     * @since 1.0.0
     *
     * @param: sql
     * @return: com.pysql.domain.DatabaseNode 
     * @author: panyong 
     * @version: 1.0.0 
     * @createTime: 2024-09-11 14:18 
     */  
    public DatabaseNode analyzer(String sql) {

        Matcher matcher = TOKEN_PATTERN.matcher(sql);
        while (matcher.find()) {
            String tokenValue = matcher.group();
            if (!tokenValue.trim().isEmpty() && !"CREATE".equalsIgnoreCase(tokenValue) && !"DATABASE".equalsIgnoreCase(tokenValue)) {
                return new DatabaseNode(tokenValue);
            }
        }

        return null;
    }
}
