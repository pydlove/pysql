package com.pysql.server.domain;

import lombok.Data;
import org.apache.calcite.sql.SqlNode;

/**
 *
 * @description: Metadata object for the inserted column
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2024-09-10 20:46
 */
@Data
public class ColumnMetadata {

    private String columnName;

    private SqlNode columnNode;
}
