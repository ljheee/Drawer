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
 * �˵����������
 * 
 * @author kowloon
 * 
 */
public class MenuListener implements ActionListener {

	private MyPanel panel;

	public MenuListener(MyPanel panel) {
		this.panel = panel;
	}

	// bmp�ļ�ͷ
	public void savebmpTop(OutputStream ops) throws Exception {
		// λͼ�ļ������ͣ�����ΪBM
		ops.write('B');
		ops.write('M');
		int height = DrawListener.array.length;
		int width = DrawListener.array[0].length;
		// λͼ�ļ��Ĵ�С�����ֽ�Ϊ��λ
		int size = 14 + 40 + height * width * 3 + (4 - width * 3 % 4) * 3 * height;
		writeInt(ops, size);
		// �����ֽڣ�����Ϊ��
		writeShort(ops, (short) 0);
		writeShort(ops, (short) 0);
		// λͼƫ����
		writeInt(ops, 54);
	}

	// λͼ��Ϣͷ
	public void savebmpInfo(OutputStream ops) throws Exception {
		int height = DrawListener.array.length;
		int width = DrawListener.array[0].length;
		// λͼ��Ϣͷ����
		writeInt(ops, 40);
		// λͼ��
		writeInt(ops, width);
		// λͼ��
		writeInt(ops, height);
		// λͼλ��������Ϊ1
		writeShort(ops, (short) 1);
		// λͼ24λ����
		writeShort(ops, (short) 24);
		// λͼ�Ƿ�ѹ����0Ϊ��ѹ��
		writeInt(ops, 0);
		// �ֽ�������λͼ��С
		writeInt(ops, height * width * 3 + (4 - width * 3 % 4) * 3 * height);
		// ˮƽ�ֱ��� ÿ��������
		writeInt(ops, 0);
		// ��ֱ�ֱ��� ÿ��������
		writeInt(ops, 0);
		// ��ɫ������0Ϊ���е�ɫ��
		writeInt(ops, 0);
		// ��ͼ����ʾ����ҪӰ�����ɫ��������Ŀ�������0����ʾ����Ҫ
		writeInt(ops, 0);
	}

	// ͼ����������
	public void savebmpDate(OutputStream ops) throws Exception {
		int height = DrawListener.array.length;
		int width = DrawListener.array[0].length;
		int m = 0;
		// ���в�0
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

	// ����һ��intֵ��ת����4��8λ�Ķ����ƣ��պ��ĸ��ֽ�
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

	// �ܹ���24λ��ɫͼ���ֳ�3���ֽ����洢��ÿ8λ����������Ϊһ���ֽڣ�
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

	// ���ڶ�ȡ�����ֽڣ��Ѷ�ȡ����4��byteת����1��int
	public int changeInt(InputStream ips) throws IOException {
		int t1 = ips.read() & 0xff;
		int t2 = ips.read() & 0xff;
		int t3 = ips.read() & 0xff;
		int t4 = ips.read() & 0xff;
		int num = (t4 << 24) + (t3 << 16) + (t2 << 8) + t1;
		System.out.println(num);
		return num;
	}

	// 24λ��ͼƬ��1������3���ֽڡ�
	public int readColor(InputStream ips) throws IOException {
		int b = ips.read() & 0xff;
		int g = ips.read() & 0xff;
		int r = ips.read() & 0xff;
		int c = (r << 16) + (g << 8) + b;
		return c;
	}

	public void actionPerformed(ActionEvent e) {
		// ��ñ����������Ķ�������
		String command = e.getActionCommand();
		JFileChooser chooser = new JFileChooser();
		if (command.equals("����")) {
			int t = chooser.showSaveDialog(null);
			if (t == JFileChooser.APPROVE_OPTION) {
				String path = chooser.getSelectedFile().getAbsolutePath();
				try {
					FileOutputStream fos = new FileOutputStream(path);
					DataOutputStream dos = new DataOutputStream(fos);
					savebmpTop(dos);// �洢�ļ�ͷ
					savebmpInfo(dos);// �洢λͼ��Ϣͷ
					savebmpDate(dos);//// ͼ����������
					fos.flush();
					fos.close();
				} catch (Exception ef) {
					JOptionPane.showMessageDialog(null, "�ļ�����ʧ�ܣ���");
					ef.printStackTrace();
				}
			}
		} else if (command.equals("��")) {

			int t = chooser.showOpenDialog(null);
			if (t == JFileChooser.APPROVE_OPTION) {
				String path = chooser.getSelectedFile().getAbsolutePath();
				try {
					FileInputStream fis = new FileInputStream(path);
					DataInputStream dis = new DataInputStream(fis);
					dis.skip(18);// ��������Ҫ�ģ���ȡ��Ⱥ͸߶�
					int width = changeInt(dis);
					int height = changeInt(dis);
					dis.skip(28);
					// ������ֱ�Ӷ�ȡλͼ���ݡ�
					DrawListener.array = new int[height][width];
					int w = 0;
					if (width * 3 % 4 > 0) {
						t = 4 - width * 3 % 4;
					}
					for (int i = height - 1; i >= 0; i--) {
						for (int j = 0; j < width; j++) {
							// �����Զ��巽�����õ�ͼƬ�����ص㲢���浽int������
							int c = readColor(dis);
							DrawListener.array[i][j] = c;
						}
						dis.skip(w);
					}
					fis.close();
					// ˢ�½���
					panel.repaint();
				} catch (Exception ef) {
					JOptionPane.showMessageDialog(null, "�ļ���ʧ�ܣ���");
					ef.printStackTrace();
				}
			}
		}

	}

}
