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
	//����
	
	//�������
	static Container c;
	
	//��ʼ����
	public static StartPanel startPanel;
	//ս������
	public static BattlePanel battlePanel;
	//��������----
	public static ScenePanel scenePanel;
	//�̵����
	public static EquipmentShopPanel equipmentShopPanel;
	//ҩ��
	public static ShopPanel shopPanel;
	//�˵�
	public static MenuPanel menuPanel;
	// load��save��panel
	public static LoadAndSavePanel lsPanel;
	public static EndPanel endPanel;
	//�ҷ���ս����λ
	public static ZhangXiaoFan zhangXiaoFan;
	public static YuJie yuJie;
	public static LuXueQi luXueQi;
	
 
	public static int SCENE_SIGNAL=0;

	public static CardLayout switcher=new CardLayout();

	public static JPanel currentPanel =new JPanel();
	  //���췽��
	  public GameLauncher(){
		MusicReader.closeBGM();
		c = this.getContentPane();
		startPanel=new StartPanel();
		
		scenePanel=new ScenePanel(this);
		battlePanel=new BattlePanel();
		//�����ҷ�����������
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
		this.setTitle("����������");
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
		//�����ҷ�����������
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
			// �������ս��������ź�Ϊ1
			case "battle":
				switcher.show(c, "battlePanel");
				currentPanel = battlePanel;
				break;
			case "shop":
				// ��������̵������ź�Ϊ1
				switcher.show(c, "shopPanel");
				currentPanel = shopPanel;
				break;
			case "equipmentShop":
				// �������װ���̵��ź�Ϊ1
				switcher.show(c, "equipmentShopPanel");
				currentPanel = equipmentShopPanel;
				break;
			case "scene":
				// ������볡��������ź�Ϊ1
				switcher.show(c, "scenePanel");
				currentPanel = scenePanel;
				SCENE_SIGNAL=1;
				break;
			case "menu":
				// �������˵�������ź�Ϊ1
				switcher.show(c, "menuPanel");
				currentPanel = menuPanel;
				menuPanel.hero1.refreshValue();
				menuPanel.hero2.refreshValue();
				menuPanel.hero4.refreshValue();
				break;
			// ������뿪ʼ������ź�Ϊ1
			case "start":
				switcher.show(c, "startPanel");
				currentPanel = startPanel;
				MusicReader.readBGM("������.mp3");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MusicReader.openBGM();
				break;
			case "ls":
				// �������浵������ź�Ϊ1
				switcher.show(c, "lsPanel");
				currentPanel = lsPanel;
				break;
			case "end":
				switcher.show(c, "endPanel");
				endPanel.start();
				break;
			}
		}

	//ʹ���ھ�����ʾ
	public  void setMiddle(){
			Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
			int screenWidth=(int)screenSize.getWidth();
			int screenHeight=(int)screenSize.getHeight();			
			int x=(screenWidth-this.getWidth())/2;
			int y=(screenHeight-this.getHeight())/2;
			
			this.setLocation(x,y);
	}
	
	//�������У����̺��������¼�
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
