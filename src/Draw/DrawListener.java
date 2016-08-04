package Draw;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 画图的监听器
 * 各种按钮功能的实现
 * @author leaf-stop
 *
 */
public class DrawListener extends MouseAdapter {

	public static ArrayList<Shape> list = new ArrayList<Shape>();

	private int x1,y1,x2,y2,x3,y3;
    private Graphics g;
    private JPanel drawJPanel;
    private ButtonGroup group;
    private String str="line";
    private Color frontColor=Color.RED;
    private Color backColor=Color.BLUE;
    private  JLabel frontLabel;
    private  JLabel backLabel;
    public static boolean flag;
   
    //重载
    public DrawListener(JPanel dp,ButtonGroup bg,JLabel fLabel,JLabel bLabel){
    	drawJPanel=dp;
    	group=bg;
    	frontLabel=fLabel;
    	backLabel=bLabel;
    }
  //定义一个保存绘制过的形状的数组序列
  	
    //鼠标按下
    public void mousePressed(MouseEvent e){
    	// 鼠标按下准备绘制图形的时候先获取能绘制的区域[画布]
    	// 获取drawPanel在屏幕上占据的区域，作为可以改变颜色的区域
	    g=drawJPanel.getGraphics();
	    
    	frontColor= frontLabel.getBackground();
    	backColor=backLabel.getBackground();
    	
    	int num= e.getButton();
    	// 获得鼠标点击的是左键还是右键
    	if(num==1){
    		 g.setColor(frontColor);
    	}else if(num==3)
    	      g.setColor(backColor);
    	
    	// 鼠标按下准备绘制的时候来确定要绘制的图形
    	// 获得被选中的按钮模型
    	 ButtonModel model= group.getSelection();
    	// 获得动作命令[每一个按钮的唯一标识]
		 str=model.getActionCommand();
		 
		 x1 = e.getX();
		 y1 = e.getY();
		 
    };
    
    //释放
    public void mouseReleased(MouseEvent e){
    	 x2=e.getX();
    	 y2=e.getY();
		
    	// 绘制图形
 		Shape shape = new Shape(x1, y1, x2, y2, frontColor, str);
 		//绘制图形
 		shape.draw(g);
 		//将形状保存到list中
 		list.add(shape);
	 
    };
    
    public void mouseDragged(MouseEvent e){	  	
    	x2=e.getX();
    	y2=e.getY();
     

    	
    	// 绘制图形
    	//刷子 		
    	if(str.equals("brush")){     				    	 
    				    	Graphics2D g2d=(Graphics2D)g;//为Graphics的一个子类
    				    	g2d.setStroke(new BasicStroke(8));//设置大小
    				    	g2d.drawLine(x1, y1, x2, y2);
    				    	Shape shape = new Shape(x1, y1, x2, y2, frontColor, str);
    				 		//将形状保存到list中
    				 		list.add(shape);
    				    	//将末位置变成下一次的初始位置
    				    	x1=x2;
    				    	y1=y2;
    				    	g2d.setStroke(new BasicStroke(1));
    				    	
    				    
    				    	}
    	//橡皮擦
    	else if(str.equals("easer")){
    				    		g.setColor(Color.WHITE);   				    		
    				    		Graphics2D g2d=(Graphics2D)g;
    				    		g2d.setStroke(new BasicStroke(8));
    				    		g2d.drawLine(x1, y1, x2, y2);
    				    		Shape shape = new Shape(x1, y1, x2, y2, frontColor, str);
        				 		//将形状保存到list中
        				 		list.add(shape);
    				    		x1=x2;
    				    		y1=y2;
    				    		g2d.setStroke(new BasicStroke(1));   		
    				    		}
    	//铅笔
    	else if(str.equals("pencial")){
    				    			
    	    					     g.drawLine(x1, y1, x2, y2);
    	    					     Shape shape = new Shape(x1, y1, x2, y2, frontColor, str);
    	        				 		//将形状保存到list中
    	        				 		list.add(shape);
    	    					     x1=x2;
    				    			 y1=y2;
    				    			}
    	//喷枪
    	else if(str.equals("spray")){
    				    				Random random=new Random();
    				    				for(int i=0;i<30;i++){   				    					
    				    					int x=random.nextInt(10);
    				    					int y=random.nextInt(10);  
    				    					g.drawLine(x1+x, y1+y, x2+x, y2+y);
    				    					 Shape shape = new Shape(x1+x, y1+y, x2+x, y2+y, frontColor, str);
    	    	        				 		//将形状保存到list中
    	    	        				 		list.add(shape);
    				    					 x1=x2;
    	    				    			 y1=y2;
    				    					}   				    				
    				    				   	
    				    				}
    	
    }
  
//进入
    public void mouseEntered(MouseEvent e){

    };
    
//离开
    public void mouseExited(MouseEvent e){

    };
  //点击
    public void mouseClicked(MouseEvent e){
    	
    }}