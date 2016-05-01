package shop;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.util.ArrayList;
import javax.swing.JPanel;

import scene.SaveAndLoad;
import tools.*;
import main.GameLauncher;
import media.MusicReader;



public class ShopPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private final static int WIDTH = 32 * 32;
	private final static int HEIGHT = 32 * 20;

	// ����ͼ
	final Image background;
	Graphics backgroundGraphics;
	// ���ָ���ͼ��
	final Image[] mouses = new Image[8];

	// ��ť��ͼƬ
	ArrayList<Image> icons = new ArrayList<Image>();
	Image image1;
	Image drugImage;

	// ����
	final Image backgroundImage;
	Image mouse;

	// �α�ĵ�ǰλ��
	int currentX = 0;
	int currentY = 0;
	// ƫ����
	int move = 5;
	// ҩ��ר������
	ArrayList<Drug> drugList = new ArrayList<Drug>();
	//����˵�Ļ�
	String message="��ӭ���������ѧҽԺ";
	String messageplus;
	//��������
	ArrayList<ShopAnimation> ani=new ArrayList<ShopAnimation>();


	// ���ְ�ť
	GameButton buy = new GameButton(445, 75, 149, 40,
			Reader.readImage("sources/Shop/����1.png"),
			Reader.readImage("sources/Shop/����2.png"),
			Reader.readImage("sources/Shop/����3.png"), this);
	GameButton sell = new GameButton(594,75,149,40,
			Reader.readImage("sources/Shop/����1.png"),
			Reader.readImage("sources/Shop/����2.png"),
			Reader.readImage("sources/Shop/����3.png"),this);
	GameButton back = new GameButton(880,20,149,40,
			Reader.readImage("sources/Shop/������Ϸ (1).png"),
			Reader.readImage("sources/Shop/������Ϸ (2).png"),
			Reader.readImage("sources/Shop/������Ϸ (3).png"),this);
	
	
	ArrayList<GameButton> buttonlist = new ArrayList<GameButton>();


	public ShopPanel() {
		// �趨��С
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		// ��������
		background = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_ARGB);
		// ����ͼƬ
		backgroundImage = Reader.readImage("sources/Shop/shopback.png");
		for (int i = 1; i <= 8; i++) {
			mouse = Reader.readImage("image/���ͼ/"+i+".png");
			mouses[i - 1] = mouse;
		}
		// ��ʼ����Ϊ��ɫ
		setBackground(new Color(0, 0, 0));

		backgroundGraphics = background.getGraphics();
		//�������ﶯ��
		ani.add(new ShopAnimation("��С��", 0, 0,8,this));
		ani.add(new ShopAnimation("½ѩ��",0,160,8,this));
		ani.add(new ShopAnimation("����",0,320,8,this));
		ani.add(new ShopAnimation("����",364,515,8,this));
		// �趨������
		setMouse();
		// ��궯��
		Thread mouseAnimation = new Thread() {
			public void run() {
				while (true) {
					for (int i = 0; i < 8; i++) {
						mouse = mouses[i];
						for(ShopAnimation animation: ani)
							animation.image=animation.images.get(i);
						try {
							Thread.sleep(120);
						} catch (Exception e) {
							e.printStackTrace();
						}
						repaint();
					}
				}
			}
		};
		// ��������
		mouseAnimation.start();

		// ����ҩƷlist
		drugList = ShopReader.readDrug();
		for (Drug drug : drugList) {
			drug.setNumber((int) (Math.random() * 10));
		}
		//���밴ťlist
		buttonlist.add(buy);
		buttonlist.add(sell);
		buttonlist.add(back);
		int x = 453, y = 200;
		for(int i=0;i<drugList.size();i++){
			GameButton decrease=new GameButton(x+90,y-10,7,12,
					Reader.readImage("sources/Shop/����1.png"),
					Reader.readImage("sources/Shop/����2.png"),
					Reader.readImage("sources/Shop/����2.png"),this);
			GameButton increase=new GameButton(x+130,y-10,7,12,
					Reader.readImage("sources/Shop/����1.png"),
					Reader.readImage("sources/Shop/����2.png"),
					Reader.readImage("sources/Shop/����2.png"),this);
			y+=20;
			buttonlist.add(decrease);
			buttonlist.add(increase);
		}
	}

	private void isMoveIn() {
		int oringinY = 180;
		for (int i = 0; i < drugList.size(); i++) {
			if (currentX > 440 && currentX < 795 && currentY > oringinY
					&& currentY < oringinY + 20) {
				drugImage = drugList.get(i).getPicture();
				message="Hp�ָ�"+drugList.get(i).getAddHp()+", Mp�ָ�"+drugList.get(i).getAddMp();
				if(drugList.get(i).getReduceMoney()>=6000)
					messageplus="ҩ�Ǻ�ҩ�����Ǻ����е����";
				else
					messageplus="�����������Ǻ�";
			}
			oringinY += 20;
		}
	}

	// ����������
	public void setMouse() {
		int[] pixels = new int[256];
		Image image = Toolkit.getDefaultToolkit().createImage(
				new MemoryImageSource(16, 16, pixels, 0, 16));
		// ����һ��͸�����α�
		Cursor transparentCursor = Toolkit.getDefaultToolkit()
				.createCustomCursor(image, new Point(0, 0), "hidden");
		// ����͸���α꣬�Դ�ģ�����α�״̬
		setCursor(transparentCursor);
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				currentX = e.getX();
				currentY = e.getY();
				for (GameButton button : buttonlist)
					button.isPressedButton(currentX, currentY);
				repaint();
			}

			public void mouseReleased(MouseEvent e) {
				currentX = e.getX();
				currentY = e.getY();
				setButton();
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent ex) {
				currentX = ex.getX();
				currentY = ex.getY();

				for (GameButton button : buttonlist)
					button.isMoveIn(currentX, currentY);
				isMoveIn();
				repaint();
			}

			public void mouseDragged(MouseEvent ex) {
				currentX = ex.getX();
				currentY = ex.getY();
				repaint();
			}
		});
	}
	
	//���ð�ť���º���¼�
	public void setButton(){
		//���������¼�
		if(buy.isIsclicked()==true){
			MusicReader.readmusic("Clip986.wav");
			for(int i=0;i<6;i++){
				int temp=Math.min(drugList.get(i).getPurchaseNumber(), drugList.get(i).getNumber());
				DrugPack.drugList.get(i).setNumberGOT(DrugPack.drugList.get(i).getNumberGOT()+temp);
				//����ʣ���Ǯ
				Money.setCoins(Money.coins-drugList.get(i).getReduceMoney()*temp);
				//��ʣ�Ķ�������
				drugList.get(i).setNumber(drugList.get(i).number-temp);
			}
			if(Money.getCoins()<0){
				for(int i=0;i<6;i++){
					int temp=Math.min(drugList.get(i).getPurchaseNumber(), drugList.get(i).getNumber());
					DrugPack.drugList.get(i).setNumberGOT(DrugPack.drugList.get(i).getNumberGOT()-temp);
					//����ʣ���Ǯ
					Money.setCoins(Money.coins+drugList.get(i).getReduceMoney()*temp);
					//��ʣ�Ķ�������
					drugList.get(i).setNumber(drugList.get(i).number+temp);
				}
				message="��ѽ,С�ֵ�,���Ǯ������,Ҫʡ�ŵ㻨��";
				messageplus=null;
			}
			for(int i=0;i<6;i++)
			drugList.get(i).setPurchaseNumber(0);
		}
		//���������¼�
		if(sell.isIsclicked()==true){
			MusicReader.readmusic("Clip986.wav");
			for(int i=0;i<6;i++){
				int temp=Math.min(drugList.get(i).getPurchaseNumber(), DrugPack.drugList.get(i).getNumberGOT());
				DrugPack.drugList.get(i).setNumberGOT(DrugPack.drugList.get(i).getNumberGOT()-temp);
				//����ʣ���Ǯ
				Money.setCoins(Money.coins+drugList.get(i).getReduceMoney()*temp);
				//��ʣ�Ķ�������
				drugList.get(i).setNumber(drugList.get(i).number+temp);
				drugList.get(i).setPurchaseNumber(0);
			}
		}	
		if(back.isIsclicked()==true){
			MusicReader.readmusic("��ͷ��.wav");
			GameLauncher.switchTo("scene");
		}
		for(int i=3;i<buttonlist.size();i+=2){
			if(buttonlist.get(i).isIsclicked()==true){
				MusicReader.readmusic("click.wav");
				if(drugList.get(i/2-1).getPurchaseNumber()>0)
				drugList.get(i/2-1).setPurchaseNumber(
						drugList.get(i/2-1).getPurchaseNumber() - 1);					
			}
			if(buttonlist.get(i+1).isIsclicked()==true){
				MusicReader.readmusic("click.wav");
				drugList.get(i/2-1).setPurchaseNumber(
						drugList.get(i/2-1).getPurchaseNumber() + 1);
			}
		}
		for (GameButton button : buttonlist)
			button.isRelesedButton(currentX, currentY);
	}


	// ����ͼ��ķ���
	public void drawIcon(Graphics g) {
		// ����ͼ���ͼƬ
		g.drawImage(drugImage, 820, 200, this);
		// Ϊ��ͼ������������
		int x = 453, y = 200;
		int i=0;
		for (Drug drug : drugList) {
			g.setFont(new Font("�Ķ��ֱָ��п�", Font.BOLD, 20));
			g.setColor(Color.white);
			g.drawString(drug.getName(), x, y);
			g.drawString(" "+drug.getReduceMoney(), x+180, y);
			g.drawString(" " + drug.getNumber(), x + 245, y);
			g.drawString(" "+DrugPack.drugList.get(i).getNumberGOT(), x+300, y);
			g.drawString(" "+Money.getCoins(), 900, 20);
			g.drawString(" "+drug.getPurchaseNumber(), x+100, y);
			y += 20;
			i++;
		}
		if(SaveAndLoad.zhang){
			backgroundGraphics.drawImage(ani.get(0).image,
					ani.get(0).getAnimationX(),ani.get(0).getAnimationY(),this);
		g.drawString("��", 55, 30+0*150);
		g.drawImage(Reader.readImage("sources/Shop/��ť���/����.png"),60,30+0*150,this);
		g.drawString("��", 55, 30+0*150+20);
		g.drawImage(Reader.readImage("sources/Shop/��ť���/����.png"),60,30+0*150+20,this);
		g.drawString("��", 55, 30+0*150+40);
		g.drawImage(Reader.readImage("sources/Shop/��ť���/����.png"),60,30+0*150+40,this);
		g.drawString("��", 55, 30+0*150+60);
		g.drawImage(Reader.readImage("sources/Shop/��ť���/����.png"),60,30+0*150+60,this);
		}
		if(SaveAndLoad.lu){
			backgroundGraphics.drawImage(ani.get(1).image,
					ani.get(1).getAnimationX(),ani.get(1).getAnimationY(),this);
		g.drawString("��", 55, 30+1*150);
		g.drawImage(Reader.readImage("sources/Shop/��ť���/����.png"),60,30+1*150,this);
		g.drawString("��", 55, 30+1*150+20);
		g.drawImage(Reader.readImage("sources/Shop/��ť���/����.png"),60,30+1*150+20,this);
		g.drawString("��", 55, 30+1*150+40);
		g.drawImage(Reader.readImage("sources/Shop/��ť���/����.png"),60,30+1*150+40,this);
		g.drawString("��", 55, 30+1*150+60);
		g.drawImage(Reader.readImage("sources/Shop/��ť���/����.png"),60,30+1*150+60,this);
		}
		if(SaveAndLoad.wen){
			backgroundGraphics.drawImage(ani.get(2).image,
					ani.get(2).getAnimationX(),ani.get(2).getAnimationY(),this);
		g.drawString("��", 55, 30+2*150);
		g.drawImage(Reader.readImage("sources/Shop/��ť���/����.png"),60,30+2*150,this);
		g.drawString("��", 55, 30+2*150+20);
		g.drawImage(Reader.readImage("sources/Shop/��ť���/����.png"),60,30+2*150+20,this);
		g.drawString("��", 55, 30+2*150+40);
		g.drawImage(Reader.readImage("sources/Shop/��ť���/����.png"),60,30+2*150+40,this);
		g.drawString("��", 55, 30+2*150+60);
		g.drawImage(Reader.readImage("sources/Shop/��ť���/����.png"),60,30+2*150+60,this);
		}
		backgroundGraphics.drawImage(ani.get(3).image,
				ani.get(3).getAnimationX(),ani.get(3).getAnimationY(),this);
		g.drawImage(Reader.readImage("sources/Shop/��ť���/����.png"),200,0,this);
		if(message!=null)
		g.drawString(message, 453,610);
		if(messageplus!=null)
		g.drawString(messageplus, 453, 630);
		g.drawImage(Reader.readImage("sources/Shop/��ť���/Ǯ.png"), 880,0, this);
	}

	public void paint(Graphics g) {

		super.paint(g);
		// ������
		backgroundGraphics.drawImage(backgroundImage, 0, 0, this);
		
		// ����ͼ��
		drawIcon(backgroundGraphics);
		// ������ť���
		for (GameButton button : buttonlist) {
			button.drawButton(backgroundGraphics);
		}
		backgroundGraphics.drawImage(mouse, currentX, currentY, this);
		// ���ػ���ͼ
		g.drawImage(background, 0, 0, this);
	}
	
	//�ṩ�ӿڸ�Recorder��loader
		public ArrayList<String> saveShopInfo(){
			ArrayList<String> shopInfo=new ArrayList<String>();
			for(Drug drug:DrugPack.drugList)
				shopInfo.add(Integer.toString(drug.getNumberGOT()));
			shopInfo.add(Integer.toString(Money.getCoins()));
			return shopInfo;
		}
		
		public void initialShopInfo(ArrayList<String> shopInfo){
			for(int i=0;i<DrugPack.drugList.size();i++)
				DrugPack.drugList.get(i).setNumberGOT(Integer.parseInt(shopInfo.get(i)));
			Money.setCoins(Integer.parseInt(shopInfo.get(drugList.size())));
		}

}
