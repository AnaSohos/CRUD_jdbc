package com.mycompany.jdbc_front;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.mycompany.jdbc_front.EmployeeTable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mycompany.jdbc_front.ConnectionDatab;
import com.mycompany.jdbc_front.model.Employee;
import com.mycompany.jdbc_front.service.PsqlStore;
import com.mycompany.jdbc_front.service.Store;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Properties;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EmpPanel extends JPanel implements Runnable {

    private ConnectionDatab connects;

    EmployeeTable btt = new EmployeeTable();
    JTable tab = new JTable(btt);

    private JButton addButton = new JButton("Добавить");

    private JButton updateButton = new JButton("Заменить");
    private JButton deleteButton = new JButton("Удалить");

    JLabel namelabel = new JLabel("Имя: ", JLabel.LEFT);
    JLabel depLabel = new JLabel("Департамент: ", JLabel.LEFT);
    JLabel posLabel = new JLabel("Должность: ", JLabel.LEFT);
    final JTextField nameText = new JTextField(10);
    final JTextField depLabelText = new JTextField(10);
    final JTextField posLabelText = new JTextField(10);

    public EmpPanel(ConnectionDatab connect) {

        this.connects = connect;
        new Thread(this).start();
        JScrollPane emloeeScroll = new JScrollPane(tab);
        emloeeScroll.setPreferredSize(new Dimension(500, 500));
        setLayout(new java.awt.GridBagLayout());
        
        

        add(emloeeScroll, new GridBagConstraints(0, 0, 4, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

        add(addButton, new GridBagConstraints(2, 4, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));

        add(updateButton, new GridBagConstraints(1, 4, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));

        add(deleteButton, new GridBagConstraints(3, 4, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));

        add(namelabel, new GridBagConstraints(1, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 15, 0, 15), 0, 0));

        add(nameText, new GridBagConstraints(1, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 15, 5, 15), 0, 0));

        add(depLabel, new GridBagConstraints(2, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 15, 0, 15), 0, 0));

        add(depLabelText, new GridBagConstraints(2, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 15, 5, 15), 0, 0));
        add(posLabel, new GridBagConstraints(3, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 15, 0, 15), 0, 0));

        add(posLabelText, new GridBagConstraints(3, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 15, 5, 15), 0, 0));

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int column = 0;
                    int row = tab.getSelectedRow();
                    String value = tab.getModel().getValueAt(row, column).toString();
                    Integer ids = Integer.valueOf(value);
                    btt.dellRow(ids);

                    tab.repaint();
                    btt.addAllEmployee(connects);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(EmpPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(EmpPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(EmpPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Object source = e.getSource();

                if (source == addButton) {
                    Employee empls = new Employee();
                    empls.setName(nameText.getText());
                    empls.setDeptId(Integer.valueOf(depLabelText.getText()));
                    empls.setPostId(Integer.valueOf(posLabelText.getText()));

                    try {
                        btt.insertEmployee(empls);
                        tab.repaint();
                        btt.addAllEmployee(connects);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(EmpPanel.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(EmpPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }

        });
        updateButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Object source = e.getSource();

                if (source == updateButton) {
                    int column = 0;
                    int row = tab.getSelectedRow();
                    String value = tab.getModel().getValueAt(row, column).toString();
                    Integer ids = Integer.valueOf(value);

                    Employee empls = new Employee();
                    empls.setName(nameText.getText());
                    empls.setDeptId(Integer.valueOf(depLabelText.getText()));
                    empls.setPostId(Integer.valueOf(posLabelText.getText()));
                    empls.setId(ids);

                    try {
                        btt.updateEmployee(empls);
                        tab.repaint();
                        btt.addAllEmployee(connects);

                    } catch (SQLException ex) {
                        Logger.getLogger(EmpPanel.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(EmpPanel.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(EmpPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }

        });

    }

    @Override
    public void run() {
        try {
            btt.addAllEmployee(connects);

            repaint();
            Thread.sleep(100);

        } catch (ArrayIndexOutOfBoundsException e) {

        } catch (InterruptedException ex) {
            Logger.getLogger(EmpPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EmpPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
