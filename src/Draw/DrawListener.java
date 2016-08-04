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
 * ��ͼ�ļ�����
 * ���ְ�ť���ܵ�ʵ��
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
   
    //����
    public DrawListener(JPanel dp,ButtonGroup bg,JLabel fLabel,JLabel bLabel){
    	drawJPanel=dp;
    	group=bg;
    	frontLabel=fLabel;
    	backLabel=bLabel;
    }
  //����һ��������ƹ�����״����������
  	
    //��갴��
    public void mousePressed(MouseEvent e){
    	// ��갴��׼������ͼ�ε�ʱ���Ȼ�ȡ�ܻ��Ƶ�����[����]
    	// ��ȡdrawPanel����Ļ��ռ�ݵ�������Ϊ���Ըı���ɫ������
	    g=drawJPanel.getGraphics();
	    
    	frontColor= frontLabel.getBackground();
    	backColor=backLabel.getBackground();
    	
    	int num= e.getButton();
    	// ��������������������Ҽ�
    	if(num==1){
    		 g.setColor(frontColor);
    	}else if(num==3)
    	      g.setColor(backColor);
    	
    	// ��갴��׼�����Ƶ�ʱ����ȷ��Ҫ���Ƶ�ͼ��
    	// ��ñ�ѡ�еİ�ťģ��
    	 ButtonModel model= group.getSelection();
    	// ��ö�������[ÿһ����ť��Ψһ��ʶ]
		 str=model.getActionCommand();
		 
		 x1 = e.getX();
		 y1 = e.getY();
		 
    };
    
    //�ͷ�
    public void mouseReleased(MouseEvent e){
    	 x2=e.getX();
    	 y2=e.getY();
		
    	// ����ͼ��
 		Shape shape = new Shape(x1, y1, x2, y2, frontColor, str);
 		//����ͼ��
 		shape.draw(g);
 		//����״���浽list��
 		list.add(shape);
	 
    };
    
    public void mouseDragged(MouseEvent e){	  	
    	x2=e.getX();
    	y2=e.getY();
     

    	
    	// ����ͼ��
    	//ˢ�� 		
    	if(str.equals("brush")){     				    	 
    				    	Graphics2D g2d=(Graphics2D)g;//ΪGraphics��һ������
    				    	g2d.setStroke(new BasicStroke(8));//���ô�С
    				    	g2d.drawLine(x1, y1, x2, y2);
    				    	Shape shape = new Shape(x1, y1, x2, y2, frontColor, str);
    				 		//����״���浽list��
    				 		list.add(shape);
    				    	//��ĩλ�ñ����һ�εĳ�ʼλ��
    				    	x1=x2;
    				    	y1=y2;
    				    	g2d.setStroke(new BasicStroke(1));
    				    	
    				    
    				    	}
    	//��Ƥ��
    	else if(str.equals("easer")){
    				    		g.setColor(Color.WHITE);   				    		
    				    		Graphics2D g2d=(Graphics2D)g;
    				    		g2d.setStroke(new BasicStroke(8));
    				    		g2d.drawLine(x1, y1, x2, y2);
    				    		Shape shape = new Shape(x1, y1, x2, y2, frontColor, str);
        				 		//����״���浽list��
        				 		list.add(shape);
    				    		x1=x2;
    				    		y1=y2;
    				    		g2d.setStroke(new BasicStroke(1));   		
    				    		}
    	//Ǧ��
    	else if(str.equals("pencial")){
    				    			
    	    					     g.drawLine(x1, y1, x2, y2);
    	    					     Shape shape = new Shape(x1, y1, x2, y2, frontColor, str);
    	        				 		//����״���浽list��
    	        				 		list.add(shape);
    	    					     x1=x2;
    				    			 y1=y2;
    				    			}
    	//��ǹ
    	else if(str.equals("spray")){
    				    				Random random=new Random();
    				    				for(int i=0;i<30;i++){   				    					
    				    					int x=random.nextInt(10);
    				    					int y=random.nextInt(10);  
    				    					g.drawLine(x1+x, y1+y, x2+x, y2+y);
    				    					 Shape shape = new Shape(x1+x, y1+y, x2+x, y2+y, frontColor, str);
    	    	        				 		//����״���浽list��
    	    	        				 		list.add(shape);
    				    					 x1=x2;
    	    				    			 y1=y2;
    				    					}   				    				
    				    				   	
    				    				}
    	
    }
  
//����
    public void mouseEntered(MouseEvent e){

    };
    
//�뿪
    public void mouseExited(MouseEvent e){

    };
  //���
    public void mouseClicked(MouseEvent e){
    	
    }}