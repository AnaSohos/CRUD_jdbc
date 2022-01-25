
package com.mycompany.jdbc_front;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

 
    public class DelButtonAction  {
//         private EmployeeTable btt = new EmployeeTable();
//        
//       private JTable tab = new JTable(btt);
//        
//      
//       
//       
//       public DelButtonAction(List<String[]> list) {
//   EmployeeTable em = new EmployeeTable();
//        }
//
//
// 
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            final Object[] valueAt = new Object[1];
//            //ListSelectionListener - слушатель реагирующий на выбор елемента List
// 
//            ListSelectionModel selectionModel = tab.getSelectionModel(); //Следит за тем , какую строку выделяют в таблице
//            selectionModel.addListSelectionListener(new ListSelectionListener() {
//                //Метод вызывающийся у слушателя при выделении строки
//                @Override
//                public void valueChanged(ListSelectionEvent e) {
// 
//                    if (tab.getSelectedRows().length != 1) //getSelectedRows() - возвращает массив индексов выделенных строк
//                    {
//                        return;
//                    }
//                    int viewRowIndex = tab.getSelectedRows()[0];
//                    int rowIndex = tab.convertRowIndexToModel(viewRowIndex);//Приобразование индексов строк в исходные (если есть сортировка)
//                    valueAt[0] = btt.getValueAt(rowIndex, 0);
//                    SwingUtilities.invokeLater(new Runnable()
//                    {
//                        @Override
//                        public void run() {
//                            // удалить данные
//                            dellRow((int) valueAt[0]);
//                            // перерисовать
//                            btt.fireTableDataChanged();
//                        }
//                    });
// 
//                }
//            });
// 
//        }
        
    }
 
