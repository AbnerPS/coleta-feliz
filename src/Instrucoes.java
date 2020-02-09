import java.awt.Color;

import javax.swing.JFrame;

public class Instrucoes extends JFrame {
	public Instrucoes() {
		getContentPane().setBackground(new Color(85, 107, 47));
		setSize(300, 300);
		setLocationRelativeTo(null); // tela no centro
		setTitle("Instruções"); // Titulo
		setResizable(false); // impede redimensionamento
		getContentPane().setLayout(null);
		setLocationRelativeTo(null); // tela no centro
		setVisible(true); // Visibilidade da tela
	}

	public static void main(String[] args) {
		new Instrucoes();
	}

}
