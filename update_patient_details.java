package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Time;

public class update_patient_details extends JFrame {


    update_patient_details(){



        JPanel panel=new JPanel();
        panel.setBounds(5,5,940,490);
        panel.setBackground(new Color(90,156,163));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon=new ImageIcon(ClassLoader.getSystemResource("icon/update.jpeg"));
        Image image=imageIcon.getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT) ;
        ImageIcon imageIcon1=new ImageIcon(image);
        JLabel label=new JLabel(imageIcon1);
        label.setBounds(500,60,300,300);
        panel.add(label);


        JLabel label1=new JLabel("UPDATE PATIENT DETAILS");
        label1.setBounds(124,11,360,25);
        label1.setFont(new Font("TAHOMA",Font.BOLD,20));
        label1.setForeground(Color.white);
        panel.add(label1);

        JLabel label2=new JLabel("NAME :");
        label2.setBounds(25,88,100,14);
        label2.setFont(new Font("TAHOMA",Font.BOLD,14));
        label2.setForeground(Color.white);
        panel.add(label2);

        Choice choice=new Choice();
        choice.setBounds(248,85,140,25);
        panel.add(choice);

        try{

            conn c=new conn();
            ResultSet resultSet=c.statement.executeQuery("select * from patient_info");
            while (resultSet.next()){
                choice.add(resultSet.getString("Name"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        JLabel label3=new JLabel("ROOM NUMBER :");
        label3.setBounds(25,129,150,14);
        label3.setFont(new Font("TAHOMA",Font.BOLD,14));
        label3.setForeground(Color.white);
        panel.add(label3);

        JTextField textFieldR=new JTextField();
        textFieldR.setBounds(248,129,140,20);
        panel.add(textFieldR);

        JLabel label4=new JLabel("IN TIME :");
        label4.setBounds(25,174,100,14);
        label4.setFont(new Font("TAHOMA",Font.BOLD,14));
        label4.setForeground(Color.white);
        panel.add(label4);

        JTextField textFieldINTIME=new JTextField();
        textFieldINTIME.setBounds(248,174,140,20);
        panel.add(textFieldINTIME);

        JLabel label5=new JLabel("AMOUNT PAID(RS) :");
        label5.setBounds(25,216,150,14);
        label5.setFont(new Font("TAHOMA",Font.BOLD,14));
        label5.setForeground(Color.white);
        panel.add(label5);

        JTextField textFieldAMOUNT=new JTextField();
        textFieldAMOUNT.setBounds(248,216,140,20);
        panel.add(textFieldAMOUNT);


        JLabel label6=new JLabel("PENDING AMOUNT(RS) :");
        label6.setBounds(25,261,180,14);
        label6.setFont(new Font("TAHOMA",Font.BOLD,14));
        label6.setForeground(Color.white);
        panel.add(label6);

        JTextField textFieldPENDING=new JTextField();
        textFieldPENDING.setBounds(248,261,140,20);
        panel.add(textFieldPENDING);

        JButton Check=new JButton("CHECK");
        Check.setBounds(281,378,89,23);
        Check.setBackground(Color.black);
        Check.setForeground(Color.white);
        panel.add(Check);
        Check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id=choice.getSelectedItem();
                String q="select * from patient_info where name='"+id+"'";
                try {
                    conn c=new conn();
                    ResultSet resultSet=c.statement.executeQuery(q);
                    while (resultSet.next()){
                        textFieldR.setText(resultSet.getString("Room_Number"));
                        textFieldINTIME.setText(resultSet.getString("Time"));
                        textFieldAMOUNT.setText(resultSet.getString("Deposit"));
                    }

                    ResultSet resultSet1=c.statement.executeQuery("select *from room where room_no='"+textFieldR.getText()+"'");
                    while (resultSet1.next()){
                        String price=resultSet1.getString("PRICE");
                        int AMOUNTPAID=Integer.parseInt(price)-Integer.parseInt(textFieldAMOUNT.getText());
                        textFieldPENDING.setText(""+AMOUNTPAID);
                    }


                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });



        JButton update=new JButton("UPDATE");
        update.setBounds(56,378,89,23);
        update.setBackground(Color.black);
        update.setForeground(Color.white);
        panel.add(update);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    conn c=new conn();
                    String q=choice.getSelectedItem();
                    String room=textFieldR.getText();
                    String time=textFieldINTIME.getText();
                    String amount=textFieldAMOUNT.getText();
                    c.statement.executeUpdate("update patient_info set Room_Number='"+room+"',Time='"+ time+"',Deposit='"+amount+"' where name='"+q+"'");
                    JOptionPane.showMessageDialog(null,"UPDATE SUCCESSFULLY");
                    setVisible(false);

                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        JButton back=new JButton("BACK");
        back.setBounds(168,378,89,23);
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
        setSize(950,500);
        setLayout(null);
        setLocation(400,250);
        setVisible(true);
    }


    public static void main(String[] args) {
        new update_patient_details();

    }
}
