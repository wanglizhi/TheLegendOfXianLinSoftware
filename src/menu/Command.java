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
	//����panel������
	FatherPanel fp;

	//����GameButton
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

	//���캯��
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

		Image image1=new ImageIcon("sources/�˵�/�˵�/������Ʒ1.png").getImage();
		Image image2=new ImageIcon("sources/�˵�/�˵�/������Ʒ2.png").getImage();
		Image image3=new ImageIcon("sources/�˵�/�˵�/������Ʒ3.png").getImage();


		thingButton=new MenuButton(x_of_GameButton, y_of_GameButton, width_of_GameButton, height_of_GameButton, image1, image2, image3, menupanel);
		buttonList.add(thingButton);

		image1=new ImageIcon("sources/�˵�/�˵�/����װ��1.png").getImage();
		image2=new ImageIcon("sources/�˵�/�˵�/����װ��2.png").getImage();
		image3=new ImageIcon("sources/�˵�/�˵�/����װ��3.png").getImage();


		equipButton=new MenuButton(x_of_GameButton+2*width_of_GameButton, y_of_GameButton, width_of_GameButton, height_of_GameButton, image1, image2, image3, menupanel);
		buttonList.add(equipButton);

		image1=new ImageIcon("sources/�˵�/�˵�/��������1.png").getImage();
		image2=new ImageIcon("sources/�˵�/�˵�/��������2.png").getImage();
		image3=new ImageIcon("sources/�˵�/�˵�/��������3.png").getImage();


		magicButton=new MenuButton(x_of_GameButton+4*width_of_GameButton, y_of_GameButton, width_of_GameButton, height_of_GameButton, image1, image2, image3,menupanel);
		buttonList.add(magicButton);

		image1=new ImageIcon("sources/�˵�/�˵�/��������1.png").getImage();
		image2=new ImageIcon("sources/�˵�/�˵�/��������2.png").getImage();
		image3=new ImageIcon("sources/�˵�/�˵�/��������3.png").getImage();


		funcButton=new MenuButton(x_of_GameButton+6*width_of_GameButton, y_of_GameButton, width_of_GameButton, height_of_GameButton, image1, image2, image3, menupanel);
		buttonList.add(funcButton);
	}

	//����Ƿ��ƶ����������̨
	public void checkMoveIn(){
		for(MenuButton button:buttonList){
			button.isMoveIn(menupanel.currentX, menupanel.currentY);	
		}
	}

	//�������Ƿ�������̨
	public void checkPressed(){
		for(MenuButton button:buttonList){
			button.isPressedButton(menupanel.currentX, menupanel.currentY);
		}

		if(thingButton.isIsclicked()){
			MusicReader.readmusic("��list.wav");
			menupanel.switcher.show(menupanel, menupanel.thingPanel.getName());
			menupanel.currentPanel=menupanel.thingPanel;
		}
		else if(magicButton.isIsclicked()){

			MusicReader.readmusic("��list.wav");
			menupanel.switcher.show(menupanel, menupanel.magicPanel.getName());
			menupanel.currentPanel=menupanel.magicPanel;
		}
		else if(funcButton.isIsclicked()){

			MusicReader.readmusic("��list.wav");
			menupanel.switcher.show(menupanel, menupanel.funcPanel.getName());
			menupanel.currentPanel=menupanel.funcPanel;
		}
		else if(equipButton.isIsclicked()){

			MusicReader.readmusic("��list.wav");
			menupanel.switcher.show(menupanel, menupanel.equipPanel.getName());
			menupanel.currentPanel=menupanel.equipPanel;
		}
	}

	//����Ƿ��ɿ����
	public void checkReleased(){
		//���� �� ��ť�Ƿ񱻰���
		for(MenuButton button:buttonList){
			button.isRelesedButton(menupanel.currentX, menupanel.currentY);
		}
	}

	//��������̨
	public void drawCommand(Graphics g){
		String s="";
		if(Reader.task!=null){
			s=Reader.task;
		}else {
			s="��";
		}
		Image image=new ImageIcon("sources/�˵�/�˵�/������.png").getImage();
		g.drawImage(image, 0, y_of_GameButton, menupanel);
		g.setColor(Color.white);
		g.setFont(new Font("�Ķ��ֱָ��п�",Font.BOLD,25));
		g.drawString("��ǰ����:"+s, 10, y_of_GameButton-13);
		for(MenuButton button:buttonList){
			button.drawButton(g);
		}
	}
}
