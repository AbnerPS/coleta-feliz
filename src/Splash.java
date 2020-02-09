import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class Splash extends JWindow {

	private int duration; // Tempo de duração do Splash

	public Splash(int d) {
		duration = d; // parâmetro sendo passado para variável
	}

	public void showSplash() {
		JPanel content = (JPanel) getContentPane(); // Criando fundo
		content.setBackground(Color.black); // Setando a cor

		// Configura a posição e o tamanho da janela
		int width = 600;
		int height = 200;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width) / 2;
		int y = (screen.height - height) / 2;
		setBounds(x, y, width, height);

		// Constrói o splash screen
		JLabel label = new JLabel(new ImageIcon(
				//"C:\\Users\\geova\\OneDrive\\Documentos\\APS_coleta\\3º Semestre\\ColetaFeliz\\src\\Splash.png",
				"C:\\Users\\Abner\\Documents\\Atividades%20Pr%C3%A1ticas%20Supervisionadas\\3º Semestre\\ColetaFeliz\\src\\Splash.png")); // Imagem
		JLabel copyrt = new JLabel("Copyright 2018, Colheita Feliz", JLabel.CENTER); // Texto inferior
		copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12)); // Características da fonte
		content.add(label, BorderLayout.CENTER); // alinhando ao centro
		content.add(copyrt, BorderLayout.SOUTH); // alinhando abaixo
		Color corborda = new Color(0, 0, 0, 0); // criando cor
		content.setBorder(BorderFactory.createLineBorder(corborda, 10)); // setando cor e espessura da borda
		setVisible(true); // Torna a janela visível

		// Espera até que os recursos estejam carregados
		try {
			Thread.sleep(duration);
		} catch (Exception e) {
		}
		setVisible(false);
	}

	public void showSplashAndExit() {
		showSplash(); // Executa Splash
		Menu menu = new Menu(); // Abre Menu

	}

	public static void main(String[] args) {
		// Mostra uma imagem com o título da aplicação
		Splash splash = new Splash(1000); // Seta Splash com duração no parâmetro
		splash.showSplashAndExit(); // Exibe e chama Menu
	}
}