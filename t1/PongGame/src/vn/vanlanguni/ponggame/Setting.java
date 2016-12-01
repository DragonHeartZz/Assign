package vn.vanlanguni.ponggame;

import java.awt.Color;

public class Setting {
	private String userName1, userName2;
	private Color backgroundColor, paddleColor, ballColor;
	private int ballNumber;

	public Setting() {
	}

	public Setting(String userName1, String userName2, Color backgroundColor,
			Color paddleColor, Color ballColor) {
		super();
		this.userName1 = userName1;
		this.userName2 = userName2;
		this.backgroundColor = backgroundColor;
		this.paddleColor = paddleColor;
		this.ballColor = ballColor;
	}

	public Setting(String u1, String u2) {
		userName1 = u1;
		userName2 = u2;
	}

	public int getBallNumber() {
		return ballNumber;
	}

	public void setBallNumber(int ballNumber) {
		this.ballNumber = ballNumber;
	}

	public String getUserName2() {
		return userName2;
	}
	public String getUserName1() {
		return userName1;
	}

	public void setUserName1(String uname) {
		userName1 = uname;
	}

	public void setUserName2(String userName2) {
		this.userName2 = userName2;
	}


	

}