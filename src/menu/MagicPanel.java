package menu;

import java.awt.*;
import java.util.ArrayList;
import media.MusicReader;
import battle.*;
import tools.Reader;
public class MagicPanel extends FatherPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6674008507522389308L;
	magicDiscription discription;
	MagicAnimation currentAnimation;
	//MagicAnimation animationDiscription;
	ArrayList<MagicAnimation> zhang_animation=new ArrayList<MagicAnimation>();
	ArrayList<MagicAnimation> lu_animation=new ArrayList<MagicAnimation>();
	ArrayList<MagicAnimation> song_animation=new ArrayList<MagicAnimation>();
	ArrayList<MagicAnimation> yu_animation=new ArrayList<MagicAnimation>();
	
	
	ArrayList<MenuButton> buttonList1=new ArrayList<MenuButton>();
	ArrayList<MenuButton> buttonList2=new ArrayList<MenuButton>();
	ArrayList<MenuButton> buttonList3=new ArrayList<MenuButton>();
	ArrayList<MenuButton> buttonList4=new ArrayList<MenuButton>();
	ArrayList<ArrayList<MenuButton>> buttonList=new ArrayList<ArrayList<MenuButton>>();
	MenuButton button_lu_1;
	MenuButton button_lu_2;
	MenuButton button_lu_3;
	MenuButton button_lu_4;
	MenuButton button_lu_5;
	
	MenuButton button_zhang_1;
	MenuButton button_zhang_2;
	MenuButton button_zhang_3;
	MenuButton button_zhang_4;
	MenuButton button_zhang_5;
	
	MenuButton button_song_1;
	MenuButton button_song_2;
	MenuButton button_song_3;
	MenuButton button_song_4;
	MenuButton button_song_5;
	
	MenuButton button_yu_1;
	MenuButton button_yu_2;
	MenuButton button_yu_3;
	MenuButton button_yu_4;
	MenuButton button_yu_5;
	



	public MagicPanel(MenuPanel a,ZhangXiaoFan h1,LuXueQi h2,YuJie h4) {
		super(a,h1,h2,h4);
		this.setName("magicPanel");
		scoll=new Scoll(this);
		discription=new magicDiscription();
		addMagicButton();
		addMagicAnimation();
	
		
	}

	private void addMagicButton() {
		// TODO Auto-generated method stub
		int x=320+12;
		int y=180;
		int width=220;
		int height=50;
		int vgap=14;
		
		//zhangxiaofan
				Image image1=Reader.readImage("sources/菜单/奇术/横剑摆渡1.png");
				Image image2=Reader.readImage("sources/菜单/奇术/横剑摆渡2.png");
				Image image3=Reader.readImage("sources/菜单/奇术/横剑摆渡3.png");
				button_zhang_1=new MenuButton(x, y, width, height, image1, image2, image3, this);
				buttonList1.add(button_zhang_1);
				

				image1=Reader.readImage("sources/菜单/奇术/浪里寻花1.png");
				image2=Reader.readImage("sources/菜单/奇术/浪里寻花2.png");
				image3=Reader.readImage("sources/菜单/奇术/浪里寻花3.png");
				button_zhang_2=new MenuButton(x, y+height+vgap, width, height, image1, image2, image3, this);
				buttonList1.add(button_zhang_2);

				image1=Reader.readImage("sources/菜单/奇术/银鹰掠地1.png");
				image2=Reader.readImage("sources/菜单/奇术/银鹰掠地2.png");
				image3=Reader.readImage("sources/菜单/奇术/银鹰掠地3.png");
				button_zhang_3=new MenuButton(x, y+2*(height+vgap), width, height, image1, image2, image3, this);
				buttonList1.add(button_zhang_3);
				
				image1=Reader.readImage("sources/菜单/奇术/龙翔九天1.png");
				image2=Reader.readImage("sources/菜单/奇术/龙翔九天2.png");
				image3=Reader.readImage("sources/菜单/奇术/龙翔九天3.png");
				button_zhang_4=new MenuButton(x, y+3*(height+vgap), width, height, image1, image2, image3, this);
				buttonList1.add(button_zhang_4);
				
				image1=Reader.readImage("sources/菜单/奇术/神剑傲州1.png");
				image2=Reader.readImage("sources/菜单/奇术/神剑傲州2.png");
				image3=Reader.readImage("sources/菜单/奇术/神剑傲州3.png");
				button_zhang_5=new MenuButton(x, y+4*(height+vgap), width, height, image1, image2, image3, this);
				buttonList1.add(button_zhang_5);
				
		//luxueqi
				image1=Reader.readImage("sources/菜单/奇术/灵凤吐珠1.png");
				image2=Reader.readImage("sources/菜单/奇术/灵凤吐珠2.png");
				image3=Reader.readImage("sources/菜单/奇术/灵凤吐珠3.png");
				button_lu_1=new MenuButton(x, y, width, height, image1, image2, image3, this);
				buttonList2.add(button_lu_1);
				

				image1=Reader.readImage("sources/菜单/奇术/踏月无痕1.png");
				image2=Reader.readImage("sources/菜单/奇术/踏月无痕2.png");
				image3=Reader.readImage("sources/菜单/奇术/踏月无痕3.png");
				button_lu_2=new MenuButton(x, y+height+vgap, width, height, image1, image2, image3, this);
				buttonList2.add(button_lu_2);
				
				image1=Reader.readImage("sources/菜单/奇术/星火乾坤圈1.png");
				image2=Reader.readImage("sources/菜单/奇术/星火乾坤圈2.png");
				image3=Reader.readImage("sources/菜单/奇术/星火乾坤圈3.png");
				button_lu_3=new MenuButton(x, y+2*(height+vgap), width, height, image1, image2, image3, this);
				buttonList2.add(button_lu_3);
				
				image1=Reader.readImage("sources/菜单/奇术/亟电崩离1.png");
				image2=Reader.readImage("sources/菜单/奇术/亟电崩离2.png");
				image3=Reader.readImage("sources/菜单/奇术/亟电崩离3.png");
				button_lu_4=new MenuButton(x, y+3*(height+vgap), width, height, image1, image2, image3, this);
				buttonList2.add(button_lu_4);
				
				image1=Reader.readImage("sources/菜单/奇术/劈云追月1.png");
				image2=Reader.readImage("sources/菜单/奇术/劈云追月2.png");
				image3=Reader.readImage("sources/菜单/奇术/劈云追月3.png");
				button_lu_5=new MenuButton(x, y+4*(height+vgap), width, height, image1, image2, image3, this);
				buttonList2.add(button_lu_5);
		
		

		//songdaren
				image1=Reader.readImage("sources/菜单/奇术/碎金削玉1.png");
				image2=Reader.readImage("sources/菜单/奇术/碎金削玉2.png");
				image3=Reader.readImage("sources/菜单/奇术/碎金削玉3.png");
				
			
				button_song_1=new MenuButton(x, y, width, height, image1, image2, image3, this);
				buttonList3.add(button_song_1);
				
				image1=Reader.readImage("sources/菜单/奇术/剑心如意1.png");
				image2=Reader.readImage("sources/菜单/奇术/剑心如意2.png");
				image3=Reader.readImage("sources/菜单/奇术/剑心如意3.png");
				
				button_song_2=new MenuButton(x, y+height+vgap, width, height, image1, image2, image3, this);
				buttonList3.add(button_song_2);
				
				image1=Reader.readImage("sources/菜单/奇术/猛虎出关1.png");
				image2=Reader.readImage("sources/菜单/奇术/猛虎出关2.png");
				image3=Reader.readImage("sources/菜单/奇术/猛虎出关3.png");
				
				button_song_3=new MenuButton(x, y+2*(height+vgap), width, height, image1, image2, image3, this);
				buttonList3.add(button_song_3);
			
				
				image1=Reader.readImage("sources/菜单/奇术/鬼斧神工1.png");
				image2=Reader.readImage("sources/菜单/奇术/鬼斧神工2.png");
				image3=Reader.readImage("sources/菜单/奇术/鬼斧神工3.png");
			
				button_song_4=new MenuButton(x, y+3*(height+vgap), width, height, image1, image2, image3, this);
				buttonList3.add(button_song_4);
				
				image1=Reader.readImage("sources/菜单/奇术/开山破海1.png");
				image2=Reader.readImage("sources/菜单/奇术/开山破海2.png");
				image3=Reader.readImage("sources/菜单/奇术/开山破海3.png");
				button_song_5=new MenuButton(x, y+4*(height+vgap), width, height, image1, image2, image3, this);
				buttonList3.add(button_song_5);
		
		
		//yujie
				
				image1=Reader.readImage("sources/菜单/奇术/伏虎冲天1.png");
				image2=Reader.readImage("sources/菜单/奇术/伏虎冲天2.png");
				image3=Reader.readImage("sources/菜单/奇术/伏虎冲天3.png");
				
				
		button_yu_1=new MenuButton(x, y, width, height, image1, image2, image3, this);
		buttonList4.add(button_yu_1);
		
		image1=Reader.readImage("sources/菜单/奇术/追星破月1.png");
		image2=Reader.readImage("sources/菜单/奇术/追星破月2.png");
		image3=Reader.readImage("sources/菜单/奇术/追星破月3.png");
		
		button_yu_2=new MenuButton(x, y+height+vgap, width, height, image1, image2, image3, this);
		buttonList4.add(button_yu_2);

		image1=Reader.readImage("sources/菜单/奇术/苍龙盖天1.png");
		image2=Reader.readImage("sources/菜单/奇术/苍龙盖天2.png");
		image3=Reader.readImage("sources/菜单/奇术/苍龙盖天3.png");
		
		button_yu_3=new MenuButton(x, y+2*(height+vgap), width, height, image1, image2, image3, this);
		buttonList4.add(button_yu_3);
		
		image1=Reader.readImage("sources/菜单/奇术/妙手回春1.png");
		image2=Reader.readImage("sources/菜单/奇术/妙手回春2.png");
		image3=Reader.readImage("sources/菜单/奇术/妙手回春3.png");
		
		button_yu_4=new MenuButton(x, y+3*(height+vgap), width, height, image1, image2, image3, this);
		buttonList4.add(button_yu_4);
		
		image1=Reader.readImage("sources/菜单/奇术/蝶影神灵1.png");
		image2=Reader.readImage("sources/菜单/奇术/蝶影神灵2.png");
		image3=Reader.readImage("sources/菜单/奇术/蝶影神灵3.png");
		button_yu_5=new MenuButton(x, y+4*(height+vgap), width, height, image1, image2, image3, this);
		buttonList4.add(button_yu_5);
		
		buttonList.add(buttonList1);
		buttonList.add(buttonList2);
		buttonList.add(buttonList3);
		buttonList.add(buttonList4);
		for(MenuButton button:buttonList3){
			button.isDraw=0;
		}
	
	
		
	}



	//初始化创建 避免了每次都读取图片;
	private void addMagicAnimation(){
		
			int x=70+32,y=10;
			int a=538;
			int b=202;
			int vgap=64;
			
			currentAnimation=new MagicAnimation(1,1,37,x,y,magicDiscription.zhang[0],a,b,this);
			zhang_animation.add(currentAnimation);
			currentAnimation=new MagicAnimation(1,2,31,x,y,magicDiscription.zhang[1],a,b+vgap,this);
			zhang_animation.add(currentAnimation);
			currentAnimation=new MagicAnimation(1,3,31,x,y,magicDiscription.zhang[2],a,b+2*vgap,this);
			zhang_animation.add(currentAnimation);
			currentAnimation=new MagicAnimation(1,4,44,x,y,magicDiscription.zhang[3],a,b+3*vgap,this);
			zhang_animation.add(currentAnimation);
			currentAnimation=new MagicAnimation(1,5,42,x,y,magicDiscription.zhang[4],a,b+4*vgap,this);
			zhang_animation.add(currentAnimation);
			
		
			currentAnimation=new MagicAnimation(2,1,17,x,y,magicDiscription.lu[0],a,b,this);
			lu_animation.add(currentAnimation);
			currentAnimation=new MagicAnimation(2,2,23,x,y,magicDiscription.lu[1],a,b+vgap,this);
			lu_animation.add(currentAnimation);
			currentAnimation=new MagicAnimation(2,3,21,x,y,magicDiscription.lu[2],a,b+2*vgap,this);
			lu_animation.add(currentAnimation);
			currentAnimation=new MagicAnimation(2,4,29,x,y,magicDiscription.lu[3],a,b+3*vgap,this);
			lu_animation.add(currentAnimation);
			currentAnimation=new MagicAnimation(2,5,29,x,y,magicDiscription.lu[4],a,b+4*vgap,this);
			lu_animation.add(currentAnimation);

		

			
			currentAnimation=new MagicAnimation(4,1,39,x,y,magicDiscription.yu[0],a,b,this);
			yu_animation.add(currentAnimation);
			currentAnimation=new MagicAnimation(4,2,32,x,y,magicDiscription.yu[1],a,b+vgap,this);
			yu_animation.add(currentAnimation);
			currentAnimation=new MagicAnimation(4,3,28,x,y,magicDiscription.yu[2],a,b+2*vgap,this);
			yu_animation.add(currentAnimation);
			currentAnimation=new MagicAnimation(4,4,16,x,y,magicDiscription.yu[3],a,b+3*vgap,this);
			yu_animation.add(currentAnimation);
			currentAnimation=new MagicAnimation(4,5,41,x,y,magicDiscription.yu[4],a,b+4*vgap,this);
			yu_animation.add(currentAnimation);
			
	}
	@Override
	public void readBackgroundImage() {
		
		backgroundImage = Reader.readImage("sources/菜单/奇术/奇术.png");

	}
	@Override
	public void drawThisPanel(Graphics g) {
	
		
		switch(this.scoll.whichHero){
		case 1:
			for(int i=0;i<ZhangXiaoFan.skillNumber;i++){
				buttonList1.get(i).isDraw=1;
			}
			for(int i=ZhangXiaoFan.skillNumber-1;i<5;i++){
				buttonList1.get(i).isDraw=0;
			}
			for(MenuButton button:buttonList2){
				button.isDraw=MenuButton.No;
			}
		
			for(MenuButton button:buttonList4){
				button.isDraw=MenuButton.No;
			}
		
			break;
		case 2:
			for(MenuButton button:buttonList1){
				button.isDraw=MenuButton.No;
			}
			for(int i=0;i<LuXueQi.skillNumber;i++){
				buttonList2.get(i).isDraw=1;
			}
			for(int i=LuXueQi.skillNumber-1;i<5;i++){
				buttonList2.get(i).isDraw=0;
			}
			
			for(MenuButton button:buttonList4){
				button.isDraw=MenuButton.No;
			}
		
			break;

		case 4:
			for(MenuButton button:buttonList1){
				button.isDraw=MenuButton.No;
			}
			for(MenuButton button:buttonList2){
				button.isDraw=MenuButton.No;
			}
			for(int i=0;i<YuJie.skillNumber;i++){
				buttonList4.get(i).isDraw=1;
			}
			for(int i=YuJie.skillNumber-1;i<5;i++){
				buttonList4.get(i).isDraw=0;
			}
		
			break;
		default : break;
		}

		for(ArrayList<MenuButton> list:buttonList){
			for(MenuButton button:list){
				button.drawButton(g);
			}
		}
		
		if(currentAnimation!=null){
				currentAnimation.drawMagicAnimation(g);
		}
	
	}

	public void update() {
		// TODO Auto-generated method stub
		if(currentAnimation!=null){
			currentAnimation.update();
			if(currentAnimation!=null){
			if(currentAnimation.code==currentAnimation.length){
				currentAnimation.code=1;
				currentAnimation=null;
			}
			}
		}
	}
	


	@Override
	public void drawSpecialImage(Graphics g) {
		// TODO Auto-generated method stub
	}


	
	@Override
	public void checkAllButtonReleased() {
		// TODO Auto-generated method stub
	
		scoll.checkReleased();
		for(ArrayList<MenuButton> list:buttonList){
			for(MenuButton button:list){
				button.isRelesedButton(currentX, currentY);
			}
		}
		
		
	}
	@Override
	public void checkAllButtonMoveIn() {
		// TODO Auto-generated method stub
		
		scoll.checkMoveIn();
		switch(this.scoll.whichHero){
		case 1:
			for(MenuButton button:buttonList1)
				button.isMoveIn(currentX, currentY);
			break;
		case 2:
			for(MenuButton button:buttonList2)
				button.isMoveIn(currentX, currentY);
			break;
		case 3:
			for(MenuButton button:buttonList3)
				button.isMoveIn(currentX, currentY);
			break;
		case 4:
			for(MenuButton button:buttonList4)
				button.isMoveIn(currentX, currentY);
			break;
		default: break;
			
		}
	
//		
	}
	@Override
	public void checkAllButtonPressed() {
		// TODO Auto-generated method stub
		
		scoll.checkPressed();
		
		switch(this.scoll.whichHero){
		case 1:
			for(MenuButton button:buttonList1)
				button.isPressedButton(currentX, currentY);
			break;
		case 2:
			for(MenuButton button:buttonList2)
				button.isPressedButton(currentX, currentY);
			break;
		case 3:
			for(MenuButton button:buttonList3)
				button.isPressedButton(currentX, currentY);
			break;
		case 4:
			for(MenuButton button:buttonList4)
				button.isPressedButton(currentX, currentY);
			break;
		default: break;
			
		}
		currentAnimation=null;
		//检查每个magic按钮是否被按下；
	
		for(int i=0;i<5;i++)
			if(buttonList1.get(i).isIsclicked()){
				currentAnimation=zhang_animation.get(i);
				MusicReader.readmusic("张小凡攻击(2).wav");break;
			}
		for(int i=0;i<5;i++)
			if(buttonList2.get(i).isIsclicked()){
				currentAnimation=lu_animation.get(i);MusicReader.readmusic("陆雪琪攻击(2).wav");break;
			}
		
		for(int i=0;i<5;i++)
			if(buttonList4.get(i).isIsclicked()){
				currentAnimation=yu_animation.get(i);MusicReader.readmusic("文敏攻击(2).wav");break;
			}
		
	}

	@Override
	public ArrayList<String> saveEquipInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initialEquipInfo(ArrayList<String> menuInfo) {
		// TODO Auto-generated method stub
		
	}


	

}
