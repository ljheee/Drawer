package Draw2;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

/**
 * ��ɫѡ��ļ�����
 * @author leaf-stop
 *
 */
public class ColorListener implements MouseListener{
	private JLabel frontLabel;
	private JLabel backLabel;
	
	public ColorListener(JLabel fLabel,JLabel bLabel){
		frontLabel=fLabel;
		backLabel=bLabel;
	}
	
	public void mouseReleased(MouseEvent e) {
		// ����¼�Դ����:�����¼������
		// ���ڼ������Ǽ���Jlabel�ϵģ������¼�Դ����һ����JLabel
		JLabel label=(JLabel)e.getSource();
		Color c=label.getBackground();
		
		// ����������������Ҽ�
		int num = e.getButton();		
		if(num==1){
			frontLabel.setBackground(c);		
		}else if(num==3){
			backLabel.setBackground(c);
		}
	}


	public void mouseEntered(MouseEvent e) {
		
	}


	public void mouseExited(MouseEvent e) {
		
	}

	public void mouseClicked(MouseEvent e) {
		
	}


	public void mousePressed(MouseEvent e) {
		
	}

}