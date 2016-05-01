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

	// 缓冲图
	final Image background;
	Graphics backgroundGraphics;
	// 鼠标指针的图形
	final Image[] mouses = new Image[8];

	// 背景
	final Image backgroundImage;
	Image mouse;
	//装备的图画
	Image equipmentImage;
	
	// 游标的当前位置
	int currentX = 0;
	int currentY = 0;
	// 偏移量
	int move = 5;
	

	// 装备店专属属性
	ArrayList<Equipment> helmetList = new ArrayList<Equipment>();
	ArrayList<Equipment> armorList = new ArrayList<Equipment>();
	ArrayList<Equipment> weaponList = new ArrayList<Equipment>();
	ArrayList<Equipment> gloveList = new ArrayList<Equipment>();
	ArrayList<Equipment> shoeList = new ArrayList<Equipment>();
	ArrayList<Equipment> decorationList = new ArrayList<Equipment>();
	
	//决定先出现哪个栏目的重要字符串
	String equipment="weapon"; 
	
	//店主说的话
	String message="欢迎来到金陵大学装备自选超市";
	String messageplus;
	String messageremark;
	//创建动画
	ArrayList<ShopAnimation> ani=new ArrayList<ShopAnimation>();

	// 各种按钮
	GameButton buy = new GameButton(445, 75, 149, 40,
			Reader.readImage("sources/Shop/购买1.png"),
			Reader.readImage("sources/Shop/购买2.png"),
			Reader.readImage("sources/Shop/购买3.png"), this);
	GameButton sell = new GameButton(594,75,149,40,
			Reader.readImage("sources/Shop/卖出1.png"),
			Reader.readImage("sources/Shop/卖出2.png"),
			Reader.readImage("sources/Shop/卖出3.png"),this);
	GameButton back = new GameButton(880,20,149,40,
			Reader.readImage("sources/Shop/返回游戏 (1).png"),
			Reader.readImage("sources/Shop/返回游戏 (2).png"),
			Reader.readImage("sources/Shop/返回游戏 (3).png"),this);
	int x=448,y=133;
	GameButton weapon= new GameButton(x, y, 47, 20, 
			Reader.readImage("sources/Shop/按钮组件/武器1.png"), 
			Reader.readImage("sources/Shop/按钮组件/武器1.png"), 
			Reader.readImage("sources/Shop/按钮组件/武器2.png"), this);
	GameButton helmet= new GameButton(x+47*1, y, 47, 20, 
			Reader.readImage("sources/Shop/按钮组件/头盔1.png"), 
			Reader.readImage("sources/Shop/按钮组件/头盔1.png"), 
			Reader.readImage("sources/Shop/按钮组件/头盔2.png"), this);
	GameButton armor= new GameButton(x+47*2, y, 47, 20, 
			Reader.readImage("sources/Shop/按钮组件/盔甲1.png"), 
			Reader.readImage("sources/Shop/按钮组件/盔甲1.png"), 
			Reader.readImage("sources/Shop/按钮组件/盔甲2.png"), this);
	GameButton glove= new GameButton(x+47*3, y, 47, 20, 
			Reader.readImage("sources/Shop/按钮组件/护臂1.png"), 
			Reader.readImage("sources/Shop/按钮组件/护臂1.png"), 
			Reader.readImage("sources/Shop/按钮组件/护臂2.png"), this);
	GameButton shoe= new GameButton(x+47*4, y, 47, 20, 
			Reader.readImage("sources/Shop/按钮组件/靴子1.png"), 
			Reader.readImage("sources/Shop/按钮组件/靴子1.png"), 
			Reader.readImage("sources/Shop/按钮组件/靴子2.png"), this);
	GameButton decoration= new GameButton(x+47*5, y, 47, 20, 
			Reader.readImage("sources/Shop/按钮组件/饰品1.png"), 
			Reader.readImage("sources/Shop/按钮组件/饰品1.png"), 
			Reader.readImage("sources/Shop/按钮组件/饰品2.png"), this);
	//创建保存按钮的arraylist
	ArrayList<GameButton> buttonList = new ArrayList<GameButton>();
	
	public EquipmentShopPanel(){
		// 设定大小
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		// 背景缓冲
		background = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_ARGB);
		// 读入图片
		backgroundImage = Reader.readImage("sources/Shop/shopback.png");
		for (int i = 1; i <= 8; i++) {
			mouse = Reader.readImage("image/鼠标图/"+i+".png");
			mouses[i - 1] = mouse;
		}
		// 初始背景为黑色
		setBackground(new Color(0, 0, 0));

		backgroundGraphics = background.getGraphics();

		// 设定鼠标监听
		setMouse();

		//设置人物动画
		ani.add(new ShopAnimation("张小凡", 0, 0,8,this));
		ani.add(new ShopAnimation("陆雪琪",0,160,8,this));
		ani.add(new ShopAnimation("文敏",0,320,8,this));
		ani.add(new ShopAnimation("小妹",364,515,8,this));
		
		// 鼠标动画
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
		// 开启动画
		mouseAnimation.start();
		// 读入装备list
		helmetList=ShopReader.readEquipment("头");
		for (Equipment equipment : helmetList)
			equipment.setNumber((int) (Math.random() * 10));
		armorList=ShopReader.readEquipment("盔甲");
		for (Equipment equipment : armorList) 
			equipment.setNumber((int) (Math.random() * 10));
		gloveList=ShopReader.readEquipment("手");
		for (Equipment equipment : gloveList) 
			equipment.setNumber((int) (Math.random() * 10));
		shoeList=ShopReader.readEquipment("脚");
		for (Equipment equipment : shoeList) 
			equipment.setNumber((int) (Math.random() * 10));
		decorationList=ShopReader.readEquipment("饰品");
		for (Equipment equipment :decorationList) 
			equipment.setNumber((int) (Math.random() * 10));
		weaponList=ShopReader.readEquipment("武器");
		for (Equipment equipment : weaponList)
			equipment.setNumber((int) (Math.random() * 10));
		
		//读入按钮
		buttonList.add(sell);
		buttonList.add(buy);
		buttonList.add(back);
		buttonList.add(weapon);
		buttonList.add(helmet);
		buttonList.add(armor);
		buttonList.add(glove);
		buttonList.add(shoe);
		buttonList.add(decoration);
		//读入增加减少的按钮
		//读入增加减少的按钮
		int x = 453, y = 200;
		for(int i=0;i<listTable(equipment).size();i++){
			GameButton decrease=new GameButton(x+90,y-10,7,12,
					Reader.readImage("sources/Shop/减少1.png"),
					Reader.readImage("sources/Shop/减少2.png"),
					Reader.readImage("sources/Shop/减少2.png"),this);
			GameButton increase=new GameButton(x+130,y-10,7,12,
					Reader.readImage("sources/Shop/增加1.png"),
					Reader.readImage("sources/Shop/增加2.png"),
					Reader.readImage("sources/Shop/增加2.png"),this);
			y+=20;
			buttonList.add(decrease);
			buttonList.add(increase);
		}
	}	

	//判断是否移动到对应的区域
	private void isMoveIn() {
		int oringinY = 180;
		for (int i = 0; i < listTable(equipment).size(); i++) {
			if (currentX > 440 && currentX < 795 && currentY > oringinY
					&& currentY < oringinY + 20) {
				equipmentImage = listTable(equipment).get(i).getPicture();
				message="体力+"+listTable(equipment).get(i).getAddPhysicalPower()+
						"敏捷+"+listTable(equipment).get(i).getAddAgile()	+					
						"武力+"+listTable(equipment).get(i).getAddStrength()+
						"精气+"+listTable(equipment).get(i).getAddSpirit();
				if(listTable(equipment).get(i).getReduceMoney()<10000)
					messageplus="真是物美价廉啊,";
				else if(listTable(equipment).get(i).getReduceMoney()<30000)
					messageplus="好东西啊，但是有点贵啊,";
				else if(listTable(equipment).get(i).getReduceMoney()<100000)
					messageplus="绝对是当世之宝器,";

				if(listTable(equipment).get(i).getUser()==0)
					messageremark="每个人都能使用";
				if(listTable(equipment).get(i).getUser()==1)
					messageremark="但是只有张小凡可以使用";
				if(listTable(equipment).get(i).getUser()==2)
					messageremark="但是只有陆雪琪可以使用";
				if(listTable(equipment).get(i).getUser()==3)
					messageremark="但是只有文敏可以使用";
			}
			oringinY += 20;
		}
	}
	//设置鼠标监听
	// 设置鼠标监听
	public void setMouse() {
		int[] pixels = new int[256];
		Image image = Toolkit.getDefaultToolkit().createImage(
				new MemoryImageSource(16, 16, pixels, 0, 16));
		// 制作一个透明的游标
		Cursor transparentCursor = Toolkit.getDefaultToolkit()
				.createCustomCursor(image, new Point(0, 0), "hidden");
		// 插入透明游标，以此模拟无游标状态
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
	
	//设置按钮按下后的事件
	public void setButton(){
		//改变选择的类别
		if(weapon.isIsclicked()==true){
			MusicReader.readmusic("换list.wav");
			equipment="weapon";
			//去除多余的按钮
			for(int i=buttonList.size()-1;i>=9;i--)
				buttonList.remove(buttonList.get(i));
			setList();
			}
		if(armor.isIsclicked()==true){
			MusicReader.readmusic("换list.wav");
			equipment="armor";
			//去除多余的按钮
			for(int i=buttonList.size()-1;i>=9;i--)
				buttonList.remove(buttonList.get(i));
			setList();
		}
		if(helmet.isIsclicked()==true){
			MusicReader.readmusic("换list.wav");
			equipment="helmet";
			//去除多余的按钮
			for(int i=buttonList.size()-1;i>=9;i--)
				buttonList.remove(buttonList.get(i));
			setList();
		}
		if(shoe.isIsclicked()==true){
			MusicReader.readmusic("换list.wav");
			equipment="shoe";
			//去除多余的按钮
			for(int i=buttonList.size()-1;i>=9;i--)
				buttonList.remove(buttonList.get(i));
			setList();
		}
		if(decoration.isIsclicked()==true){
			MusicReader.readmusic("换list.wav");
			equipment="decoration";
			//去除多余的按钮
			for(int i=buttonList.size()-1;i>=9;i--)
				buttonList.remove(buttonList.get(i));
			setList();
		}
		if(glove.isIsclicked()==true){
			MusicReader.readmusic("换list.wav");
			equipment="glove";
			//去除多余的按钮
			for(int i=buttonList.size()-1;i>=9;i--)
				buttonList.remove(buttonList.get(i));
			setList();
		}
		//触发购买事件
		if(buy.isIsclicked()==true){
			MusicReader.readmusic("Clip986.wav");
			for(int i=0;i<listTable(equipment).size();i++){
				int temp=Math.min(listTable(equipment).get(i).getPurchaseNumber(), listTable(equipment).get(i).getNumber());
				EquipmentPack.listTable(equipment).get(i).setNumberGOT(EquipmentPack.listTable(equipment).get(i).getNumberGOT()+temp);
				//计算剩余的钱
				Money.setCoins(Money.coins-listTable(equipment).get(i).getReduceMoney()*temp);
				//还剩的东西减少
				listTable(equipment).get(i).setNumber(listTable(equipment).get(i).getNumber()-temp);

			}
			if(Money.getCoins()<0){
				for(int i=0;i<listTable(equipment).size();i++){
					int temp=Math.min(listTable(equipment).get(i).getPurchaseNumber(), listTable(equipment).get(i).getNumber());
					EquipmentPack.listTable(equipment).get(i).setNumberGOT(EquipmentPack.listTable(equipment).get(i).getNumberGOT()-temp);
					//计算剩余的钱
					Money.setCoins(Money.coins+listTable(equipment).get(i).getReduceMoney()*temp);
					//还剩的东西减少
					listTable(equipment).get(i).setNumber(listTable(equipment).get(i).getNumber()+temp);
				}
				message="哎呀,小兄弟,你的钱不顾了,要省着点花啊";
				messageplus=null;
				messageremark=null;
			}
			for(int i=0;i<listTable(equipment).size();i++)
			listTable(equipment).get(i).setPurchaseNumber(0);
		}
		//触发卖出事件
		if(sell.isIsclicked()==true){
			MusicReader.readmusic("Clip986.wav");
			for(int i=0;i<listTable(equipment).size();i++){
				int temp=Math.min(listTable(equipment).get(i).getPurchaseNumber(), EquipmentPack.listTable(equipment).get(i).getNumberGOT());
				EquipmentPack.listTable(equipment).get(i).setNumberGOT(EquipmentPack.listTable(equipment).get(i).getNumberGOT()-temp);
				//计算剩余的钱
				Money.setCoins(Money.coins+listTable(equipment).get(i).getReduceMoney()*temp);
				//还剩的东西减少
				listTable(equipment).get(i).setNumber(listTable(equipment).get(i).getNumber()+temp);
				listTable(equipment).get(i).setPurchaseNumber(0);
			}
		}	
		if(back.isIsclicked()==true){
			MusicReader.readmusic("换头像.wav");
			GameLauncher.switchTo("scene");
		}
		//触发交易量增加减少的事件 
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
	
	//读入安排增加减少按钮的set方法
	public void setList(){
		//读入增加减少的按钮
		int x = 453, y = 200;
		for(int i=0;i<listTable(equipment).size();i++){
			GameButton decrease=new GameButton(x+90,y-10,7,12,
					Reader.readImage("sources/Shop/减少1.png"),
					Reader.readImage("sources/Shop/减少2.png"),
					Reader.readImage("sources/Shop/减少2.png"),this);
			GameButton increase=new GameButton(x+130,y-10,7,12,
					Reader.readImage("sources/Shop/增加1.png"),
					Reader.readImage("sources/Shop/增加2.png"),
					Reader.readImage("sources/Shop/增加2.png"),this);
			y+=20;
			buttonList.add(decrease);
			buttonList.add(increase);
		}
		
	}



	// 画出图标的方法
	public void drawIcon(Graphics g) {
		// 读入图标的图片
		g.drawImage(equipmentImage, 820, 200, this);
		// 为画图而给出的坐标
		int x = 453, y = 200;
		int i=0;
		for (Equipment e : listTable(equipment)) {
			g.setFont(new Font("文鼎粗钢笔行楷", Font.BOLD, 20));
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
		g.drawString("体", 55, 30+0*150);
		g.drawImage(Reader.readImage("sources/Shop/按钮组件/体力.png"),60,30+0*150,this);
		g.drawString("敏", 55, 30+0*150+20);
		g.drawImage(Reader.readImage("sources/Shop/按钮组件/敏捷.png"),60,30+0*150+20,this);
		g.drawString("武", 55, 30+0*150+40);
		g.drawImage(Reader.readImage("sources/Shop/按钮组件/武力.png"),60,30+0*150+40,this);
		g.drawString("精", 55, 30+0*150+60);
		g.drawImage(Reader.readImage("sources/Shop/按钮组件/精气.png"),60,30+0*150+60,this);
		}
		if(SaveAndLoad.lu){
			g.drawImage(ani.get(1).image,
					ani.get(1).getAnimationX(),ani.get(1).getAnimationY(),this);
		g.drawString("体", 55, 30+1*150);
		g.drawImage(Reader.readImage("sources/Shop/按钮组件/体力.png"),60,30+1*150,this);
		g.drawString("敏", 55, 30+1*150+20);
		g.drawImage(Reader.readImage("sources/Shop/按钮组件/敏捷.png"),60,30+1*150+20,this);
		g.drawString("武", 55, 30+1*150+40);
		g.drawImage(Reader.readImage("sources/Shop/按钮组件/武力.png"),60,30+1*150+40,this);
		g.drawString("精", 55, 30+1*150+60);
		g.drawImage(Reader.readImage("sources/Shop/按钮组件/精气.png"),60,30+1*150+60,this);
		}
		if(SaveAndLoad.wen){
			g.drawImage(ani.get(2).image,
					ani.get(2).getAnimationX(),ani.get(2).getAnimationY(),this);
		g.drawString("体", 55, 30+2*150);
		g.drawImage(Reader.readImage("sources/Shop/按钮组件/体力.png"),60,30+2*150,this);
		g.drawString("敏", 55, 30+2*150+20);
		g.drawImage(Reader.readImage("sources/Shop/按钮组件/敏捷.png"),60,30+2*150+20,this);
		g.drawString("武", 55, 30+2*150+40);
		g.drawImage(Reader.readImage("sources/Shop/按钮组件/武力.png"),60,30+2*150+40,this);
		g.drawString("精", 55, 30+2*150+60);
		g.drawImage(Reader.readImage("sources/Shop/按钮组件/精气.png"),60,30+2*150+60,this);
		}
		backgroundGraphics.drawImage(ani.get(3).image,
				ani.get(3).getAnimationX(),ani.get(3).getAnimationY(),this);
		
		g.drawImage(Reader.readImage("sources/Shop/按钮组件/招牌_副本.png"),200,0,this);
		if(message!=null)
		g.drawString(message, 453,610);
		if(messageplus!=null)
		g.drawString(messageplus, 453, 630);
		if(messageremark!=null)
		g.drawString(messageremark,730, 610);
		g.drawImage(Reader.readImage("sources/Shop/按钮组件/钱.png"), 880,0, this);
	}

	public void paint(Graphics g) {

		super.paint(g);
		// 画背景
		backgroundGraphics.drawImage(backgroundImage, 0, 0, this);
		
		// 画出图标
		drawIcon(backgroundGraphics);
		// 画出按钮组件
		for (GameButton button : buttonList) {
			button.drawButton(backgroundGraphics);
		}
		backgroundGraphics.drawImage(mouse, currentX, currentY, this);
		// 加载缓存图
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
