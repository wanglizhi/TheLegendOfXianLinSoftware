package start;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import tools.Reader;


public class StartAnimation {
		//������ͼƬ����
		Image[] array;
		//��ǰ��ʾ�Ķ���ͼƬ
		Image currentImage;
		//��¼��ǰ���Ű��ڼ���ͼƬ
		int i;
		//�����Ƿ������Ŷ������ź�
		boolean isStop;
		//�����ж϶����Ƿ񲥷�һ��ѭ�����ж�
		boolean isLoop;
		//���ö������ֵ�λ��
		int x,y;
		//���ö������ֵ����
		JPanel panel;
		
		public StartAnimation(int length,String s,JPanel panel,int x,int y){
			array=new Image[length];
			for(int i=0;i<length;i++){
				array[i]=Reader.readImage("sources/StartPanel/"+s+"/"+(i+1)+".png");
			}
			currentImage=array[0];
			this.panel=panel;
			this.x=x;
			this.y=y;
			//�����ɲ�����Ϊtrue
			isStop=true;
			//���Ƿ�Ϊһѭ����Ϊfalse
			isLoop=false;
		}
		
		public void updateImage(){
			if(i==array.length-1)
				isLoop=true;
			if(!isStop){
				currentImage=array[i];
				if(i<array.length-1)
					i++;
				else
					i=0;
			}
		}
		
		public void drawAnimation(Graphics g){
			g.drawImage(currentImage, x,y, panel);
		}
		
		public void startAnimation(){
			isStop=false;
		}
		
		public void stopButtonAnimation(){
			isStop=true;
			currentImage=array[0];
		}
		
		public void stopScorllAnimation(){
			isStop=true;
			isLoop=false;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public boolean isLoop() {
			return isLoop;
		}

		public void setLoop(boolean isLoop) {
			this.isLoop = isLoop;
		}
}
