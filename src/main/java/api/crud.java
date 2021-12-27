package api;

import Entity.TbInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class crud {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        String selectSql = "select * from public.Untitled";
////        ResultSet resultSet = Connect_Greenplum.query(selectSql);
////        Connect_Greenplum.output(resultSet);
//        Integer rs = Connect_Greenplum.dropTable("public.Untitled");
        Integer rs = Connect_Greenplum.createTable("public.Untitled","id int4,\n" +
                "  date date,\n" +
                "  amt varchar(255)","id");
        System.out.println(rs);
        Connect_Greenplum.closeConnection();
    }
}
