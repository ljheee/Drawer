package Draw2;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MyPanel extends JPanel {
	// ��дJPanel�л���ͼ�εķ���
	// ��巢���ı���Ҫ���»��Ƶ�ʱ����Զ�����
	public void paint(Graphics g) {
		super.paint(g);// ����屾���������Ļ��

		// ����巢���ı��ʱ��
		// �������б���ĵ����ɫȡ���������»���
		// System.out.println("����");
		for (int i = 0; i < DrawListener.array.length; i++) {
			for (int j = 0; j < DrawListener.array[i].length; j++) {
				Color color = new Color(DrawListener.array[i][j]);
				g.setColor(color);
				g.drawLine(j, i, j, i);

			}
		}
	}
}
