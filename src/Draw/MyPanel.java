package Draw;

import java.awt.Graphics;

import javax.swing.JPanel;

public class MyPanel extends JPanel{
	// 重写JPanel中绘制图形的方法
		// 面板发生改变需要重新绘制的时候就自动调用
		public void paint(Graphics g) {
			super.paint(g);// 将面板本身绘制在屏幕上

			// 当面板发生改变的时候，
			//将ArrayList中保存的形状对象取出来，重新绘制
		//	System.out.println("变了");
			for(int i=0;i<DrawListener.list.size();i++){
				Shape shape = DrawListener.list.get(i);
				shape.draw(g);
			}
		}
}
