package vn.vanlanguni.ponggame;

import java.awt.Dimension;

import javax.swing.JDialog;

public class JDialogSettings extends JDialog{

	private static int WIDTH = 400, HEIGHT = 600;
	
	public JDialogSettings(){
		setTitle("Config infomation");
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setLocation(380, 150);
		setLayout(null);
		pack();
	}
}
