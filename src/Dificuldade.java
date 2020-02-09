import javax.swing.JFrame;
import javax.swing.JRadioButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Dificuldade extends JFrame {
	public Dificuldade() {
		getContentPane().setBackground(new Color(0, 153, 102));
		setSize(200, 250);
		setLocationRelativeTo(null); // tela no centro
		setTitle("Coleta Feliz"); // Titulo
		setResizable(false); // impede redimensionamento
		getContentPane().setLayout(null);

		JRadioButton rdbtnFcil = new JRadioButton("F\u00E1cil");
		rdbtnFcil.setBackground(new Color(0, 153, 102));
		rdbtnFcil.setSelected(true);
		rdbtnFcil.setBounds(6, 58, 109, 23);
		getContentPane().add(rdbtnFcil);

		JRadioButton rdbtnInsano = new JRadioButton("Dif\u00EDcil");
		rdbtnInsano.setBackground(new Color(0, 153, 102));
		rdbtnInsano.setBounds(6, 108, 109, 23);
		getContentPane().add(rdbtnInsano);

		JLabel lblDificuldade = new JLabel("DIFICULDADE");
		lblDificuldade.setHorizontalAlignment(SwingConstants.CENTER);
		lblDificuldade.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		lblDificuldade.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDificuldade.setBounds(40, 13, 96, 29);
		getContentPane().add(lblDificuldade);

		JButton btnSelecionar = new JButton("SELECIONAR");
		btnSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnFcil.isSelected()) {
					Game_Easy easy = new Game_Easy(); // Chama tela menu
				}else {
					Game_Hard hard = new Game_Hard();
				}
				dispose(); // fecha tela
			}
		});
		btnSelecionar.setBounds(34, 159, 115, 23);
		getContentPane().add(btnSelecionar);

		// Agrupando RadioButtons
		ButtonGroup GroupRadioButton = new ButtonGroup();
		GroupRadioButton.add(rdbtnFcil);
		GroupRadioButton.add(rdbtnInsano);

		setLocationRelativeTo(null);
		setVisible(true); // Visibilidade da tela
	}

	public static void main(String[] args) {
		new Dificuldade();
	}
}
