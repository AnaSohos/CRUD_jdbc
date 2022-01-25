package com.mycompany.jdbc_front;

import com.mycompany.jdbc_front.model.Position;
import com.mycompany.jdbc_front.model.Department;
import com.mycompany.jdbc_front.model.Employee;
import com.mycompany.jdbc_front.service.PsqlStore;
import com.mycompany.jdbc_front.service.Store;
import com.mycompany.jdbc_front.EmployeeTable;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import static java.awt.GridBagConstraints.CENTER;
import static java.awt.GridBagConstraints.NONE;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Main {

    public static void main(String[] args) throws SQLException,
            ClassNotFoundException,
            IOException {
//        // устанавливаем драйвер БД
//        Class.forName(cfg().getProperty("jdbc.driver"));
//
//        //получаем настройки подключения
        String url = cfg().getProperty("jdbc.url");
        String login = cfg().getProperty("jdbc.username");
       String password = cfg().getProperty("jdbc.password");
//
//        //создаем подключения к БД согласно нашим настройкам
//        Connection connection = DriverManager.getConnection(url, login, password);
//        //создаем объек который реализует интерфейс Store, для работы с данными из
//        // java кода в sql
    
        ConnectionDatab connect = new ConnectionDatab(url, login, password);
        connect.setNameDataBasses("jdbc_new");
        connect.initProperties();
        connect.init();


        JFrame frame = new JFrame("Сотрудники Зоопарка Ушастик");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
       
        EmpPanel empPanel = new EmpPanel(connect);

        frame.add(empPanel, BorderLayout.CENTER);

        frame.setVisible(true);
        frame.pack();

        //создаем департанет и сохранияем его в таблицу
        PsqlStore store = new PsqlStore();
        Department it = Department.of("It", "zai@mail.ru", "778778");
        Department Department_of_Construction = Department.of("Department of Construction", "zaiss@mail.ru", "7786778");
        Department Administrative_production = Department.of("Administrative production", "zaiysh@mail.ru", "565756778");
        // store.insertDepartment(it);
        //store.insertDepartment(Department_of_Construction);
        //store.insertDepartment(Administrative_production);
        Department department = store.findByNameDepartment("Accounting");
        Position director = Position.of("director", 1500000);
        //store.insertPosition(director);
        Position programmer = Position.of("programmer", 1500000);
        // store.insertPosition(programmer);
        Position manager = Position.of("manager", 70000);

        //   store.insertPosition(manager);
        //поиск департамента
        //          Department department = store.findByNameDepartment("IT");
        //сохранения нового сотрудника с привязкой к конкретному департаменту
//        store.insertEmployee(Employee.of("Vita", it.getId(), programmer.getId()));
//store.insertEmployee(Employee.of("Vera",Department_of_Construction.getId(), programmer.getId()));
//store.insertEmployee(Employee.of("Sasha", Administrative_production.getId(), director.getId()));
//store.insertEmployee(Employee.of("Elena", it.getId(), programmer.getId()));
// store.insertEmployee(Employee.of("Aleftina",Department_of_Construction.getId(), programmer.getId()));
//   store.insertEmployee(Employee.of("Nina", Administrative_production.getId(), director.getId()));
    }
    //метод cfg который будет собирать настройки подключения к БД в объект
    // Properties.

    public static Properties cfg() throws IOException {
        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream("src/main/java/resource/cfg.properties");
            property.load(fis);

        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }

        return property;
    }
}
