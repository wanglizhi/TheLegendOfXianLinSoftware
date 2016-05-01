package battle;
import tools.*;
import java.awt.*;
import java.util.*;
//�α���
public class Mouse {
   //ͼƬ����
	Image mouseImage;
	//��ǰ��ͼƬ
	Image currentImage;
    //ͼƬ����
	ArrayList<Image> images =new ArrayList<Image>();
	//����
	int x;
	int y;
	//�Ƿ񻭳�
	boolean isDraw;
	//�Ƿ�ֹͣ
	boolean isStop;
	//ս���������
	BattlePanel bp;
	
	//���
	int code;
	
	//���췽��
	public Mouse(BattlePanel bp){
		this.bp=bp;
		x=bp.currentX;
		y=bp.currentY;
		getImage();
		isDraw=true;
		isStop=false;
	}
	
	//��ȡͼƬ
	public void getImage(){
		for(int i=1;i<=8;i++){
			mouseImage =Reader.readImage("image/���ͼ/"+i+".png");
			images.add(mouseImage);
		}
	}
	
	//�����α�
	public void drawMouse(Graphics g){
		if(isDraw){
		g.drawImage(currentImage, x, y, bp);
		}
	}
	
	//���·���
	public void update(){
		x=bp.currentX;
		y=bp.currentY;
		if(!isStop&&code<8){
			currentImage=images.get(code);
			code++;
		}
		else if(code==8){
			code=0;
		}
	}
}
