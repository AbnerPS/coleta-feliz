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

    private int duration; // Tempo de dura��o do Splash

    public Splash(int d) {
        duration = d; // par�metro sendo passado para vari�vel
    }

    public void showSplash() {
        JPanel content = (JPanel) getContentPane(); // Criando fundo
        content.setBackground(Color.black); // Setando a cor

        // Configura a posi��o e o tamanho da janela
        int width = 600;
        int height = 200;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);

        // Constr�i o splash screen
        JLabel label = new JLabel(new ImageIcon("src\\imagens\\Splash.png")); // Imagem
        JLabel copyrt = new JLabel("Copyright 2018, Colheita Feliz", JLabel.CENTER); // Texto inferior
        copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12)); // Caracter�sticas da fonte
        content.add(label, BorderLayout.CENTER); // alinhando ao centro
        content.add(copyrt, BorderLayout.SOUTH); // alinhando abaixo
        Color corborda = new Color(0, 0, 0, 0); // criando cor
        content.setBorder(BorderFactory.createLineBorder(corborda, 10)); // setando cor e espessura da borda
        setVisible(true); // Torna a janela vis�vel

        // Espera at� que os recursos estejam carregados
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
        // Mostra uma imagem com o t�tulo da aplica��o
        Splash splash = new Splash(1000); // Seta Splash com dura��o no par�metro
        splash.showSplashAndExit(); // Exibe e chama Menu
    }
}