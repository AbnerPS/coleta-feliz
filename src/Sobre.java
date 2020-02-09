import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Sobre extends JFrame {

	Sobre() {
		JPanel content = (JPanel) getContentPane();
		content.setBackground(Color.GRAY);

		// Configura a posição e o tamanho da janela
		int width = 500;
		int height = 500;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width) / 2;
		int y = (screen.height - height) / 2;
		setBounds(x, y, width, height);

		// Constrói tela
		JLabel label = new JLabel(new ImageIcon("imagem_Sobre.jpg"));
		JLabel copyrt = new JLabel("Copyright 2018, Colheita Feliz", JLabel.CENTER);
		copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12));
		content.add(label, BorderLayout.CENTER);
		content.add(copyrt, BorderLayout.SOUTH);
		Color corborda = new Color(0, 0, 0, 0);
		content.setBorder(BorderFactory.createLineBorder(corborda, 10));
		// Torna visível
		setVisible(true);
	}

	public static void main(String[] args) {
		new Sobre();
	}
}
