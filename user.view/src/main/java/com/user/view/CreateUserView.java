package com.user.view;

import com.user.model.BalanceOperation;
import com.user.service.BeanService;
import com.user.service.appservice.service.UserExpService;
import com.user.service.appservice.service.UserService;
import com.user.service.dbservice.domain.User;
import com.user.service.dbservice.domain.UserExp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.UUID;

public class CreateUserView extends JDialog {
    private JLabel nameLb;
    private JTextField nameTxt;
    private JLabel phoneLb;
    private JTextField phoneTxt;
    private JLabel commentLb;
    private JTextField commentTxt;
    private JLabel balanceLb;
    private JTextField balanceTxt;
    private JButton okbtn;
    private boolean result = false;

    public CreateUserView(JFrame p) {
        super(p, true);

        init();
    }

    private void init() {
        nameLb = new JLabel("姓名");
        nameTxt = new JTextField();
        nameTxt.setPreferredSize(new Dimension(100, 26));

        phoneLb = new JLabel("手机号：");
        phoneTxt = new JTextField();
        phoneTxt.setPreferredSize(new Dimension(100, 26));

        commentLb = new JLabel("备注：");
        commentTxt = new JTextField();
        commentTxt.setPreferredSize(new Dimension(100, 26));

        balanceLb = new JLabel("充值金额：");
        balanceTxt = new JTextField();
        balanceTxt.setPreferredSize(new Dimension(100, 26));

        okbtn = new JButton("确定");
        okbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createUser();
            }
        });

        Container con = getContentPane();

        con.setLayout(new GridLayout(5, 2));
        con.add(nameLb);
        con.add(nameTxt);
        con.add(phoneLb);
        con.add(phoneTxt);
        con.add(commentLb);
        con.add(commentTxt);
        con.add(balanceLb);
        con.add(balanceTxt);

        con.add(new JLabel());
        con.add(okbtn);

        pack();

        this.setResizable(false);
        this.setTitle("注册");
        this.setLocationRelativeTo(getParent());
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void createUser() {
        String name = nameTxt.getText();
        if (name == null || name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "用户名不能为空！", "提示"
                    , JOptionPane.WARNING_MESSAGE);
            return;
        }

        String phoneno = phoneTxt.getText();
        phoneno = null == phoneno ? "" : phoneno;

        if (!phoneno.isEmpty()) {

            try {
                Long.parseLong(phoneno);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "手机号格式错误！", "提示"
                        , JOptionPane.WARNING_MESSAGE);
                return;
            }
        }


        String comment = commentTxt.getText();
        String balance = balanceTxt.getText();
        if (balance == null || balance.isEmpty()) {
            balance = "0.0";
        }
        double value = 0.0;
        try {
            value = Double.parseDouble(balance);

            if (value < 0.0) {
                JOptionPane.showMessageDialog(this, "充值金额错误！", "提示"
                        , JOptionPane.WARNING_MESSAGE);
                return;
            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "充值金额错误！", "提示"
                    , JOptionPane.WARNING_MESSAGE);
            return;
        }


        User user = new User();
        user.setUserID(UUID.randomUUID().toString());
        user.setUserName(name);
        user.setBalance(value);
        user.setPhoneNo(phoneno);
        user.setRegDate(new Date(System.currentTimeMillis()));
        user.setComment(comment);

        UserService userService = BeanService.getService(UserService.class);

        try {
            userService.createUser(user);

            //记录
            UserExp exp = new UserExp();
            exp.setCurrentBalance(user.getBalance());
            exp.setExpDate(new Date(System.currentTimeMillis()));
            exp.setExpID(UUID.randomUUID().toString());
            exp.setExpValue(value);
            exp.setUserID(user.getUserID());
            exp.setOperation(BalanceOperation.ADD);

            UserExpService userExpService = BeanService.getService(UserExpService.class);
            try {
                userExpService.create(exp);
            } catch (Exception ee) {
                ee.printStackTrace();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "提示"
                    , JOptionPane.WARNING_MESSAGE);
            return;
        }
        result = true;
        dispose();
    }

    public boolean getResult() {
        return result;
    }

    public static void main(String[] args) {
        new CreateUserView(new JFrame()).setVisible(true);
    }
}
