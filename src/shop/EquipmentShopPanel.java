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

import main.GameLauncher;
import media.MusicReader;

import scene.SaveAndLoad;
import tools.GameButton;
import tools.Reader;

public class EquipmentShopPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1930609849857145580L;
	private final static int WIDTH = 32 * 32;
	private final static int HEIGHT = 32 * 20;

	// ����ͼ
	final Image background;
	Graphics backgroundGraphics;
	// ���ָ���ͼ��
	final Image[] mouses = new Image[8];

	// ����
	final Image backgroundImage;
	Image mouse;
	//װ����ͼ��
	Image equipmentImage;
	
	// �α�ĵ�ǰλ��
	int currentX = 0;
	int currentY = 0;
	// ƫ����
	int move = 5;
	

	// װ����ר������
	ArrayList<Equipment> helmetList = new ArrayList<Equipment>();
	ArrayList<Equipment> armorList = new ArrayList<Equipment>();
	ArrayList<Equipment> weaponList = new ArrayList<Equipment>();
	ArrayList<Equipment> gloveList = new ArrayList<Equipment>();
	ArrayList<Equipment> shoeList = new ArrayList<Equipment>();
	ArrayList<Equipment> decorationList = new ArrayList<Equipment>();
	
	//�����ȳ����ĸ���Ŀ����Ҫ�ַ���
	String equipment="weapon"; 
	
	//����˵�Ļ�
	String message="��ӭ���������ѧװ����ѡ����";
	String messageplus;
	String messageremark;
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
	int x=448,y=133;
	GameButton weapon= new GameButton(x, y, 47, 20, 
			Reader.readImage("sources/Shop/��ť���/����1.png"), 
			Reader.readImage("sources/Shop/��ť���/����1.png"), 
			Reader.readImage("sources/Shop/��ť���/����2.png"), this);
	GameButton helmet= new GameButton(x+47*1, y, 47, 20, 
			Reader.readImage("sources/Shop/��ť���/ͷ��1.png"), 
			Reader.readImage("sources/Shop/��ť���/ͷ��1.png"), 
			Reader.readImage("sources/Shop/��ť���/ͷ��2.png"), this);
	GameButton armor= new GameButton(x+47*2, y, 47, 20, 
			Reader.readImage("sources/Shop/��ť���/����1.png"), 
			Reader.readImage("sources/Shop/��ť���/����1.png"), 
			Reader.readImage("sources/Shop/��ť���/����2.png"), this);
	GameButton glove= new GameButton(x+47*3, y, 47, 20, 
			Reader.readImage("sources/Shop/��ť���/����1.png"), 
			Reader.readImage("sources/Shop/��ť���/����1.png"), 
			Reader.readImage("sources/Shop/��ť���/����2.png"), this);
	GameButton shoe= new GameButton(x+47*4, y, 47, 20, 
			Reader.readImage("sources/Shop/��ť���/ѥ��1.png"), 
			Reader.readImage("sources/Shop/��ť���/ѥ��1.png"), 
			Reader.readImage("sources/Shop/��ť���/ѥ��2.png"), this);
	GameButton decoration= new GameButton(x+47*5, y, 47, 20, 
			Reader.readImage("sources/Shop/��ť���/��Ʒ1.png"), 
			Reader.readImage("sources/Shop/��ť���/��Ʒ1.png"), 
			Reader.readImage("sources/Shop/��ť���/��Ʒ2.png"), this);
	//�������水ť��arraylist
	ArrayList<GameButton> buttonList = new ArrayList<GameButton>();
	
	public EquipmentShopPanel(){
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

		// �趨������
		setMouse();

		//�������ﶯ��
		ani.add(new ShopAnimation("��С��", 0, 0,8,this));
		ani.add(new ShopAnimation("½ѩ��",0,160,8,this));
		ani.add(new ShopAnimation("����",0,320,8,this));
		ani.add(new ShopAnimation("С��",364,515,8,this));
		
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
		// ����װ��list
		helmetList=ShopReader.readEquipment("ͷ");
		for (Equipment equipment : helmetList)
			equipment.setNumber((int) (Math.random() * 10));
		armorList=ShopReader.readEquipment("����");
		for (Equipment equipment : armorList) 
			equipment.setNumber((int) (Math.random() * 10));
		gloveList=ShopReader.readEquipment("��");
		for (Equipment equipment : gloveList) 
			equipment.setNumber((int) (Math.random() * 10));
		shoeList=ShopReader.readEquipment("��");
		for (Equipment equipment : shoeList) 
			equipment.setNumber((int) (Math.random() * 10));
		decorationList=ShopReader.readEquipment("��Ʒ");
		for (Equipment equipment :decorationList) 
			equipment.setNumber((int) (Math.random() * 10));
		weaponList=ShopReader.readEquipment("����");
		for (Equipment equipment : weaponList)
			equipment.setNumber((int) (Math.random() * 10));
		
		//���밴ť
		buttonList.add(sell);
		buttonList.add(buy);
		buttonList.add(back);
		buttonList.add(weapon);
		buttonList.add(helmet);
		buttonList.add(armor);
		buttonList.add(glove);
		buttonList.add(shoe);
		buttonList.add(decoration);
		//�������Ӽ��ٵİ�ť
		//�������Ӽ��ٵİ�ť
		int x = 453, y = 200;
		for(int i=0;i<listTable(equipment).size();i++){
			GameButton decrease=new GameButton(x+90,y-10,7,12,
					Reader.readImage("sources/Shop/����1.png"),
					Reader.readImage("sources/Shop/����2.png"),
					Reader.readImage("sources/Shop/����2.png"),this);
			GameButton increase=new GameButton(x+130,y-10,7,12,
					Reader.readImage("sources/Shop/����1.png"),
					Reader.readImage("sources/Shop/����2.png"),
					Reader.readImage("sources/Shop/����2.png"),this);
			y+=20;
			buttonList.add(decrease);
			buttonList.add(increase);
		}
	}	

	//�ж��Ƿ��ƶ�����Ӧ������
	private void isMoveIn() {
		int oringinY = 180;
		for (int i = 0; i < listTable(equipment).size(); i++) {
			if (currentX > 440 && currentX < 795 && currentY > oringinY
					&& currentY < oringinY + 20) {
				equipmentImage = listTable(equipment).get(i).getPicture();
				message="����+"+listTable(equipment).get(i).getAddPhysicalPower()+
						"����+"+listTable(equipment).get(i).getAddAgile()	+					
						"����+"+listTable(equipment).get(i).getAddStrength()+
						"����+"+listTable(equipment).get(i).getAddSpirit();
				if(listTable(equipment).get(i).getReduceMoney()<10000)
					messageplus="��������������,";
				else if(listTable(equipment).get(i).getReduceMoney()<30000)
					messageplus="�ö������������е��,";
				else if(listTable(equipment).get(i).getReduceMoney()<100000)
					messageplus="�����ǵ���֮����,";

				if(listTable(equipment).get(i).getUser()==0)
					messageremark="ÿ���˶���ʹ��";
				if(listTable(equipment).get(i).getUser()==1)
					messageremark="����ֻ����С������ʹ��";
				if(listTable(equipment).get(i).getUser()==2)
					messageremark="����ֻ��½ѩ������ʹ��";
				if(listTable(equipment).get(i).getUser()==3)
					messageremark="����ֻ����������ʹ��";
			}
			oringinY += 20;
		}
	}
	//����������
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
				for (GameButton button : buttonList)
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

				for (GameButton button : buttonList)
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
		//�ı�ѡ������
		if(weapon.isIsclicked()==true){
			MusicReader.readmusic("��list.wav");
			equipment="weapon";
			//ȥ������İ�ť
			for(int i=buttonList.size()-1;i>=9;i--)
				buttonList.remove(buttonList.get(i));
			setList();
			}
		if(armor.isIsclicked()==true){
			MusicReader.readmusic("��list.wav");
			equipment="armor";
			//ȥ������İ�ť
			for(int i=buttonList.size()-1;i>=9;i--)
				buttonList.remove(buttonList.get(i));
			setList();
		}
		if(helmet.isIsclicked()==true){
			MusicReader.readmusic("��list.wav");
			equipment="helmet";
			//ȥ������İ�ť
			for(int i=buttonList.size()-1;i>=9;i--)
				buttonList.remove(buttonList.get(i));
			setList();
		}
		if(shoe.isIsclicked()==true){
			MusicReader.readmusic("��list.wav");
			equipment="shoe";
			//ȥ������İ�ť
			for(int i=buttonList.size()-1;i>=9;i--)
				buttonList.remove(buttonList.get(i));
			setList();
		}
		if(decoration.isIsclicked()==true){
			MusicReader.readmusic("��list.wav");
			equipment="decoration";
			//ȥ������İ�ť
			for(int i=buttonList.size()-1;i>=9;i--)
				buttonList.remove(buttonList.get(i));
			setList();
		}
		if(glove.isIsclicked()==true){
			MusicReader.readmusic("��list.wav");
			equipment="glove";
			//ȥ������İ�ť
			for(int i=buttonList.size()-1;i>=9;i--)
				buttonList.remove(buttonList.get(i));
			setList();
		}
		//���������¼�
		if(buy.isIsclicked()==true){
			MusicReader.readmusic("Clip986.wav");
			for(int i=0;i<listTable(equipment).size();i++){
				int temp=Math.min(listTable(equipment).get(i).getPurchaseNumber(), listTable(equipment).get(i).getNumber());
				EquipmentPack.listTable(equipment).get(i).setNumberGOT(EquipmentPack.listTable(equipment).get(i).getNumberGOT()+temp);
				//����ʣ���Ǯ
				Money.setCoins(Money.coins-listTable(equipment).get(i).getReduceMoney()*temp);
				//��ʣ�Ķ�������
				listTable(equipment).get(i).setNumber(listTable(equipment).get(i).getNumber()-temp);

			}
			if(Money.getCoins()<0){
				for(int i=0;i<listTable(equipment).size();i++){
					int temp=Math.min(listTable(equipment).get(i).getPurchaseNumber(), listTable(equipment).get(i).getNumber());
					EquipmentPack.listTable(equipment).get(i).setNumberGOT(EquipmentPack.listTable(equipment).get(i).getNumberGOT()-temp);
					//����ʣ���Ǯ
					Money.setCoins(Money.coins+listTable(equipment).get(i).getReduceMoney()*temp);
					//��ʣ�Ķ�������
					listTable(equipment).get(i).setNumber(listTable(equipment).get(i).getNumber()+temp);
				}
				message="��ѽ,С�ֵ�,���Ǯ������,Ҫʡ�ŵ㻨��";
				messageplus=null;
				messageremark=null;
			}
			for(int i=0;i<listTable(equipment).size();i++)
			listTable(equipment).get(i).setPurchaseNumber(0);
		}
		//���������¼�
		if(sell.isIsclicked()==true){
			MusicReader.readmusic("Clip986.wav");
			for(int i=0;i<listTable(equipment).size();i++){
				int temp=Math.min(listTable(equipment).get(i).getPurchaseNumber(), EquipmentPack.listTable(equipment).get(i).getNumberGOT());
				EquipmentPack.listTable(equipment).get(i).setNumberGOT(EquipmentPack.listTable(equipment).get(i).getNumberGOT()-temp);
				//����ʣ���Ǯ
				Money.setCoins(Money.coins+listTable(equipment).get(i).getReduceMoney()*temp);
				//��ʣ�Ķ�������
				listTable(equipment).get(i).setNumber(listTable(equipment).get(i).getNumber()+temp);
				listTable(equipment).get(i).setPurchaseNumber(0);
			}
		}	
		if(back.isIsclicked()==true){
			MusicReader.readmusic("��ͷ��.wav");
			GameLauncher.switchTo("scene");
		}
		//�������������Ӽ��ٵ��¼� 
		for(int i=9;i<buttonList.size();i+=2){
			if(buttonList.get(i).isIsclicked()==true){
				MusicReader.readmusic("click.wav");
				if(listTable(equipment).get(i/2-4).getPurchaseNumber()>0)
					listTable(equipment).get(i/2-4).setPurchaseNumber(
							listTable(equipment).get(i/2-4).getPurchaseNumber() - 1);
			}
			if(buttonList.get(i+1).isIsclicked()==true){
				MusicReader.readmusic("click.wav");
				listTable(equipment).get(i/2-4).setPurchaseNumber(
						listTable(equipment).get(i/2-4).getPurchaseNumber() + 1);
			}
		}
		
		for (GameButton button : buttonList)
			button.isRelesedButton(currentX, currentY);
		repaint();
	}

	public ArrayList<Equipment> listTable(String s){
		switch(s){
		case "armor":
		return armorList;
		case "helmet":
		return helmetList;
		case "glove":
		return gloveList;
		case "shoe":
			return shoeList;
		case "weapon":
			return weaponList;
		case "decoration":
			return decorationList;
		default:
			return null;
		}
	}
	
	//���밲�����Ӽ��ٰ�ť��set����
	public void setList(){
		//�������Ӽ��ٵİ�ť
		int x = 453, y = 200;
		for(int i=0;i<listTable(equipment).size();i++){
			GameButton decrease=new GameButton(x+90,y-10,7,12,
					Reader.readImage("sources/Shop/����1.png"),
					Reader.readImage("sources/Shop/����2.png"),
					Reader.readImage("sources/Shop/����2.png"),this);
			GameButton increase=new GameButton(x+130,y-10,7,12,
					Reader.readImage("sources/Shop/����1.png"),
					Reader.readImage("sources/Shop/����2.png"),
					Reader.readImage("sources/Shop/����2.png"),this);
			y+=20;
			buttonList.add(decrease);
			buttonList.add(increase);
		}
		
	}



	// ����ͼ��ķ���
	public void drawIcon(Graphics g) {
		// ����ͼ���ͼƬ
		g.drawImage(equipmentImage, 820, 200, this);
		// Ϊ��ͼ������������
		int x = 453, y = 200;
		int i=0;
		for (Equipment e : listTable(equipment)) {
			g.setFont(new Font("�Ķ��ֱָ��п�", Font.BOLD, 20));
			g.setColor(Color.white);
			g.drawString(e.getName(), x, y);
			g.drawString(" "+e.getReduceMoney(), x+180, y);
			g.drawString(" " + e.getNumber(), x + 260, y);
			g.drawString(" "+EquipmentPack.listTable(equipment).get(i).getNumberGOT(), x+300, y);
			g.drawString(" "+Money.getCoins(), 900, 20);
			g.drawString(" "+e.getPurchaseNumber(), x+102, y);
			y += 20;
			i++;
		}
		if(SaveAndLoad.zhang){
			g.drawImage(ani.get(0).image,
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
			g.drawImage(ani.get(1).image,
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
			g.drawImage(ani.get(2).image,
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
		
		g.drawImage(Reader.readImage("sources/Shop/��ť���/����_����.png"),200,0,this);
		if(message!=null)
		g.drawString(message, 453,610);
		if(messageplus!=null)
		g.drawString(messageplus, 453, 630);
		if(messageremark!=null)
		g.drawString(messageremark,730, 610);
		g.drawImage(Reader.readImage("sources/Shop/��ť���/Ǯ.png"), 880,0, this);
	}

	public void paint(Graphics g) {

		super.paint(g);
		// ������
		backgroundGraphics.drawImage(backgroundImage, 0, 0, this);
		
		// ����ͼ��
		drawIcon(backgroundGraphics);
		// ������ť���
		for (GameButton button : buttonList) {
			button.drawButton(backgroundGraphics);
		}
		backgroundGraphics.drawImage(mouse, currentX, currentY, this);
		// ���ػ���ͼ
		g.drawImage(background, 0, 0, this);
	}
	
	public ArrayList<String> saveEquipmentShopInfo(){
		ArrayList<String> equipmentShopInfo=new ArrayList<String>();
		for(Equipment e:EquipmentPack.helmetList)
			equipmentShopInfo.add(Integer.toString(e.getNumberGOT()));
		for(Equipment e:EquipmentPack.armorList)
			equipmentShopInfo.add(Integer.toString(e.getNumberGOT()));
		for(Equipment e:EquipmentPack.weaponList)
			equipmentShopInfo.add(Integer.toString(e.getNumberGOT()));
		for(Equipment e:EquipmentPack.gloveList)
			equipmentShopInfo.add(Integer.toString(e.getNumberGOT()));
		for(Equipment e:EquipmentPack.shoeList)
			equipmentShopInfo.add(Integer.toString(e.getNumberGOT()));
		for(Equipment e:EquipmentPack.decorationList)
			equipmentShopInfo.add(Integer.toString(e.getNumberGOT()));
		return equipmentShopInfo;
	}
	
	public void initialEquipmentShopInfo(ArrayList<String> equipmentShopInfo){
		int counter=0;
		for(int i=0+counter;i<helmetList.size();i++){
			helmetList.get(i).setNumberGOT(Integer.parseInt(equipmentShopInfo.get(i+counter)));
			counter++;
		}
		for(int i=0+counter;i<armorList.size();i++){
			armorList.get(i).setNumberGOT(Integer.parseInt(equipmentShopInfo.get(i+counter)));
			counter++;
		}
		for(int i=0+counter;i<weaponList.size();i++){
			weaponList.get(i).setNumberGOT(Integer.parseInt(equipmentShopInfo.get(i+counter)));
			counter++;
		}
		for(int i=0+counter;i<gloveList.size();i++){
			gloveList.get(i).setNumberGOT(Integer.parseInt(equipmentShopInfo.get(i+counter)));
			counter++;
		}
		for(int i=0+counter;i<shoeList.size();i++){
			shoeList.get(i).setNumberGOT(Integer.parseInt(equipmentShopInfo.get(i+counter)));
			counter++;
		}
		for(int i=0+counter;i<decorationList.size();i++){
			decorationList.get(i).setNumberGOT(Integer.parseInt(equipmentShopInfo.get(i+counter)));
			counter++;
		}
	}
}
