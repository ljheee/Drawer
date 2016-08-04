package Draw;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

/**
 * 颜色选择的监听器
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
		// 获得事件源对象:发生事件的组件
		// 由于监听器是加在Jlabel上的，所以事件源对象一定是JLabel
		JLabel label=(JLabel)e.getSource();
		Color c=label.getBackground();
		
		// 获得鼠标是左键还是右键
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