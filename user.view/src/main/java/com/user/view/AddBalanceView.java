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

public class AddBalanceView extends JDialog {

    private JLabel nameLb;
    private JTextField nameTxt;
    private JButton okBt;
    private String findname;
    private User user;

    public AddBalanceView(JFrame p, User user) {
        super(p, true);

        this.user = user;

        init();

        initDlgParams();
    }

    private void initDlgParams() {
        this.setTitle("充值");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(240, 80);
        this.setResizable(false);
        this.setLocationRelativeTo(this.getParent());
    }

    private void init() {
        nameLb = new JLabel("金额：");
        nameTxt = new JTextField();
        nameTxt.setPreferredSize(new Dimension(100, 26));

        okBt = new JButton("确定");
        okBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBalanceAction();
            }
        });

        Container con = this.getContentPane();
        con.setLayout(new FlowLayout(FlowLayout.LEFT));
        con.add(nameLb);
        con.add(nameTxt);
        con.add(okBt);
    }

    private void addBalanceAction() {
        String newb = nameTxt.getText();
        try {
            double value = Double.parseDouble(newb);

            if (value <= 0.0) {
                JOptionPane.showMessageDialog(this, "充值金额错误！", "提示"
                        , JOptionPane.WARNING_MESSAGE);
                return;
            } else {
                double oldbalance = user.getBalance();
                user.setBalance(oldbalance + value);

                UserService userService = BeanService.getService(UserService.class);
                try {
                    userService.updateBalance(user.getUserID(), oldbalance + value);

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
                    }

                    JOptionPane.showMessageDialog(this, "充值成功，充值后余额为：" + user.getBalance(), "提示"
                            , JOptionPane.WARNING_MESSAGE);

                    dispose();
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(this, "充值失败！", "提示"
                            , JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "充值金额错误！", "提示"
                    , JOptionPane.WARNING_MESSAGE);
            return;
        }


    }

    public String getNameCondition() {
        return findname;
    }

}
