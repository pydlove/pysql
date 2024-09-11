package com.pysql.server.domain;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 *
 * @description: Inserted metadata object
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2024-09-10 20:45
 */
@Data
@ToString
public class InsertMetadata {

    private String targetTableName;

    private List<ColumnMetadata> columnMetadataList;
}
