package hospital.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class searchroom extends JFrame {
    Choice choice;
    JTable table;

    searchroom(){

        JPanel panel=new JPanel();
        panel.setBounds(5,5,690,490);
        panel.setBackground(new Color(90,156,163));
        panel.setLayout(null);
        add (panel);

        JLabel FOR=new JLabel("SEARCH FOR ROOM");
        FOR.setBounds(250,11,400,31);
        FOR.setForeground(Color.white);
        FOR.setFont(new Font("TAHOMA",Font.BOLD,20));
        panel.add(FOR);

        JLabel status=new JLabel("STATUS :");
        status.setBounds(70,70,80,20);
        status.setForeground(Color.white);
        status.setFont(new Font("TAHOMA",Font.BOLD,14));
        panel.add(status);

        choice =new Choice();
        choice.setBounds(170,70,120,20);
        choice.add("Available");
        choice.add("Occupied");
        panel.add(choice);

        table=new JTable();
        table.setBounds(0,187,700,210);
        table.setBackground(new Color(90,156,163));
        table.setForeground(Color.white);
        panel.add(table);

        try {
            conn c=new conn();
            String q="select * from room";
            ResultSet resultSet=c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        }catch (Exception e){
            e.printStackTrace();
        }

        JLabel roomno=new JLabel("ROOM NUMBER");
        roomno.setBounds(23,162,150,20);
        roomno.setForeground(Color.white);
        roomno.setFont(new Font("TAHOMA",Font.BOLD,14));
        panel.add(roomno);

        JLabel availabil=new JLabel("AVAILABILITY");
        availabil.setBounds(175,162,150,20);
        availabil.setForeground(Color.white);
        availabil.setFont(new Font("TAHOMA",Font.BOLD,14));
        panel.add(availabil);

        JLabel price=new JLabel("PRICE");
        price.setBounds(410,162,150,20);
        price.setForeground(Color.white);
        price.setFont(new Font("TAHOMA",Font.BOLD,14));
        panel.add(price);

        JLabel bed=new JLabel("BED TYPE");
        bed.setBounds(560,162,150,20);
        bed.setForeground(Color.white);
        bed.setFont(new Font("TAHOMA",Font.BOLD,14));
        panel.add(bed);

        JButton search=new JButton("SEARCH");
        search.setBounds(200,420,120,25);
        search.setBackground(Color.black);
        search.setForeground(Color.white);
        panel.add(search);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String q="select * from Room where Availability='"+choice.getSelectedItem()+"'";
                try {
                    conn c=new conn();
                    ResultSet resultSet=c.statement.executeQuery(q);
                    table.setModel(DbUtils.resultSetToTableModel(resultSet));

                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        JButton back=new JButton("BACK");
        back.setBounds(380,420,120,25);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });






        setUndecorated(true);
        setSize(700,500);
        setLayout(null);
        setLocation(450,250);
        setVisible(true);
    }


    static void main(String[] args) {
        new searchroom();
    }
}
