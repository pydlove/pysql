package com.pysql.server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @description: DatabaseNode
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2024-09-11 14:16
 */
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseNode {

    private String databaseName;
}
