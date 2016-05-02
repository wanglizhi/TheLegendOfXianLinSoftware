# 仙林软件奇侠传

![](https://raw.githubusercontent.com/wanglizhi/TheLegendOfXianLinSoftware/master/shotcut/startPanel.png)

> 转眼间四年过去了，每每回顾它总能唤起青涩的回忆，现在的我相比之前也许成长了很多，但总归也失去了很多。前段时间我曾两次展示项目给新的研究生同学看，他们也都很惊讶我们在大一的时候就可以做出这么牛逼的东西来，我自己回想起来也觉得很棒，不到半年的时间，整个Firework团队那么专注地去完成一个工作量巨大的项目，感谢队友的陪伴，现在大家都各自奋斗，不知道我们再一块回忆这段时光的时候是哪一天，但是我想都不会忘记吧！

#### 运行说明

此项目我一点东西也没舍得删，clone下来到eclipse里应该就可以跑，没有数据库，这也是txt好的地方吧。。。

项目下面有`仙林软件奇侠传设计文档`和`游戏说明书`，readme里的内容就是从里面摘抄的，详细内容到里面去找。

**图片和音乐大都来自`天之痕`，部分配音来自仙剑，地图由RPG Maker绘制，故事人物的名字来自`诛仙`，用诛仙人物名字是因为我当时在看，想要达到此间的少年的感觉**

#### 需求

- 以**校园**为地图背景：将南京大学设定为一所培养武术人才的大学——金陵大学
- 以**大学生**为主角：我们的主角是软件堂的弟子
- 以**大学生活**为主线：编写控制准确且波澜曲折的剧情作为游戏的主线任务。并通过一些回答关于校史和编程方面的问题来体现大学生活的多样性

#### 故事背景（狗血剧情）

金陵大学堂是神州大地培养武术人才的最高学府。金陵大学堂由物理阁，文学谷，商塔，软件堂四大门派构成，物理阁独霸大学堂，但是随着软件堂的发展并且在软件堂院庆之际研发出“龙心”，实力大增，严重地威胁到物理阁的地位。

张小凡为金陵大学堂软件堂大一弟子，聪颖过人，深得软件堂教头钦鎏仙杰，堂主道蓄真人的赏识，在“龙心”被盗之后，委以重任，负责调查此事，张小凡就与青梅竹马的玩伴陆雪琪及学姐文敏展开了一次动人心魄的“仙林奇侠传”

#### 实现的亮点

- 基本弃用了swing组件，主要运用自己编写的GameButton和Animation类来进行类似swing组件的功能。
- 鼠标换成了漂亮的动态图标
- 开始界面上的云彩可以**飘动**，当玩家将按钮放到按钮区域时按钮会出现动态转动的效果，按下按钮后会有卷轴拉开的效果作为缓冲
- 实现人物的走动，还可以实现人物的跑动
- 在场景中进行对话时，**对话框**有流畅的弹出效果，重要人物还有相应的**头像**，并根据人物当前的心情变换表情。
- 战斗界面中有漂亮的拉开和合闭的效果，在战斗中有丰富的音效和动画，战斗胜利后有淡出的战斗成果界面，可以以跳动数字的方法来计算获得的经验值。
- 每一个Panel画图中采用了**双缓冲**的机制，这样做有效防止了画图时出现的闪烁。
- 在载入图片方面我们运用了**多级缓冲**的技术
- 实现音乐的循环播放，顺序播放和打断式播放，并能根据场景切换音乐，实现音乐与剧情同步。

#### 项目截图

**存档界面**

![](https://raw.githubusercontent.com/wanglizhi/TheLegendOfXianLinSoftware/master/shotcut/record.png)

大地图（场景界面）

![](https://raw.githubusercontent.com/wanglizhi/TheLegendOfXianLinSoftware/master/shotcut/bigMap.png)

**物品菜单**

![](https://raw.githubusercontent.com/wanglizhi/TheLegendOfXianLinSoftware/master/shotcut/menu.png)

**武器**

![](https://raw.githubusercontent.com/wanglizhi/TheLegendOfXianLinSoftware/master/shotcut/equipment.png)



**技能**

![](https://raw.githubusercontent.com/wanglizhi/TheLegendOfXianLinSoftware/master/shotcut/trick.png)

 

**战斗系统**

![](https://raw.githubusercontent.com/wanglizhi/TheLegendOfXianLinSoftware/master/shotcut/fight1.png)

![](https://raw.githubusercontent.com/wanglizhi/TheLegendOfXianLinSoftware/master/shotcut/fight2.png)



**药店界面**

![](https://raw.githubusercontent.com/wanglizhi/TheLegendOfXianLinSoftware/master/shotcut/drugstore.png)

**装备超市界面**

![](https://raw.githubusercontent.com/wanglizhi/TheLegendOfXianLinSoftware/master/shotcut/equipmentStore.png)



**NPC对话**

![](https://raw.githubusercontent.com/wanglizhi/TheLegendOfXianLinSoftware/master/shotcut/npc.png)

![](https://raw.githubusercontent.com/wanglizhi/TheLegendOfXianLinSoftware/master/shotcut/npc2.png)

![](https://raw.githubusercontent.com/wanglizhi/TheLegendOfXianLinSoftware/master/shotcut/fightRpc.png)



![](https://raw.githubusercontent.com/wanglizhi/TheLegendOfXianLinSoftware/master/shotcut/pk.png)



**迷宫**

![](https://raw.githubusercontent.com/wanglizhi/TheLegendOfXianLinSoftware/master/shotcut/migong.png)



**回答问题**

![](https://raw.githubusercontent.com/wanglizhi/TheLegendOfXianLinSoftware/master/shotcut/answerQuestion.png)











