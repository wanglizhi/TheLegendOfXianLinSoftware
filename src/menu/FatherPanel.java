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
	//�����С
	private final static int WIDTH = 32 * 32;
	private final static int HEIGHT = 32 * 20;
	//����	
	//��������
	ZhangXiaoFan hero1;
	LuXueQi hero2;
	SongDaRen hero3;
	YuJie hero4;
	MenuPanel menuPanel;
	Mouse mouse;
	Scoll scoll;
	// ���� �ڳ��󷽷� readBackgroundImage()�� �ɸ��Ե�panelʵ��
	Image backgroundImage;
	//����ͼƬ
	Image bufferedPic;
	//���廭��
	Graphics bufferedGraphics;


	// �α�ĵ�ǰλ��
	int currentX = 0;
	int currentY = 0;
	// ƫ����
	int move = 5;

	public FatherPanel (MenuPanel a,ZhangXiaoFan h1,LuXueQi h2,YuJie h4){
		menuPanel=a;
		hero1=h1;
		hero2=h2;
		hero4=h4;
		setPreferredSize(new Dimension(WIDTH, HEIGHT));		
		//˫����׼��
		bufferedPic=new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_ARGB);
		bufferedGraphics=bufferedPic.getGraphics();

		mouse=new Mouse(this);


		// ����ͼƬ
		readBackgroundImage();


		//�����߳�
		Thread t=new Thread(this);
		t.start();
	}
	public abstract void readBackgroundImage();

	//α������
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
		// ������
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
