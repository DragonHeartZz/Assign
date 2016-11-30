package vn.vanlanguni.ponggame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;




public class JDialogSettings extends JDialog {
	String userName1;
	String userName2;
	String u1;
	String u2;
	public DialogR dialog;
	private static int WIDTH = 400, HEIGHT = 600;
	private JTextField txtUsername1;
	private JTextField txtUsername2;
	public JDialogSettings() {
		setTitle("Config infomation");
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setLocation(380, 150);
		setLayout(null);
		pack();
		//
		userName1 = userName1;
		userName2 = userName2;
		//
		userName1 = u1;
		userName2 = u2;
	
	txtUsername1 = new JTextField(10);
	txtUsername2 = new JTextField(10);
	getContentPane().add(txtUsername1);
	getContentPane().add(txtUsername2);
	txtUsername1.setBounds(90, 26, 100, 20);
	txtUsername2.setBounds(90, 66, 100, 20);
	
	JLabel lblUser_1 = new JLabel("Username 1");
	lblUser_1.setBounds(10, 29, 71, 14);
	getContentPane().add(lblUser_1);
	
	JLabel lblUser_2 = new JLabel("Username 2");
	lblUser_2.setBounds(10, 69, 71, 14);
	getContentPane().add(lblUser_2);
	
	JButton btnSave = new JButton("Save");
	btnSave.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			dialog = DialogR.YES;
			setVisible(false);
		}
	});
	btnSave.setBounds(44, 114, 89, 23);
	getContentPane().add(btnSave);
	
	JButton btnCancel = new JButton("Cancel");
	btnCancel.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dialog = DialogR.CANCEL;
			setVisible(false);
		}
	});
	btnCancel.setBounds(154, 114, 89, 23);
	getContentPane().add(btnCancel);
	setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	pack();
	
	addWindowListener(new WindowAdapter() {

		@Override
		public void windowClosing(WindowEvent arg0) {
			int result = JOptionPane.showConfirmDialog(JDialogSettings.this, "Exit?");
			if(result == JOptionPane.YES_OPTION){
				setVisible(false);
			}				
		}
	});
}

public JDialogSettings getSetings(){
	JDialogSettings st = new JDialogSettings();
	st.setUserName1(txtUsername1.getText());
	st.setUserName2(txtUsername2.getText());		
	return st;
}

	public String getUserName2() {
		return userName2;
	}

	public void setUserName2(String userName2) {
		this.userName2 = userName2;
	}

	public String getUserName1() {
		return userName1;
	}

	public void setUserName1(String uname) {
		userName1 = uname;
	}
}
