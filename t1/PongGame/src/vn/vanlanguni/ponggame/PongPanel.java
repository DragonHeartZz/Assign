/*
 *
 * PONG GAME REQUIREMENTS
 * This simple "tennis like" game features two paddles and a ball,
 * the goal is to defeat your opponent by being the first one to gain 3 point,
 * a player gets a point once the opponent misses a ball.
 * The game can be played with two human players, one on the left and one on
 * the right. They use keyboard to start/restart game and control the paddles.
 * The ball and two paddles should be red and separating lines should be green.
 * Players score should be blue and background should be black.
 * Keyboard requirements:
 * + P key: start
 * + Space key: restart
 * + W/S key: move paddle up/down
 * + Up/Down key: move paddle up/down
 *
 * Version: 0.5
 */
package vn.vanlanguni.ponggame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;

/**
 * 
 * @author Invisible Man
 * 
 */
public class PongPanel extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
	private static final long serialVersionUID = -1097341635155021546L;
	private boolean showTitleScreen = true;
	private boolean playing;
	private boolean gameOver;
	/** Background. */
	private Color backgroundColor = Color.BLACK;
	/** State on the control keys. */
	private boolean upPressed;
	private boolean downPressed;
	private boolean wPressed;
	private boolean sPressed;
	/** The ball: position, diameter */
	private int ballX = 200;
	private int ballY = 200;
	private int diameter = 25;
	private int ballDeltaX = -1;
	private int ballDeltaY = 3;
	/** Player 1's paddle: position and size */
	private int playerOneX = 0;
	private int playerOneY = 220;
	private int playerOneWidth = 10;
	private int playerOneHeight = 60;
	/** Player 2's paddle: position and size */
	private int playerTwoX = 481;
	private int playerTwoY = 220;
	private int playerTwoWidth = 10;
	private int playerTwoHeight = 60;
	/** Speed of the paddle - How fast the paddle move. */
	private int paddleSpeed = 5;
	/** Player score, show on upper left and right. */
	private int playerOneScore;
	private int playerTwoScore;
	/** Name user 1 and name user 2 */
	String cNameplayer1 = "Player one";
	String cNameplayer2 = "Player two";
	int cball;
	//setting rectangle
	Rectangle rectSetting;
	BufferedImage imgSetting,imgSettingUp;
	private int xSetting = 150, ySetting = 430, wSetting = 200, hSetting = 35;
	/** Construct a PongPanel. */
	public PongPanel() {
		setBackground(backgroundColor);
		// listen to key presses
		setFocusable(true);
		addKeyListener(this);
		// call step() 60 fps
		Timer timer = new Timer(1000 / 60, this);
		timer.start();
		//setting 
		addMouseListener(this);
		addMouseMotionListener(this);
		
		rectSetting = new Rectangle(xSetting,ySetting,wSetting,hSetting);
		try {
			imgSetting = ImageIO.read(new File("images/blue-button-setting.png"));
			imgSettingUp = ImageIO.read(new File("images/pink-button-setting.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}

	/** Implement actionPerformed */
	public void actionPerformed(ActionEvent e) {
		step();
	}

	/** Repeated task */
	public void step() {
		if (playing) {
			/* Playing mode */
			// move player 1
			// Move up if after moving, paddle is not outside the screen
			if (wPressed && playerOneY - paddleSpeed > 0) {
				playerOneY -= paddleSpeed;
			}
			// Move down if after moving paddle is not outside the screen
			if (sPressed
					&& playerOneY + playerOneHeight + paddleSpeed < getHeight()) {
				playerOneY += paddleSpeed;
			}
			// move player 2
			// Move up if after moving paddle is not outside the screen
			if (upPressed && playerTwoY - paddleSpeed > 0) {
				playerTwoY -= paddleSpeed;
			}
			// Move down if after moving paddle is not outside the screen
			if (downPressed
					&& playerTwoY + playerTwoHeight + paddleSpeed < getHeight()) {
				playerTwoY += paddleSpeed;
			}
			/*
			 * where will the ball be after it moves? calculate 4 corners: Left,
			 * Right, Top, Bottom of the ball used to determine whether the ball
			 * was out yet
			 */
			int nextBallLeft = ballX + ballDeltaX;
			int nextBallRight = ballX + diameter + ballDeltaX;
			// FIXME Something not quite right here
			int nextBallTop = ballY + ballDeltaY;
			int nextBallBottom = ballY + diameter + ballDeltaX;
			// Player 1's paddle position
			int playerOneRight = playerOneX + playerOneWidth;
			int playerOneTop = playerOneY;
			int playerOneBottom = playerOneY + playerOneHeight;
			// Player 2's paddle position
			float playerTwoLeft = playerTwoX;
			float playerTwoTop = playerTwoY;
			float playerTwoBottom = playerTwoY + playerTwoHeight;
			// ball bounces off top and bottom of screen
			if (nextBallTop < 0 || nextBallBottom > getHeight()) {
				ballDeltaY *= -1;
				Sound.play("sound/Hit.wav");
			}
			// will the ball go off the left side?
			if (nextBallLeft < playerOneRight) {
				// is it going to miss the paddle?
				if (nextBallTop > playerOneBottom
						|| nextBallBottom < playerOneTop) {
					playerTwoScore++;
					Sound.play("sound/Wall.wav");
					// Player 2 Win, restart the game
					if (playerTwoScore == 3) {
						Sound.play("sound/End.wav");
						playing = false;
						gameOver = true;
					}
					ballX = 240;
					ballY = 240;
				} else {
					// If the ball hitting the paddle, it will bounce back
					// FIXME Something wrong here
					ballDeltaX *= -1;
					Sound.play("sound/Hit.wav");
				}
			}
			// will the ball go off the right side?
			if (nextBallRight > playerTwoLeft) {
				// is it going to miss the paddle?
				if (nextBallTop > playerTwoBottom
						|| nextBallBottom < playerTwoTop) {
					playerOneScore++;
					Sound.play("sound/Wall.wav");
					
					// Player 1 Win, restart the game
					if (playerOneScore == 3) {
						Sound.play("sound/End.wav");
						playing = false;
						gameOver = true;
					}
					ballX = 240;
					ballY = 240;
				} else {
					// If the ball hitting the paddle, it will bounce back
					// FIXME Something wrong here
					ballDeltaX *= -1;
					Sound.play("sound/Hit.wav");
				}
			}
			// move the ball
			ballX += ballDeltaX;
			ballY += ballDeltaY;
		}
		// stuff has moved, tell this JPanel to repaint itself
		repaint();
	}

	/** Paint the game screen. */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (showTitleScreen) {
			/* Show welcome screen */
			// Draw game title and start message
			ImageIcon BG1 = new ImageIcon("images/BGwel.png");
			g.drawImage(BG1.getImage(), 0, 0, 500, 500, null);
			g.setColor(Color.ORANGE);
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 40));
			g.drawString("Pong Game", 140, 70);
			// FIXME Wellcome message below show smaller than game title
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
			g.setColor(Color.red);
			g.drawString("Press 'P' to play", 155, 400);
			if(!showSetting){
				g.drawImage(imgSetting, xSetting, ySetting, xSetting + wSetting, ySetting + hSetting, 0, 0, 600, 230, null);
			}
			else{
				g.drawImage(imgSettingUp, xSetting, ySetting, xSetting + wSetting, ySetting + hSetting, 0, 0, 600, 230, null);
			}
		} else if (playing) {
			/* Game is playing */
			ImageIcon BG2 = new ImageIcon("images/BGplay.png");
			g.drawImage(BG2.getImage(), 0, 0, 500, 500, null);
			// set the coordinate limit
			int playerOneRight = playerOneX + playerOneWidth;
			int playerTwoLeft = playerTwoX;
			// draw dashed line down center
			g.setColor(Color.RED);
			for (int lineY = 0; lineY < getHeight(); lineY += 50) {
				g.drawLine(250, lineY, 250, lineY + 25);
			}
			// draw "goal lines" on each side
			g.drawLine(playerOneRight, 0, playerOneRight, getHeight());
			g.drawLine(playerTwoLeft, 0, playerTwoLeft, getHeight());
			// draw the scores
			g.setColor(Color.CYAN);
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
			g.drawString(cNameplayer1, 80, 50);
			g.drawString(String.valueOf(playerOneScore), 100, 100); // Player 1
			// score
			g.setColor(Color.MAGENTA);
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
			g.drawString(cNameplayer2, 330, 50);
			g.drawString(String.valueOf(playerTwoScore), 350, 100); // Player 2
			// score
			
			

			// draw the ball
			if (cball == 1) {
				ImageIcon imgball = new ImageIcon("./images/Ball1.png");
				g.drawImage(imgball.getImage(), ballX, ballY, diameter, diameter, null);
			} else if (cball == 2) {
				ImageIcon imgball = new ImageIcon("./images/Ball2.png");
				g.drawImage(imgball.getImage(), ballX, ballY, diameter, diameter, null);
			} else if (cball == 3) {
				ImageIcon imgball = new ImageIcon("./images/Ball3.png");
				g.drawImage(imgball.getImage(), ballX, ballY, diameter, diameter, null);
			}else {
				g.setColor(Color.RED);
				g.fillOval(ballX, ballY, 20, 20);
			}
			// draw the paddles.
			ImageIcon paddle1 = new ImageIcon("images/paddles1.png");
			g.drawImage(paddle1.getImage(),playerOneX, playerOneY, playerOneWidth, playerOneHeight,null);
			ImageIcon paddle2 = new ImageIcon("images/paddles2.png");
			g.drawImage(paddle2.getImage(),playerTwoX, playerTwoY, playerTwoWidth, playerTwoHeight,null);
		} else if (gameOver) {
			/* Show End game screen with winner name and score */
			ImageIcon imgball = new ImageIcon("./images/End.gif");
			g.drawImage(imgball.getImage(), 0, 0, 500, 500, null);
			// Draw scores
			g.setColor(Color.RED);
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
			g.drawString(String.valueOf(playerOneScore), 100, 100);
			g.drawString(String.valueOf(playerTwoScore), 400, 100);
			// Draw the winner name
			g.setColor(Color.RED);
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
			if (playerOneScore > playerTwoScore) {
				g.drawString(cNameplayer1 + " win!", 130, 200);
			} else {
				g.drawString(cNameplayer2 + " win!", 130, 200);
			}
			// Draw Restart message
			g.setColor(Color.MAGENTA);
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
			g.drawString("Press 'Spacebar' to restart ", 140, 400);
			// TODO Draw a restart message
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		if (showTitleScreen) {
			if (e.getKeyChar() == 'p') {
				showTitleScreen = false;
				playing = true;
				Sound.play("sound/Start.wav");
			}
		} else if (playing) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				upPressed = true;
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				downPressed = true;
			} else if (e.getKeyCode() == KeyEvent.VK_W) {
				wPressed = true;
			} else if (e.getKeyCode() == KeyEvent.VK_S) {
				sPressed = true;
			}
		} else if (gameOver && e.getKeyCode() == KeyEvent.VK_SPACE) {
			gameOver = false;
			showTitleScreen = true;
			playerOneScore = 0;
			playerTwoScore = 0;
			playerOneY = 250;
			playerTwoY = 250;
			ballX = 250;
			ballY = 250;
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			upPressed = false;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			downPressed = false;
		} else if (e.getKeyCode() == KeyEvent.VK_W) {
			wPressed = false;
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			sPressed = false;
		}
	}

	boolean showSetting = false;

	public void mouseMoved(MouseEvent e) {
		//System.out.println(String.format("%d %d", e.getX(),e.getY()));
		if(rectSetting.contains(e.getX(),e.getY())){
			showSetting = true;
		}
		else{
			showSetting = false;
		}
	}

	public void mouseDragged(MouseEvent e) { }
	JDialogSettings settingsDialog;
	public void mouseClicked(MouseEvent e) { 
		if(showSetting){
			settingsDialog = new JDialogSettings();
			settingsDialog.setModal(true);
			settingsDialog.setVisible(true);
			Setting st = settingsDialog.getSetings();
			cNameplayer1 = st.getUserName1();
			cNameplayer2 = st.getUserName2();
			cball = st.getBallNumber();
			settingsDialog.dispose();
		}
	}

	public void mouseEntered(MouseEvent e) { }

	public void mouseExited(MouseEvent e) { }

	public void mousePressed(MouseEvent e) { }

	public void mouseReleased(MouseEvent e) { }
}
