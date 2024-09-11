package com.pysql.server.parser;

import lombok.extern.slf4j.Slf4j;
import org.apache.calcite.sql.*;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.parser.impl.SqlParserImpl;
import org.apache.calcite.sql.validate.SqlConformanceEnum;
import org.apache.calcite.sql.validate.SqlValidatorUtil;
import org.apache.calcite.tools.FrameworkConfig;
import org.apache.calcite.tools.Frameworks;
import org.apache.calcite.tools.Planner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @description: Lexical Analyzer
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2024-09-10 15:59
 */
@Slf4j
public class Tokenization {

    /**
     * Perform lexical analysis on sql and decompose it into a word list
     *
     * @since 1.0.0
     * @param sql
     * @return: org.apache.calcite.sql.SqlNode
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2024-09-10 19:44
     */
    public SqlNode analyzer(String sql) {

        try {

            // Lexical decomposition:
            // Here I choose the open source lexical analyzer (Apache Calcite)
            SqlParser.Config config = SqlParser.config().withCaseSensitive(false);

            SqlParser sqlParser = SqlParser.create(sql, config);

            return sqlParser.parseStmt();

        } catch (Exception ex) {
             log.error("sql parse error, sql: {}, cause by:", sql, ex);
        }

        return null;
    }
}
