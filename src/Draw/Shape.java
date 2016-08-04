package Draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.Random;
//实现这个接口以后，可以使对象实现IO流
//存文件尽量不要用对象流，，最好用字节流；
public class Shape  implements Serializable{
	private int x1, y1, x2, y2, x3, y3;// 坐标
	private Color color;// 形状颜色
	private String type;// 形状类型
	private boolean flag = false;

	public Shape(int x1, int y1, int x2, int y2, Color color, String type) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
		this.type = type;

	}

	// 绘制图形的方法
	public void draw(Graphics g) {
		// 要绘制的颜色
		g.setColor(color);
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
			g.drawLine(x1, y1  ,x2 , y2);
		}

	}

}
