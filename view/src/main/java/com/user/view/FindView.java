package com.user.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindView extends JDialog {

    private JLabel nameLb;
    private JTextField nameTxt;
    private JButton find;
    private String findname;

    public FindView(JFrame p) {
        super(p, true);

        init();

        initDlgParams();
    }

    private void initDlgParams() {
        this.setTitle("查找");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(240, 80);
        this.setResizable(false);
        this.setLocationRelativeTo(this.getParent());
    }

    private void init() {
        nameLb = new JLabel("姓名：");
        nameTxt = new JTextField();
        nameTxt.setPreferredSize(new Dimension(100, 26));
        nameTxt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finduser();
            }
        });
        find = new JButton("查找");
        find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finduser();
            }
        });

        Container con = this.getContentPane();
        con.setLayout(new FlowLayout(FlowLayout.LEFT));
        con.add(nameLb);
        con.add(nameTxt);
        con.add(find);
    }

    private void finduser() {
        findname = nameTxt.getText();
        dispose();
    }

    public String getNameCondition() {
        return findname;
    }

    public static void main(String[] args) {
        new FindView(new JFrame()).setVisible(true);
    }

}
