package start;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import tools.Reader;
import java.util.ArrayList;
import javax.swing.JPanel;
import main.GameLauncher;

public class StartPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private final static int WIDTH = 32 * 32;
	private final static int HEIGHT = 32 * 20;

	// ����ͼ
	final Image background;
	Graphics backgroundGraphics;

	// ����
	final Image backgroundImage;

	// ��ʼ����ǰ �α�ĵ�ǰλ��
	int currentX = 0;
	int currentY = 0;

	// ����Ӧ�еĶ���
	// ��ť����
	ArrayList<StartAnimation> buttonAnimations = new ArrayList<StartAnimation>();
	// ��궯��
	Mouse mouse;
	// ���ᶯ��
	StartAnimation scroll;
	// ������ᶯ��
	StartAnimation backScroll;
	// �ƲʵĶ���
	CloudAnimation upCloud;
	CloudAnimation rightCloud;
	// ����Ķ���
	StartAnimation loadAnimation;
	StartAnimation loadAnimation2;
	// ����Ӧ�еİ�ť
	StartButton start;
	StartButton load;
	StartButton about;
	StartButton end;
	StartButton back;
	ArrayList<StartButton> buttons = new ArrayList<StartButton>();

	// ���þ����Ƿ�չ����״̬
	boolean isUnfolded = false;

	// ���ð�ť���ź�,0Ϊ��ʼ��1Ϊload��2Ϊabout
	int BUTTON_SIGNAL;

	// �ж�aboutpanel��ʱ���ֵ�timer
	StartTimer aboutTimer;
	StartTimer loadTimer;

	public StartPanel() {
		// ��ʼ����ʱ��
		loadTimer = new StartTimer();
		aboutTimer = new StartTimer();
		// ��ʼ�������Ļ���ͼƬ
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		background = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_ARGB);

		// ��ʼ����Ϊ��ɫ
		setBackground(new Color(0, 0, 0));
		backgroundGraphics = background.getGraphics();

		// ����������
		setMouse();
		// ��ʼ������ͼƬ
		backgroundImage = Reader.readImage("sources/StartPanel/back.png");
		// ����
		initialAnimations();
		startAnimationThread();
		// ��ť
		initialButtons();
		buttons.add(start);
		buttons.add(load);
		buttons.add(about);
		buttons.add(end);
	}

	private void initialAnimations() {
		// TODO Auto-generated method stub
		// ��ʼ����������
		// ��ť����
		for (int i = 0; i < 4; i++) {
			StartAnimation buttonAnimation = new StartAnimation(4, "��ť����",
					this, 200, 150 + i * 100);
			buttonAnimations.add(buttonAnimation);
		}
		StartAnimation buttonAnimation = new StartAnimation(4, "��ť����", this,
				800, 550);
		buttonAnimations.add(buttonAnimation);
		// ��궯��
		mouse = new Mouse(
				new StartAnimation(8, "���", this, currentX, currentY), this);
		// ���ᶯ��
		scroll = new StartAnimation(10, "����", this, -320, -100);
		backScroll = new StartAnimation(10, "�������", this, -320, -100);
		// �Ʋʶ���
		upCloud = new CloudAnimation(0, 360,
				Reader.readImage("sources/StartPanel/�����Ʋ�.png"), 0, this);
		loadAnimation = new StartAnimation(10, "����", this, 650, 480);
		loadAnimation2 = new StartAnimation(3, "����2", this, 700, 200);
	}

	private void initialButtons() {
		// TODO Auto-generated method stub
		start = new StartButton(200, 150, 50, 50,
				Reader.readImage("sources/StartPanel/��ť/��.png"),
				Reader.readImage("sources/StartPanel/��ť/��2.png"),
				Reader.readImage("sources/StartPanel/��ť/��2.png"), this,
				buttonAnimations.get(0));
		load = new StartButton(200, 250, 50, 50,
				Reader.readImage("sources/StartPanel/��ť/��.png"),
				Reader.readImage("sources/StartPanel/��ť/��2.png"),
				Reader.readImage("sources/StartPanel/��ť/��2.png"), this,
				buttonAnimations.get(1));
		about = new StartButton(200, 350, 50, 50,
				Reader.readImage("sources/StartPanel/��ť/ת.png"),
				Reader.readImage("sources/StartPanel/��ť/ת2.png"),
				Reader.readImage("sources/StartPanel/��ť/ת2.png"), this,
				buttonAnimations.get(2));
		end = new StartButton(200, 450, 50, 50,
				Reader.readImage("sources/StartPanel/��ť/��.png"),
				Reader.readImage("sources/StartPanel/��ť/��2.png"),
				Reader.readImage("sources/StartPanel/��ť/��2.png"), this,
				buttonAnimations.get(3));
		back = new StartButton(800, 550, 50, 50,
				Reader.readImage("sources/StartPanel/��ť/��.png"),
				Reader.readImage("sources/StartPanel/��ť/��2.png"),
				Reader.readImage("sources/StartPanel/��ť/��2.png"), this,
				buttonAnimations.get(4));
	}

	private void startAnimationThread() {
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
					// ������ᶯ��
					scroll.updateImage();
					backScroll.updateImage();
					// �����Ʋʶ���
					upCloud.updateCoordinate();
					loadAnimation.updateImage();
					loadAnimation2.updateImage();
					// ����about����
					aboutTimer.update();
					loadTimer.update();
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
		if (!isUnfolded) {
			if (start.isIsclicked() == true) {
				scroll.startAnimation();
				BUTTON_SIGNAL = 0;
			}
			if (load.isIsclicked() == true) {
				scroll.startAnimation();
				BUTTON_SIGNAL = 1;
			}
			if (about.isIsclicked() == true) {
				scroll.startAnimation();
				aboutTimer.start(10);
				BUTTON_SIGNAL = 2;
			}
			if (end.isIsclicked() == true)
				System.exit(0);
		} else {
			if (back.isIsclicked() == true) {
				buttons.remove(back);
				aboutTimer.start(10);
				backScroll.startAnimation();
				BUTTON_SIGNAL = 3;
			}
		}
	}

	private void startButtonAction() {
		switch (BUTTON_SIGNAL) {
		case 0:
			loadAnimation.startAnimation();
			loadAnimation2.startAnimation();
			loadTimer.start(30);
			break;
		case 1:
			loadAnimation.startAnimation();
			loadAnimation2.startAnimation();
			loadTimer.start(30);
			break;
		case 2:
			buttons.add(back);
			break;
		}
	}

	public void paint(Graphics g) {
		// ������
		backgroundGraphics.drawImage(backgroundImage, 0, 0, this);
		// ���ƶ���
		// ��
		upCloud.drawAnimation(backgroundGraphics);
		// ��ť
		for (StartButton button : buttons)
			if (!button.equals(back))
				button.drawButton(backgroundGraphics);
		// ����
		drawScroll();
		// ���붯��
		if (loadAnimation.isStop != true) {
			loadAnimation.drawAnimation(backgroundGraphics);
		}
		if (loadAnimation2.isStop != true)
			loadAnimation2.drawAnimation(backgroundGraphics);
		// ��ť
		if (buttons.contains(back))
			back.drawButton(backgroundGraphics);
		// �����
		mouse.getMouseAnimation().setX(currentX);
		mouse.getMouseAnimation().setY(currentY);
		mouse.getMouseAnimation().drawAnimation(backgroundGraphics);
		// ���ػ���ͼ
		g.drawImage(background, 0, 0, this);
	}

	private void drawScroll() {

		if (!isUnfolded) {
			scroll.drawAnimation(backgroundGraphics);
			if (BUTTON_SIGNAL == 2)
				backgroundGraphics.drawImage(
						Reader.readImage("sources/StartPanel/��������.png"), 0, 0,
						100 * (9 - aboutTimer.getTimeLeft()), 640, 0, 0,
						100 * (9 - aboutTimer.getTimeLeft()), 640, this);
			if (scroll.isLoop() == true) {
				scroll.stopScorllAnimation();
				scroll.stopButtonAnimation();
				isUnfolded = true;
				// ���Ƿ����ѭ����Ϊfalse
				scroll.setLoop(false);
				startButtonAction();
			}
		} else {
			backScroll.drawAnimation(backgroundGraphics);
			if (BUTTON_SIGNAL == 3)
				backgroundGraphics.drawImage(
						Reader.readImage("sources/StartPanel/��������.png"), 0, 0,
						100 * (aboutTimer.getTimeLeft()), 640, 0, 0,
						100 * (aboutTimer.getTimeLeft()), 640, this);
			if (BUTTON_SIGNAL == 2)
				backgroundGraphics.drawImage(
						Reader.readImage("sources/StartPanel/��������.png"), 0, 0,
						this);
			startLoadAction();
			if (backScroll.isLoop() == true) {
				backScroll.stopScorllAnimation();
				backScroll.stopButtonAnimation();
				isUnfolded = false;
				// ���Ƿ����ѭ����Ϊfalse
				backScroll.setLoop(false);
				BUTTON_SIGNAL = -1;
			}
		}
	}

	private void startLoadAction() {
		switch (BUTTON_SIGNAL) {
		case 0:
			if (loadTimer.stop()) {
			//	Game.game.init();
				GameLauncher.switchTo("scene");
				loadAnimation.stopButtonAnimation();
				loadAnimation2.stopButtonAnimation();
				loadTimer.initial();
				isUnfolded = false;
				Thread t=new Thread(GameLauncher.scenePanel);
				GameLauncher.scenePanel.initiation("�ű�1.txt");
				t.start();
			}
			break;
		case 1:
			if (loadTimer.stop()) {
				GameLauncher.lsPanel.setLastPanel("start");
				GameLauncher.lsPanel.changeStateTo(LoadAndSavePanel.LOAD);
				GameLauncher.switchTo("ls");
				loadAnimation.stopButtonAnimation();
				loadAnimation2.stopButtonAnimation();
				loadTimer.initial();
				isUnfolded = false;
			}
			break;
		}
	}
}
