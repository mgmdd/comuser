package com.user.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class LockView extends JDialog {

    private JLabel passLb;
    private JPasswordField passtxt;
    private JButton login;
    private boolean loginresult;

    public LockView(JFrame p) {
        super(p, true);

        init();

        initDlgParams();
    }

    private void initDlgParams() {
        this.setTitle("登录");
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setSize(240, 80);
        this.setResizable(false);
        this.setLocationRelativeTo(this.getParent());
        passtxt.requestFocus();
        repaint();
    }

    private void init() {
        passLb = new JLabel("密码：");
        passtxt = new JPasswordField();
        passtxt.setPreferredSize(new Dimension(100, 26));
        passtxt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        login = new JButton("登录");
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        Container con = this.getContentPane();
        con.setLayout(new FlowLayout(FlowLayout.LEFT));
        con.add(passLb);
        con.add(passtxt);
        con.add(login);
    }

    private void login() {
        char[] pass = new char[6];
        for (int i = 0; i < 6; i++) {
            pass[i] = (char) (49 + i);
        }

        char[] password = passtxt.getPassword();
        boolean canlogin = Arrays.equals(pass, password);
        if (canlogin) {
            loginresult = true;
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "密码错误！", "提示"
                    , JOptionPane.WARNING_MESSAGE);
        }
    }

    public boolean getLoginresult() {
        return loginresult;
    }

    public static void main(String[] args) {
        new LockView(new JFrame()).setVisible(true);
    }

}
