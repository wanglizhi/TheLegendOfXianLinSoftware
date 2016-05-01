package start;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import javax.swing.JPanel;

public class Mouse {
	//���Ķ���
	StartAnimation mouseAnimation;
	int[] pixels;
	Image image;
	Cursor transparentCursor;
	JPanel panel;


	public Mouse(StartAnimation animation,JPanel panel){
		this.mouseAnimation=animation;
		pixels = new int[256];
		image = Toolkit.getDefaultToolkit().createImage(
				new MemoryImageSource(16, 16, pixels, 0, 16));
		// ����һ��͸�����α�
		transparentCursor = Toolkit.getDefaultToolkit()
				.createCustomCursor(image, new Point(0, 0), "hidden");
		// ����͸���α꣬�Դ�ģ�����α�״̬
		panel.setCursor(transparentCursor);
	}
	
	public void startMouseAnimation(){
		mouseAnimation.startAnimation();
	}
	
	public StartAnimation getMouseAnimation() {
		return mouseAnimation;
	}

	public void setMouseAnimation(StartAnimation mouseAnimation) {
		this.mouseAnimation = mouseAnimation;
	}
}
