package menu;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
//���ڻ����µ����
public class Mouse {
	
	//����
	FatherPanel fp;
	
	Image mouseImage;//ͼƬ����
	Image currentImage;//��ǰ��ͼƬ
	ArrayList<Image> images =new ArrayList<Image>();//ͼƬ����
	int x,y;//����
	int code;//���
			
			//���췽��
	public Mouse(FatherPanel a){
		this.fp=a;
		x=fp.currentX;
		y=fp.currentY;
		getImage();
				
	}
			
			//��ȡͼƬ
	public void getImage(){
		for(int i=1;i<=8;i++){
			mouseImage =new ImageIcon("sources/�˵�/���ͼ/"+i+".png").getImage();
			images.add(mouseImage);
		}
		currentImage=images.get(0);
	}
			
			//�����α�
	public void drawMouse(Graphics g){
		g.drawImage(currentImage, x, y, fp);
	}
			
			//���·���
	public void update(){
		x=fp.currentX;
		y=fp.currentY;
		if(code<8){
			currentImage=images.get(code);
			code++;
		}
		else if(code==8){
			code=1;
		}
	}
	
}
