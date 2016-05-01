package menu;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import battle.*;
import tools.*;


public class FuncPanel extends FatherPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3823920650738643403L;
	FuncButtons fb;

	public FuncPanel(MenuPanel a,ZhangXiaoFan h1,LuXueQi h2,YuJie h4 ) {
		super(a,h1,h2,h4);
		fb=new FuncButtons(this);
		this.setName("funcPanel");
		
	}
	@Override
	public void drawSpecialImage(Graphics g) {
		// TODO Auto-generated method stub
		int x=543;
		int y=300;
		Image image=new ImageIcon("sources/菜单/主人公4人2.png").getImage();
		g.drawImage(image, x, y, this);
	}
	@Override
	public void readBackgroundImage() {
		// TODO Auto-generated method stub
		backgroundImage = Reader.readImage("sources/菜单/天书/天书.png");
	}

	

	@Override
	public void checkAllButtonReleased() {
		// TODO Auto-generated method stub
	
		fb.checkReleased();
	}

	@Override
	public void checkAllButtonMoveIn() {
		// TODO Auto-generated method stub
		
		fb.checkMoveIn();
	}

	@Override
	public void checkAllButtonPressed() {
		// TODO Auto-generated method stub
		
		fb.checkPressed();
	}
	@Override

	public void drawThisPanel(Graphics g) {
		// TODO Auto-generated method stub
		fb.drawFuncButtons(g);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ArrayList<String> saveEquipInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void initialEquipInfo(ArrayList<String> menuInfo) {
		// TODO Auto-generated method stub
		
	}
	
}
