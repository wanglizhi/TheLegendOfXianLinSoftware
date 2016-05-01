package battle;
import javax.swing.*;

import tools.Reader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.util.ArrayList;
import media.*;

public class BattlePanel extends JPanel implements Runnable{
	private static final long serialVersionUID = 4L;
	//定义宽度和高度
	private static final int WIDTH=32*32;
	private static final int HEIGHT=20*32;
	
	//战斗图片背景
	Image background;
	//缓冲图片
	Image bufferedPic;
	//缓冲画笔
	Graphics bufferedGraphics;
	//字体
	Font font;	
	//游标对象
	Mouse mouse;
	
	//游标当前位置
	int currentX;
	int currentY;
	
	//控制台
	Command command;
	
	//技能菜单
	SkillMenu skillMenu;
	
	//药品菜单
	DrugMenu drugMenu;
	
	//状态栏
	StateBlank stateBlank;
	
	//指示标志
	Instruct instruct;
	
	//怪物选择器
	EnemySlector enemySlector;
	
	//攻击发动器
	LaunchAttack launchAttack;
	
	//检查器
	Check check;
	
	//伤害值显示
	ArrayList<HurtValue> hurtValues;
	
	//开始动画
	StartAnimation startAnimation;
	
	//背景动画
	BackgroundAnimation backgroundAnimation;
	
	//技能动画
	SkillAnimation skillAnimation;
	
	//提示
	Reminder reminder;
	
	//战斗胜利提示
	VictoryReminder victoryReminder;
	
	//怒气槽
	ArrayList<AngryBar> angryBars=new ArrayList<AngryBar>();
	
	//我方战斗单位引用
	ZhangXiaoFan zxf;
	YuJie yj;
	LuXueQi lxq;
	//我方战斗单位集合
	ArrayList<Hero> heroes=new ArrayList<Hero>();
	
	//敌人引用
	Enemy em1;
	Enemy em2;
	Enemy em3;
	ArrayList<Enemy> enemies =new ArrayList<Enemy>();
	
	//怪物智能
	EnemyAI enemyAI;
	
	//进度条
	ProgressBar progressBar;
	
	//小精灵
	Pet pet;
	
	//游戏结束画面
	GameOver gameOver;
	
	//当前回合 1.张小凡 2.文敏 3.陆雪琪 4.宋大仁 5.怪物1 6.怪物2 7.怪物3
	int currentRound;
	
	//当前被攻击对象 1.张小凡 2.文敏 3.陆雪琪 4.宋大仁 5.怪物1 6.怪物2 7.怪物3
	int currentBeAttacked;
	
	//当前攻击模式  1.普通攻击 2.技能1 3.技能2 4.技能3 5.技能4 6.技能5
	int currentPattern;
	
	//构造方法
	public BattlePanel(){
		 setPreferredSize(new Dimension(WIDTH,HEIGHT));
		 //双缓冲准备
		 bufferedPic=new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_ARGB);
		 bufferedGraphics=bufferedPic.getGraphics();
		 font=new Font("文鼎粗钢笔行楷", Font.BOLD, 15);
		 bufferedGraphics.setFont(font);
		 
		 //创建游标
		 mouse=new Mouse(this);
		 setMouse();
		 requestFocus();
		 
		 
		 //创建控制台
		 command=new Command(this);
		 
		 //创建指示图标
		 instruct=new Instruct(this);
		 
		 //创建药品菜单
		 drugMenu=new DrugMenu(this);
		 
		 //创建伤害值显示
		 hurtValues=new ArrayList<HurtValue>();
		 
		 //创建背景动画
		 backgroundAnimation=new BackgroundAnimation(this);
		 
		 //创建技能动画
		 skillAnimation=new SkillAnimation(this);
		 
		 //创建提示
		 reminder=new Reminder(this, 500,120);
		 
		 //创建检查器
		 check=new Check(this);
		 	 
		 //开启线程
		 Thread t=new Thread(this);
		 t.start();
	}
	
	//初始化方法
	public void initial(String s,ZhangXiaoFan z,YuJie y,LuXueQi l,Enemy e1,Enemy e2,Enemy e3){
		background=Reader.readImage(s);
		//根据背景加入音乐
		switch(s){
		case "image/背景图/伏魔山树林.png":
			MusicReader.readBGM("B6.mp3");
			break;
		case "image/背景图/校园小道.png":
			MusicReader.readBGM("B6.mp3");
			break;
		case "image/背景图/大活迷宫.png":
			MusicReader.readBGM("仗剑.mp3");
			break;
		case "image/背景图/藏宝阁外.png":
			MusicReader.readBGM("浣花洗剑.mp3");
			break;
		case "image/背景图/仙二迷宫.png":
			MusicReader.readBGM("会心一击.mp3");
			break;
		case "image/背景图/仙二教学楼.png":
			MusicReader.readBGM("浣花洗剑·变奏.mp3");
			break;
		case "image/背景图/藏经阁一层.png":
			MusicReader.readBGM("文学谷第一战.mp3");
			break;
		case "image/背景图/藏经阁三层.png":
			MusicReader.readBGM("临危恃勇.mp3");
			break;
		case "image/背景图/商塔迷宫.png":
			MusicReader.readBGM("镜影命缘.mp3");
			break;
		case "image/背景图/商塔顶层.png":
			MusicReader.readBGM("紧急.mp3");
			break;
		case "image/背景图/校园内部.png":
			MusicReader.readBGM("C45.mp3");
			break;
		case "image/背景图/比武场.png":
			MusicReader.readBGM("肆涌暗云.mp3");
			break;
		}
		
		
		//添加我方的战斗单位
		zxf=z;
		yj=y;
		lxq=l;
		if(zxf!=null){
		heroes.add(z);
		}
		if(yj!=null){
		heroes.add(y);
		}
		if(lxq!=null){
		heroes.add(l);
		}
		//添加敌人
		em1=e1;
		em2=e2;
		em3=e3;
		
		//怪物智能
		enemyAI=new EnemyAI(this);
		//添加怪物 注意顺序 解决遮掩性问题
		if(em2!=null){
			enemies.add(em2);
		}
		if(em1!=null){
			enemies.add(em1);
		}
		if(em3!=null){
			enemies.add(em3);
		}
		
		//创建小精灵
		pet=null;
		 
		progressBar=new ProgressBar(300, 50, this);
		stateBlank=new StateBlank(this);
		
		//创建怒气槽
		angryBars.clear();
		for(Hero hero:heroes){
			AngryBar an=new AngryBar(this, hero);
			angryBars.add(an);
		}
		
		 //创建技能菜单
		 skillMenu=new SkillMenu(this);
		 
		 //创建怪物选择器
		 enemySlector=new EnemySlector(this);
		 
		 //创建攻击发动器
		 launchAttack=new LaunchAttack(this);
		 
		 //创建胜利提示
		 victoryReminder=new VictoryReminder(this);
		 
		 //创建游戏结束画面
		 gameOver=new GameOver(this);
		 
		 //创建开始动画
		 startAnimation=new StartAnimation(this);
		 
		//进度条开始
		 progressBar.isStop=false;
		 command.isDraw=false;
		 drugMenu.isDraw=false;
		 instruct.isDraw=false;
		 
		 //检查上场战斗时候有人死亡,若有,每人回复10%的hp
		 for(Hero hero:heroes){
			 if(hero.wheatherDead()){
				 hero.setDead(false);
				 //如果hp为0
				 if(hero.getHp()==0){
				 hero.setHp(((int)(hero.getHpMax()*0.1)));
				 }
			 }
		 }
	}
	
	//设置一个键盘监听(外挂)
	public void keyPressed(int keyCode){
		if(keyCode==KeyEvent.VK_J){
			enemies.clear();
			em1=null;
			em2=null;
			em3=null;
			check.checkEnemyDead();
		}
	}
	
	
	
	//设置鼠标
	public void setMouse(){
		int[] pixels = new int[256];
	    Image image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(16, 16, pixels, 0, 16));
	    // 制作一个透明的游标      
	     Cursor transparentCursor = Toolkit.getDefaultToolkit() .createCustomCursor(image, new Point(0, 0), "hidden");
	     // 插入透明游标，以此模拟无游标状态       
	     setCursor(transparentCursor);
	     addMouseListener(new MouseAdapter() {
	    	 public void mousePressed(MouseEvent e){
	    		 currentX=e.getX();
	    		 currentY=e.getY();
	    		 
	    		 if(command.isDraw){
	    			 command.checkPressed();
	    		 }
	    		 
	    		 if(skillMenu.isDraw){
	    			 skillMenu.checkPressed();
	    		 }
	    		 
	    		 if(drugMenu.isDraw){
	    			 drugMenu.checkPressed();
	    		 }
	    		 
	    		 if(enemySlector.isSlectable){
	    			 enemySlector.checkClick(currentX, currentY);
	    		 }
	    	 }
	    	 
	    	 public void mouseReleased(MouseEvent e) {
	    		 currentX=e.getX();
	    		 currentY=e.getY();
	    		 
	    		 if(command.isDraw){
	    			 command.checkReleased();
	    		 }
	    		 
	    		 if(skillMenu.isDraw){
	    			 skillMenu.checkReleased();
	    		 }
	    		 
	    		 if(drugMenu.isDraw){
	    			 drugMenu.checkReleased();
	    		 }
	    	 }
		});
	     
	     addMouseMotionListener(new MouseMotionAdapter() {
	    	 public void mouseMoved(MouseEvent ex) {
	    		 currentX=ex.getX();
	    		 currentY=ex.getY();
	    	
	    		 if(command.isDraw){
	    			 command.checkMoveIn();
	    		 }
	    		 
	    		 if(skillMenu.isDraw){
	    			 skillMenu.checkMoveIn();
	    		 }
	    		 
	    		 if(drugMenu.isDraw){
	    			 drugMenu.checkMoveIn();
	    		 }
	    		 
	    		 enemySlector.checkMoveIn(currentX, currentY);
	    	 }
	    	 
	    	 public void mouseDragged(MouseEvent ex) {
	    		 currentX=ex.getX();
	    		 currentY=ex.getY();
	    		 
	    		 if(command.isDraw){
	    			 command.checkMoveIn();
	    		 }
	    		 
	    		 if(skillMenu.isDraw){
	    			 skillMenu.checkMoveIn();
	    		 }
	    		 
	    		 if(drugMenu.isDraw){
	    			 drugMenu.checkMoveIn();
	    		 }
	    	 }
		});
	    	 
	     
	}
	
	public void paint(Graphics g){
		bufferedGraphics.drawImage(background, 0, 0, this);
		backgroundAnimation.drawBackAnimation(bufferedGraphics);
		stateBlank.drawStateBlank(bufferedGraphics);
		for(AngryBar angryBar:angryBars){
			angryBar.drawAngryBar(bufferedGraphics);
		}
		if(command!=null){
		command.drawCommand(bufferedGraphics);
		}
		
		drugMenu.drawDrugMenu(bufferedGraphics);
		for(Hero hero:heroes){
			if(hero!=null){
				hero.drawHero(bufferedGraphics);
			}
		}
		for(Hero hero:heroes){
			if(hero!=null){
				hero.getDeadAnimation().drawDeadAniamtion(bufferedGraphics);
			}
		}
		for(Hero hero:heroes){
			if(hero!=null){
				hero.getVictoryAnimation().drawVictoryAnimation(bufferedGraphics);
			}
		}
	    for(Enemy enemy:enemies){
	    	if(enemy!=null){
	    	enemy.drawEnemy(bufferedGraphics);
	    	}
	    }
	    if(pet!=null){
	    pet.drawPet(bufferedGraphics);
	    }
	    if(progressBar!=null){
	    progressBar.drawProgressBar(bufferedGraphics);
	    }
	    skillMenu.drawSkillMenu(bufferedGraphics);
	    for(Enemy enemy:enemies){
	    	if(enemy.beAttackedAnimation!=null){
	    	enemy.beAttackedAnimation.drawAnimation(bufferedGraphics);
	    	}
	    }
	    for(Hero hero:heroes){
	    	if(hero.getBeAttackedAnimation()!=null){
	    		hero.getBeAttackedAnimation().drawAnimation(bufferedGraphics);
	    	}
	    }
	    skillAnimation.drawAnimation(bufferedGraphics);
	    for(Hero hero:heroes){
			   hero.getBattleState().drawState(bufferedGraphics);
		   }
	    for(Enemy enemy:enemies){
	    	enemy.battleState.drawState(bufferedGraphics);
	    }
	    for(HurtValue hurtValue:hurtValues){
	    hurtValue.drawHurtValue(bufferedGraphics);
	    }
	    instruct.drawInstruct(bufferedGraphics);
	   
	    reminder.drawReminder(bufferedGraphics);
	    victoryReminder.drawVictoryReminder(bufferedGraphics);
	    mouse.drawMouse(bufferedGraphics);
	    if(gameOver!=null){
	    	gameOver.drawGameOver(bufferedGraphics);
	    }
	    if(startAnimation!=null){
	    startAnimation.drawStartAnimation(bufferedGraphics);
	    }
	    g.drawImage(bufferedPic,0,0,this);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			if(mouse!=null){
				mouse.update();
			}
			if(backgroundAnimation!=null){
				backgroundAnimation.update();
			}
		    for(Hero hero:heroes){
		    	if(hero!=null){
		    		hero.doAction();
		    	}
		    }
		  for(Hero hero:heroes){
			  if(hero!=null){
				  hero.getVictoryAnimation().update();
			  }
		  }
		  for(Hero hero:heroes){
			  if(hero!=null){
				  hero.getDeadAnimation().update();
			  }
		  }
		 for(Enemy enemy:enemies){
				 if(enemy!=null){
			    	enemy.doAction();
				 }
			    }
			if(progressBar!=null){
				progressBar.updateProgress();
			}
			skillAnimation.update();
			for(Enemy enemy:enemies){
			    if(enemy.beAttackedAnimation!=null){
			    	enemy.beAttackedAnimation.update();
			    }
			  }
			for(Hero hero:heroes){
				if(hero.getBeAttackedAnimation()!=null){
					hero.getBeAttackedAnimation().update();
				}
			}
			if(pet!=null){
				pet.update();
			}
			for(HurtValue hurtValue:hurtValues){
			if(hurtValue!=null){
				hurtValue.update();
			}
			}
			if(instruct!=null){
				instruct.update();
				
			}
			if(reminder!=null){
				reminder.update();
			}	
			for(Enemy enemy:enemies){
				if(enemy!=null){
		    	enemy.battleState.check();
				}
		    }
			if(launchAttack!=null){
				launchAttack.check();
			}
			
			if(stateBlank!=null){
				stateBlank.update();
			}
			if(angryBars.size()!=0){
			for(AngryBar angryBar:angryBars){
				if(angryBar!=null){
					angryBar.update();
				}
			  }
			}
			if(victoryReminder!=null){
				victoryReminder.update();
			}
			for(Hero hero:heroes){
				if(hero!=null){
				hero.getBattleState().check();
				}
			}
			if(gameOver!=null){
				gameOver.update();
			}
			if(startAnimation!=null){
				startAnimation.update();
			}
			repaint();
		}
	}
}
