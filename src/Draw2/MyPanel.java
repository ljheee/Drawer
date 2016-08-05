package Draw2;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MyPanel extends JPanel {
	// 重写JPanel中绘制图形的方法
	// 面板发生改变需要重新绘制的时候就自动调用
	public void paint(Graphics g) {
		super.paint(g);// 将面板本身绘制在屏幕上

		// 当面板发生改变的时候，
		// 将数组中保存的点的颜色取出来，重新绘制
		// System.out.println("变了");
		for (int i = 0; i < DrawListener.array.length; i++) {
			for (int j = 0; j < DrawListener.array[i].length; j++) {
				Color color = new Color(DrawListener.array[i][j]);
				g.setColor(color);
				g.drawLine(j, i, j, i);

			}
		}
	}
}
