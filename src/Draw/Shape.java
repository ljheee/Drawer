package Draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.Random;
//ʵ������ӿ��Ժ󣬿���ʹ����ʵ��IO��
//���ļ�������Ҫ�ö���������������ֽ�����
public class Shape  implements Serializable{
	private int x1, y1, x2, y2, x3, y3;// ����
	private Color color;// ��״��ɫ
	private String type;// ��״����
	private boolean flag = false;

	public Shape(int x1, int y1, int x2, int y2, Color color, String type) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
		this.type = type;

	}

	// ����ͼ�εķ���
	public void draw(Graphics g) {
		// Ҫ���Ƶ���ɫ
		g.setColor(color);
		int x = x1 < x2 ? x1 : x2;// ȡС��
		int y = y1 < y2 ? y1 : y2;
		int width = x1 < x2 ? x2 - x1 : x1 - x2;
		int height = y1 < y2 ? y2 - y1 : y1 - y2;
		int arcWidth = 10;
		int arcHeight = 10;

		// ֱ�ߣ����Σ���Բ�Ļ���
		if (type.equals("line")) {// ֱ��
			g.drawLine(x1, y1, x2, y2);
		} else if (type.equals("rect")) {// ����
			g.drawRect(x, y, width, height);
		} else if (type.equals("oval")) {// ��Բ
			g.drawOval(x, y, width, height);
		} else if (type.equals("roundrect")) {// Բ�Ǿ���
			g.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
		} else if (type.equals("polygon")) {// �����
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
		} // ˢ��
		else if (type.equals("brush")) {
			Graphics2D g2d = (Graphics2D) g;// ΪGraphics��һ������
			g2d.setStroke(new BasicStroke(8));// ���ô�С
			g2d.drawLine(x1, y1, x2, y2);
			g2d.setStroke(new BasicStroke(1));

		}
		// ��Ƥ��
		else if (type.equals("easer")) {
			g.setColor(Color.WHITE);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setStroke(new BasicStroke(8));
			g2d.drawLine(x1, y1, x2, y2);
			g2d.setStroke(new BasicStroke(1));
		}
		// Ǧ��
		else if (type.equals("pencial")) {
			g.drawLine(x1, y1, x2, y2);
		}
		// ��ǹ
		else if (type.equals("spray")) {
			g.drawLine(x1, y1  ,x2 , y2);
		}

	}

}
