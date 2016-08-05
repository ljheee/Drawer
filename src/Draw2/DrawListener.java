package Draw2;

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 画图的监听器 各种按钮功能的实现
 * 
 * @author leaf-stop
 * 
 */
public class DrawListener extends MouseAdapter {

	public static int[][] array = null;
	private int x1, y1, x2, y2, x3, y3;
	private Graphics g;
	private JPanel drawJPanel;
	private ButtonGroup group;
	private String type = "line";
	private Color frontColor = Color.RED;
	private Color backColor = Color.BLUE;
	private JLabel frontLabel;
	private JLabel backLabel;
	public static boolean flag;

	Robot robot = null;

	// 重载
	public DrawListener(JPanel dp, ButtonGroup bg, JLabel fLabel, JLabel bLabel) {
		drawJPanel = dp;
		group = bg;
		frontLabel = fLabel;
		backLabel = bLabel;
		// 获得面板的大小
		Dimension dim = drawJPanel.getPreferredSize();
		// 根据面板大小创建保存面板数据的二维数组
		array = new int[dim.height][dim.width];
		// 保存初始颜色
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				array[i][j] = Color.WHITE.getRGB();
			}
		}

		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}

	}

	// 鼠标按下
	public void mousePressed(MouseEvent e) {
		// 鼠标按下准备绘制图形的时候先获取能绘制的区域[画布]
		// 获取drawPanel在屏幕上占据的区域，作为可以改变颜色的区域
		g = drawJPanel.getGraphics();

		frontColor = frontLabel.getBackground();
		backColor = backLabel.getBackground();

		int num = e.getButton();
		// 获得鼠标点击的是左键还是右键
		if (num == 1) {
			g.setColor(frontColor);
		} else if (num == 3)
			g.setColor(backColor);

		// 鼠标按下准备绘制的时候来确定要绘制的图形
		// 获得被选中的按钮模型
		ButtonModel model = group.getSelection();
		// 获得动作命令[每一个按钮的唯一标识]
		type = model.getActionCommand();

		x1 = e.getX();
		y1 = e.getY();

	};

	// 释放
	public void mouseReleased(MouseEvent e) {
		x2 = e.getX();
		y2 = e.getY();

		int x = x1 < x2 ? x1 : x2;// 取小的
		int y = y1 < y2 ? y1 : y2;
		int width = x1 < x2 ? x2 - x1 : x1 - x2;
		int height = y1 < y2 ? y2 - y1 : y1 - y2;
		int arcWidth = 10;
		int arcHeight = 10;

		// 直线，矩形，椭圆的绘制
		if (type.equals("line")) {// 直线
			g.drawLine(x1, y1, x2, y2);
		} else if (type.equals("rect")) {// 矩形
			g.drawRect(x, y, width, height);
		} else if (type.equals("oval")) {// 椭圆
			g.drawOval(x, y, width, height);
		} else if (type.equals("roundrect")) {// 圆角矩形
			g.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
		} else if (type.equals("polygon")) {// 多边形
			if (flag == false) {
				g.drawLine(x1, y1, x2, y2);
				x1 = x2;
				y1 = y2;
				flag = true;
			} else if (flag == true) {
				g.drawLine(x3, y3, x1, y1);
				x3 = x1;
				y3 = y1;
			}
		} // 刷子
		else if (type.equals("brush")) {
			Graphics2D g2d = (Graphics2D) g;// 为Graphics的一个子类
			g2d.setStroke(new BasicStroke(8));// 设置大小
			g2d.drawLine(x1, y1, x2, y2);
			g2d.setStroke(new BasicStroke(1));

		}
		// 橡皮檫
		else if (type.equals("easer")) {
			g.setColor(Color.WHITE);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setStroke(new BasicStroke(8));
			g2d.drawLine(x1, y1, x2, y2);
			g2d.setStroke(new BasicStroke(1));
		}
		// 铅笔
		else if (type.equals("pencial")) {
			g.drawLine(x1, y1, x2, y2);
		}
		// 喷枪
		else if (type.equals("spray")) {
			g.drawLine(x1, y1, x2, y2);
		}

		// 释放一次，就从新保存一次
		// 1.截屏
		Point point = drawJPanel.getLocationOnScreen();
		Dimension dim = drawJPanel.getPreferredSize();
		Rectangle screenRect = new Rectangle(point, dim);
		BufferedImage bufferImg = robot.createScreenCapture(screenRect);
		// 根据面板大小调整数组大小
		array = new int[dim.height][dim.width];

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				array[i][j] = bufferImg.getRGB(j, i);
			}
		}

	};

	public void mouseDragged(MouseEvent e) {
		x2 = e.getX();
		y2 = e.getY();

		// 绘制图形
		// 刷子
		if (type.equals("brush")) {
			Graphics2D g2d = (Graphics2D) g;// 为Graphics的一个子类
			g2d.setStroke(new BasicStroke(8));// 设置大小
			g2d.drawLine(x1, y1, x2, y2);

			// 将末位置变成下一次的初始位置
			x1 = x2;
			y1 = y2;
			g2d.setStroke(new BasicStroke(1));

		}
		// 橡皮擦
		else if (type.equals("easer")) {
			g.setColor(Color.WHITE);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setStroke(new BasicStroke(8));
			g2d.drawLine(x1, y1, x2, y2);

			x1 = x2;
			y1 = y2;
			g2d.setStroke(new BasicStroke(1));
		}
		// 铅笔
		else if (type.equals("pencial")) {

			g.drawLine(x1, y1, x2, y2);

			x1 = x2;
			y1 = y2;
		}
		// 喷枪
		else if (type.equals("spray")) {
			Random random = new Random();
			for (int i = 0; i < 30; i++) {
				int x = random.nextInt(10);
				int y = random.nextInt(10);
				g.drawLine(x1 + x, y1 + y, x2 + x, y2 + y);

				x1 = x2;
				y1 = y2;
			}

		}

	}

	// 进入
	public void mouseEntered(MouseEvent e) {

	};

	// 离开
	public void mouseExited(MouseEvent e) {

	};

	// 点击
	public void mouseClicked(MouseEvent e) {

	}
}