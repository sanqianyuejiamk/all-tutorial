package com.mengka.springboot.component;

import com.mengka.springboot.model.TableColumnTypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.DatabaseMetaDataCallback;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.MetaDataAccessException;
import org.springframework.stereotype.Component;
import javax.sql.*;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mengka
 * @version 1.0
 * @date 2021/5/17 20:14
 */
@Component
public class MetaDataProcess {

    @Autowired
    DataSource dataSource;


    @SuppressWarnings("unchecked")
    public List<TableColumnTypeMap> getTableColumns(String tableName) {
        try {

            return (List<TableColumnTypeMap>) JdbcUtils.extractDatabaseMetaData(dataSource,
                    new DatabaseMetaDataCallback() {
                        @Override
                        public Object processMetaData(DatabaseMetaData dbmd)
                                throws SQLException, MetaDataAccessException {
                            ResultSet rs = dbmd
                                    .getColumns("", "%", tableName + "%", null);
                            List<TableColumnTypeMap> list = new ArrayList();
                            while (rs.next()) {

                                String tableCat = rs.getString("TABLE_CAT");
                                String tableSchem = rs.getString("TABLE_SCHEM");
                                String tableName = rs.getString("TABLE_NAME");
                                String columnName = rs.getString("COLUMN_NAME");
                                String typeName = rs.getString("TYPE_NAME");
                                String columnSize = rs.getString("COLUMN_SIZE");
                                String nullable = rs.getString("NULLABLE");
                                String remarks = rs.getString("REMARKS");
                                //String sql_data_type = rs.getString("type");
                                String type = rs.getString("SQL_DATA_TYPE");
                                int dataType = rs.getInt("DATA_TYPE");
                                //int precision = rs.getInt("PRECISION");

                                System.out.println(tableName);
                                TableColumnTypeMap tableColumnTypeMap = new TableColumnTypeMap()
                                        .setColumnName(columnName)
                                        .setColumnType(typeName)
                                        .setJavaClassName(getColumnCLassName(dataType))
                                        .setRemarks(remarks);
                                list.add(tableColumnTypeMap);
                            }
                            return list;
                        }
                    });
        } catch (MetaDataAccessException ex) {
            throw new RuntimeException("get table list failed", ex);
        }
    }


    private static String getColumnCLassName(int sqlType) {
        String className = String.class.getName();

        switch (sqlType) {

            case Types.NUMERIC:
            case Types.DECIMAL:
                className = java.math.BigDecimal.class.getName();
                break;

            case Types.BIT:
                className = java.lang.Boolean.class.getName();
                break;

            case Types.TINYINT:
                className = java.lang.Byte.class.getName();
                break;

            case Types.SMALLINT:
                className = java.lang.Short.class.getName();
                break;

            case Types.INTEGER:
                className = java.lang.Integer.class.getName();
                break;

            case Types.BIGINT:
                className = java.lang.Long.class.getName();
                break;

            case Types.REAL:
                className = java.lang.Float.class.getName();
                break;

            case Types.FLOAT:
            case Types.DOUBLE:
                className = java.lang.Double.class.getName();
                break;

            case Types.BINARY:
            case Types.VARBINARY:
            case Types.LONGVARBINARY:
                className = "byte[]";
                break;

            case Types.DATE:
                className = java.sql.Date.class.getName();
                break;

            case Types.TIME:
                className = java.sql.Time.class.getName();
                break;

            case Types.TIMESTAMP:
                className = java.sql.Timestamp.class.getName();
                break;

            case Types.BLOB:
                className = java.sql.Blob.class.getName();
                break;

            case Types.CLOB:
                className = java.sql.Clob.class.getName();
                break;
            default:
                break;
        }

        return className;
    }
}
