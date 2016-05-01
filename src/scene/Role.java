package scene;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import tools.Reader;

public class Role {
	public static final int LEFT = 16;
	public static final int RIGHT = 24;
	public static final int UP = 8;
	public static final int DOWN = 0;
	// 在大地图上的位置
	private int x, y;
	// 判断是否移动
	public boolean isMove = false;
	// 计步器
	// 每进行一次行走 计步器+7
	public int stepNum = 0;
	// 控制角色行走图变换的变量
	private int count = 0;
	private int count2 = 0;
	boolean nextStep;
	// 移动方向
	private int direction;
	private Image image;
	private ScenePanel scene;
	private ArrayList<Image> walkImages;
	private ArrayList<Image> runImages;
	private int event;
	private boolean isRun;
	private boolean runOver = false;
	private boolean canStop;
	Timer walk = new Timer(80, new walk());
	Timer run = new Timer(80, new run());

	public Role(int x, int y, ScenePanel scene) {
		this.x = x;
		this.y = y;
		this.scene = scene;
		walkImages = new ArrayList<Image>();
		runImages = new ArrayList<Image>();
		for (int i = 0; i < 32; i++) {
			walkImages.add(Reader.readImage("roles//zhangxiaofan//" + i
					+ ".png"));
		}
		for (int i = 1; i <= 16; i++) {
			runImages.add(Reader.readImage("roles//zhangxiaofanRun//" + i
					+ ".png"));
		}
	}

	public void drawHero(Graphics g, int offsetX, int offsetY) {
		if (isRun) {
			g.drawImage(runImages.get(direction / 2 + count2), x + offsetX - 5,
					y + offsetY - 32, x + offsetX + 37, y + offsetY + 32, 0, 0,
					42, 64, scene);
		} else {
			g.drawImage(walkImages.get(direction + count), x + offsetX, y
					+ offsetY - 32, x + offsetX + 32, y + offsetY + 32, 0, 0,
					32, 64, scene);
		}
	}

	// 移动方法
	public void move(int event) {
		switch (event) {
		case LEFT:
			// 依次判定事件
			if (scene.roleEvent.isAllow((int) Math.ceil(x / 32.0) - 1,
					(int) Math.ceil(y / 32.0))) {
				if (isRun) {
					x -= 16;
				} else {
					x -= 8;
				}
			}
			direction = LEFT;
			break;
		case RIGHT:
			if (scene.roleEvent.isAllow((int) Math.floor(x / 32.0) + 1,
					(int) Math.ceil(y / 32.0))) {
				if (isRun) {
					x += 16;
				} else {
					x += 8;
				}
			}
			direction = RIGHT;
			break;
		case UP:
			if (scene.roleEvent.isAllow((int) Math.ceil(x / 32.0),
					(int) Math.ceil(y / 32.0) - 1)) {
				if (isRun) {
					y -= 16;
				} else {
					y -= 8;
				}
			}
			direction = UP;
			break;
		case DOWN:
			if (scene.roleEvent.isAllow((int) Math.ceil(x / 32.0),
					(int) Math.floor(y / 32.0) + 1)) {
				if (isRun) {
					y += 16;
				} else {
					y += 8;
				}

			}
			direction = DOWN;
			break;
		default:
			break;
		}
	}

	class walk implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (nextStep) {
				if (count < 3) {
					move(event);
					count++;
				} else {
					count = 4;
					move(event);
					nextStep = !nextStep;
					if (canStop) {
						x = 32 * (x / 32);
						y = 32 * (y / 32);
						canStop = false;
						walk.stop();
					}
				}
			} else {
				if (count < 7) {
					count++;
					move(event);
				} else {
					count = 0;
					move(event);
					nextStep = !nextStep;
					if (canStop) {
						x = 32 * (x / 32);
						y = 32 * (y / 32);
						canStop = false;
						walk.stop();
					}
				}
			}
		}
	}

	class run implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (count2 < 3) {
				count2++;
				move(event);
			} else {
				count2 = 0;
				move(event);
				if (canStop) {
					if (direction == 0 || direction == 24) {
						x = (int) (32 * (Math.floor(x / 32.0)));
						y = (int) (32 * (Math.floor(y / 32.0)));
					} else {
						x = (int) (32 * (Math.ceil(x / 32.0)));
						y = (int) (32 * (Math.ceil(y / 32.0)));
					}
					canStop = false;
					isRun = false;
					run.stop();

				}
			}
		}
	}

	public int getX() {
		return x / 32;
	}

	public int getRealX() {
		return x;
	}

	public void setX(int x) {
		this.x = x * 32;
	}

	public int getRealY() {
		return y;
	}

	public int getY() {
		return y / 32;
	}

	public void setY(int y) {
		this.y = y * 32;
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

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public ScenePanel getScene() {
		return scene;
	}

	public void setScene(ScenePanel scene) {
		this.scene = scene;
	}

	public int getEvent() {
		return event;
	}

	public void setEvent(int event) {
		if (isRun) {
			if (!run.isRunning() && !walk.isRunning()) {
				walk.stop();
				this.event = event;
				run.start();
			}
		} else {
			if (!walk.isRunning() && !run.isRunning()) {
				this.event = event;
				walk.start();
			}
		}
	}

	public void setEvent(boolean b) {
		if (b) {
			canStop = true;
		}
	}

	public boolean isRun() {
		return isRun;
	}

	public void setRun(boolean isRun) {
		this.isRun = isRun;
	}

	public ArrayList<Image> getWalkImages() {
		return walkImages;
	}

	public void setWalkImages(ArrayList<Image> walkImages) {
		this.walkImages = walkImages;
	}

	public ArrayList<Image> getRunImages() {
		return runImages;
	}

	public void setRunImages(ArrayList<Image> runImages) {
		this.runImages = runImages;
	}

	public boolean isRunOver() {
		return runOver;
	}

	public void setRunOver(boolean runOver) {
		this.runOver = runOver;
	}
}
