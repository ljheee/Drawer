package Draw2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * 菜单处理监听器
 * 
 * @author kowloon
 * 
 */
public class MenuListener implements ActionListener {

	private MyPanel panel;

	public MenuListener(MyPanel panel) {
		this.panel = panel;
	}

	// bmp文件头
	public void savebmpTop(OutputStream ops) throws Exception {
		// 位图文件的类型，必须为BM
		ops.write('B');
		ops.write('M');
		int height = DrawListener.array.length;
		int width = DrawListener.array[0].length;
		// 位图文件的大小，以字节为单位
		int size = 14 + 40 + height * width * 3 + (4 - width * 3 % 4) * 3 * height;
		writeInt(ops, size);
		// 保留字节，必须为零
		writeShort(ops, (short) 0);
		writeShort(ops, (short) 0);
		// 位图偏移量
		writeInt(ops, 54);
	}

	// 位图信息头
	public void savebmpInfo(OutputStream ops) throws Exception {
		int height = DrawListener.array.length;
		int width = DrawListener.array[0].length;
		// 位图信息头长度
		writeInt(ops, 40);
		// 位图宽
		writeInt(ops, width);
		// 位图高
		writeInt(ops, height);
		// 位图位面数总是为1
		writeShort(ops, (short) 1);
		// 位图24位像素
		writeShort(ops, (short) 24);
		// 位图是否被压缩，0为不压缩
		writeInt(ops, 0);
		// 字节数代表位图大小
		writeInt(ops, height * width * 3 + (4 - width * 3 % 4) * 3 * height);
		// 水平分辨率 每米像素数
		writeInt(ops, 0);
		// 垂直分辨率 每米像素数
		writeInt(ops, 0);
		// 颜色索引，0为所有调色板
		writeInt(ops, 0);
		// 对图象显示有重要影响的颜色索引的数目。如果是0，表示都重要
		writeInt(ops, 0);
	}

	// 图像数据阵列
	public void savebmpDate(OutputStream ops) throws Exception {
		int height = DrawListener.array.length;
		int width = DrawListener.array[0].length;
		int m = 0;
		// 进行补0
		if (width * 3 % 4 > 0) {
			m = 4 - width * 3 % 4;
		}
		for (int i = height - 1; i >= 0; i--) {
			for (int j = 0; j < width; j++) {
				int t = DrawListener.array[i][j];
				writeColor(ops, t);
			}
			for (int k = 0; k < m; k++) {
				ops.write(0);
			}
		}
	}

	// 传入一个int值，转化成4个8位的二进制，刚好四个字节
	public void writeInt(OutputStream ops, int t) throws Exception {
		int a = (t >> 24) & 0xff;
		int b = (t >> 16) & 0xff;
		int c = (t >> 8) & 0xff;
		int d = t & 0xff;
		ops.write(d);
		ops.write(c);
		ops.write(b);
		ops.write(a);
	}

	// 总共是24位的色图，分成3个字节来存储（每8位二进制数据为一个字节）
	public void writeColor(OutputStream ops, int t) throws Exception {
		int b = (t >> 16) & 0xff;
		int c = (t >> 8) & 0xff;
		int d = t & 0xff;
		ops.write(d);
		ops.write(c);
		ops.write(b);
	}

	public void writeShort(OutputStream ops, short t) throws Exception {
		int c = (t >> 8) & 0xff;
		int d = t & 0xff;
		ops.write(d);
		ops.write(c);
	}

	// 由于读取的是字节，把读取到的4个byte转化成1个int
	public int changeInt(InputStream ips) throws IOException {
		int t1 = ips.read() & 0xff;
		int t2 = ips.read() & 0xff;
		int t3 = ips.read() & 0xff;
		int t4 = ips.read() & 0xff;
		int num = (t4 << 24) + (t3 << 16) + (t2 << 8) + t1;
		System.out.println(num);
		return num;
	}

	// 24位的图片是1个像素3个字节。
	public int readColor(InputStream ips) throws IOException {
		int b = ips.read() & 0xff;
		int g = ips.read() & 0xff;
		int r = ips.read() & 0xff;
		int c = (r << 16) + (g << 8) + b;
		return c;
	}

	public void actionPerformed(ActionEvent e) {
		// 获得被点击的组件的动作命令
		String command = e.getActionCommand();
		JFileChooser chooser = new JFileChooser();
		if (command.equals("保存")) {
			int t = chooser.showSaveDialog(null);
			if (t == JFileChooser.APPROVE_OPTION) {
				String path = chooser.getSelectedFile().getAbsolutePath();
				try {
					FileOutputStream fos = new FileOutputStream(path);
					DataOutputStream dos = new DataOutputStream(fos);
					savebmpTop(dos);// 存储文件头
					savebmpInfo(dos);// 存储位图信息头
					savebmpDate(dos);//// 图像数据阵列
					fos.flush();
					fos.close();
				} catch (Exception ef) {
					JOptionPane.showMessageDialog(null, "文件保存失败！！");
					ef.printStackTrace();
				}
			}
		} else if (command.equals("打开")) {

			int t = chooser.showOpenDialog(null);
			if (t == JFileChooser.APPROVE_OPTION) {
				String path = chooser.getSelectedFile().getAbsolutePath();
				try {
					FileInputStream fis = new FileInputStream(path);
					DataInputStream dis = new DataInputStream(fis);
					dis.skip(18);// 跳过不需要的，读取宽度和高度
					int width = changeInt(dis);
					int height = changeInt(dis);
					dis.skip(28);
					// 跳过，直接读取位图数据。
					DrawListener.array = new int[height][width];
					int w = 0;
					if (width * 3 % 4 > 0) {
						t = 4 - width * 3 % 4;
					}
					for (int i = height - 1; i >= 0; i--) {
						for (int j = 0; j < width; j++) {
							// 调用自定义方法，得到图片的像素点并保存到int数组中
							int c = readColor(dis);
							DrawListener.array[i][j] = c;
						}
						dis.skip(w);
					}
					fis.close();
					// 刷新界面
					panel.repaint();
				} catch (Exception ef) {
					JOptionPane.showMessageDialog(null, "文件打开失败！！");
					ef.printStackTrace();
				}
			}
		}

	}

}
