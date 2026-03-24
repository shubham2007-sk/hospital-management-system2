package hospital.management.system;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class patient_discharge extends JFrame {

    patient_discharge(){

        JPanel panel=new JPanel();
        panel.setBounds(5,5,790,390);
        panel.setBackground(new Color(90,156,163));
        panel.setLayout(null);
        add(panel);

        JLabel label=new JLabel("CHECK-OUT");
        label.setBounds(100,20,150,20);
        label.setFont(new Font("TAHOMA",Font.BOLD,20));
        label.setForeground(Color.white);
        panel.add(label);


        JLabel label2=new JLabel("CUSTOMER ID");
        label2.setBounds(30,80,150,20);
        label2.setFont(new Font("TAHOMA",Font.BOLD,14));
        label2.setForeground(Color.white);
        panel.add(label2);

        Choice choice=new Choice();
        choice.setBounds(200,80,150,25);
        panel.add(choice);


        try{
            conn c=new conn();
            ResultSet resultSet=c.statement.executeQuery("select * from patient_info");
            while (resultSet.next()){
                choice.add(resultSet.getString("number"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        JLabel label3=new JLabel("ROOM NUMBER");
        label3.setBounds(30,130,150,20);
        label3.setFont(new Font("TAHOMA",Font.BOLD,14));
        label3.setForeground(Color.white);
        panel.add(label3);

        JLabel RNo=new JLabel();
        RNo.setBounds(200,130,150,20);
        RNo.setFont(new Font("TAHOMA",Font.BOLD,14));
        RNo.setForeground(Color.white);
        panel.add(RNo);

        JLabel label4=new JLabel("IN TIME");
        label4.setBounds(30,180,150,20);
        label4.setFont(new Font("TAHOMA",Font.BOLD,10));
        label4.setForeground(Color.white);
        panel.add(label4);

        JLabel INTIME=new JLabel();
        INTIME.setBounds(200,180,250,20);
        INTIME.setFont(new Font("TAHOMA",Font.BOLD,14));
        INTIME.setForeground(Color.white);
        panel.add(INTIME);

        JLabel label5=new JLabel("OUT TIME");
        label5.setBounds(30,230,150,20);
        label5.setFont(new Font("TAHOMA",Font.BOLD,14));
        label5.setForeground(Color.white);
        panel.add(label5);

        Date date=new Date();

        JLabel OUTTIME=new JLabel(""+date);
        OUTTIME.setBounds(200,230,250,20);
        OUTTIME.setFont(new Font("TAHOMA",Font.BOLD,14));
        OUTTIME.setForeground(Color.white);
        panel.add(OUTTIME);



        JButton discharge=new JButton("DISCHARGE");
        discharge.setBounds(30,300,120,30);
        discharge.setBackground(Color.black);
        discharge.setForeground(Color.white);
        panel.add(discharge);

        discharge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conn c=new conn();
                try{
                    c.statement.executeUpdate("delete from patient_info where number='"+choice.getSelectedItem()+"'");
                    c.statement.executeUpdate("update room set Availability='Available' where room_no='"+RNo.getText()+"'");
                    JOptionPane.showMessageDialog(null,"Done");
                    setVisible(false);
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        JButton Check=new JButton("CHECK");
        Check.setBounds(170,300,120,30);
        Check.setBackground(Color.black);
        Check.setForeground(Color.white);
        panel.add(Check);
        Check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conn c =new conn();
                try{
                    ResultSet resultSet=c.statement.executeQuery("select * from patient_info where number='"+choice.getSelectedItem()+"'");

                    while (resultSet.next()){
                        RNo.setText(resultSet.getString("Room_Number"));
                        INTIME.setText(resultSet.getString("Time"));
                    }

                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });



        JButton Back=new JButton("BACK");
        Back.setBounds(300,300,120,30);
        Back.setBackground(Color.black);
        Back.setForeground(Color.white);
        panel.add(Back);
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

            }
        });




        try{
            conn c=new conn();
            ResultSet resultSet=c.statement.executeQuery("select * from patient_info");
            while (resultSet.next()){
                choice.add(resultSet.getString("number"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }







        setUndecorated(true);
        setSize(800,400);
        setLayout(null);
        setLocation(400,250);
        setVisible(true);

    }

    public static void main(String[] args) {
        new patient_discharge();

    }
}
