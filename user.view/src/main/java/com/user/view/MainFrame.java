package com.user.view;

import com.user.service.BeanService;
import com.user.service.appservice.service.UserService;
import com.user.service.dbservice.domain.User;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

public class MainFrame extends JFrame {
    private JTable tableView;
    private DefaultTableModel dataModel;
    private JLabel status;
    private JScrollPane jScrollPane;
    private boolean isLocked = true;

    public MainFrame() {
        super("会员管理");
        init();
    }

    private void init() {
        createMenu();
        createTable();
        createStatus();

        initFrameParams();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                lockSystem();
                getData();
            }
        });

        SwingUtilities.updateComponentTreeUI(this);
    }

    private void initFrameParams() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit()
                .getScreenSize();
        Rectangle bounds = new Rectangle(screenSize);
        Insets insets = Toolkit.getDefaultToolkit()
                .getScreenInsets(this.getGraphicsConfiguration());
        bounds.x += insets.left;
        bounds.y += insets.top;
        bounds.width -= insets.left + insets.right;
        bounds.height -= insets.top + insets.bottom;

        this.setBounds(bounds);
    }

    private void createStatus() {
        status = new JLabel();
        getContentPane().add(status, BorderLayout.SOUTH);
    }


    ////TEST
    private User buildUser() {
        User user = new User();
        user.setUserID(UUID.randomUUID().toString());
        user.setUserName("用户名--" + System.currentTimeMillis());
        user.setRegDate(new Date(System.currentTimeMillis()));
        user.setPhoneNo("" + System.currentTimeMillis());
        user.setBalance(Math.random() * 50);
        user.setComment("月落乌啼霜满天");

        return user;
    }

    private void getData() {
        if (isLocked) {
            return;
        }

        clearTableData();
        //get data
        clearStatus();

        addUsers(getAllUsers());
    }

    private void addUsers(List<User> users) {
        Vector dataVector = dataModel.getDataVector();
        for (User user : users) {
            Vector row = new Vector();
            row.add(user);
            row.add(user.getPhoneNo());
            row.add(user.getRegDate());
            row.add(user.getBalance());
            row.add(user.getComment());

            dataVector.add(row);
        }

        dataModel.fireTableDataChanged();
    }

    private List<User> getAllUsers() {
        UserService userService = BeanService.getService(UserService.class);
        return userService.queryAll();
    }

    private void clearStatus() {
        status.setText("");
    }

    private void clearTableData() {
        tableView.getSelectionModel().clearSelection();
        dataModel.getDataVector().clear();
        dataModel.fireTableDataChanged();
    }

    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu userMenu = new JMenu("用户");
        JMenuItem addUser = new JMenuItem("注册");
        addUser.addActionListener(new CreateAction());
        JMenuItem findUser = new JMenuItem("查找");
        findUser.addActionListener(new FindUserAction());
        JMenuItem delUser = new JMenuItem("删除");
        delUser.addActionListener(new DelUserAction());
        userMenu.add(addUser);
        userMenu.add(findUser);
        userMenu.add(delUser);

        menuBar.add(userMenu);

        JMenu balanceMenu = new JMenu("金额");
        JMenuItem addbalance = new JMenuItem("充值");
        addbalance.addActionListener(new AddBalanceAction());
        JMenuItem debalance = new JMenuItem("消费");
        debalance.addActionListener(new DeBalanceAction());
        balanceMenu.add(addbalance);
        balanceMenu.add(debalance);

        JMenuItem showrecords = new JMenuItem("消费记录");
        showrecords.addActionListener(new ShowRecordsAction());
        balanceMenu.add(showrecords);


        menuBar.add(balanceMenu);


        JMenu opMenu = new JMenu("操作");
        JMenuItem locksys = new JMenuItem("锁定");
        locksys.addActionListener(new LockSystemAction());
        JMenuItem exitsys = new JMenuItem("退出");
        exitsys.addActionListener(new ExitSystemAction());
        opMenu.add(locksys);
        opMenu.add(exitsys);
        menuBar.add(opMenu);

        this.setJMenuBar(menuBar);
    }


    public void createTable() {

        // final
        final String[] names = {
                "姓名",
                "手机号",
                "注册日期",
                "余额",
                "备注"
        };
        dataModel = new DefaultTableModel(names, 0);
        // Create the table
        tableView = new JTable(dataModel) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
//        TableRowSorter sorter = new TableRowSorter(dataModel);
//        tableView.setRowSorter(sorter);
        dataModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                updateStatus();
            }
        });
        tableView.getTableHeader().setReorderingAllowed(false);
        tableView.setRowHeight(25);
        tableView.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jScrollPane = new JScrollPane(tableView);

        this.getContentPane().add(jScrollPane, BorderLayout.CENTER);
    }

    private void updateStatus() {
        status.setText("总数：" + dataModel.getRowCount());
    }


    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        new MainFrame().setVisible(true);
    }


    private class CreateAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            CreateUserView cv = new CreateUserView(MainFrame.this);
            cv.setVisible(true);
            if (cv.getResult()) {
                getData();
            }

        }
    }

    private class FindUserAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            findUser();
        }
    }

    private void findUser() {
        FindView findView = new FindView(this);
        findView.setVisible(true);

        String namecondition = findView.getNameCondition();
        if (namecondition == null || namecondition.isEmpty()) {
            getData();
        } else {

            UserService userService = BeanService.getService(UserService.class);
            List<User> users = userService.queryByName(namecondition);

            clearTableData();
            //get data
            clearStatus();
            addUsers(users);
        }
    }

    private class AddBalanceAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = tableView.getSelectedRow();
            if (-1 == selectedRow) {
                JOptionPane.showMessageDialog(MainFrame.this, "请选择要充值的用户！", "提示"
                        , JOptionPane.WARNING_MESSAGE);

                return;
            }

            User user = (User) tableView.getValueAt(selectedRow, 0);
            AddBalanceView addBalanceView = new AddBalanceView(MainFrame.this, user);

            addBalanceView.setVisible(true);

            getData();
            tableView.getSelectionModel().addSelectionInterval(selectedRow, selectedRow);
        }
    }

    private class DeBalanceAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = tableView.getSelectedRow();
            if (-1 == selectedRow) {
                JOptionPane.showMessageDialog(MainFrame.this, "请选择要操作的用户！", "提示"
                        , JOptionPane.WARNING_MESSAGE);

                return;
            }

            User user = (User) tableView.getValueAt(selectedRow, 0);
            DeBalanceView deBalanceView = new DeBalanceView(MainFrame.this, user);

            deBalanceView.setVisible(true);

            getData();
            tableView.getSelectionModel().addSelectionInterval(selectedRow, selectedRow);

        }
    }

    private class LockSystemAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            lockSystem();
            getData();
        }
    }

    private void lockSystem() {
        String lock = System.getProperty("lock");
        if (null == lock) {
            isLocked = false;
            return;
        }
        isLocked = true;
        clearTableData();

        clearStatus();

        LockView lockView = new LockView(this);
        lockView.setVisible(true);
        if (lockView.getLoginresult()) {
            isLocked = false;
        }

        //TODO refresh table data

    }

    private class ExitSystemAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private class ShowRecordsAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = tableView.getSelectedRow();
            if (-1 == selectedRow) {
                JOptionPane.showMessageDialog(MainFrame.this, "请选择要操作的用户！", "提示"
                        , JOptionPane.WARNING_MESSAGE);

                return;
            }

            User user = (User) tableView.getValueAt(selectedRow, 0);
            new RecordView(MainFrame.this, user).setVisible(true);
        }
    }

    private class DelUserAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = tableView.getSelectedRow();
            if (-1 == selectedRow) {
                JOptionPane.showMessageDialog(MainFrame.this, "请选择要删除的用户！", "提示"
                        , JOptionPane.WARNING_MESSAGE);

                return;
            }

            User user = (User) tableView.getValueAt(selectedRow, 0);

            UserService userService = BeanService.getService(UserService.class);
            userService.deleteUser(user.getUserID());
            getData();
        }
    }
}
