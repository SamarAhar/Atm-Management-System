package javaProject;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddUser {
	
	JTextField pinField, atmField;
	Random random = new Random();
	
	public void addView() throws SQLException {
		Commons commons = new Commons();
		JFrame frame = (JFrame) commons.Frame();
		Font txt = new Font("", Font.BOLD, 20);
		SQLManage manage = new SQLManage();
		Success success = new Success();
		
		//--------------NAME--------------------
		JLabel name = new JLabel("Name : ");
		name.setBounds(50, 200, 100, 25);
		name.setFont(txt);
		JTextField nmField = new JTextField();
		nmField.setBounds(50, 230, 500, 30);
		frame.add(nmField);
		frame.add(name);
		//--------------------------------------
		
		//-------------ATMNUMBER------------------
		JLabel atmno = new JLabel("ATM Card Number : ");
		atmno.setBounds(50, 300, 500, 25);
		atmno.setFont(txt);
		atmField = new JTextField();
		atmField.setBounds(50, 330, 500, 30);
		atmField.setEditable(false);
		frame.add(atmField);
		frame.add(atmno);
		//----------------------------------------
		
		//-------------ATMPIN------------------
		JLabel atmpin = new JLabel("ATM Card PIN : ");
		atmpin.setBounds(50, 400, 500, 25);
		atmpin.setFont(txt);
		pinField = new JTextField();
		pinField.setBounds(50, 430, 200, 30);
		pinField.setEditable(false);
		frame.add(pinField);
		frame.add(atmpin);
		//----------------------------------------
		
		//-------------BALANCE------------------
		JLabel bal = new JLabel("BALANCE : ");
		bal.setBounds(350, 400, 500, 25);
		bal.setFont(txt);
		JTextField balField = new JTextField();
		balField.setBounds(350, 430, 200, 30);
		frame.add(balField);
		frame.add(bal);
		//----------------------------------------
		
		//--------------AUTOGENERATION----------------
		auto();
		//---------------------------------------------
		
		//---------------SUBMIT-------------------
		JButton sbmt = new JButton("SUBMIT");
		sbmt.setBounds(200, 500, 200, 50);
		frame.add(sbmt);
		sbmt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!nmField.getText().equals("")) {
					if(balField.getText().equals(""))
						balField.setText("0");
					try {
						manage.adding(atmField.getText(), pinField.getText(), nmField.getText(), balField.getText());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					success.detailView(atmField.getText(), pinField.getText());
					balField.setText("");
					nmField.setText("");
					auto();
				}
			}
		});
		//------------------------------------------
		
		frame.setVisible(true);
	}
	
	public void auto() {
		String str = "";
		for(int i=0; i<16; i++) {
			str += random.nextInt(9 - 0 + 1) + 0;
		}
		atmField.setText(str);
		str = "";
		for(int i=0; i<4; i++) {
			str += random.nextInt(9 - 0 + 1) + 0;
		}
		pinField.setText(str);
	}
}