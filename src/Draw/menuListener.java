package Draw;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class menuListener implements ActionListener{
	DrawUI dui;
	Graphics g;
	MyPanel mp=new MyPanel();
	public menuListener(DrawUI dui) {
		this.dui = dui;

	}
	public void actionPerformed(ActionEvent e) {
		//保存开按钮
		String path = "D:\\日常文件\\java\\画图文件\\abc.txt";
		if (e.getSource() == dui.save) {
			// System.out.println("1111111");
			try {
				FileOutputStream fos = new FileOutputStream(path);
				// 将文件字节流包装成对象输出流
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				for (int i = 0; i < DrawListener.list.size(); i++) {
					Shape shape = DrawListener.list.get(i);
					oos.writeObject(shape);
				}
				oos.flush();
				fos.close();
				System.out.println("保存成功！");
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		//打开按钮
		if(e.getSource()==dui.open){
			FileInputStream fis;
			try {
				this.g=dui. drawpJPanel.getGraphics();
				fis = new FileInputStream(path);
				// 将输入流包装成对象输入流
				ObjectInputStream ois = new ObjectInputStream(fis);
				for (int i = 0; i < DrawListener.list.size(); i++) {
				Shape shape = (Shape) ois.readObject();
				shape.draw(g);
				}
				fis.close();
				System.out.println("打开成功");
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}

	}

	
}
