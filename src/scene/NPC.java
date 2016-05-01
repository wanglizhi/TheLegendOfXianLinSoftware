package scene;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

import tools.Reader;

public class NPC {
	// 位置
	private int x;
	private int y;
	ScenePanel scene;
	Image image;
	// 0表示静止，1表示走动，2表示原地动
	private int type;
	// 口头语
	String oral[];
	String name;
	private ArrayList<Image> images = new ArrayList<Image>();
	// 动画计数，走的长度，走的方向（1左，5右 9下 13上）
	private int count = 0;
	private int length = 0;
	private int temp;
	private int direction = 1;
	private int actionRate;
	private Timer action = new Timer(200, new action());
	private Timer walk = new Timer(200, new walk());

	// 构造函数一，静止的
	public NPC(int x, int y, String fileName, String oral) {
		this.name = fileName.split("\\.")[0];
		type = 0;
		this.x = x * 32;
		this.y = y * 32;
		this.oral = oral.split(";");
		image = Reader.readImage("NPCs//" + fileName);
	}

	// 构造函数二，原地运动的
	public NPC(int x, int y, int imageSize, String fileName, String oral) {
		this.name = fileName;
		type = 2;
		this.x = x * 32;
		this.y = y * 32;
		this.actionRate = imageSize;
		this.oral = oral.split(";");
		;
		for (int i = 1; i <= imageSize; i++) {
			images.add(Reader
					.readImage("NPCs//" + fileName + "//" + i + ".png"));
		}
		action.start();
	}

	// 构造函数三，单向走动的
	public NPC(int x, int y, int direction, int length, int imageSize,
			String fileName, String oral) {
		this.name = fileName;
		type = 1;
		this.x = x * 32;
		this.y = y * 32;
		this.oral = oral.split(";");
		this.direction = direction;
		this.length = length;
		temp = length;
		for (int i = 0; i < imageSize; i++) {
			images.add(Reader.readImage("NPCs//" + fileName + "//"
					+ (i + direction) + ".png"));
		}
		walk.start();

	}// 画出npc

	public void drawNPC(Graphics g, int firstTileX, int firstTileY) {

		if (type == 0) {
			g.drawImage(image, x - firstTileX * 8, y - firstTileY * 8, scene);
		} else if (type == 1) {
			g.drawImage(images.get(count), x - firstTileX * 8, y - firstTileY
					* 8, scene);
		} else if (type == 2) {
			g.drawImage(images.get(count), x - firstTileX * 8, y - firstTileY
					* 8, scene);
		}
	}

	class action implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (count < actionRate - 1) {
				count++;
			} else if (count == actionRate - 1) {
				count = 0;
			}
		}
	}

	class walk implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			switch (direction) {
			case 1:
				if (count < 3) {
					count++;
					x -= 8;
				} else if (count == 3) {
					count = 0;
					x -= 8;
					length--;
				}
				if (length == 0) {
					direction = 5;
					count = 4;
					length = temp;
				}
				break;
			case 5:
				if (count < 7 && count >= 4) {
					count++;
					x += 8;
				} else if (count == 7) {
					count = 4;
					x += 8;
					length--;
				}
				if (length == 0) {
					direction = 1;
					count = 0;
					length = temp;
				}
				break;
			case 9:
				if (count < 3) {
					count++;
					y += 8;
				} else if (count == 3) {
					count = 0;
					y += 8;
					length--;
				}
				if (length == 0) {
					direction = 13;
					count = 4;
					length = temp;
				}
				break;
			case 13:
				if (count < 7 && count >= 4) {
					count++;
					y -= 8;
				} else if (count == 7) {
					count = 4;
					y -= 8;
					length--;
				}
				if (length == 0) {
					direction = 9;
					count = 0;
					length = temp;
				}
				break;
			}
		}
	}

	public int getX() {
		return x / 32;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y / 32;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Timer getAction() {
		return action;
	}

	public void setAction(Timer action) {
		this.action = action;
	}

	public Timer getWalk() {
		return walk;
	}

	public void setWalk(Timer walk) {
		this.walk = walk;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
