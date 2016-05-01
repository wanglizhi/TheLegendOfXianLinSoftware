package menu;
import media.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import tools.*;
import javax.swing.ImageIcon;


/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class Command {
	//各种panel的引用
	FatherPanel fp;

	//各种GameButton
	int width_of_GameButton=52;
	int height_of_GameButton=37;
	int x_of_GameButton=400;
	int y_of_GameButton=50;

	MenuButton thingButton;
	MenuButton equipButton;
	MenuButton magicButton;
	MenuButton funcButton;
	ArrayList<MenuButton> buttonList = new ArrayList<MenuButton>();
	MenuPanel menupanel;

	//构造函数
	public Command(MenuPanel menuPanel){
		this.menupanel=menuPanel;
		addGameButton();
	}
	public Command(FatherPanel a){
		this.fp=a;
		addGameButton();
	}
	private void addGameButton() {
		// TODO Auto-generated method stub

		Image image1=new ImageIcon("sources/菜单/菜单/标题物品1.png").getImage();
		Image image2=new ImageIcon("sources/菜单/菜单/标题物品2.png").getImage();
		Image image3=new ImageIcon("sources/菜单/菜单/标题物品3.png").getImage();


		thingButton=new MenuButton(x_of_GameButton, y_of_GameButton, width_of_GameButton, height_of_GameButton, image1, image2, image3, menupanel);
		buttonList.add(thingButton);

		image1=new ImageIcon("sources/菜单/菜单/标题装备1.png").getImage();
		image2=new ImageIcon("sources/菜单/菜单/标题装备2.png").getImage();
		image3=new ImageIcon("sources/菜单/菜单/标题装备3.png").getImage();


		equipButton=new MenuButton(x_of_GameButton+2*width_of_GameButton, y_of_GameButton, width_of_GameButton, height_of_GameButton, image1, image2, image3, menupanel);
		buttonList.add(equipButton);

		image1=new ImageIcon("sources/菜单/菜单/标题奇术1.png").getImage();
		image2=new ImageIcon("sources/菜单/菜单/标题奇术2.png").getImage();
		image3=new ImageIcon("sources/菜单/菜单/标题奇术3.png").getImage();


		magicButton=new MenuButton(x_of_GameButton+4*width_of_GameButton, y_of_GameButton, width_of_GameButton, height_of_GameButton, image1, image2, image3,menupanel);
		buttonList.add(magicButton);

		image1=new ImageIcon("sources/菜单/菜单/标题天书1.png").getImage();
		image2=new ImageIcon("sources/菜单/菜单/标题天书2.png").getImage();
		image3=new ImageIcon("sources/菜单/菜单/标题天书3.png").getImage();


		funcButton=new MenuButton(x_of_GameButton+6*width_of_GameButton, y_of_GameButton, width_of_GameButton, height_of_GameButton, image1, image2, image3, menupanel);
		buttonList.add(funcButton);
	}

	//检查是否移动鼠标进入控制台
	public void checkMoveIn(){
		for(MenuButton button:buttonList){
			button.isMoveIn(menupanel.currentX, menupanel.currentY);	
		}
	}

	//检查鼠标是否点击控制台
	public void checkPressed(){
		for(MenuButton button:buttonList){
			button.isPressedButton(menupanel.currentX, menupanel.currentY);
		}

		if(thingButton.isIsclicked()){
			MusicReader.readmusic("换list.wav");
			menupanel.switcher.show(menupanel, menupanel.thingPanel.getName());
			menupanel.currentPanel=menupanel.thingPanel;
		}
		else if(magicButton.isIsclicked()){

			MusicReader.readmusic("换list.wav");
			menupanel.switcher.show(menupanel, menupanel.magicPanel.getName());
			menupanel.currentPanel=menupanel.magicPanel;
		}
		else if(funcButton.isIsclicked()){

			MusicReader.readmusic("换list.wav");
			menupanel.switcher.show(menupanel, menupanel.funcPanel.getName());
			menupanel.currentPanel=menupanel.funcPanel;
		}
		else if(equipButton.isIsclicked()){

			MusicReader.readmusic("换list.wav");
			menupanel.switcher.show(menupanel, menupanel.equipPanel.getName());
			menupanel.currentPanel=menupanel.equipPanel;
		}
	}

	//检查是否松开鼠标
	public void checkReleased(){
		//检验 击 按钮是否被按下
		for(MenuButton button:buttonList){
			button.isRelesedButton(menupanel.currentX, menupanel.currentY);
		}
	}

	//画出控制台
	public void drawCommand(Graphics g){
		String s="";
		if(Reader.task!=null){
			s=Reader.task;
		}else {
			s="无";
		}
		Image image=new ImageIcon("sources/菜单/菜单/标题栏.png").getImage();
		g.drawImage(image, 0, y_of_GameButton, menupanel);
		g.setColor(Color.white);
		g.setFont(new Font("文鼎粗钢笔行楷",Font.BOLD,25));
		g.drawString("当前任务:"+s, 10, y_of_GameButton-13);
		for(MenuButton button:buttonList){
			button.drawButton(g);
		}
	}
}
