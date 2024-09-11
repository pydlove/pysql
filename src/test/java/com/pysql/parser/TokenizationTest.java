package com.pysql.parser;

import com.pysql.server.domain.DatabaseNode;
import com.pysql.server.domain.InsertMetadata;
import com.pysql.server.parser.ddl.CreateDatabaseTokenization;
import com.pysql.server.parser.CreateTableParsing;
import com.pysql.server.parser.InsertParsing;
import com.pysql.server.parser.Tokenization;
import com.pysql.server.parser.ddl.CreateTableTokenization;
import com.pysql.server.storage.metadata.CreateDatabaseStore;
import org.apache.calcite.sql.SqlNode;
import org.junit.Test;

import java.util.Objects;

public class TokenizationTest {

    public static void main(String[] args) {
        String sql = "INSERT INTO users (id, name, email, created_at) " +
                "VALUES (1, '张三', 'zhangsan@example.com', '2023-04-01 12:00:00')";

        Tokenization tokenization = new Tokenization();
        SqlNode sqlNode = tokenization.analyzer(sql);

        InsertParsing insertParsing = new InsertParsing();
        InsertMetadata insertMetadata = insertParsing.parse(sqlNode);
        System.out.println(insertMetadata);
    }

    @Test
    public void createDatabase() {
        String sql = "CREATE DATABASE my_database";

        CreateDatabaseTokenization createDatabaseTokenization = new CreateDatabaseTokenization();
        DatabaseNode databaseNode = createDatabaseTokenization.analyzer(sql);
        if (Objects.nonNull(databaseNode)) {
            CreateDatabaseStore createDatabaseStore = new CreateDatabaseStore();
            createDatabaseStore.store(databaseNode);
        }
    }

    @Test
    public void createTable() {
        String sql = "CREATE TABLE my_table (id INT, name VARCHAR(255))";

        String sql1 = "CREATE TABLE `system`.tables (\n" +
                "\tdatabase String NOT NULL,\n" +
                "\tname String NOT NULL,\n" +
                "\tuuid UUID NOT NULL,\n" +
                "\tengine String NOT NULL,\n" +
                "\tis_temporary UInt8 NOT NULL,\n" +
                "\tdata_paths Array(String) NOT NULL,\n" +
                "\tmetadata_path String NOT NULL,\n" +
                "\tmetadata_modification_time DateTime NOT NULL,\n" +
                "\tdependencies_database Array(String) NOT NULL,\n" +
                "\tdependencies_table Array(String) NOT NULL,\n" +
                "\tcreate_table_query String NOT NULL,\n" +
                "\tengine_full String NOT NULL,\n" +
                "\tpartition_key String NOT NULL,\n" +
                "\tsorting_key String NOT NULL,\n" +
                "\tprimary_key String NOT NULL,\n" +
                "\tsampling_key String NOT NULL,\n" +
                "\tstorage_policy String NOT NULL,\n" +
                "\ttotal_rows UInt64,\n" +
                "\ttotal_bytes UInt64,\n" +
                "\tlifetime_rows UInt64,\n" +
                "\tlifetime_bytes UInt64\n" +
                ")";

        CreateTableTokenization createTableTokenization = new CreateTableTokenization();
        createTableTokenization.analyzer(sql);
    }
}