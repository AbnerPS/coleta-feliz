import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Component;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Menu extends JFrame {
	
	public Menu() {
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 11));
		getContentPane().setForeground(new Color(255, 255, 255));
		getContentPane().setBackground(new Color(0, 153, 102));
		setSize(200, 300);
		setLocationRelativeTo(null); // tela no centro
		setTitle("Coleta Feliz"); // Titulo
		setResizable(false); // impede redimensionamento
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Encerra aplicação ao fechar

		JButton btnIniciar = new JButton("INICIAR");
		btnIniciar.setLocation(new Point(150, 200));
		btnIniciar.setBounds(31, 75, 116, 47);
		getContentPane().add(btnIniciar);
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Game_Easy game = new Game_Easy();

				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		JButton btnSobre = new JButton("SOBRE");
		btnSobre.setLocation(new Point(150, 200));
		btnSobre.setBounds(31, 157, 116, 47);
		getContentPane().add(btnSobre);
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, (new ImageIcon("C:\\Users\\geova\\OneDrive\\Documentos\\APS_coleta\\3º Semestre\\ColetaFeliz\\src\\Splash.png",
						"C:\\Users\\Abner\\Documents\\Atividades%20Pr%C3%A1ticas%20Supervisionadas\\3º Semestre\\ColetaFeliz\\src\\Splash.png")), "Sobre:",
				                  JOptionPane.PLAIN_MESSAGE, null);
			}
		});

		JLabel lblColetaFeliz = new JLabel("COLETA FELIZ");
		lblColetaFeliz.setForeground(new Color(255, 255, 255));
		lblColetaFeliz.setHorizontalAlignment(SwingConstants.CENTER);
		lblColetaFeliz.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		lblColetaFeliz.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblColetaFeliz.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblColetaFeliz.setBounds(47, 11, 80, 17);
		getContentPane().add(lblColetaFeliz);

		JLabel label = new JLabel("Copyright 2018, Coleta Feliz", SwingConstants.CENTER);
		label.setFont(new Font("Dialog", Font.BOLD, 12));
		label.setBounds(18, 256, 176, 16);
		getContentPane().add(label);
		setLocationRelativeTo(null); // tela no centro
		setVisible(true); // Visibilidade da tela
	}

	public static void main(String[] args) {
		new Menu();
	}
}