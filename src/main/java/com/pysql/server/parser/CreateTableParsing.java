package com.pysql.server.parser;

import com.pysql.server.domain.InsertMetadata;
import org.apache.calcite.sql.SqlKind;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.ddl.SqlCreateTable;

import java.util.Objects;

/**
 *
 * @description: CreateTableParsing
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2024-09-11 16:51
 */
public class CreateTableParsing {

    public void parse(SqlNode sqlNode) {

        if (Objects.isNull(sqlNode)) {
            return;
        }

        if (Objects.equals(SqlKind.CREATE_TABLE, sqlNode.getKind())) {
            SqlCreateTable sqlCreateTable = (SqlCreateTable) sqlNode;
            sqlCreateTable.getOperandList();
        }
    }
}
