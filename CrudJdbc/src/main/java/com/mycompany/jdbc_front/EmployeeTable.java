package com.mycompany.jdbc_front;

import com.mycompany.jdbc_front.model.Employee;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.table.AbstractTableModel;

import javax.swing.table.AbstractTableModel;

public class EmployeeTable extends AbstractTableModel {

    private ConnectionDatab con = new ConnectionDatab("jdbc:postgresql://localhost:5432/jdbc_new", "postgres", "222222");
    Connection conn;
    Statement st;

    private int columnCount = 4;
    private List<String[]> dataArrayList;

    public EmployeeTable(List<String[]> list) {
        this.dataArrayList = list;
    }

    public EmployeeTable() {
        dataArrayList = new ArrayList<String[]>();

        for (int i = 1; i < dataArrayList.size(); i++) {
            dataArrayList.add(new String[getColumnCount()]);

        }

    }

    @Override
    public int getRowCount() {
        return dataArrayList.size();

    }

    @Override
    public int getColumnCount() {
        return columnCount;

    }

    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Id";
            case 1:
                return "Имя";
            case 2:
                return "Департамент";
            case 3:
                return "Должность";

        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] rows = dataArrayList.get(rowIndex);

        return rows[columnIndex];

    }

    public Object getValueAtRow(int rowIndex) {
        String[] rows = dataArrayList.get(rowIndex);

        return rows[0];

    }

    public void addDate(String[] row) {
        String[] rowTable = new String[getColumnCount()];
        rowTable = row;
        dataArrayList.add(rowTable);
    }

    public void removeDate(String[] row) {
        String[] rowTable = new String[getColumnCount()];
        rowTable = row;
        dataArrayList.remove(rowTable);
    }

    public void addAllEmployee(ConnectionDatab connect) throws SQLException {
        List<Employee> employee = new ArrayList<>();
        dataArrayList.clear();
        ResultSet result = connect.resultSetQuery("select * from employee");
        try {
            while (result.next()) {

                String[] row = {
                    result.getString("id"),
                    result.getString("name"),
                    result.getString("dept_Id"),
                    result.getString("post_Id")

                };

                addDate(row);
            }
            result.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<String[]> getDataArrayList() {
        return dataArrayList;
    }

    public void setDataArrayList(ArrayList<String[]> dataArrayList) {
        this.dataArrayList = dataArrayList;
    }

    public void dellRow(int id) throws IOException, ClassNotFoundException, SQLException {

        try {

            String sql = "DELETE FROM employee WHERE id='" + id + "'";

            con.init();
            con.sqlQuery(sql);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EmployeeTable.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Employee insertEmployee(Employee employee) throws ClassNotFoundException, SQLException {
        dataArrayList.clear();
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_new", "postgres", "222222");

        try (PreparedStatement statement = conn.prepareStatement(
                "insert into employee(name, dept_id, post_id) values (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        )) {
            statement.setString(1, employee.getName());
            statement.setInt(2, employee.getDeptId());
            statement.setInt(3, employee.getPostId());
            statement.execute();
            try (ResultSet generateKey = statement.getGeneratedKeys()) {
                if (generateKey.next()) {
                    employee.setId(generateKey.getInt(1));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return employee;
    }

    public Employee updateEmployee(Employee employee) throws ClassNotFoundException, SQLException, IOException {
        dataArrayList.clear();
        Class.forName("org.postgresql.Driver");
        try {

            String query = "update employee set name='" + employee.getName() + "',dept_id ='" + employee.getDeptId() + "',"
                    + "post_id ='" + employee.getPostId() + "' where id='" + employee.getId() + "' ";

            con.init();
            con.sqlQuery(query);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EmployeeTable.class.getName()).log(Level.SEVERE, null, ex);
        }

        return employee;

    }
    
    

}
