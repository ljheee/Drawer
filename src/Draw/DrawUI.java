package Draw;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * ������
 * @author leaf-stop
 *
 */
public class DrawUI{
	static DrawUI dui;
	public JMenuItem save;
	public JMenuItem open;
	public JMenuItem exit;
	public DrawListener dlis;
	public MyPanel drawpJPanel;
	public static void main(String[] args) {		
		 dui=new DrawUI();
		dui.shoeUI();
	}
	
/**
 * ��ͼ��������
 * ��ͼ�岼�ּ���������ŵĶ���
 */
	public void shoeUI(){
		// ��������
		JFrame jf = new JFrame();
		jf.setTitle("��ͼ��");
		jf.setSize(600,500);
		jf.setDefaultCloseOperation(3);

		//ָ������Ϊ�߿򲼾�
		BorderLayout layout = new BorderLayout();
		jf.setLayout(layout);
		
		//��߲���
		JPanel left =new JPanel();
		Dimension leftDim=new Dimension(100,1);
		left.setPreferredSize(leftDim);
		
		//�м䲼��
		JPanel center =new JPanel();
		center.setBackground(Color.GRAY);

		//�ײ�����
		JPanel foot =new JPanel();
		Dimension footdim=new Dimension(1,100);//
		foot.setPreferredSize(footdim);
		
		//����λ��
		jf.add(left,BorderLayout.WEST);
		jf.add(center,BorderLayout.CENTER);
		jf.add(foot,BorderLayout.SOUTH);

		
		//******************�����������״ѡ�񹤾�***************************************//
		
		//���ͼƬ����ť
		ButtonGroup group =new ButtonGroup();
		//����һ��һά��������Ź�����ͼƬ�����֣����ֱ�ʾ��û��ʵ�ֵİ�ť
		String[] strs ={ "0", "1", "easer", "3", "4", "5", "pencial", "brush", "spray", "9",
				"line", "11", "rect", "polygon", "oval", "roundrect"};
		
		for(int i=0;i<16;i++){
			JRadioButton btn1 =new JRadioButton();
			left.add(btn1);
			group.add(btn1);
			btn1.setActionCommand(strs[i]);
			btn1.setSelected(true);
			// ���ð�ť��ͼƬ
			ImageIcon defaultIcon = new ImageIcon("image/draw"+i+".jpg");
			btn1.setIcon(defaultIcon);
			
			ImageIcon rolloverIcon =new ImageIcon("image/draw"+i+"-1.jpg");
			btn1.setRolloverIcon(rolloverIcon);
			
			ImageIcon pressedIcon = new ImageIcon("image/draw"+i+"-2.jpg");
			btn1.setPressedIcon(pressedIcon);
			
			ImageIcon selectedIcon =new ImageIcon("image/draw"+i+"-3.jpg");
			btn1.setSelectedIcon(selectedIcon);
		}
	
			
		//******************�м�������***********************************************//
	 drawpJPanel=new MyPanel();	
		Dimension drawDim=new Dimension(400,300);
		drawpJPanel.setPreferredSize(drawDim);
		drawpJPanel.setBackground(Color.WHITE);
		
		// ���Ĭ�ϵĲ�������ʽ����
		// ָ���м����Ĳ���Ϊ��ʽ���������
		FlowLayout f1=new FlowLayout(FlowLayout.LEFT);
		center.setLayout(f1);
     	center.add(drawpJPanel);
     	
		//*******************�ײ���������ɫѡ�񹤾�****************************************//
		JLabel frontLabel =new JLabel();
		JLabel backLabel=new JLabel();
     	
		// ʹ�þ��Զ�λ����
		foot.setLayout(null);
		frontLabel.setBounds(50, 20, 40, 60);
		backLabel.setBounds(110, 20, 40, 60);
		frontLabel.setBackground(Color.BLACK);
		backLabel.setBackground(Color.BLUE);
		// �Ƿ�������ɫ��ʾ����
		frontLabel.setOpaque(true);
		backLabel.setOpaque(true);
		
     	foot.add(frontLabel);
     	foot.add(backLabel);
     	
     	//��ɫ
     	Color[] cs={Color.BLACK, Color.GRAY, new Color(128, 0, 0),Color.RED,
     			new Color(255, 128, 0), Color.YELLOW, Color.GREEN,
				new Color(0, 128, 255), Color.BLUE, Color.MAGENTA, new Color(255,128,128),
				new Color(128, 0, 125), new Color(128, 255, 0), new Color(128, 0, 255),
				new Color(0, 128, 120),new Color(128, 0, 20), new Color(128, 128, 0),
				new Color(100, 128, 255), new Color(128, 30, 50), new Color(128, 110, 50)};
    	ColorListener mlis =new ColorListener(frontLabel,backLabel);
     
     	for(int i=0;i<20;i++){
     		JLabel colorLabel= new JLabel();
     		// ����ɫLabel�����������
     		colorLabel.addMouseListener(mlis);
     		
     		if(i<10){
     			colorLabel.setBounds(160+30*i, 20, 25, 25);
     		}else{
     			colorLabel.setBounds(160+30*(i-10), 50, 25, 25);
     		} 
     		colorLabel.setBackground(cs[i]);
     		colorLabel.setOpaque(true);
     		
     		foot.add(colorLabel);
     	}
    	//***********************��ɫ��
     	JButton jb=new JButton(" ��ɫ��   " );
     	jb.setBounds(470,30,90,40);
     	jb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Color newcolor=JColorChooser.showDialog(drawpJPanel,"��ɫ��", drawpJPanel.getBackground());
				if(newcolor!=null){
					//��ô��JColorChooser�������������
				//	int num = e.getButton();		
				//	if(num==1){
					frontLabel.setBackground(newcolor);//JFrame������ֻ��������������趨��ɫ
				//	}else if(num==3){
				//		backLabel.setBackground(newcolor);
				//	}
				}
				
			}});
     	foot.add(jb);
     	
     	 
     	
    
    	//*******************�˵�����ʵ��****************************************//	
    	//����menulistener������
     	menuListener ma=new menuListener(dui);
     	JMenuBar jmb=new JMenuBar();
    	JMenu m=new JMenu("�ļ�");
        open=new JMenuItem("��");
    	open.addActionListener(ma);
    	save=new JMenuItem("����");
    	save.addActionListener(ma);
    	 exit=new JMenuItem("�˳�");
    	exit.addActionListener(ma);
    	m.add(save);
    	m.addSeparator();
    	m.add(open);
    	m.addSeparator();
    	m.add(exit);
    	jmb.add(m);
    	jf.setJMenuBar(jmb);
    	
    	
     	jf.setVisible(true);
     	//���ô����������ķ��������������
		 dlis = new DrawListener(drawpJPanel,group,frontLabel,backLabel);
		drawpJPanel.addMouseListener(dlis);
		drawpJPanel.addMouseMotionListener(dlis);
	}
	

		      
}
	