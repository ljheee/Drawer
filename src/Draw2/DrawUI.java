package Draw2;

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
 * 
 * @author leaf-stop
 * 
 */
public class DrawUI {

	public static void main(String[] args) {
		DrawUI dui = new DrawUI();
		dui.shoeUI();
	}

	/**
	 * ��ͼ�������� ��ͼ�岼�ּ���������ŵĶ���
	 */
	public void shoeUI() {
		// ��������
		JFrame jf = new JFrame();
		jf.setTitle("��ͼ��");
		jf.setSize(600, 500);
		jf.setDefaultCloseOperation(3);

		// ָ������Ϊ�߿򲼾�
		BorderLayout layout = new BorderLayout();
		jf.setLayout(layout);

		// ��߲���
		JPanel left = new JPanel();
		Dimension leftDim = new Dimension(100, 1);
		left.setPreferredSize(leftDim);

		// �м䲼��
		JPanel center = new JPanel();
		center.setBackground(Color.GRAY);

		// �ײ�����
		JPanel foot = new JPanel();
		Dimension footdim = new Dimension(1, 100);//
		foot.setPreferredSize(footdim);

		// ����λ��
		jf.add(left, BorderLayout.WEST);
		jf.add(center, BorderLayout.CENTER);
		jf.add(foot, BorderLayout.SOUTH);

		// ******************�����������״ѡ�񹤾�***************************************//

		// ���ͼƬ����ť
		ButtonGroup group = new ButtonGroup();
		// ����һ��һά��������Ź�����ͼƬ�����֣����ֱ�ʾ��û��ʵ�ֵİ�ť
		String[] strs = { "0", "1", "easer", "3", "4", "5", "pencial", "brush",
				"spray", "9", "line", "11", "rect", "polygon", "oval",
				"roundrect" };

		for (int i = 0; i < 16; i++) {
			JRadioButton btn1 = new JRadioButton();
			left.add(btn1);
			group.add(btn1);
			btn1.setActionCommand(strs[i]);
			btn1.setSelected(true);
			// ���ð�ť��ͼƬ
			ImageIcon defaultIcon = new ImageIcon("image/draw" + i + ".jpg");
			btn1.setIcon(defaultIcon);

			ImageIcon rolloverIcon = new ImageIcon("image/draw" + i + "-1.jpg");
			btn1.setRolloverIcon(rolloverIcon);

			ImageIcon pressedIcon = new ImageIcon("image/draw" + i + "-2.jpg");
			btn1.setPressedIcon(pressedIcon);

			ImageIcon selectedIcon = new ImageIcon("image/draw" + i + "-3.jpg");
			btn1.setSelectedIcon(selectedIcon);
		}

		// ******************�м�������***********************************************//
		final MyPanel drawpJPanel = new MyPanel();
		Dimension drawDim = new Dimension(400, 300);
		drawpJPanel.setPreferredSize(drawDim);
		drawpJPanel.setBackground(Color.WHITE);

		// ���Ĭ�ϵĲ�������ʽ����
		// ָ���м����Ĳ���Ϊ��ʽ���������
		FlowLayout f1 = new FlowLayout(FlowLayout.LEFT);
		center.setLayout(f1);
		center.add(drawpJPanel);

		// *******************�ײ���������ɫѡ�񹤾�****************************************//
		final JLabel frontLabel = new JLabel();
		JLabel backLabel = new JLabel();

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

		// ��ɫ
		Color[] cs = { Color.BLACK, Color.GRAY, new Color(128, 0, 0),
				Color.RED, new Color(255, 128, 0), Color.YELLOW, Color.GREEN,
				new Color(0, 128, 255), Color.BLUE, Color.MAGENTA,
				new Color(255, 128, 128), new Color(128, 0, 125),
				new Color(128, 255, 0), new Color(128, 0, 255),
				new Color(0, 128, 120), new Color(128, 0, 20),
				new Color(128, 128, 0), new Color(100, 128, 255),
				new Color(128, 30, 50), new Color(128, 110, 50) };
	
		ColorListener clis = new ColorListener(frontLabel, backLabel);
		for (int i = 0; i < 20; i++) {
			JLabel colorLabel = new JLabel();
			// ����ɫLabel�����������
			colorLabel.addMouseListener(clis);

			if (i < 10) {
				colorLabel.setBounds(160 + 30 * i, 20, 25, 25);
			} else {
				colorLabel.setBounds(160 + 30 * (i - 10), 50, 25, 25);
			}
			colorLabel.setBackground(cs[i]);
			colorLabel.setOpaque(true);

			foot.add(colorLabel);
		}
		JButton jb = new JButton(" ��ɫ��   ");
		jb.setBounds(470, 30, 90, 40);
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color newcolor = JColorChooser.showDialog(drawpJPanel, "��ɫ��",
						drawpJPanel.getBackground());
				if (newcolor != null) {
					frontLabel.setBackground(newcolor);// JFrame������ֻ��������������趨��ɫ
				}

			}
		});
		foot.add(jb);

		// ***********************��ɫ��

		// *******************�˵�����ʵ��****************************************//
		MenuListener mlis = new MenuListener(drawpJPanel);

		// ��Ӳ˵�
		JMenuBar bar = new JMenuBar();
		String[] menus = { "�ļ�", "�༭", "�鿴", "ͼ��", "����" };
		String[][] items = { { "��", "����"}, { "����", "����", "ճ��" },
				{}, {}, {} };
		for (int i = 0; i < menus.length; i++) {
			JMenu menu = new JMenu(menus[i]);
			bar.add(menu);
			for (int j = 0; j < items[i].length; j++) {
				JMenuItem item1 = new JMenuItem(items[i][j]);
				//���ö�������
				item1.setActionCommand(items[i][j]);
				menu.add(item1);
				item1.addActionListener(mlis);
			}
		}

		// ���ô���Ĳ˵���
		jf.setJMenuBar(bar);
		jf.setVisible(true);
		// ���ô����������ķ��������������
		DrawListener dlis = new DrawListener(drawpJPanel, group, frontLabel,
				backLabel);
		drawpJPanel.addMouseListener(dlis);
		drawpJPanel.addMouseMotionListener(dlis);
	}

}
