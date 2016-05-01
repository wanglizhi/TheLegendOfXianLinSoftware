package start;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;
import main.GameLauncher;
import tools.Reader;

public class LoadAndSavePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private final static int WIDTH = 32 * 32;
	private final static int HEIGHT = 32 * 20;
Thread t=new Thread(GameLauncher.scenePanel);
	// ����ͼ
	final Image background;
	Graphics backgroundGraphics;

	// ����
	Image backgroundImage;

	// ��ʼ����ǰ �α�ĵ�ǰλ��
	int currentX = 0;
	int currentY = 0;

	// ����Ӧ�еĶ���
	// ��ť����
	ArrayList<StartAnimation> buttonAnimations = new ArrayList<StartAnimation>();
	// ���ﶯ��
	ArrayList<StartAnimation> zhangXiaoFan = new ArrayList<StartAnimation>();
	ArrayList<StartAnimation> luXueQi = new ArrayList<StartAnimation>();
	ArrayList<StartAnimation> wenMin = new ArrayList<StartAnimation>();
	// ��궯��
	Mouse mouse;

	// ����Ӧ�е�Button
	ArrayList<StartButton> buttons = new ArrayList<StartButton>();

	// ������ȡ�ʹ浵��װ��
	Loader loader;
	Recorder recorder;

	// �жϻ�ʲô������
	int[][] isRoleExist;
	ArrayList<String> maps;
	ArrayList<String> tasks;

	// �ж�Ӧ��ʵ��ʲô���Ĺ���
	public static boolean PanelState = false;
	public static boolean SAVE = true;
	public static boolean LOAD = false;
	
	//������һ��panel��ʲô
	private String lastPanel;

	public LoadAndSavePanel() {
		// ��ʼ�������Ļ���ͼƬ
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		background = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_ARGB);

		// ��ʼ����Ϊ��ɫ
		setBackground(new Color(0, 0, 0));
		backgroundGraphics = background.getGraphics();

		// ����������
		setMouse();

		// ����
		initialAnimations();
		startAnimationThread();
		// ��ť
		initialButtons();

		loader = new Loader();
		recorder = new Recorder();
		isRoleExist = new int[3][3];
		maps = new ArrayList<String>();
		tasks = new ArrayList<String>();

		prepareScenes();
	}

	private void initialAnimations() {
		// TODO Auto-generated method stub
		// ��ť���������ﶯ��
		for (int i = 0; i < 3; i++) {
			StartAnimation buttonAnimation = new StartAnimation(4, "���붯��",
					this, 800, 150 + i * 200);
			StartAnimation zhangAnimation = new StartAnimation(8, "��С��", this,
					300, 150 + i * 200);
			StartAnimation luAnimation = new StartAnimation(8, "½ѩ��", this,
					400, 150 + i * 200);
			StartAnimation wenAnimation = new StartAnimation(8, "����", this,
					500, 150 + i * 200);
			buttonAnimations.add(buttonAnimation);
			zhangXiaoFan.add(zhangAnimation);
			luXueQi.add(luAnimation);
			wenMin.add(wenAnimation);
		}
		// ��궯��
		mouse = new Mouse(
				new StartAnimation(8, "���", this, currentX, currentY), this);
	}

	private void initialButtons() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 3; i++) {
			StartButton load = new StartButton(800, 150 + i * 200, 80, 80,
					Reader.readImage("sources/����/��ť/�հ�.png"),
					Reader.readImage("sources/����/��ť/�հ�.png"),
					Reader.readImage("sources/����/��ť/�հ�.png"), this,
					buttonAnimations.get(i));
			buttons.add(load);
		}
	}

	private void startAnimationThread() {
		// TODO Auto-generated method stub

		Thread animation = new Thread() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// ������궯��
					mouse.startMouseAnimation();
					mouse.getMouseAnimation().updateImage();
					// ����ť����
					for (StartAnimation animation : buttonAnimations)
						animation.updateImage();
					for (StartAnimation animation : zhangXiaoFan)
						animation.updateImage();
					for (StartAnimation animation : luXueQi)
						animation.updateImage();
					for (StartAnimation animation : wenMin)
						animation.updateImage();
					repaint();
				}
			}
		};
		// �����߳�
		animation.start();
	}

	private void setMouse() {

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				currentX = e.getX();
				currentY = e.getY();
				for (StartButton button : buttons)
					button.isPressedButton(currentX, currentY);
			}

			public void mouseReleased(MouseEvent e) {
				currentX = e.getX();
				currentY = e.getY();
				// ��鰴ť��Ӧ
				setButton();
				for (StartButton button : buttons)
					button.isRelesedButton(currentX, currentY);
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent ex) {
				currentX = ex.getX();
				currentY = ex.getY();

				for (StartButton button : buttons)
					button.isMoveIn(currentX, currentY);
			}

			public void mouseDragged(MouseEvent ex) {
				currentX = ex.getX();
				currentY = ex.getY();
			}
		});
	}

	private void setButton() {
		if (PanelState == LOAD) {
			for (int i = 0; i < buttons.size(); i++)
				if (buttons.get(i).isIsclicked()) {
					if (!loader.isNull(i)) {
						loader.load(i);
						if(!t.isAlive())
						t.start();
						GameLauncher.switchTo("scene");
					}
				}
		} else {
			for (int i = 0; i < buttons.size(); i++)
				if (buttons.get(i).isIsclicked()) {
						recorder.save(i);
						prepareScenes();
				}
		}

	}

	private void prepareScenes() {
		maps.removeAll(maps);
		tasks.removeAll(tasks);
		for (int i = 0; i < 3; i++) {
			if (!loader.isNull(i)) {
				ArrayList<String> roleAndMapInfo=loader.getTextInfo(i);
				if (Boolean.parseBoolean(roleAndMapInfo.get(0)))
					isRoleExist[i][0] = 1;
				if (Boolean.parseBoolean(roleAndMapInfo.get(1)))
					isRoleExist[i][1] = 1;
				if (Boolean.parseBoolean(roleAndMapInfo.get(2)))
					isRoleExist[i][2] = 1;
				maps.add(roleAndMapInfo.get(3));
				tasks.add(roleAndMapInfo.get(4));
			} else {
				maps.add("��");
				tasks.add("��");
			}
		}
	}
	
	public void changeStateTo(boolean state){
		//��ʼ��״̬
		PanelState=state;
		// ��ʼ������ͼƬ
		if (PanelState == SAVE)
			backgroundImage = Reader.readImage("sources/����/��ť/�浵.png");
		else
			backgroundImage = Reader.readImage("sources/����/��ť/��ȡ.png");
		setMouse();
	}
	public void paint(Graphics g) {
		
		// ������
		backgroundGraphics.drawImage(backgroundImage, 0, 0, this);
		// ���ƶ���
		// ��ť

		for (int i = 0; i < 3; i++) {
			backgroundGraphics.drawImage(Reader.readImage("sources/����/�װ�.png"),
					90, 60 + i * 200, this);
			if (isRoleExist[i][0] == 1){
				zhangXiaoFan.get(i).startAnimation();
				zhangXiaoFan.get(i).drawAnimation(backgroundGraphics);
			}
			if (isRoleExist[i][1] == 1){
				luXueQi.get(i).startAnimation();
				luXueQi.get(i).drawAnimation(backgroundGraphics);
			}
			if (isRoleExist[i][2] == 1){
				wenMin.get(i).startAnimation();
				wenMin.get(i).drawAnimation(backgroundGraphics);
			}
			// ��������ͼ
				backgroundGraphics.drawImage(
						Reader.readImage("maps/" + maps.get(i)), 100,
						100 + i * 200, 150, 100, this);
			// ������ǰ����λ��
			String mapName=maps.get(i).split("\\.")[0];
			backgroundGraphics.setColor(Color.WHITE);
			backgroundGraphics.setFont(new Font("�Ķ��ֱָ��п�", Font.BOLD, 20));
			backgroundGraphics.drawString(mapName, 100, 220 + i * 200);
			if(tasks.get(i)!="��")
			backgroundGraphics.drawString(tasks.get(i), 400, 120+i*200);
		}
		for (StartButton button : buttons)
			button.drawButton(backgroundGraphics);
		// �����
		mouse.getMouseAnimation().setX(currentX);
		mouse.getMouseAnimation().setY(currentY);
		mouse.getMouseAnimation().drawAnimation(backgroundGraphics);
		// ���ػ���ͼ
		g.drawImage(background, 0, 0, this);
	}
	
	public void setLastPanel(String lastPanel){
		this.lastPanel=lastPanel;
	}
	
	private void returnToLastPanel(){
		GameLauncher.switchTo(lastPanel);
	}

	public void keyPressed(int keyCode) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.VK_ESCAPE)
			returnToLastPanel();
	}

	
}
