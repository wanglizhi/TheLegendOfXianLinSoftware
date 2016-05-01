package menu;
import media.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import scene.*;
import javax.swing.ImageIcon;

import battle.*;

import tools.*;

public class Scoll {
	//��������
	FatherPanel fp;


	private int x_scoll=60+32;
	private int y_scoll=70;
	Image scollImage;

	MenuButton hero1;
	MenuButton hero2;

	MenuButton hero4;
	int x_head=x_scoll+14;
	int y_head=y_scoll+20;
	int width_head=40;
	int height_head=40;
	int hgap=10;

	Image currentHeroImage;
	int whichHero=1;
	static int zhangxiaofan=1;
	static int luxueqi=2;
	static int songdaren=3;
	static int yujie=4;

	Image image1;
	Image image2;
	Image image3;
	Image image4;
	int x_hero=x_scoll+38+50;
	int y_hero=y_scoll+41;
	int width_hero=176;
	int height_hero=131;

	ArrayList<GameButton> buttonList=new ArrayList<GameButton>();


	// ��ʾ��ǰ�ļ��� ���������

	String level=""+ZhangXiaoFan.level;

	int x_level=x_head+90;
	int y_level=y_scoll+70;
	Image level_ima;

	int x_num=x_level+30;
	int y_num=y_level+62;
	public Scoll(FatherPanel a){
		fp=a;
		addImage();
		initial();
	}
	private void addImage(){
		level_ima=new ImageIcon("sources/�˵�/scoll/�ȼ�.png").getImage();
	}
	private void initial() {

		image1=new ImageIcon("sources/�˵�/scoll/hero1.png").getImage();
		image2=new ImageIcon("sources/�˵�/scoll/hero12.png").getImage();
		image3=new ImageIcon("sources/�˵�/scoll/hero13.png").getImage();


		hero1=new MenuButton(x_head, y_head, width_head, height_head, image1, image2, image3, fp);
		hero1.isDraw=MenuButton.Yes;
		buttonList.add(hero1);

		image1=new ImageIcon("sources/�˵�/scoll/hero2.png").getImage();
		image2=new ImageIcon("sources/�˵�/scoll/hero22.png").getImage();
		image3=new ImageIcon("sources/�˵�/scoll/hero23.png").getImage();
		hero2=new MenuButton(x_head+width_head+hgap, y_head+6, width_head, height_head, image1, image2, image3, fp);
		hero2.isDraw=MenuButton.No;
		buttonList.add(hero2);

		image1=new ImageIcon("sources/�˵�/scoll/hero4.png").getImage();
		image2=new ImageIcon("sources/�˵�/scoll/hero42.png").getImage();
		image3=new ImageIcon("sources/�˵�/scoll/hero43.png").getImage();
		hero4=new MenuButton(x_head+2*(width_head+hgap), y_head, width_head, height_head, image1, image2, image3, fp);
		hero4.isDraw=MenuButton.No;
		buttonList.add(hero4);


		image1=new ImageIcon("sources/�˵�/scoll/����1.png").getImage();
		image2=new ImageIcon("sources/�˵�/scoll/����2.png").getImage();
		image3=new ImageIcon("sources/�˵�/scoll/����3.png").getImage();
		image4=new ImageIcon("sources/�˵�/scoll/����4.png").getImage();
		scollImage=image1;
		level=""+ZhangXiaoFan.level;
	}

	//����Ƿ��ƶ�������
	public void checkMoveIn(){

		if(SaveAndLoad.lu){
			hero2.isDraw=MenuButton.Yes;
		}
		if(SaveAndLoad.wen){
			hero4.isDraw=MenuButton.Yes;
		}
		for(GameButton button:buttonList){
			button.isMoveIn(fp.currentX, fp.currentY);	
		}
	}

	//�������Ƿ���
	public void checkPressed(){
		for(GameButton button:buttonList){
			button.isPressedButton(fp.currentX, fp.currentY);
		}
		
		if(hero1.isIsclicked()){
			scollImage=image1;
			whichHero=zhangxiaofan;
			level=""+ZhangXiaoFan.level;

			MusicReader.readmusic("��ͷ��.wav");
		}
		
			if(SaveAndLoad.lu){

				if(hero2.isIsclicked()){
					scollImage=image2;
					whichHero=luxueqi;
					level=""+LuXueQi.level;

					MusicReader.readmusic("��ͷ��.wav");
					}
			}

		
				if(SaveAndLoad.wen){

					if(hero4.isIsclicked()){
						scollImage=image4;
						whichHero=yujie;
						level=""+YuJie.level;
						MusicReader.readmusic("��ͷ��.wav");
						}
				}

	}

	//����Ƿ��ɿ����
	public void checkReleased(){
		//���� �� ��ť�Ƿ񱻰���
		for(GameButton button:buttonList){
			button.isRelesedButton(fp.currentX, fp.currentY);
		}
	}

	//��������̨
	public void drawScoll(Graphics g){
		if(SaveAndLoad.zhang){
			hero1.isDraw=MenuButton.Yes;
			level=""+ZhangXiaoFan.level;
		}
		if(SaveAndLoad.lu){
			hero2.isDraw=MenuButton.Yes;
		}
		if(SaveAndLoad.wen){
			hero4.isDraw=MenuButton.Yes;
		}

		
		g.drawImage(scollImage,x_scoll, y_scoll, fp);
		g.drawImage(level_ima, x_level, y_level, fp);
		g.setColor(Color.red);
		g.setFont(new Font("�Ķ��ֱָ��п�", Font.BOLD, 30));
		g.drawString(level, x_num, y_num);

		for(GameButton button:buttonList){
			button.drawButton(g);
		}


	}

}
