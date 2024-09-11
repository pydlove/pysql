package com.pysql.server.parser;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.ImmutableList;
import com.pysql.server.domain.ColumnMetadata;
import com.pysql.server.domain.InsertMetadata;
import org.apache.calcite.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InsertParsing {

    /**
     * parse
     * 
     * @since 1.0.0
     *
     * @param: sqlNode 
     * @return: com.pysql.domain.InsertMetadata 
     * @author: panyong 
     * @version: 1.0.0 
     * @createTime: 2024-09-11 9:47 
     */  
    public InsertMetadata parse(SqlNode sqlNode) {

        if (Objects.isNull(sqlNode)) {
            return null;
        }

        InsertMetadata insertMetadata = new InsertMetadata();
        if (Objects.equals(SqlKind.INSERT, sqlNode.getKind())) {

            SqlInsert sqlInsert = (SqlInsert) sqlNode;

            String targetTableName = getTargetTableName(sqlInsert);

            List<ColumnMetadata> columnMetadataList = getColumnMetadataList(sqlInsert);

            insertMetadata.setTargetTableName(targetTableName);
            insertMetadata.setColumnMetadataList(columnMetadataList);
        }

        return insertMetadata;
    }

    /**
     * getColumnMetadataList
     *
     * @since 1.0.0
     * @param sqlInsert
     * @return: java.util.List<com.pysql.domain.ColumnMetadata>
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2024-09-11 9:24
     */
    private List<ColumnMetadata> getColumnMetadataList(SqlInsert sqlInsert) {

        List<ColumnMetadata> columnMetadataList = new ArrayList<>();

        List<String> targetColumnNameList = getTargetColumnNameList(sqlInsert);

        List<SqlNode> valueNodeList = getValueNodeList(sqlInsert);

        if (CollUtil.isNotEmpty(targetColumnNameList)) {
            int valueNodeSize = CollUtil.isNotEmpty(valueNodeList) ? valueNodeList.size() : 0;
            if (targetColumnNameList.size() == valueNodeSize) {
                for (int i = 0; i < targetColumnNameList.size(); i++) {

                    ColumnMetadata columnMetadata = new ColumnMetadata();
                    columnMetadata.setColumnName(targetColumnNameList.get(i));
                    columnMetadata.setColumnNode(valueNodeList.get(i));
                    columnMetadataList.add(columnMetadata);
                }
            }
        }

        return columnMetadataList;
    }

    /**
     * getValueNodeList
     *
     * @since 1.0.0
     * @param: sqlInsert
     * @return: java.util.List<org.apache.calcite.sql.SqlNode>
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2024-09-11 9:39
     */
    private List<SqlNode> getValueNodeList(SqlInsert sqlInsert) {

        List<SqlNode> valueNodes = null;

        SqlNode source = sqlInsert.getSource();
        if (Objects.equals(SqlKind.VALUES, source.getKind())) {

            SqlBasicCall sqlBasicCall = (SqlBasicCall) source;
            List<SqlNode> operandList = sqlBasicCall.getOperandList();
            if (CollUtil.isNotEmpty(operandList)) {
                for (SqlNode rowNode : operandList) {
                    if (Objects.equals(SqlKind.ROW, rowNode.getKind())) {
                        SqlBasicCall rowBasicCall = (SqlBasicCall) rowNode;
                        valueNodes = rowBasicCall.getOperandList();
                    }
                }
            }
        }
        
        return valueNodes;
    }

    /**
     * getTargetColumnNameList
     *
     * @since 1.0.0
     * @param sqlInsert
     * @return: java.util.List<java.lang.String>
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2024-09-11 9:33
     */
    private List<String> getTargetColumnNameList(SqlInsert sqlInsert) {

        List<String> targetColumnNameList = new ArrayList<>();

        SqlNodeList targetColumnList = sqlInsert.getTargetColumnList();
        if (CollUtil.isEmpty(targetColumnList)) {
            return targetColumnNameList;
        }

        for (SqlNode sqlNode : targetColumnList) {

            if (sqlNode instanceof SqlIdentifier) {
                SqlIdentifier sqlIdentifier = (SqlIdentifier) sqlNode;
                ImmutableList<String> names = sqlIdentifier.names;
                if (CollUtil.isNotEmpty(names)) {
                    targetColumnNameList.add(names.get(0));
                }
            }
        }

        return targetColumnNameList;
    }

    /**
     * getTargetTableName
     *
     * @param sqlInsert
     * @return: java.lang.String
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2024-09-10 20:58
     * @since 1.0.0
     */
    private String getTargetTableName(SqlInsert sqlInsert) {

        String targetTableName = null;
        SqlNode targetTable = sqlInsert.getTargetTable();
        if (targetTable instanceof SqlIdentifier) {
            SqlIdentifier sqlIdentifier = (SqlIdentifier) targetTable;
            ImmutableList<String> names = sqlIdentifier.names;
            if (CollUtil.isNotEmpty(names)) {
                targetTableName = names.get(0);
            }
        }

        return targetTableName;
    }
}
