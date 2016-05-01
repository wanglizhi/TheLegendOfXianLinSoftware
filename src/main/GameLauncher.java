package main;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import scene.*;
import media.MusicReader;
import menu.*;
import shop.*;
import start.*;
import battle.*;
import javax.swing.*;


public class GameLauncher extends JFrame implements KeyListener {
	
	private static final long serialVersionUID = 1L;
	//测试
	
	//框架容器
	static Container c;
	
	//开始界面
	public static StartPanel startPanel;
	//战斗界面
	public static BattlePanel battlePanel;
	//场景界面----
	public static ScenePanel scenePanel;
	//商店界面
	public static EquipmentShopPanel equipmentShopPanel;
	//药店
	public static ShopPanel shopPanel;
	//菜单
	public static MenuPanel menuPanel;
	// load和save的panel
	public static LoadAndSavePanel lsPanel;
	public static EndPanel endPanel;
	//我方的战斗单位
	public static ZhangXiaoFan zhangXiaoFan;
	public static YuJie yuJie;
	public static LuXueQi luXueQi;
	
 
	public static int SCENE_SIGNAL=0;

	public static CardLayout switcher=new CardLayout();

	public static JPanel currentPanel =new JPanel();
	  //构造方法
	  public GameLauncher(){
		MusicReader.closeBGM();
		c = this.getContentPane();
		startPanel=new StartPanel();
		
		scenePanel=new ScenePanel(this);
		battlePanel=new BattlePanel();
		//创建我方的三个对象
     	zhangXiaoFan=new ZhangXiaoFan(560, 160, battlePanel);
		yuJie=new YuJie(750, 150, battlePanel);
    	luXueQi=new LuXueQi(800, 330, battlePanel);
		menuPanel=new MenuPanel(zhangXiaoFan,luXueQi,yuJie);
		shopPanel=new ShopPanel();
		equipmentShopPanel=new EquipmentShopPanel();
		lsPanel = new LoadAndSavePanel();
		endPanel=new EndPanel();
		
		this.setLayout();
		this.addKeyListener(this);
		this.setTitle("仙林奇侠传");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
    //	this.setSize(1040,680);
    	this.setResizable(false);
    	this.pack();
	//	this.setMiddle();
		this.setVisible(true);
		currentPanel=null;
		switchTo("start");
	}
	  private void setLayout() {
			// TODO Auto-geerated method stub
			c.setLayout(switcher);
			c.add("startPanel", startPanel);
			c.add("scenePanel", scenePanel);
			c.add("battlePanel", battlePanel);
			c.add("shopPanel", shopPanel);
			c.add("equipmentShopPanel", equipmentShopPanel);
			c.add("menuPanel", menuPanel);	
			c.add("lsPanel", lsPanel);
			c.add("endPanel",endPanel);
			
		}	
   public  void init(){
	   c.remove(battlePanel);
	   c.remove(equipmentShopPanel);
	   c.remove(menuPanel);
	   c.remove(shopPanel);
		battlePanel=new BattlePanel();
		//创建我方的三个对象
    	zhangXiaoFan=new ZhangXiaoFan(560, 160, battlePanel);
		yuJie=new YuJie(750, 150, battlePanel);
   	luXueQi=new LuXueQi(800, 330, battlePanel);
		menuPanel=new MenuPanel(zhangXiaoFan,luXueQi,yuJie);
		shopPanel=new ShopPanel();
		equipmentShopPanel=new EquipmentShopPanel();
		c.add("battlePanel", battlePanel);
		c.add("shopPanel", shopPanel);
		c.add("equipmentShopPanel", equipmentShopPanel);
		c.add("menuPanel", menuPanel);	
		currentPanel=null;
		switchTo("start");
   }
	  
	  public static void switchTo(String panelName) {
			switch (panelName) {
			// 如果进入战斗界面的信号为1
			case "battle":
				switcher.show(c, "battlePanel");
				currentPanel = battlePanel;
				break;
			case "shop":
				// 如果进入商店界面的信号为1
				switcher.show(c, "shopPanel");
				currentPanel = shopPanel;
				break;
			case "equipmentShop":
				// 如果进入装备商店信号为1
				switcher.show(c, "equipmentShopPanel");
				currentPanel = equipmentShopPanel;
				break;
			case "scene":
				// 如果进入场景界面的信号为1
				switcher.show(c, "scenePanel");
				currentPanel = scenePanel;
				SCENE_SIGNAL=1;
				break;
			case "menu":
				// 如果进入菜单界面的信号为1
				switcher.show(c, "menuPanel");
				currentPanel = menuPanel;
				menuPanel.hero1.refreshValue();
				menuPanel.hero2.refreshValue();
				menuPanel.hero4.refreshValue();
				break;
			// 如果进入开始界面的信号为1
			case "start":
				switcher.show(c, "startPanel");
				currentPanel = startPanel;
				MusicReader.readBGM("主题曲.mp3");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MusicReader.openBGM();
				break;
			case "ls":
				// 如果进入存档界面的信号为1
				switcher.show(c, "lsPanel");
				currentPanel = lsPanel;
				break;
			case "end":
				switcher.show(c, "endPanel");
				endPanel.start();
				break;
			}
		}

	//使窗口居中显示
	public  void setMiddle(){
			Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
			int screenWidth=(int)screenSize.getWidth();
			int screenHeight=(int)screenSize.getHeight();			
			int x=(screenWidth-this.getWidth())/2;
			int y=(screenHeight-this.getHeight())/2;
			
			this.setLocation(x,y);
	}
	
	//处理（所有）键盘和鼠标监听事件
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
		
		}
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if(currentPanel==scenePanel){
			int keyCode=e.getKeyCode();
			scenePanel.keyPressed(keyCode,e.isControlDown());
			}
			if(currentPanel==lsPanel){
				int keyCode = e.getKeyCode();
				lsPanel.keyPressed(keyCode);
			}
			if(currentPanel==battlePanel){
			int keyCode=e.getKeyCode();
			battlePanel.keyPressed(keyCode);
			}
			
		}
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			if(currentPanel==scenePanel){

			int keyCode=e.getKeyCode();
			scenePanel.keyReleased(keyCode);
			}
		}
}
