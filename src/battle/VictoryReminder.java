package battle;

import java.awt.*;
import tools.*;
import java.util.*;
import shop.*;
import main.GameLauncher;
//战胜结束后的提示
public class VictoryReminder {
    //底部图片引用
	Image back;
	//获得物品底部图片
	Image thingBack;
	int thing_dx1;
	int thing_dy1;
	int thing_dx2;
	int thing_dy2;
	int thing_sx1;
	int thing_sy1;
	int thing_sx2;
	int thing_sy2;
    //图片集合
	ArrayList<Image> images=new ArrayList<Image>();
	//张小凡
	Image zhang1;
	Image zhang2;
	//文敏
	Image wen1;
	Image wen2;
	//陆雪琪
	Image lu1;
	Image lu2;
	//宋大仁
	Image song1;
	Image song2;
    //升级图片
    Image levelUpImage;
    //获得物品的图片
    Image getThingImage;
    //是否画出
    boolean isDraw;
    //是否停止
    boolean isStop;
    
    //第一次图片是否画出
    boolean firstIsDraw;
    //第二次图片是否画出
    boolean secondIsDraw;
    //升级小图片是否画出
    boolean levelUpIsDraw;
    //获得物品图片是否画出
    boolean getThingIsDraw;
    
    //第一次字符是否显示
    boolean firstString;
    //第二次字符是否显示
    boolean secondString;
    //第三次字符是否显示
    boolean thirdString;
    
    //计时编号
    int timeCode;
    
    //所要显示字符的集合
    ArrayList<Integer> showNums=new ArrayList<Integer>();
    
    //战斗面板引用
    BattlePanel bp;
    
  //目标矩形第一个角的坐标
  	int dx1;
  	int dy1;
  	//目标矩形第二个角的坐标
  	int dx2;
  	int dy2;
  	//源矩形第一个角的坐标
  	int sx1;
  	int sy1;
  	//源矩形第二个角的坐标
  	int sx2;
  	int sy2;
  	
  	//第一张图片坐标
  	int firstX;
  	int firstY;
  	
  	//获得经验坐标
  	int firstStringX;
  	int firstStringY;
  	
  	//体力坐标
  	int secondStringX;
  	int secondStringY;
  	
  	//第一个获得物品坐标
  	int thirdStringX;
  	int thirdStringY;
  	
  	//升级图片坐标
  	int levelUpX;
  	int levelUpY;
  	
  	//获得物品图片坐标
  	int thingX;
  	int thingY;
  	
  	//当前战斗可以获得的经验值
  	int expToGet;
  	//当前战斗可以获得的物品
  	ArrayList<String> things=new ArrayList<String>();
  	//当前战斗可以获得的金钱
  	int moneyToGet;
  	
  	//构造方法
  	public VictoryReminder(BattlePanel bp){
  		this.bp=bp;
  		
  		isDraw=false;
  		isStop=true;
  		
  		dx1=412;
  		dy1=80;
  		dx2=612;
  		dy2=80;
  		
  		sx1=0;
  		sy1=0;
  		sx2=200;
  		sy2=0;
  		
  		thing_dx1=710;
  		thing_dy1=155;
  		thing_dx2=710;
  		thing_dy2=155;
  		thing_sx1=60;
  		thing_sy1=75;
  		thing_sx2=60;
  		thing_sy2=75;
  		
  		levelUpX=412+75;
  		levelUpY=80+60;
  		
  		thingX=660;
  		thingY=80;
  		
  		firstX=412;
  		firstY=160;
  		
  		firstStringX=532;
  		firstStringY=80+120;
  		
  		secondStringX=412+90;
  		secondStringY=80+100;
  		
  		thirdStringX=690;
  		thirdStringY=80+70;
  		
  		loadImage();
  		getInformation();
  	}
  	
  	//载入图片
  	public void loadImage(){
  		back=Reader.readImage("image/战斗胜利/0副本.png");
  		thingBack=Reader.readImage("image/战斗胜利/物品.png");
  		levelUpImage=Reader.readImage("image/战斗胜利/9.png");
  		getThingImage=Reader.readImage("image/战斗胜利/获得物品.png");
  		for(int i=1;i<=8;i++){
  			//0--3是第一组 4--7是第二组
  			Image image=Reader.readImage("image/战斗胜利/"+i+".png");
  			images.add(image);
  		}
  	   zhang1=images.get(0);
  	   zhang2=images.get(4);
  	   wen1=images.get(1);
  	   wen2=images.get(5);
  	   lu1=images.get(2);
  	   lu2=images.get(6);
  	   song1=images.get(3);
  	   song2=images.get(7);
  	}
  	
  	//得到战斗信息
  	public void getInformation(){
  		//计算当前战斗可以获得多少经验,金钱,物品
  		for(Enemy e:bp.enemies){
  			expToGet+=e.exp;
  			moneyToGet+=e.money;
  			things.add(e.thing);
  		}
        
  		//得到数据 (0--2是各个英雄当前升级所需经验 3--6是张小凡的数据 7--10是文敏
  		//11--14是陆雪琪)
  		Integer i=ZhangXiaoFan.expToLevelUp-ZhangXiaoFan.exp;
  		if(i<0){
  			i=0;
  		}
  		showNums.add(i);
  		i=YuJie.expToLevelUp-YuJie.exp;
  		if(i<0){
  			i=0;
  		}
  		showNums.add(i);
  		i=LuXueQi.expToLevelUp-LuXueQi.exp;
  		if(i<0){
  			i=0;
  		}
  		showNums.add(i);
  		
  		i=ZhangXiaoFan.physicalPower;
  		showNums.add(i);
  		i=ZhangXiaoFan.sprit;
  		showNums.add(i);
  		i=ZhangXiaoFan.agile;
  		showNums.add(i);
  		i=ZhangXiaoFan.strength;
  		showNums.add(i);
  		
  		i=YuJie.physicalPower;
  		showNums.add(i);
  		i=YuJie.sprit;
  		showNums.add(i);
  		i=YuJie.agile;
  		showNums.add(i);
  		i=YuJie.strength;
  		showNums.add(i);
  		
  		i=LuXueQi.physicalPower;
  		showNums.add(i);
  		i=LuXueQi.sprit;
  		showNums.add(i);
  		i=LuXueQi.agile;
  		showNums.add(i);
  		i=LuXueQi.strength;
  		showNums.add(i);
  	}
  	
  	//属性增加的动画效果
  	public void addValue(int start,Hero hero){
  		if(showNums.get(start)<hero.getPhysicalPower()){
  			showNums.set(start, showNums.get(start)+1);
  		}
  		if(showNums.get(start+1)<hero.getSprit()){
  			showNums.set(start+1, showNums.get(start+1)+1);
  		}
  		if(showNums.get(start+2)<hero.getAgile()){
  			showNums.set(start+2, showNums.get(start+2)+1);
  		}
  		if(showNums.get(start+3)<hero.getStrength()){
  			showNums.set(start+3, showNums.get(start+3)+1);
  		}
  	}
  	
  	//画出
  	public void drawVictoryReminder(Graphics g){
  		if(isDraw){
  			g.drawImage(back, dx1,dy1, dx2, dy2, sx1, sy1, sx2, sy2, bp);
  			g.drawImage(thingBack, thing_dx1, thing_dy1, thing_dx2,thing_dy2,thing_sx1,thing_sy1,thing_sx2,thing_sy2,bp);
  			if(firstIsDraw){
  				if(bp.zxf!=null){
  					g.drawImage(zhang1, firstX, firstY, bp);
  				}
  				if(bp.yj!=null){
  					g.drawImage(wen1, firstX, firstY+100, bp);
  				}
  				if(bp.lxq!=null){
  					g.drawImage(lu1, firstX, firstY+100*2,bp);
  				}
  			}
  			if(secondIsDraw){
  				if(bp.zxf!=null&&bp.zxf.isLevelUp==true){
  					g.drawImage(zhang2, firstX, firstY, bp);
  				}
  				if(bp.yj!=null&&bp.yj.isLevelUp==true){
  					g.drawImage(wen2, firstX, firstY+100, bp);
  				}
  				if(bp.lxq!=null&&bp.lxq.isLevelUp==true){
  					g.drawImage(lu2, firstX, firstY+100*2, bp);
  				}
  			}
  		   if(firstString){
  			   if(bp.zxf!=null){
  				g.drawString(expToGet+"", firstStringX, firstStringY);   
  				g.drawString(showNums.get(0)+"", firstStringX, firstStringY+30);
  			   }
  			   if(bp.yj!=null){
  				g.drawString(expToGet+"", firstStringX, firstStringY+100);   
   				g.drawString(showNums.get(1)+"", firstStringX, firstStringY+100+30);
  			   }
  			   if(bp.lxq!=null){
  				g.drawString(expToGet+"", firstStringX, firstStringY+100*2);
  			    g.drawString(showNums.get(2)+"", firstStringX, firstStringY+100*2+30);
  			   }
  		   }
  		   
  		   if(secondString){
  			   if(bp.zxf!=null&&bp.zxf.isLevelUp==true){   
  				  for(int i=3;i<=6;i++){
  					 g.drawString(showNums.get(i)+"", secondStringX, secondStringY+(i-3)*20); 
  				  }
  			   }
  			   
  			 if(bp.yj!=null&&bp.yj.isLevelUp==true){
  				for(int i=7;i<=10;i++){
 					 g.drawString(showNums.get(i)+"", secondStringX, secondStringY+(i-7)*20+100); 
 				  } 
  			 }
  			 
  			 if(bp.lxq!=null&&bp.lxq.isLevelUp==true){
   				for(int i=11;i<=14;i++){
  					 g.drawString(showNums.get(i)+"", secondStringX, secondStringY+(i-11)*20+100*2); 
  				  } 
   			 }
  		   }
  		   if(thirdString){
  			   for(int i=0;i<things.size();i++){
  				   g.drawString(things.get(i).split("/")[0], thirdStringX, thirdStringY+i*20);
  			   }
  			   g.drawString("金钱 "+moneyToGet, thirdStringX, thirdStringY+things.size()*20);
  		   }
  		   
  		   if(levelUpIsDraw){
  			   g.drawImage(levelUpImage, levelUpX, levelUpY, bp);
  		   }
  		   if(getThingIsDraw){
  			   g.drawImage(getThingImage, thingX, thingY, bp);
  		   }
  		}
  	}
  	
  	//更新
  	public void update(){
  		if(!isStop){
  			if(sy2<480){
  				dy2+=20;
  				sy2+=20;
  			}
  			if(thing_sx1>0){
  				thing_dx1-=4;
  				thing_dx2+=4;
  				thing_sx1-=4;
  				thing_sx2+=4;
  				thing_dy1-=5;
  				thing_dy2+=5;
  				thing_sy1-=5;
  				thing_sy2+=5;
  			}
  			if(thing_sx1==4){
  				 //获得金钱和物品
  				for(String s:things){
  					switch(s.split("/")[1]){
  					case "1":
  						DrugPack.addDrug(s.split("/")[0], 1);
  						break;
  					case "2":
  						EquipmentPack.addEqupment(s.split("/")[0], 1);
  						break;
  					}
  				}
  				Money.addCoins(moneyToGet);
  			}
  			if(thing_sx1==0){
  				getThingIsDraw=true;
  				thirdString=true;
  			}
  			if(sy2==480){
  				if(secondIsDraw==false&&secondString==false){
  				firstIsDraw=true;
  				firstString=true;
  				}
  				if(timeCode<10){
  					timeCode++;
  				}
  				if(timeCode==10){
  					//开始进行经验值的减少动画
  					if(expToGet>0){
  					   expToGet-=40;
  					for(int i=0;i<=2;i++){
  						if(showNums.get(i)>0){
  					         showNums.set(i,showNums.get(i)-40);
  						}else{
  							showNums.set(i, 0);
  						}
  					 }
  				   }
  				}//经验值减少的动画效果播放完毕
  					if(expToGet<=0){
  					    expToGet=0;
  						//延迟一下
  						timeCode++;
  					}
  					if(timeCode==15){
  					for(Hero hero:bp.heroes){
  						if(hero.wheatherLevelUp()==true){
  							levelUpIsDraw=true;
  						}
  					}
  					//没有升级就退出系统
  					if(!levelUpIsDraw){
  						//levelUpIsDraw=false;
  						GameLauncher.zhangXiaoFan.isLevelUp=false;
  						GameLauncher.yuJie.isLevelUp=false;
  						GameLauncher.luXueQi.isLevelUp=false;
  						bp.heroes.clear();
  						GameLauncher.switchTo("scene");	
  				      }
  					}
  					if(timeCode==25){
  					firstIsDraw=false;
  					firstString=false;
  					//显示第二个画面
  					secondIsDraw=true;
  					secondString=true;
  				}
  				if(timeCode>=35&&timeCode<55){
  					//开始属性增加的动画效果
  					if(bp.zxf!=null){
  					addValue(3, bp.zxf);
  					}
  					if(bp.yj!=null){
  					addValue(7, bp.yj);
  					}
  					if(bp.lxq!=null){
  					addValue(11,bp.lxq);
  					}
  				}
  				if(timeCode==55){
  					//结束
  					//System.exit(0);
  					levelUpIsDraw=false;
					GameLauncher.zhangXiaoFan.isLevelUp=false;
  					GameLauncher.yuJie.isLevelUp=false;
  					GameLauncher.luXueQi.isLevelUp=false;
  					bp.heroes.clear();
  					GameLauncher.switchTo("scene");
					}
  				}
  			}
  		}
  	}




