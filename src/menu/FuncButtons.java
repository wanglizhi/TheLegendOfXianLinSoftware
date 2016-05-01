package menu;
import media.*;
import main.*;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

import start.LoadAndSavePanel;
public class FuncButtons {
	FatherPanel fp;
	//for saveButton
	MenuButton saveButton;

	//for readButton
	MenuButton readButton;

	//for setButton
	MenuButton setButton;
	MenuButton setBGM;
	MenuButton setClick;
	MenuButton setKey;

	MenuButton on_BGM;
	MenuButton off_BGM;
	MenuButton on_click;
	MenuButton off_click;

	//for returnButton
	MenuButton returnButton;

	//for exitButon
	MenuButton exitButton;
	MenuButton exitForSure;
	MenuButton restart;

	MenuButton[][] subButtonList=new MenuButton[5][];
	MenuButton[] buttonList=new MenuButton[5];


	public FuncButtons(FatherPanel a){
		fp=a;
		addButton();
	}

	private void addButton() {
		// TODO Auto-generated method stub

		int x_GameButton=400;
		int y_GameButton=150;
		int width_GameButton=88;
		int height_GameButton=50;
		int y_move=10;//偏移量
		int x_move=0;


		Image image1=new ImageIcon("sources/菜单/天书/存档1.png").getImage();
		Image image2=new ImageIcon("sources/菜单/天书/存档2.png").getImage();
		Image image3=new ImageIcon("sources/菜单/天书/存档3.png").getImage();

		saveButton=new MenuButton(x_GameButton, y_GameButton, width_GameButton, height_GameButton, image1, image2, image3, fp.menuPanel);

		image1=new ImageIcon("sources/菜单/天书/提取1.png").getImage();
		image2=new ImageIcon("sources/菜单/天书/提取2.png").getImage();
		image3=new ImageIcon("sources/菜单/天书/提取3.png").getImage();

		readButton=new MenuButton(x_GameButton+1*width_GameButton+x_move, y_GameButton, width_GameButton, height_GameButton, image1, image2, image3, fp.menuPanel);

		image1=new ImageIcon("sources/菜单/天书/设定1.png").getImage();
		image2=new ImageIcon("sources/菜单/天书/设定2.png").getImage();
		image3=new ImageIcon("sources/菜单/天书/设定3.png").getImage();
		setButton=new MenuButton(x_GameButton+2*width_GameButton+2*x_move, y_GameButton, width_GameButton, height_GameButton, image1, image2, image3, fp.menuPanel);


		image1=new ImageIcon("sources/菜单/天书/返回1.png").getImage();
		image2=new ImageIcon("sources/菜单/天书/返回2.png").getImage();
		image3=new ImageIcon("sources/菜单/天书/返回3.png").getImage();
		returnButton=new MenuButton(x_GameButton+3*width_GameButton+2*x_move, y_GameButton, width_GameButton, height_GameButton, image1, image2, image3, fp.menuPanel);

		image1=new ImageIcon("sources/菜单/天书/退出1.png").getImage();
		image2=new ImageIcon("sources/菜单/天书/退出2.png").getImage();
		image3=new ImageIcon("sources/菜单/天书/退出3.png").getImage();
		exitButton=new MenuButton(x_GameButton+4*width_GameButton+3*x_move, y_GameButton, width_GameButton, height_GameButton, image1, image2, image3, fp.menuPanel);

		buttonList[0]=saveButton;
		buttonList[1]=readButton;
		buttonList[2]=setButton;
		buttonList[3]=returnButton;
		buttonList[4]=exitButton;



		int y_MenuButton=230;
		int width_MenuButton=145;
		int height_MenuButton=40;

		int width_on=40;
		int height_on=40;


		image1=new ImageIcon("sources/菜单/天书/背景音乐1.png").getImage();
		image2=new ImageIcon("sources/菜单/天书/背景音乐2.png").getImage();
		image3=new ImageIcon("sources/菜单/天书/背景音乐3.png").getImage();
		setBGM=new MenuButton(x_GameButton+2*width_GameButton+2*x_move,y_MenuButton,width_MenuButton, height_MenuButton, image1, image2, image3, fp);

		image1=new ImageIcon("sources/菜单/天书/特殊音效1.png").getImage();
		image2=new ImageIcon("sources/菜单/天书/特殊音效2.png").getImage();
		image3=new ImageIcon("sources/菜单/天书/特殊音效3.png").getImage();
		setClick=new MenuButton(x_GameButton+2*width_GameButton+2*x_move,y_MenuButton+1*(y_move+height_MenuButton),width_MenuButton, height_MenuButton, image1, image2, image3, fp);

		image1=new ImageIcon("sources/菜单/天书/键盘设定1.png").getImage();
		image2=new ImageIcon("sources/菜单/天书/键盘设定2.png").getImage();
		image3=new ImageIcon("sources/菜单/天书/键盘设定3.png").getImage();
		setKey=new MenuButton(x_GameButton+2*width_GameButton+2*x_move,y_MenuButton+2*(y_move+height_MenuButton),width_MenuButton, height_MenuButton, image1, image2, image3, fp);

		image1=new ImageIcon("sources/菜单/天书/开1.png").getImage();
		image2=new ImageIcon("sources/菜单/天书/开2.png").getImage();
		image3=new ImageIcon("sources/菜单/天书/开3.png").getImage();
		on_BGM=new MenuButton(x_GameButton+4*width_GameButton+2*x_move,y_MenuButton-y_move,width_on, height_on, image1, image2, image3, fp);

		image1=new ImageIcon("sources/菜单/天书/关1.png").getImage();
		image2=new ImageIcon("sources/菜单/天书/关2.png").getImage();
		image3=new ImageIcon("sources/菜单/天书/关3.png").getImage();
		off_BGM=new MenuButton(x_GameButton+4*width_GameButton+2*x_move,y_MenuButton+2*y_move+10,width_on, height_on, image1, image2, image3, fp);

		image1=new ImageIcon("sources/菜单/天书/开1.png").getImage();
		image2=new ImageIcon("sources/菜单/天书/开2.png").getImage();
		image3=new ImageIcon("sources/菜单/天书/开3.png").getImage();
		on_click=new MenuButton(x_GameButton+4*width_GameButton+3*x_move,y_MenuButton-y_move+height_MenuButton,width_on, height_on, image1, image2, image3, fp);

		image1=new ImageIcon("sources/菜单/天书/关1.png").getImage();
		image2=new ImageIcon("sources/菜单/天书/关2.png").getImage();
		image3=new ImageIcon("sources/菜单/天书/关3.png").getImage();
		off_click=new MenuButton(x_GameButton+4*width_GameButton+3*x_move,y_MenuButton+2*y_move+10+height_MenuButton,width_on, height_on, image1, image2, image3, fp);


		image1=new ImageIcon("sources/菜单/天书/确认离开1.png").getImage();
		image2=new ImageIcon("sources/菜单/天书/确认离开2.png").getImage();
		image3=new ImageIcon("sources/菜单/天书/确认离开3.png").getImage();
		exitForSure=new MenuButton(x_GameButton+4*width_GameButton+4*x_move,y_MenuButton,width_MenuButton, height_MenuButton, image1, image2, image3, fp);

		image1=new ImageIcon("sources/菜单/天书/重新开始1.png").getImage();
		image2=new ImageIcon("sources/菜单/天书/重新开始2.png").getImage();
		image3=new ImageIcon("sources/菜单/天书/重新开始3.png").getImage();
		restart=new MenuButton(x_GameButton+4*width_GameButton+4*x_move,y_MenuButton+1*(y_move+height_MenuButton),width_MenuButton, height_MenuButton, image1, image2, image3, fp);


		subButtonList[1]=new MenuButton[2];//for set
		subButtonList[2]=new MenuButton[2];//for BGM
		subButtonList[3]=new MenuButton[2];//for Click
		subButtonList[4]=new MenuButton[2];//for exit


		subButtonList[1][0]=setBGM;
		subButtonList[1][1]=setClick;

		subButtonList[2][0]=on_BGM;
		subButtonList[2][1]=off_BGM;

		subButtonList[3][0]=on_click;
		subButtonList[3][1]=off_click;

		subButtonList[4][0]=exitForSure;
		subButtonList[4][1]=restart;
		for(int i=1;i<5;i++){
			for(MenuButton b:subButtonList[i])
				b.isDraw=MenuButton.No;

		}

	}


	/**
	 * 
	 */
	public void checkPressed(){

		for(MenuButton button:this.fp.menuPanel.command.buttonList){
			if(button.isclicked){
				for(int i=1;i<5;i++){
					for(MenuButton b:subButtonList[i])
						b.isDraw=MenuButton.No;

				}
			}
		}

		for(MenuButton button:buttonList){
			button.isPressedButton(fp.currentX, fp.currentY);
		}
		if(saveButton.isclicked){
			MusicReader.readmusic("换list.wav");
			for(int i=1;i<5;i++){
				for(MenuButton b:subButtonList[i])
					b.isDraw=MenuButton.No;

			}

			GameLauncher.lsPanel.setLastPanel("menu");
			GameLauncher.lsPanel.changeStateTo(LoadAndSavePanel.SAVE);
			GameLauncher.switchTo("ls");

		}else if(readButton.isclicked){
			MusicReader.readmusic("换list.wav");
			for(int i=1;i<5;i++){
				for(MenuButton b:subButtonList[i])
					b.isDraw=MenuButton.No;

			}
			GameLauncher.lsPanel.setLastPanel("menu");
			GameLauncher.lsPanel.changeStateTo(LoadAndSavePanel.LOAD);
			GameLauncher.switchTo("ls");
		}else if(setButton.isclicked){
			MusicReader.readmusic("换list.wav");
			for(int i=1;i<5;i++){
				for(MenuButton b:subButtonList[i])
					b.isDraw=MenuButton.No;
			}

			for(MenuButton button:subButtonList[1])
				button.isDraw=MenuButton.Yes;

		}else if(returnButton.isclicked){
			MusicReader.readmusic("换list.wav");
			for(int i=1;i<5;i++){
				for(MenuButton b:subButtonList[i])
					b.isDraw=MenuButton.No;
				GameLauncher.switchTo("scene");

			}

		}else if(exitButton.isclicked){
			MusicReader.readmusic("换list.wav");
			for(int i=1;i<5;i++){
				for(MenuButton b:subButtonList[i])
					b.isDraw=MenuButton.No;
			}
			for(MenuButton button:subButtonList[4])
				button.isDraw=MenuButton.Yes;
		}


		//////子button
		for(int i=1;i<5;i++){	
			for(MenuButton button:subButtonList[i])
				button.isPressedButton(fp.currentX, fp.currentY);
		}


		//3
		if(setBGM.isclicked){	MusicReader.readmusic("换list.wav");
		for(int i=1;i<5;i++){
			for(MenuButton b:subButtonList[i])
				b.isDraw=MenuButton.No;
		}
		for(MenuButton button:subButtonList[1])
			button.isDraw=MenuButton.Yes;
		for(MenuButton button:subButtonList[2])
			button.isDraw=MenuButton.Yes;
		}
		//4
		if(setClick.isclicked){	MusicReader.readmusic("换list.wav");

		for(MenuButton button:subButtonList[1])
			button.isDraw=MenuButton.Yes;
		for(MenuButton button:subButtonList[2])
			button.isDraw=MenuButton.No;
		for(MenuButton button:subButtonList[3])
			button.isDraw=MenuButton.Yes;
		for(MenuButton button:subButtonList[4])
			button.isDraw=MenuButton.No;
		}


		//5
		if(setKey.isIsclicked()){	MusicReader.readmusic("换list.wav");
		for(int i=1;i<5;i++){
			for(MenuButton b:subButtonList[i])
				b.isDraw=MenuButton.No;
		}
		for(MenuButton button:subButtonList[1])
			button.isDraw=MenuButton.Yes;
		//重新设置键盘

		}
		//6
		if(on_BGM.isIsclicked()){	MusicReader.readmusic("换list.wav");

		for(MenuButton button:subButtonList[1])
			button.isDraw=MenuButton.Yes;
		for(MenuButton button:subButtonList[2])
			button.isDraw=MenuButton.Yes;
		for(MenuButton button:subButtonList[3])
			button.isDraw=MenuButton.No;
		for(MenuButton button:subButtonList[4])
			button.isDraw=MenuButton.No;
		//开背景音乐
		MusicReader.openBGM();


		}
		//7
		if(off_BGM.isIsclicked()){	
			MusicReader.readmusic("换list.wav");

			for(MenuButton button:subButtonList[1])
				button.isDraw=MenuButton.Yes;
			for(MenuButton button:subButtonList[2])
				button.isDraw=MenuButton.Yes;
			for(MenuButton button:subButtonList[3])
				button.isDraw=MenuButton.No;
			for(MenuButton button:subButtonList[4])
				button.isDraw=MenuButton.No;
			//关背景音乐

			MusicReader.closeBGM();

		}
		//8
		if(on_click.isIsclicked()){	

			for(MenuButton button:subButtonList[1])
				button.isDraw=MenuButton.Yes;
			for(MenuButton button:subButtonList[2])
				button.isDraw=MenuButton.No;
			for(MenuButton button:subButtonList[3])
				button.isDraw=MenuButton.Yes;
			for(MenuButton button:subButtonList[4])
				button.isDraw=MenuButton.No;

			//开音乐
			MusicReader.openMusic();
			MusicReader.readmusic("换list.wav");
		}
		//9
		if(off_click.isIsclicked()){	

			for(MenuButton button:subButtonList[1])
				button.isDraw=MenuButton.Yes;
			for(MenuButton button:subButtonList[2])
				button.isDraw=MenuButton.No;
			for(MenuButton button:subButtonList[3])
				button.isDraw=MenuButton.Yes;
			for(MenuButton button:subButtonList[4])
				button.isDraw=MenuButton.No;
			//关音乐
			MusicReader.closeMusic();

		}

		//10
		if(restart.isIsclicked()){	
			MusicReader.readmusic("换list.wav");			


			for(MenuButton button:subButtonList[1])
				button.isDraw=MenuButton.No;
			for(MenuButton button:subButtonList[2])
				button.isDraw=MenuButton.No;
			for(MenuButton button:subButtonList[3])
				button.isDraw=MenuButton.No;
			for(MenuButton button:subButtonList[4])
				button.isDraw=MenuButton.Yes;
			//重新开始
		
			GameLauncher.switchTo("start");
		}
		//11
		if(exitForSure.isIsclicked()){	
			MusicReader.readmusic("换list.wav");

			for(int i=1;i<5;i++){
				for(MenuButton b:subButtonList[i])
					b.isDraw=MenuButton.No;
			}
			for(MenuButton button:subButtonList[4])
				button.isDraw=MenuButton.Yes;
			System.exit(0);
		}
	}


	//检查是否松开鼠标
	public void checkReleased(){
		//检验 击 按钮是否被按下
		for(MenuButton button:buttonList){
			button.isRelesedButton(fp.currentX, fp.currentY);
		}
		for(int i=1;i<5;i++){
			for(MenuButton button:subButtonList[i])
				button.isRelesedButton(fp.currentX, fp.currentY);
		}
	}
	public void checkMoveIn(){
		for(MenuButton button:buttonList){
			button.isMoveIn(fp.currentX, fp.currentY);	
		}
		for(int i=1;i<5;i++){
			for(MenuButton button:subButtonList[i])
				button.isMoveIn(fp.currentX, fp.currentY);
		}
	}

	public void drawFuncButtons(Graphics g){
		for(MenuButton button:buttonList){
			button.drawButton(g);
		}
		for(int i=1;i<5;i++){
			for(MenuButton button:subButtonList[i])
				button.drawButton(g);
		}
	}
}
