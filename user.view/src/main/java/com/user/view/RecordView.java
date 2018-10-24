package com.user.view;

import com.user.model.BalanceOperation;
import com.user.service.BeanService;
import com.user.service.appservice.service.UserExpService;
import com.user.service.dbservice.domain.User;
import com.user.service.dbservice.domain.UserExp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class RecordView extends JDialog {
    private JLabel name;
    private JLabel status;
    private User user;

    public RecordView(JFrame p, User user) {
        super(p, true);

        this.user = user;

        init();

        setSize(800, 500);
        setTitle("消费记录);");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(getParent());

    }

    private void init() {
        name = new JLabel();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        name.setText("姓名：" + user.getUserName() + "  , 查询时间： " + sf.format(new Date()));
        this.getContentPane().setLayout(new BorderLayout());

        this.getContentPane().add(name, BorderLayout.NORTH);
        status = new JLabel();
        this.getContentPane().add(status, BorderLayout.SOUTH);

        createTable();
    }

    private void createTable() {
        // final
        final String[] names = {
                "操作日期",
                "操作类型",
                "金额",
                "操作后余额"
        };
        DefaultTableModel dataModel = new DefaultTableModel(names, 0);
        // Create the table
        JTable tableView = new JTable(dataModel) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
//
        tableView.getTableHeader().setReorderingAllowed(false);
        tableView.setRowHeight(25);
        tableView.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane jScrollPane = new JScrollPane(tableView);

        this.getContentPane().add(jScrollPane, BorderLayout.CENTER);

        UserExpService userExpService = BeanService.getService(UserExpService.class);
        List<UserExp> userExps = userExpService.queryByUserID(user.getUserID());

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Vector dataVector = dataModel.getDataVector();
        for (UserExp userExp : userExps) {
            Vector v = new Vector();
            v.add(sf.format(userExp.getExpDate()));
            int op = userExp.getOperation();
            if (BalanceOperation.ADD == op) {
                v.add("充值");
            } else {
                v.add("消费");
            }
            v.add(userExp.getExpValue());
            v.add(userExp.getCurrentBalance());

            dataVector.add(v);
        }

        status.setText("总数：" + dataVector.size());
        dataModel.fireTableDataChanged();
    }

}
