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

        JRadioButton rdbtnFacil = new JRadioButton("FACIL");
        rdbtnFacil.setBackground(new Color(0, 153, 102));
        rdbtnFacil.setSelected(true);
        rdbtnFacil.setBounds(6, 65, 109, 23);
        getContentPane().add(rdbtnFacil);

        JRadioButton rdbtnDificil = new JRadioButton("DIFICIL");
        rdbtnDificil.setBackground(new Color(0, 153, 102));
        rdbtnDificil.setBounds(6, 100, 109, 23);
        getContentPane().add(rdbtnDificil);

        JLabel lblDificuldade = new JLabel("DIFICULDADE");
        lblDificuldade.setHorizontalAlignment(SwingConstants.CENTER);
        lblDificuldade.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
        lblDificuldade.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblDificuldade.setBounds(40, 13, 96, 29);
        getContentPane().add(lblDificuldade);

        JButton btnSelecionar = new JButton("SELECIONAR");
        btnSelecionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (rdbtnFacil.isSelected()) {
                    Modo_Facil easy = new Modo_Facil(); // Chama tela menu
                } else {
                    Modo_Dificil hard = new Modo_Dificil();
                }
                dispose(); // fecha tela
            }
        });
        btnSelecionar.setBounds(34, 159, 115, 23);
        getContentPane().add(btnSelecionar);

        // Agrupando RadioButtons
        ButtonGroup GroupRadioButton = new ButtonGroup();
        GroupRadioButton.add(rdbtnFacil);
        GroupRadioButton.add(rdbtnDificil);

        setLocationRelativeTo(null);
        setVisible(true); // Visibilidade da tela
    }

    public static void main(String[] args) {
        new Dificuldade();
    }
}