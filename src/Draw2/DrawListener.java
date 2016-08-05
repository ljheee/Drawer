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
 * ��ͼ�ļ����� ���ְ�ť���ܵ�ʵ��
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

	// ����
	public DrawListener(JPanel dp, ButtonGroup bg, JLabel fLabel, JLabel bLabel) {
		drawJPanel = dp;
		group = bg;
		frontLabel = fLabel;
		backLabel = bLabel;
		// ������Ĵ�С
		Dimension dim = drawJPanel.getPreferredSize();
		// ��������С��������������ݵĶ�ά����
		array = new int[dim.height][dim.width];
		// �����ʼ��ɫ
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

	// ��갴��
	public void mousePressed(MouseEvent e) {
		// ��갴��׼������ͼ�ε�ʱ���Ȼ�ȡ�ܻ��Ƶ�����[����]
		// ��ȡdrawPanel����Ļ��ռ�ݵ�������Ϊ���Ըı���ɫ������
		g = drawJPanel.getGraphics();

		frontColor = frontLabel.getBackground();
		backColor = backLabel.getBackground();

		int num = e.getButton();
		// ��������������������Ҽ�
		if (num == 1) {
			g.setColor(frontColor);
		} else if (num == 3)
			g.setColor(backColor);

		// ��갴��׼�����Ƶ�ʱ����ȷ��Ҫ���Ƶ�ͼ��
		// ��ñ�ѡ�еİ�ťģ��
		ButtonModel model = group.getSelection();
		// ��ö�������[ÿһ����ť��Ψһ��ʶ]
		type = model.getActionCommand();

		x1 = e.getX();
		y1 = e.getY();

	};

	// �ͷ�
	public void mouseReleased(MouseEvent e) {
		x2 = e.getX();
		y2 = e.getY();

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
			g.drawLine(x1, y1, x2, y2);
		}

		// �ͷ�һ�Σ��ʹ��±���һ��
		// 1.����
		Point point = drawJPanel.getLocationOnScreen();
		Dimension dim = drawJPanel.getPreferredSize();
		Rectangle screenRect = new Rectangle(point, dim);
		BufferedImage bufferImg = robot.createScreenCapture(screenRect);
		// ��������С���������С
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

		// ����ͼ��
		// ˢ��
		if (type.equals("brush")) {
			Graphics2D g2d = (Graphics2D) g;// ΪGraphics��һ������
			g2d.setStroke(new BasicStroke(8));// ���ô�С
			g2d.drawLine(x1, y1, x2, y2);

			// ��ĩλ�ñ����һ�εĳ�ʼλ��
			x1 = x2;
			y1 = y2;
			g2d.setStroke(new BasicStroke(1));

		}
		// ��Ƥ��
		else if (type.equals("easer")) {
			g.setColor(Color.WHITE);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setStroke(new BasicStroke(8));
			g2d.drawLine(x1, y1, x2, y2);

			x1 = x2;
			y1 = y2;
			g2d.setStroke(new BasicStroke(1));
		}
		// Ǧ��
		else if (type.equals("pencial")) {

			g.drawLine(x1, y1, x2, y2);

			x1 = x2;
			y1 = y2;
		}
		// ��ǹ
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

	// ����
	public void mouseEntered(MouseEvent e) {

	};

	// �뿪
	public void mouseExited(MouseEvent e) {

	};

	// ���
	public void mouseClicked(MouseEvent e) {

	}
}