package start;
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import tools.*;

public class EndPanel extends JPanel implements Runnable{
private static final long serialVersionUID = 1L;
//±³¾°Í¼
Image back;
Image currentImage;
Image word;
Image blank;

int Width;
int Height;

BufferedImage bufferedImage;
Graphics bufferGraphics;

boolean isDraw;
boolean isStop;

int wordX;
int wordY;

int blankX;
int blankY;

int code;

public EndPanel(){
  Width=1024;
  Height=640;
  setPreferredSize(new Dimension(Width, Height));
  bufferedImage=new BufferedImage(Width, Height, BufferedImage.TYPE_INT_ARGB);
  bufferGraphics=bufferedImage.getGraphics();
  back=Reader.readImage("sources/End/ºÚÉ«±³¾°.png");
  word=Reader.readImage("sources/End/½áÊø×ÖÄ».png");
  blank=Reader.readImage("sources/End/½áÊø²àÀ¸.png");
  isDraw=false;
  isStop=true;
  wordX=0;
  wordY=640;
  blankX=700;
  blankY=-1920;
  code=1;

}

public void start(){
	Thread t=new Thread(this);
	t.start();
	isDraw=true;
	isStop=false;
}

public void paint(Graphics g){
	if(isDraw){
		bufferGraphics.drawImage(back, 0, 0, this);
		bufferGraphics.drawImage(currentImage, 0, 0, this);
		bufferGraphics.drawImage(word, wordX, wordY, this);
		bufferGraphics.drawImage(blank, blankX, blankY, this);
		g.drawImage(bufferedImage, 0, 0, this);
	}
}

public void update(){
if(!isStop){
    if(code<25){
	currentImage=Reader.readImage("sources/End/"+code+".jpg");
	code++;
}
if(code==25){
	currentImage=Reader.readImage("sources/End/"+code+".jpg");
	code=1;
}

if(wordY>-1280){
	wordY-=5;
	blankY+=5;
}
if(wordY==-1280){
	isStop=true;
}
    repaint();

  }
}

public void run() {
	while(true){
	try {
		Thread.sleep(100);

	  }   catch (Exception e) {
		e.printStackTrace();
	  }
    update();
  }
}
}
