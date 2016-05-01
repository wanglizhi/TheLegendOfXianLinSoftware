package start;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import javax.swing.JPanel;

public class Mouse {
	//鼠标的动画
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
		// 制作一个透明的游标
		transparentCursor = Toolkit.getDefaultToolkit()
				.createCustomCursor(image, new Point(0, 0), "hidden");
		// 插入透明游标，以此模拟无游标状态
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
