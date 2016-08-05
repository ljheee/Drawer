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
 * 主函数
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
	 * 画图板主窗口 画图板布局及各个板块存放的东西
	 */
	public void shoeUI() {
		// 创建窗体
		JFrame jf = new JFrame();
		jf.setTitle("画图板");
		jf.setSize(600, 500);
		jf.setDefaultCloseOperation(3);

		// 指定布局为边框布局
		BorderLayout layout = new BorderLayout();
		jf.setLayout(layout);

		// 左边布局
		JPanel left = new JPanel();
		Dimension leftDim = new Dimension(100, 1);
		left.setPreferredSize(leftDim);

		// 中间布局
		JPanel center = new JPanel();
		center.setBackground(Color.GRAY);

		// 底部布局
		JPanel foot = new JPanel();
		Dimension footdim = new Dimension(1, 100);//
		foot.setPreferredSize(footdim);

		// 设置位置
		jf.add(left, BorderLayout.WEST);
		jf.add(center, BorderLayout.CENTER);
		jf.add(foot, BorderLayout.SOUTH);

		// ******************左边面板添加形状选择工具***************************************//

		// 添加图片及按钮
		ButtonGroup group = new ButtonGroup();
		// 定义一个一维数组来存放工具栏图片的名字，数字表示还没有实现的按钮
		String[] strs = { "0", "1", "easer", "3", "4", "5", "pencial", "brush",
				"spray", "9", "line", "11", "rect", "polygon", "oval",
				"roundrect" };

		for (int i = 0; i < 16; i++) {
			JRadioButton btn1 = new JRadioButton();
			left.add(btn1);
			group.add(btn1);
			btn1.setActionCommand(strs[i]);
			btn1.setSelected(true);
			// 设置按钮的图片
			ImageIcon defaultIcon = new ImageIcon("image/draw" + i + ".jpg");
			btn1.setIcon(defaultIcon);

			ImageIcon rolloverIcon = new ImageIcon("image/draw" + i + "-1.jpg");
			btn1.setRolloverIcon(rolloverIcon);

			ImageIcon pressedIcon = new ImageIcon("image/draw" + i + "-2.jpg");
			btn1.setPressedIcon(pressedIcon);

			ImageIcon selectedIcon = new ImageIcon("image/draw" + i + "-3.jpg");
			btn1.setSelectedIcon(selectedIcon);
		}

		// ******************中间面板添加***********************************************//
		final MyPanel drawpJPanel = new MyPanel();
		Dimension drawDim = new Dimension(400, 300);
		drawpJPanel.setPreferredSize(drawDim);
		drawpJPanel.setBackground(Color.WHITE);

		// 面板默认的布局是流式布局
		// 指定中间面板的布局为流式布局左对齐
		FlowLayout f1 = new FlowLayout(FlowLayout.LEFT);
		center.setLayout(f1);
		center.add(drawpJPanel);

		// *******************底部面板添加颜色选择工具****************************************//
		final JLabel frontLabel = new JLabel();
		JLabel backLabel = new JLabel();

		// 使用绝对定位布局
		foot.setLayout(null);
		frontLabel.setBounds(50, 20, 40, 60);
		backLabel.setBounds(110, 20, 40, 60);
		frontLabel.setBackground(Color.BLACK);
		backLabel.setBackground(Color.BLUE);
		// 是否允许背景色显示出来
		frontLabel.setOpaque(true);
		backLabel.setOpaque(true);

		foot.add(frontLabel);
		foot.add(backLabel);

		// 颜色
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
			// 给颜色Label添加鼠标监听器
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
		JButton jb = new JButton(" 颜色板   ");
		jb.setBounds(470, 30, 90, 40);
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color newcolor = JColorChooser.showDialog(drawpJPanel, "调色板",
						drawpJPanel.getBackground());
				if (newcolor != null) {
					frontLabel.setBackground(newcolor);// JFrame类里面只能由内容面板来设定颜色
				}

			}
		});
		foot.add(jb);

		// ***********************调色板

		// *******************菜单栏的实现****************************************//
		MenuListener mlis = new MenuListener(drawpJPanel);

		// 添加菜单
		JMenuBar bar = new JMenuBar();
		String[] menus = { "文件", "编辑", "查看", "图像", "关于" };
		String[][] items = { { "打开", "保存"}, { "撤销", "复制", "粘贴" },
				{}, {}, {} };
		for (int i = 0; i < menus.length; i++) {
			JMenu menu = new JMenu(menus[i]);
			bar.add(menu);
			for (int j = 0; j < items[i].length; j++) {
				JMenuItem item1 = new JMenuItem(items[i][j]);
				//设置动作命令
				item1.setActionCommand(items[i][j]);
				menu.add(item1);
				item1.addActionListener(mlis);
			}
		}

		// 设置窗体的菜单条
		jf.setJMenuBar(bar);
		jf.setVisible(true);
		// 调用创建监听器的方法，并传入参数
		DrawListener dlis = new DrawListener(drawpJPanel, group, frontLabel,
				backLabel);
		drawpJPanel.addMouseListener(dlis);
		drawpJPanel.addMouseMotionListener(dlis);
	}

}
