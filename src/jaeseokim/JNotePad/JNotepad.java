package jaeseokim.JNotePad;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

public class JNotepad extends JFrame implements ActionListener{
	int fontsize = 15;
	double ver = 0.9;
	FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("txt 파일", "txt");
	
	JPanel textPanel= new JPanel();
	
	JMenuItem mif1 = new JMenuItem("새 파일");
	JMenuItem mif2 = new JMenuItem("파일 열기");
	JMenuItem mif3 = new JMenuItem("파일 저장");
	JMenuItem mif4 = new JMenuItem("종료");
	JTextArea text = new JTextArea();
	
	JMenuItem mis1 = new JMenuItem("Font Up");
	JMenuItem mis2 = new JMenuItem("Font Down");
	JMenuItem mis3 = new JMenuItem("Set Font Size");
	
	JMenuItem mii1 = new JMenuItem("license");
	JMenuItem mii2 = new JMenuItem("information");
	
	ImageIcon imageIcon = new ImageIcon("icon.png");
	Image image = imageIcon.getImage();
	
	JLabel footer = new JLabel("준비완료");
	
	public void SaveJFileChooser() {
		JFileChooser jfc = new JFileChooser();
		jfc.addChoosableFileFilter(txtFilter);
		jfc.setFileFilter(txtFilter);
		int returnVal = jfc.showSaveDialog(jfc);
		if(returnVal == 0) {
			File file = jfc.getSelectedFile();
			System.out.println("저장할 파일 " + file.getName());
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(file,false));
				bw.write(text.getText());
				bw.flush();
				bw.close();
				footer.setText(file.getName() +" 저장완료!");
			}catch(Exception e) {e.printStackTrace();}
			
		}
	}
	
	public void OpenJFileChooser() {
		JFileChooser jfc = new JFileChooser();
		jfc.addChoosableFileFilter(txtFilter);
		jfc.setFileFilter(txtFilter);
		int returnVal = jfc.showSaveDialog(jfc);
		if(returnVal == 0) {
			File file = jfc.getSelectedFile();
			System.out.println("열어볼 파일 " + file.getName());
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String tmp;
				text.setText("");
				while((tmp=br.readLine())!=null){
					text.append(tmp);
					text.append("\n");
				}
				footer.setText(file.getName() +" 불러오기 성공!");
				br.close();
			}catch(Exception e) {e.printStackTrace();}
		}
	}
	
	public void MakeMenu() {
		JMenuBar mb = new JMenuBar();
		JMenu m1 = new JMenu("파일");
		JMenu m2 = new JMenu("설정");
		JMenu m3 = new JMenu("정보");
		
		JMenu ms1 = new JMenu("Font Size"); 
		
		ms1.add(mis1);
		ms1.add(mis2);
		ms1.add(mis3);
		
		m1.add(mif1);
		m1.add(mif2);
		m1.add(mif3);
		m1.add(mif4);

		m2.add(ms1);
		
		m3.add(mii1);
		m3.add(mii2);
		
		
		
		mis1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, ActionEvent.CTRL_MASK));
		mis2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS,ActionEvent.CTRL_MASK));
		
		mif1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
		mif2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
		mif3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		
		mif1.addActionListener(this);
		mif2.addActionListener(this);
		mif3.addActionListener(this);
		mif4.addActionListener(this);
		
		mis1.addActionListener(this);
		mis2.addActionListener(this);
		mis3.addActionListener(this);
		
		mii1.addActionListener(this);
		mii2.addActionListener(this);
		
		mb.setBackground(Color.lightGray);
		mb.add(m1);
		mb.add(m2);
		mb.add(m3);
		
		setJMenuBar(mb);
	}
	
	
	public JNotepad() {
		MakeMenu();
		JScrollPane sp = new JScrollPane(text);
		textPanel.setLayout(new BorderLayout());
		textPanel.add(sp);
		text.setLineWrap(true); 
		text.setFont(new Font("고딕체", Font.PLAIN, fontsize));
		textPanel.setBackground(Color.white);
		add(textPanel);
		add(footer,BorderLayout.SOUTH);
		
		super.setIconImage(image);
		setTitle("JNotePad");
		setVisible(true);
		setSize(400,500);
		setLocation(300,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new JNotepad();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "새 파일":
			System.out.println("새 파일");
			int com = JOptionPane.showConfirmDialog(null, "새로 파일을 만드시겠습니까?");
			switch (com) {
			case 0:
				text.setText(null);
				footer.setText("준비완료(새 파일)");
				break;
			default:
				break;
			}
			break;
		case "파일 열기":
			System.out.println("파일 열기");
			OpenJFileChooser();
			break;
		case "파일 저장":
			System.out.println("파일 저장");
			SaveJFileChooser();
			break;
		case "종료":
			System.exit(0);
			break;
		case "license":
			JOptionPane.showMessageDialog(null, "Copyright ⓒ 2019.김재서 All Rights Reserved.");
			break;
		case "information":
			JOptionPane.showMessageDialog(null, ver+".Ver");
			break;
		case "Font Up":
			fontsize+=2;
			if(fontsize>100)
				fontsize=100;
			text.setFont(new Font("고딕체", Font.PLAIN, fontsize));
			break;
		case "Font Down":
			fontsize-=2;
			if(fontsize<10)
				fontsize=10;
			text.setFont(new Font("고딕체", Font.PLAIN, fontsize));
			break;
		case "Set Font Size":
			int flag = 1;
			while(flag == 1) {
				try {
					int tmp = Integer.parseInt(JOptionPane.showInputDialog("폰트 사이즈를 입력해주세요(10~100)"));	
					fontsize = tmp;
					if(fontsize > 100)
						fontsize = 100;
					else if(fontsize <10)
						fontsize = 10;
					text.setFont(new Font("고딕체", Font.PLAIN, fontsize));
					flag = 0;
				} catch (Exception e2) {
					if(e2.getMessage() == "null") {
						flag = 0;
					}else {
						JOptionPane.showMessageDialog(null, "정확한 숫자를 입력하세요.");
						flag = 1;
					}
				}
			}
			break;
		default:
			break;
		}
	}
}
