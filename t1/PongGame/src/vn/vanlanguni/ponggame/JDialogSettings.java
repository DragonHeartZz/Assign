package vn.vanlanguni.ponggame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
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
	private JRadioButton opt1 = new JRadioButton("Ball1"), opt2 = new JRadioButton("Ball2"), opt3 = new JRadioButton("Ball3");
	private JLabel ball = new JLabel("Chose Ball"),   ball1 = new JLabel(""),ball2 = new JLabel(""),ball3 = new JLabel("");
	ButtonGroup btngr = new ButtonGroup();
	ImageIcon Ball1 = new ImageIcon("imgaes/Ball1.png");
	ImageIcon Ball2 = new ImageIcon("imgaes/Ball2.png");
	ImageIcon Ball3 = new ImageIcon("imgaes/Ball3.png");
	public JDialogSettings() {
		setTitle("Config infomation");
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setLocation(380, 150);
		setLayout(null);
		pack();
	txtUsername1 = new JTextField(10);
	txtUsername2 = new JTextField(10);
	getContentPane().add(txtUsername1);
	getContentPane().add(txtUsername2);
	txtUsername1.setBounds(90, 26, 100, 20);
	txtUsername2.setBounds(90, 66, 100, 20);
	
	JLabel lblUser_1 = new JLabel("Player one 1");
	lblUser_1.setBounds(10, 29, 71, 14);
	getContentPane().add(lblUser_1);
	
	JLabel lblUser_2 = new JLabel("Player two 2");
	lblUser_2.setBounds(10, 69, 71, 14);
	getContentPane().add(lblUser_2);
	
	add(ball);
	ball.setBounds(10, 90, 200, 25);
	ball.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
	add(opt1);
	opt1.setBounds(10, 130, 100, 25);
	add(opt2);
	opt2.setBounds(110, 130, 100, 25);
	add(opt3);
	opt3.setBounds(210, 130, 100, 25);
	btngr.add(opt1);
	btngr.add(opt2);
	btngr.add(opt3);
	
	
	JButton btnSave = new JButton("Save");
	btnSave.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			dialog = DialogR.YES;
			setVisible(false);
		}
	});
	btnSave.setBounds(44, 300, 89, 23);
	getContentPane().add(btnSave);
	
	JButton btnCancel = new JButton("Cancel");
	btnCancel.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dialog = DialogR.CANCEL;
			setVisible(false);
		}
	});
	btnCancel.setBounds(154, 300, 89, 23);
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
	public Setting getSetings() {
		Setting st = new Setting();
		st.setUserName1(txtUsername1.getText());
		st.setUserName2(txtUsername2.getText());
		if (opt1.isSelected()) {
			st.setBallNumber(1);
		} else if (opt2.isSelected()) {
			st.setBallNumber(2);
		}else if(opt3.isSelected()){
			st.setBallNumber(3);
		}
		return st;
	}
}