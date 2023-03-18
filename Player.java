package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	GamePanel gp;
	KeyHandler keyH;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();
		getPlayerImage();
	}
	//giá trị mặc định cho vị trí và tốc độ player
	public void setDefaultValues() {
		//Cài đặt mặc định vị trí của player
		x = 100;
		y = 100;
		speed = 4;//tốc độ di chuyển 4pixel
		direction = "down";//hướng mặc định: hướng xuống
	}
	
	public void getPlayerImage() {
		try {
			
			up = ImageIO.read(new FileInputStream("res/player/boy_up.png"));
			up1 = ImageIO.read(new FileInputStream("res/player/boy_up_1.png"));
			up2 = ImageIO.read(new FileInputStream("res/player/boy_up_2.png"));
			down = ImageIO.read(new FileInputStream("res/player/boy_down.png"));
			down1 = ImageIO.read(new FileInputStream("res/player/boy_down_1.png"));
			down2 = ImageIO.read(new FileInputStream("res/player/boy_down_2.png"));
			left = ImageIO.read(new FileInputStream("res/player/boy_left.png"));
			left1 = ImageIO.read(new FileInputStream("res/player/boy_left_1.png"));
			left2 = ImageIO.read(new FileInputStream("res/player/boy_left_2.png"));
			right = ImageIO.read(new FileInputStream("res/player/boy_right.png"));
			right1 = ImageIO.read(new FileInputStream("res/player/boy_right_1.png"));
			right2 = ImageIO.read(new FileInputStream("res/player/boy_right_2.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//cập nhật trạng thái của player
	public void update() {
		if(keyH.upPressed == true) {
			direction = "up";
			y -= speed; 
		}
		else if(keyH.downPressed == true) {
			direction = "down";
			y += speed; 
		}
		else if(keyH.leftPressed == true) {
			direction = "left";
			x -= speed; 
		}
		else if(keyH.rightPressed == true) {
			direction = "right";
			x += speed; 
		}
		
		spriteCounter++;
		if(spriteCounter > 5) {//tăng giá trị của spriceNum sau 5 khung hình
			if(spriteNum == 1) spriteNum = 2;
			else spriteNum = 1;
			spriteCounter = 0;
		}
	
	}
	//vẽ player lên màn hình
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		if(keyH.leftPressed || keyH.downPressed || keyH.upPressed || keyH.rightPressed)
			switch (direction) {
				case "up":
					if(spriteNum == 1)
						image = up1;
					else image = up2;
					break;
				case "down":
					if(spriteNum == 1)
						image = down1;
					else image = down2;
					break;
				case "left":
					if(spriteNum == 1)
						image = left1;
					else image = left2;
					break;	
				case "right":
					if(spriteNum == 1)
						image = right1;
					else image = right2;
					break;
			}
		else
			switch(direction) {
				case "up":
					image = up;
					break;
				case "down":
					image = down;
					break;
				case "left":
					image = left;
					break;
				case "right":
					image = right;
					break;
			}
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}
	
}
