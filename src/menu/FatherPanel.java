package menu;
import battle.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.*;


public abstract class FatherPanel extends JPanel implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1663046739601211478L;
	//窗体大小
	private final static int WIDTH = 32 * 32;
	private final static int HEIGHT = 32 * 20;
	//引用	
	//人物引用
	ZhangXiaoFan hero1;
	LuXueQi hero2;
	SongDaRen hero3;
	YuJie hero4;
	MenuPanel menuPanel;
	Mouse mouse;
	Scoll scoll;
	// 背景 在抽象方法 readBackgroundImage()中 由各自的panel实现
	Image backgroundImage;
	//缓冲图片
	Image bufferedPic;
	//缓冲画笔
	Graphics bufferedGraphics;


	// 游标的当前位置
	int currentX = 0;
	int currentY = 0;
	// 偏移量
	int move = 5;

	public FatherPanel (MenuPanel a,ZhangXiaoFan h1,LuXueQi h2,YuJie h4){
		menuPanel=a;
		hero1=h1;
		hero2=h2;
		hero4=h4;
		setPreferredSize(new Dimension(WIDTH, HEIGHT));		
		//双缓冲准备
		bufferedPic=new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_ARGB);
		bufferedGraphics=bufferedPic.getGraphics();

		mouse=new Mouse(this);


		// 读入图片
		readBackgroundImage();


		//开启线程
		Thread t=new Thread(this);
		t.start();
	}
	public abstract void readBackgroundImage();

	//伪监听！
	public void mousePressed(int x,int y) {
		currentX = x;
		currentY =y;
		checkAllButtonPressed();

	}

	public void mouseReleased(int x,int y) {
		currentX = x;
		currentY =y;
		checkAllButtonReleased();
	}



	public void mouseMoved(int x,int y) {
		currentX = x;
		currentY =y;
		checkAllButtonMoveIn();

	}

	public void mouseDragged(int x,int y) {
		currentX = x;
		currentY =y;
		checkAllButtonMoveIn();

	}

	public abstract void checkAllButtonReleased();
	public abstract void checkAllButtonMoveIn();
	public abstract void checkAllButtonPressed();
	public void paint(Graphics g) {

		super.paint(g);
		// 画背景
		bufferedGraphics.drawImage(backgroundImage, 0, 0, this);
		drawSpecialImage(bufferedGraphics);
		menuPanel.command.drawCommand(bufferedGraphics);
		if(scoll!=null){
			scoll.drawScoll(bufferedGraphics);
		}
		drawThisPanel(bufferedGraphics);

		mouse.drawMouse(bufferedGraphics);
		g.drawImage(bufferedPic,0,0,this);

	}
	public abstract void drawSpecialImage(Graphics g);
	public	abstract void drawThisPanel(Graphics g);

	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			update();
			mouse.update();

			repaint();
		}
	}
	public abstract void update();
	public abstract ArrayList<String> saveEquipInfo();
	public abstract void initialEquipInfo(ArrayList<String> menuInfo);
}
