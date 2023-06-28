package javaProject;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Admin {
	public void adminView() {
		Commons commons = new Commons();
		JFrame frame = (JFrame) commons.Frame();
		
		//-------------ADDUSERS---------------------
		JButton add = new JButton("ADD USERS");
		add.setBounds(150, 250, 300, 100);
		add.setFont(new Font("Rockwell", Font.BOLD, 25));
		frame.add(add);
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddUser user = new AddUser();
				try {
					user.addView();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				frame.dispose();
			}
		});
		//------------------------------------------
		
		//--------------EXIT---------------------------
		JButton exit = new JButton("EXIT");
		exit.setBounds(150, 400, 300, 100);
		exit.setFont(new Font("Rockwell", Font.BOLD, 25));
		frame.add(exit);
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		//---------------------------------------------
		frame.setVisible(true);
	}
}