package Draw;

import java.awt.Graphics;

import javax.swing.JPanel;

public class MyPanel extends JPanel{
	// ��дJPanel�л���ͼ�εķ���
		// ��巢���ı���Ҫ���»��Ƶ�ʱ����Զ�����
		public void paint(Graphics g) {
			super.paint(g);// ����屾���������Ļ��

			// ����巢���ı��ʱ��
			//��ArrayList�б������״����ȡ���������»���
		//	System.out.println("����");
			for(int i=0;i<DrawListener.list.size();i++){
				Shape shape = DrawListener.list.get(i);
				shape.draw(g);
			}
		}
}
