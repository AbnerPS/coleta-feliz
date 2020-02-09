
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import java.awt.Component;
import javax.swing.Box;

public class Game_Medium extends JFrame implements KeyListener { // Implementa JFrame para parte visual e KeyListener para
																// ouvir teclas pressionadas

	private int FPS = 30; // FPS da movimentação
	private int janelaW = 600; // width da janela
	private int janelaH = 380; // heigth da janela
	private char teclaPressionada; // listener do teclado
	private int xObj = 53; // Posição X objeto
	private int yObj = 122; // Posição Y objeto
	private String status="FÁCIL";
	private int pmetal, pvidro, pplastico, pnrec, ppapel, porganico; // variaveis para a pontuação de cada reciclavel
	private int delay = 500; // Taxa de atualização, delay de 0,5 seg.
	private int interval = 2000; // intervalo de 1 seg.
	private int pontos; // variavel para armagenar a pontuação total
	private int contadorBar; // contador para a barra de progressão
	private int flagReciclaveis; // variavel para escolher os reciclaveis aleatorialemente
	int contMinutos = 0;
	int contSegundos = 0;
	JLabel lblBola = new JLabel(new ImageIcon(
			"C:\\Users\\geova\\OneDrive\\Documentos\\APS_coleta\\3º Semestre\\ColetaFeliz\\src\\person.png",
			//"C:\\Users\\Abner\\Documents\\Atividades%20Pr%C3%A1ticas%20Supervisionadas\\3º Semestre\\ColetaFeliz\\src\\person.png",
			"C:\\\\Users\\\\Doug_King\\\\Documents\\\\Atividades Práticas Supervisionadas\\\\3º Semestre\\\\ColetaFeliz\\\\src\\\\person.gif"));
	JLabel lblVidro = new JLabel("");
	JLabel lbltimer = new JLabel("TEMPO");
	JLabel lblMetal = new JLabel("");
	JLabel lblNaoReciclavel = new JLabel("");
	JLabel lblOrganico = new JLabel("");
	JLabel lblPapel = new JLabel("");
	JLabel lblPlastico = new JLabel("");
	JLabel lblReciclavel = new JLabel("Reciclavel");
	JLabel lblIconPlastico = new JLabel("x 0"); // icone e pontuação do plastico
	JLabel lblIconVidro = new JLabel("x 0"); // icone e pontuação do vidro
	JLabel lblIconPapel = new JLabel("x 0"); // icone e pontuação do papel
	JLabel lblIconOrganico = new JLabel("x 0"); // icone e pontuação do organico
	JLabel lblIconMetal = new JLabel("x 0"); // icone e pontuação do metal
	JLabel lblNaoRec = new JLabel("x 0"); // icone e pontuação do não reciclavel
	JLabel lblPontos = new JLabel(); // variavel para exibir a pontuação
	JLabel label_1 = new JLabel();
	JProgressBar progressBar = new JProgressBar();
	Random aleatorio = new Random();
	Timer temporizador; // timer para a movimentação do reciclavel
	Timer tempo = new Timer(); // timer para o tempo do jogo
	
	public void atualizarFundo(){
		label_1.setIcon(new ImageIcon(
				"C:\\Users\\geova\\OneDrive\\Documentos\\APS_coleta\\3\u00BA Semestre\\ColetaFeliz\\src\\bg1.png",
				"C:\\Users\\Abner\\Documents\\Atividades%20Pr%C3%A1ticas%20Supervisionadas\\3º Semestre\\ColetaFeliz\\src\\bg1.png"));
		label_1.setBounds(0, 112, 600, 380);
		getContentPane().add(label_1);
	}
	
	public Game_Medium() {
		inicializar(); // Método que inicia componentes visuais
	}

	public void inicializar() {
		setTitle("Coleta Feliz"); // Titulo
		setSize(604, 520); // Tamanho da tela
		setResizable(false); // impede redimensionamento
		// setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		pontos = 0; // inicializa a pontuação com 0
		lblPontos.setText("Pontos: " + pontos);
		// Visualização e posicionamento inicial personagem
		lblBola.setHorizontalAlignment(SwingConstants.CENTER);
		lblBola.setBounds(53, 122, 32, 50); // posição inicial
		getContentPane().add(lblBola);

		JLabel lblMenu = new JLabel("MENU");
		lblMenu.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "", TitledBorder.CENTER,
				TitledBorder.TOP, null, null));
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMenu.setBounds(0, 0, 86, 22);
		getContentPane().add(lblMenu);
		lblMenu.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Menu menu = new Menu(); // Chama tela menu
				dispose();
			}
		});

		JLabel lblDificuldade = new JLabel("DIFICULDADE");
		lblDificuldade.setHorizontalAlignment(SwingConstants.CENTER);
		lblDificuldade.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDificuldade.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "", TitledBorder.CENTER,
				TitledBorder.TOP, null, null));
		lblDificuldade.setBounds(85, 0, 104, 22);
		getContentPane().add(lblDificuldade);
		lblDificuldade.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Dificuldade dificuldade = new Dificuldade(); // chama tela Dificuldade
				dispose();
			}
		});

		JLabel lblSobre = new JLabel("SOBRE");
		lblSobre.setHorizontalAlignment(SwingConstants.CENTER);
		lblSobre.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSobre.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "", TitledBorder.CENTER,
				TitledBorder.TOP, null, null));
		lblSobre.setBounds(189, 0, 86, 22);
		getContentPane().add(lblSobre);
		lblSobre.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, (new ImageIcon("C:\\Users\\geova\\OneDrive\\Documentos\\APS_coleta\\3º Semestre\\ColetaFeliz\\src\\Splash.png",
				"C:\\Users\\Abner\\Documents\\Atividades%20Pr%C3%A1ticas%20Supervisionadas\\3º Semestre\\ColetaFeliz\\src\\Splash.png")), "Sobre:",
		                  JOptionPane.PLAIN_MESSAGE, null);
				//Sobre sobre = new Sobre(); // chama tela Sobre
			}
		});

		JLabel lblEspacoParaPausar = new JLabel("ESPA\u00C7O PARA PAUSAR");
		lblEspacoParaPausar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblEspacoParaPausar.setBounds(470, 0, 165, 14);
		getContentPane().add(lblEspacoParaPausar);

		lblPontos.setIcon(new ImageIcon(
				"C:\\Users\\geova\\OneDrive\\Documentos\\APS_coleta\\3º Semestre\\ColetaFeliz\\src\\IconPonto.png",
				//"C:\\Users\\Abner\\Documents\\Atividades%20Pr%C3%A1ticas%20Supervisionadas\\3º Semestre\\ColetaFeliz\\src\\IconPonto.png",
				"C:\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\Doug_King\\\\\\\\\\\\\\\\Documents\\\\\\\\\\\\\\\\Atividades Práticas Supervisionadas\\\\\\\\\\\\\\\\3º Semestre\\\\ColetaFeliz\\\\src\\\\IconPonto.png"));
		lblPontos.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPontos.setBounds(394, 33, 165, 24);
		getContentPane().add(lblPontos);

		lblVidro.setIcon(new ImageIcon(
				"C:\\Users\\geova\\OneDrive\\Documentos\\APS_coleta\\3º Semestre\\ColetaFeliz\\src\\vidro.png",
				//"C:\\Users\\Abner\\Documents\\Atividades%20Pr%C3%A1ticas%20Supervisionadas\\3º Semestre\\ColetaFeliz\\src\\vidro.png",
				"C:\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\Doug_King\\\\\\\\\\\\\\\\Documents\\\\\\\\\\\\\\\\Atividades Práticas Supervisionadas\\\\\\\\\\\\\\\\3º Semestre\\\\ColetaFeliz\\\\src\\\\vidro.gif"));
		lblVidro.setBounds(548, 112, 50, 50);
		getContentPane().add(lblVidro);

		lblMetal.setIcon(new ImageIcon(
				"C:\\Users\\geova\\OneDrive\\Documentos\\APS_coleta\\3º Semestre\\ColetaFeliz\\src\\metal.png",
				//"C:\\Users\\Abner\\Documents\\Atividades%20Pr%C3%A1ticas%20Supervisionadas\\3º Semestre\\ColetaFeliz\\src\\metal.png",
				"C:\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\Doug_King\\\\\\\\\\\\\\\\Documents\\\\\\\\\\\\\\\\Atividades Práticas Supervisionadas\\\\\\\\\\\\\\\\3º Semestre\\\\ColetaFeliz\\\\src\\\\metal.gif"));
		lblMetal.setBounds(548, 442, 50, 50);
		getContentPane().add(lblMetal);

		lblNaoReciclavel.setIcon(new ImageIcon(
				"C:\\Users\\geova\\OneDrive\\Documentos\\APS_coleta\\3º Semestre\\ColetaFeliz\\src\\naoreciclavel.png",
				//"C:\\Users\\Abner\\Documents\\Atividades%20Pr%C3%A1ticas%20Supervisionadas\\3º Semestre\\ColetaFeliz\\src\\naoreciclavel.png",
				"C:\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\Doug_King\\\\\\\\\\\\\\\\Documents\\\\\\\\\\\\\\\\Atividades Práticas Supervisionadas\\\\\\\\\\\\\\\\3º Semestre\\\\ColetaFeliz\\\\src\\\\naoreciclavel.gif"));
		lblNaoReciclavel.setBounds(287, 442, 50, 50);
		getContentPane().add(lblNaoReciclavel);

		lblOrganico.setIcon(new ImageIcon(
				"C:\\Users\\geova\\OneDrive\\Documentos\\APS_coleta\\3º Semestre\\ColetaFeliz\\src\\organico.png",
				//"C:\\Users\\Abner\\Documents\\Atividades%20Pr%C3%A1ticas%20Supervisionadas\\3º Semestre\\ColetaFeliz\\src\\organico.png",
				"C:\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\Doug_King\\\\\\\\\\\\\\\\Documents\\\\\\\\\\\\\\\\Atividades Práticas Supervisionadas\\\\\\\\\\\\\\\\3º Semestre\\\\ColetaFeliz\\\\src\\\\organico.gif"));
		lblOrganico.setBounds(0, 442, 50, 50);
		getContentPane().add(lblOrganico);

		lblPapel.setIcon(new ImageIcon(
				"C:\\Users\\geova\\OneDrive\\Documentos\\APS_coleta\\3º Semestre\\ColetaFeliz\\src\\papel.png",
				//"C:\\Users\\Abner\\Documents\\Atividades%20Pr%C3%A1ticas%20Supervisionadas\\3º Semestre\\ColetaFeliz\\src\\papel.png",
				"C:\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\Doug_King\\\\\\\\\\\\\\\\Documents\\\\\\\\\\\\\\\\Atividades Práticas Supervisionadas\\\\\\\\\\\\\\\\3º Semestre\\\\ColetaFeliz\\\\src\\\\papel.gif"));
		lblPapel.setBounds(287, 112, 50, 50);
		getContentPane().add(lblPapel);

		lblPlastico.setIcon(new ImageIcon(
				"C:\\Users\\geova\\OneDrive\\Documentos\\APS_coleta\\3º Semestre\\ColetaFeliz\\src\\plastico.png",
				//"C:\\Users\\Abner\\Documents\\Atividades%20Pr%C3%A1ticas%20Supervisionadas\\3º Semestre\\ColetaFeliz\\src\\plastico.png",
				"C:\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\Doug_King\\\\\\\\\\\\\\\\Documents\\\\\\\\\\\\\\\\Atividades Práticas Supervisionadas\\\\\\\\\\\\\\\\3º Semestre\\\\ColetaFeliz\\\\src\\\\plastico.gif"));
		lblPlastico.setBounds(287, 275, 50, 50);
		getContentPane().add(lblPlastico);

		// Metodo do temporizador, tudo que estiver dentro deste metodo sera repetido
		// pelo tempo de intervalo

		
		progressBar.setBounds(146, 43, 146, 14);
		progressBar.setMaximum(120);
		getContentPane().add(progressBar);

		JLabel lblStatus = new JLabel(" STATUS: MÉDIO");
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblStatus.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "", TitledBorder.CENTER,

				TitledBorder.TOP, null, null));
		lblStatus.setBounds(274, 0, 154, 22);
		getContentPane().add(lblStatus);

		lblIconPlastico.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIconPlastico.setIcon(new ImageIcon(
				"C:\\Users\\geova\\OneDrive\\Documentos\\APS_coleta\\3º Semestre\\ColetaFeliz\\src\\IconPlastico.png",
				//"C:\\Users\\Abner\\Documents\\Atividades%20Pr%C3%A1ticas%20Supervisionadas\\3º Semestre\\ColetaFeliz\\src\\IconPlastico.png",
				"C:\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\Doug_King\\\\\\\\\\\\\\\\Documents\\\\\\\\\\\\\\\\Atividades Práticas Supervisionadas\\\\\\\\\\\\\\\\3º Semestre\\\\ColetaFeliz\\\\src\\\\IconPlastico.png"));
		lblIconPlastico.setBounds(423, 68, 74, 32);
		getContentPane().add(lblIconPlastico);

		lblIconVidro.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIconVidro.setIcon(new ImageIcon(
				"C:\\Users\\geova\\OneDrive\\Documentos\\APS_coleta\\3º Semestre\\ColetaFeliz\\src\\Iconvidro.png",
				//"C:\\Users\\Abner\\Documents\\Atividades%20Pr%C3%A1ticas%20Supervisionadas\\3º Semestre\\ColetaFeliz\\src\\IconVidro.png",
				"C:\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\Doug_King\\\\\\\\\\\\\\\\Documents\\\\\\\\\\\\\\\\Atividades Práticas Supervisionadas\\\\\\\\\\\\\\\\3º Semestre\\\\ColetaFeliz\\\\src\\\\IconVidro.png"));
		lblIconVidro.setBounds(85, 68, 74, 32);
		getContentPane().add(lblIconVidro);

		lblIconPapel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIconPapel.setIcon(new ImageIcon(
				"C:\\Users\\geova\\OneDrive\\Documentos\\APS_coleta\\3º Semestre\\ColetaFeliz\\src\\IconPapel.png",
				//"C:\\Users\\Abner\\Documents\\Atividades%20Pr%C3%A1ticas%20Supervisionadas\\3º Semestre\\ColetaFeliz\\src\\IconPapel.png",
				"C:\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\Doug_King\\\\\\\\\\\\\\\\Documents\\\\\\\\\\\\\\\\Atividades Práticas Supervisionadas\\\\\\\\\\\\\\\\3º Semestre\\\\ColetaFeliz\\\\src\\\\IconPapel.png"));
		lblIconPapel.setBounds(258, 68, 76, 32);
		getContentPane().add(lblIconPapel);

		lblIconOrganico.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIconOrganico.setIcon(new ImageIcon(
				"C:\\Users\\geova\\OneDrive\\Documentos\\APS_coleta\\3º Semestre\\ColetaFeliz\\src\\IconOrganico.png",
				//"C:\\Users\\Abner\\Documents\\Atividades%20Pr%C3%A1ticas%20Supervisionadas\\3º Semestre\\ColetaFeliz\\src\\IconOrganico.png",
				"C:\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\Doug_King\\\\\\\\\\\\\\\\Documents\\\\\\\\\\\\\\\\Atividades Práticas Supervisionadas\\\\\\\\\\\\\\\\3º Semestre\\\\ColetaFeliz\\\\src\\\\IconOrganico.png"));
		lblIconOrganico.setBounds(344, 68, 76, 32);
		getContentPane().add(lblIconOrganico);

		lblIconMetal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIconMetal.setIcon(new ImageIcon(
				"C:\\Users\\geova\\OneDrive\\Documentos\\APS_coleta\\3º Semestre\\ColetaFeliz\\src\\IconMetal.png",
				//"C:\\Users\\Abner\\Documents\\Atividades%20Pr%C3%A1ticas%20Supervisionadas\\3º Semestre\\ColetaFeliz\\src\\IconMetal.png",
				"C:\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\Doug_King\\\\\\\\\\\\\\\\Documents\\\\\\\\\\\\\\\\Atividades Práticas Supervisionadas\\\\\\\\\\\\\\\\3º Semestre\\\\ColetaFeliz\\\\src\\\\IconMetal.png"));
		lblIconMetal.setBounds(169, 68, 79, 32);
		getContentPane().add(lblIconMetal);

		lblNaoRec.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNaoRec.setIcon(new ImageIcon(
				"C:\\Users\\geova\\OneDrive\\Documentos\\APS_coleta\\3º Semestre\\ColetaFeliz\\src\\IconNoRec.png",
				//"C:\\Users\\Abner\\Documents\\Atividades%20Pr%C3%A1ticas%20Supervisionadas\\3º Semestre\\ColetaFeliz\\src\\IconNoRec.png",
				"C:\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\Doug_King\\\\\\\\\\\\\\\\Documents\\\\\\\\\\\\\\\\Atividades Práticas Supervisionadas\\\\\\\\\\\\\\\\3º Semestre\\\\ColetaFeliz\\\\src\\\\IconNoRec.png"));
		lblNaoRec.setBounds(500, 68, 88, 32);
		getContentPane().add(lblNaoRec);

		JLabel lblIconTime = new JLabel("");
		lblIconTime.setIcon(new ImageIcon(
				"C:\\Users\\geova\\OneDrive\\Documentos\\APS_coleta\\3º Semestre\\ColetaFeliz\\src\\IconTimer.png",
				//"C:\\Users\\Abner\\Documents\\Atividades%20Pr%C3%A1ticas%20Supervisionadas\\3º Semestre\\ColetaFeliz\\src\\IconTimer.png",
				"C:\\\\Users\\\\geova\\\\OneDrive\\\\Documentos\\\\APS_coleta\\\\3º Semestre\\\\ColetaFeliz\\\\src\\\\IconTimer.png"));
		lblIconTime.setBounds(114, 33, 24, 24);
		getContentPane().add(lblIconTime);

		JLabel lblIconLogo = new JLabel("");
		lblIconLogo.setIcon(new ImageIcon(
				"C:\\Users\\geova\\OneDrive\\Documentos\\APS_coleta\\3º Semestre\\ColetaFeliz\\src\\Avatar.png",
				//"C:\\Users\\Abner\\Documents\\Atividades%20Pr%C3%A1ticas%20Supervisionadas\\3º Semestre\\ColetaFeliz\\src\\IconPerson.png",
				"C:\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\Doug_King\\\\\\\\\\\\\\\\Documents\\\\\\\\\\\\\\\\Atividades Práticas Supervisionadas\\\\\\\\\\\\\\\\3º Semestre\\\\ColetaFeliz\\\\src\\\\IconPerson.png"));
		lblIconLogo.setBounds(10, 33, 64, 64);
		getContentPane().add(lblIconLogo);
		iniciatemp();
		JLabel label = new JLabel("______________________________________________________________________________________________________");
		label.setBounds(0, 97, 612, 14);
		getContentPane().add(label);
		JLabel label_1 = new JLabel();
		//label_1.setIcon(new ImageIcon("C:\\Users\\geova\\OneDrive\\Documentos\\APS_coleta\\3\u00BA Semestre\\ColetaFeliz\\src\\bg1.png"));
		label_1.setBounds(0, 112, 600, 380);
		getContentPane().add(label_1);
		lbltimer.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbltimer.setBounds(302, 40, 64, 17);
		getContentPane().add(lbltimer);
		atualizarFundo();
		iniciaTimer();
		setLocationRelativeTo(null); // tela no centro
		setVisible(true); // Visibilidade da tela
		addKeyListener(this); // Listener das teclas
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(1000 / FPS); // Setando FPS
			} catch (Exception e) {
				JOptionPane.showConfirmDialog(null, "Thread interrompida!"); // Caso ocorra um erro
			}
		}
	}

	public static void main(String[] args) {
		new Game_Medium(); // Inicia Jogo
	}

	public void keyPressed(KeyEvent e) { // método para quando uma tecla for pressionada

		teclaPressionada = e.getKeyChar(); // recebe unicode da tecla pressionada

		// if para testar se a tecla left foi pressionada
		if (e.getKeyCode() == e.VK_LEFT) {
			lblBola.setIcon(new ImageIcon("C:\\Users\\geova\\OneDrive\\Documentos\\APS_coleta\\3º Semestre\\ColetaFeliz\\src\\left.gif"));
			if (xObj <= 0) { // testa se chegou a extremidade
			} 
			else if ((xObj <= lblPapel.getX() +50) && ((xObj >= lblPapel.getX()) &&  (yObj <= lblPapel.getY() +40))) {// testa se o personagem bateu na lateral da lixeira
				JOptionPane.showMessageDialog(null, "DEPOSITADO PAPEL");
				if (ppapel >= 1) {
					pontos += ppapel;
					ppapel = 0;
					lblIconPapel.setText("x " + ppapel); // atualiza pontuação do reciclavel na tela
					lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
				}
				else {
					pontos -= 1;
					lblIconPapel.setText("x " + ppapel); // atualiza pontuação do reciclavel na tela
					lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
				}
			}	
			else if ((xObj <= lblPlastico.getX() +50) && ((xObj >= lblPlastico.getX()) &&  (yObj <= lblPlastico.getY() +40)  &&  (yObj >= lblPlastico.getY() -50))) {// testa se o personagem bateu na lateral da lixeira
					JOptionPane.showMessageDialog(null, "DEPOSITADO PLASTICO");
					
					if (pplastico >= 1) {
						pontos += pplastico;
						pplastico = 0;
						lblIconPlastico.setText("x " + pplastico); // atualiza pontuação do reciclavel na tela
						lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
					}
					else {
						pontos -= 1;
						lblIconPlastico.setText("x " + pplastico); // atualiza pontuação do reciclavel na tela
						lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
					}
				
			}  else if ((xObj <= lblNaoReciclavel.getX() +50)  && ((xObj >= lblNaoReciclavel.getX()) && (yObj >= lblNaoReciclavel.getY() - 40))) {// testa se o personagem bateu na lateral da lixeira
				JOptionPane.showMessageDialog(null, "DEPOSITADO NÃO RECICLAVEL");
				
				if (pnrec >= 1) {
					pontos += pnrec;
					pnrec = 0;
					lblNaoRec.setText("x " + pnrec); // atualiza pontuação do reciclavel na tela
					lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
				}
				else {
					pontos -= 1;
					lblNaoRec.setText("x " + pnrec); // atualiza pontuação do reciclavel na tela
					lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
				}
				
			}  else if ((xObj <= lblOrganico.getX()+50)  && (yObj >= lblOrganico.getY() - 40)) {// testa se o personagem bateu na lateral da lixeira
				JOptionPane.showMessageDialog(null, "DEPOSITADO ORGÂNICO");
				
				if (porganico >= 1) {
					pontos += porganico;
					porganico = 0;
					lblIconOrganico.setText("x " + porganico); // atualiza pontuação do reciclavel na tela
					lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
				}
				else {
					pontos -= 1;
					lblIconOrganico.setText("x " + porganico); // atualiza pontuação do reciclavel na tela
					lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
				}
				
			}
			else if (((xObj <= lblReciclavel.getX() + 35) && (xObj >= lblReciclavel.getX())) && ((yObj <= lblReciclavel.getY() + 25) && (yObj >= lblReciclavel.getY() - 45))) { // colisão de direita com reciclavel
				JOptionPane.showMessageDialog(null, "COLETADO UM RECICLAVEL");
				lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
				
				
			
			// incrementa e atualiza a pontuação na tela
				
				switch (flagReciclaveis) { // dependendo do valor da variavel, escolhe um tipo de reciclavel
				case 0:
					pmetal+=1;
					lblIconMetal.setText("x " + pmetal); // se coletar metal
					temporizador.cancel();
					temporizador.purge();
					iniciatemp();
					break;
				case 1:
					pplastico += 1;
					lblIconPlastico.setText("x " + pplastico); // se coletar plástico
					temporizador.cancel();
					temporizador.purge();
					iniciatemp();
					break;
				case 2:
					pvidro += 1;
					lblIconVidro.setText("x " + pvidro); // se coletar vidro
					temporizador.cancel();
					temporizador.purge();
					iniciatemp();
					break;
				case 3:
					ppapel += 1;
					lblIconPapel.setText("x " + ppapel); // se coletar pepal
					temporizador.cancel();
					temporizador.purge();
					iniciatemp();
					break;
				case 4:
					pnrec += 1;
					lblNaoRec.setText("x " + pnrec); // se coletar não reciclável
					temporizador.cancel();
					temporizador.purge();
					iniciatemp();
					break;
				case 5:
					porganico += 1;
					lblIconOrganico.setText("x " + porganico); // se coletar organico
					temporizador.cancel();
					temporizador.purge();
					iniciatemp();
					break;
				}
			}
			else { // colisão com limite da tela
				xObj -= 10;
				lblBola.setBounds(xObj, yObj, 30, 50);
			}
		}

		// if para testar de a tecla right fo1 pressionada
		if (e.getKeyCode() == e.VK_RIGHT) {
			lblBola.setIcon(new ImageIcon("C:\\Users\\geova\\OneDrive\\Documentos\\APS_coleta\\3º Semestre\\ColetaFeliz\\src\\right.gif"));
			if (xObj >= 555) {
			}  else if ((xObj >= lblPapel.getX() -40)  && ((xObj <= lblPapel.getX()) && (yObj <= lblPapel.getY() + 40))) {// testa se o personagem bateu na lateral da lixeira
				JOptionPane.showMessageDialog(null, "DEPOSITADO PAPEL");
				if (ppapel >= 1) {
					pontos += ppapel;
					ppapel = 0;
					lblIconPapel.setText("x " + ppapel); // atualiza pontuação do reciclavel na tela
					lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
				}
				else {
					pontos -= 1;
					lblIconPapel.setText("x " + ppapel); // atualiza pontuação do reciclavel na tela
					lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
				}
				
			}	
			else if ((xObj >= lblPlastico.getX()  - 30) && ((xObj <= lblPlastico.getX()) &&  (yObj <= lblPlastico.getY() +40)  &&  (yObj >= lblPlastico.getY() -50))) {// testa se o personagem bateu na lateral da lixeira
					JOptionPane.showMessageDialog(null, "DEPOSITADO PLASTICO");
					if (pplastico >= 1) {
						pontos += pplastico;
						pplastico = 0;
						lblIconPlastico.setText("x " + pplastico); // atualiza pontuação do reciclavel na tela
						lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
					}
					else {
						pontos -= 1;
						lblIconPlastico.setText("x " + pplastico); // atualiza pontuação do reciclavel na tela
						lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
					}
				
			}  else if ((xObj >= lblNaoReciclavel.getX() -40)  && ((xObj <= lblNaoReciclavel.getX()) && (yObj >= lblNaoReciclavel.getY() - 40))) {// testa se o personagem bateu na lateral da lixeira
				JOptionPane.showMessageDialog(null, "DEPOSITADO NÃO RECICLAVEL");
				if (pnrec >= 1) {
					pontos += pnrec;
					pnrec = 0;
					lblNaoRec.setText("x " + pnrec); // atualiza pontuação do reciclavel na tela
					lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
				}
				else {
					pontos -= 1;
					lblNaoRec.setText("x " + pnrec); // atualiza pontuação do reciclavel na tela
					lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
				}
				
			}  else if ((xObj >= lblVidro.getX()- 30) && (yObj <= lblVidro.getY() + 40)) {// testa se o personagem bateu na lateral da lixeira
				JOptionPane.showMessageDialog(null, "DEPOSITADO VIDRO");
				if (pvidro >= 1) {
					pontos += pvidro;
					pvidro = 0;
					lblIconVidro.setText("x " + pvidro); // atualiza pontuação do reciclavel na tela
					lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
				}
				else {
					pontos -= 1;
					lblIconVidro.setText("x " + pvidro); // atualiza pontuação do reciclavel na tela
					lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
				}
			}
			 else if ((xObj >= lblMetal.getX()- 30) && (yObj >= lblMetal.getY() -40)) {// testa se o personagem bateu na lateral da lixeira
					JOptionPane.showMessageDialog(null, "DEPOSITADO METAL");
					
					if (pmetal >= 1) {
						pontos += pmetal;
						pmetal = 0;
						lblIconMetal.setText("x " + pmetal); // atualiza pontuação do reciclavel na tela
						lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
					}
					else {
						pontos -= 1;
						lblIconMetal.setText("x " + pmetal); // atualiza pontuação do reciclavel na tela
						lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
					}
			
			 }else if (((xObj <= lblReciclavel.getX()) && (xObj >= lblReciclavel.getX() - 35)) && ((yObj <= lblReciclavel.getY() + 25) && (yObj >= lblReciclavel.getY() - 45))) { // colisão de esquerda com reciclavel
					JOptionPane.showMessageDialog(null, "COLETADO UM RECICLAVEL");
					lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
					switch (flagReciclaveis) { // dependendo do valor da variavel, escolhe um tipo de reciclavel
					case 0:
						pmetal+=1;
						lblIconMetal.setText("x "+pmetal); // se coletar metal
						temporizador.cancel();
						temporizador.purge();
						iniciatemp();
						break;
					case 1:
						pplastico+=1;
						lblIconPlastico.setText("x "+pplastico); // se coletar plástico
						temporizador.cancel();
						temporizador.purge();
						iniciatemp();
						break;
					case 2:
						pvidro+=1;
						lblIconVidro.setText("x "+pvidro); // se coletar vidro
						temporizador.cancel();
						temporizador.purge();
						iniciatemp();
						break;
					case 3:
						ppapel+=1;
						lblIconPapel.setText("x "+ppapel); // se coletar pepal
						temporizador.cancel();
						temporizador.purge();
						iniciatemp();
						break;
					case 4:
						pnrec+=1;
						lblNaoRec.setText("x "+pnrec); // se coletar não reciclável
						temporizador.cancel();
						temporizador.purge();
						break;
					case 5:
						porganico+=1;
						lblIconOrganico.setText("x "+porganico); // se coletar organico
						temporizador.cancel();
						temporizador.purge();
						iniciatemp();
						break;
					}
				}
				else {
					xObj += 10;
					lblBola.setBounds(xObj, yObj, 30, 50);
				}
			}


		// if para testar se a tecla up foi pressionada
		if (e.getKeyCode() == e.VK_UP) {
			lblBola.setIcon(new ImageIcon("C:\\Users\\geova\\OneDrive\\Documentos\\APS_coleta\\3º Semestre\\ColetaFeliz\\src\\up.gif"));
			if (yObj <= 115) {
			}
			else if ((xObj >= lblVidro.getX() - 25) && (yObj <= lblVidro.getY() + 58)) { // colisão de baixo com vidro
				JOptionPane.showMessageDialog(null, "DEPOSITADO VIDRO");
				if (pvidro >= 1) {
					pontos += pvidro;
					pvidro = 0;
					lblIconVidro.setText("x " + pvidro); // atualiza pontuação do reciclavel na tela
					lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
				}
				else {
					pontos -= 1;
					lblIconVidro.setText("x " + pvidro); // atualiza pontuação do reciclavel na tela
					lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
				}
	
			}
			else if ((yObj <= lblPlastico.getY() + 50)
					&& ((yObj  >= lblPlastico.getY()) &&  (xObj <= lblPlastico.getX() + 40) && (xObj >= lblPlastico.getX() -30))){ // colisão de baixo com plastico
				JOptionPane.showMessageDialog(null, "DEPOSITADO PLASTICO");
				
				if (pplastico >= 1) {
					pontos += pplastico;
					pplastico = 0;
					lblIconPlastico.setText("x " + pplastico); // atualiza pontuação do reciclavel na tela
					lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
				}
				else {
					pontos -= 1;
					lblIconPlastico.setText("x " + pplastico); // atualiza pontuação do reciclavel na tela
					lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
				}
				
			}else if ((yObj <= lblPapel.getY()+50)
					&& ((yObj  >= lblPapel.getY()) &&  (xObj <= lblPapel.getX() + 40)  && (xObj >= lblPapel.getX() -30))){ // colisão de baixo com papel
				JOptionPane.showMessageDialog(null, "DEPOSITADO PAPEL");
				
				if (ppapel >= 1) {
					pontos += ppapel;
					ppapel = 0;
					lblIconPapel.setText("x " + ppapel); // atualiza pontuação do reciclavel na tela
					lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
				}
				else {
					pontos -= 1;
					lblIconPapel.setText("x " + ppapel); // atualiza pontuação do reciclavel na tela
					lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
				}
				
				
			}else if ((xObj == lblReciclavel.getX()) && (yObj == lblReciclavel.getY())) { // colisão de baixo com reciclavel
				JOptionPane.showMessageDialog(null, "COLETADO UM RECICLAVEL");
			}
			
			else if (((xObj <= lblReciclavel.getX() + 35) && (xObj >= lblReciclavel.getX() - 35)) && ((yObj <= lblReciclavel.getY() + 40) && (yObj >= lblReciclavel.getY()))) { // colisão de baixo com reciclavel
				JOptionPane.showMessageDialog(null, "COLETADO UM RECICLAVEL ");
				lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
				switch (flagReciclaveis) { // dependendo do valor da variavel, escolhe um tipo de reciclavel
				case 0:
					pmetal+=1;
					lblIconMetal.setText("x "+pmetal); // se coletar metal
					temporizador.cancel();
					temporizador.purge();
					iniciatemp();
					break;
				case 1:
					pplastico+=1;
					lblIconPlastico.setText("x "+pplastico); // se coletar plástico
					temporizador.cancel();
					temporizador.purge();
					iniciatemp();
					break;
				case 2:
					pvidro+=1;
					lblIconVidro.setText("x "+pvidro); // se coletar vidro
					temporizador.cancel();
					temporizador.purge();
					iniciatemp();
					break;
				case 3:
					ppapel+=1;
					lblIconPapel.setText("x "+ppapel); // se coletar pepal
					temporizador.cancel();
					temporizador.purge();
					iniciatemp();
					break;
				case 4:
					pnrec+=1;
					lblNaoRec.setText("x "+pnrec); // se coletar não reciclável
					temporizador.cancel();
					temporizador.purge();
					iniciatemp();
					break;
				case 5:
					porganico+=1;
					lblIconOrganico.setText("x "+porganico); // se coletar organico
					temporizador.cancel();
					temporizador.purge();
					iniciatemp();
					break;
				}
			}
			else {
				yObj -= 10;
				lblBola.setBounds(xObj, yObj, 30, 50);
			}
		}
		// if para testar de a tecla down foi pressionada
		if (e.getKeyCode() == e.VK_DOWN) {
			lblBola.setIcon(new ImageIcon("C:\\Users\\geova\\OneDrive\\Documentos\\APS_coleta\\3º Semestre\\ColetaFeliz\\src\\down.gif"));
			if (yObj >= 440) {
			}
			else if (  (yObj >= lblPlastico.getY() -60)&& (yObj <= lblPlastico.getY() -60) && ((xObj <= lblPlastico.getX()+40) && (xObj  >= lblPlastico.getX() - 24))) {// testa se o personagem bateu na parte superior da lixeira
				JOptionPane.showMessageDialog(null, "DEPOSITADO PLÁSTICO");
				if (pplastico >= 1) {
					pontos += pplastico;
					pplastico = 0;
					lblIconPlastico.setText("x " + pplastico); // atualiza pontuação do reciclavel na tela
					lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
				}
				else {
					pontos -= 1;
					lblIconPlastico.setText("x " + pplastico); // atualiza pontuação do reciclavel na tela
					lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
				}
			}
			else if ((yObj >=lblMetal.getY() -50)
					&& ((xObj  >= lblMetal.getX() -30))) {// testa se o personagem bateu na parte superior da lixeira
				JOptionPane.showMessageDialog(null, "DEPOSITO METAL");
				if (pmetal >= 1) {
					pontos += pmetal;
					pmetal = 0;
					lblIconMetal.setText("x " + pmetal); // atualiza pontuação do reciclavel na tela
					lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
				}
				else {
					pontos -= 1;
					lblIconMetal.setText("x " + pmetal); // atualiza pontuação do reciclavel na tela
					lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
				}
				
				
			}
			else if ((yObj >= lblNaoReciclavel.getY() -50)
					&& ((xObj <= lblNaoReciclavel.getX() +40) && (xObj >= lblNaoReciclavel.getX() -24))) {// testa se o personagem bateu na parte superior da lixeira
				JOptionPane.showMessageDialog(null, "DEPOSITADO NÃO RECICLAVEL");
				
				if (pnrec >= 1) {
					pontos += pnrec;
					pnrec = 0;
					lblNaoRec.setText("x " + pnrec); // atualiza pontuação do reciclavel na tela
					lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
				}
				else {
					pontos -= 1;
					lblNaoRec.setText("x " + pnrec); // atualiza pontuação do reciclavel na tela
					lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
				}
			}
			else if ((yObj >= lblOrganico.getY() -50)
					&& ((xObj <= lblOrganico.getX() +48))) {// testa se o personagem bateu na parte superior da lixeira
				JOptionPane.showMessageDialog(null, "DEPOSITADO ORGÂNICO");
				
				if (porganico >= 1) {
					pontos += porganico;
					porganico = 0;
					lblIconOrganico.setText("x " + porganico); // atualiza pontuação do reciclavel na tela
					lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
				}
				else {
					pontos -= 1;
					lblIconOrganico.setText("x " + porganico); // atualiza pontuação do reciclavel na tela
					lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
					}
			}
			
			else if ((xObj == lblReciclavel.getX()) && (yObj == lblReciclavel.getY())) { // colisão de cima com reciclavel
				JOptionPane.showMessageDialog(null, "COLETADO UM RECICLAVEL");
			}
			
			else if (((xObj <= lblReciclavel.getX() + 30) && (xObj >= lblReciclavel.getX() - 30)) && ((yObj <= lblReciclavel.getY()) && (yObj >= lblReciclavel.getY() - 60))) { // colisão de cima com reciclavel
				JOptionPane.showMessageDialog(null, "COLETADO UM RECICLAVEL");
				lblPontos.setText("Pontos: " + pontos); // atualiza a pontuação na tela
				switch (flagReciclaveis) { // dependendo do valor da variavel, escolhe um tipo de reciclavel
				case 0:
					pmetal+=1;
					lblIconMetal.setText("x "+pmetal); // se coletar metal
					temporizador.cancel();
					temporizador.purge();
					iniciatemp();
					break;
				case 1:
					pplastico+=1;
					lblIconPlastico.setText("x "+pplastico); // se coletar plástico
					temporizador.cancel();
					temporizador.purge();
					iniciatemp();
					break;
				case 2:
					pvidro+=1;
					lblIconVidro.setText("x "+pvidro); // se coletar vidro
					temporizador.cancel();
					temporizador.purge();
					iniciatemp();
					break;
				case 3:
					ppapel+=1;
					lblIconPapel.setText("x "+ppapel); // se coletar pepal
					temporizador.cancel();
					temporizador.purge();
					iniciatemp();				
					break;
				case 4:
					pnrec+=1;
					lblNaoRec.setText("x "+pnrec); // se coletar não reciclável
					temporizador.cancel();
					temporizador.purge();
					iniciatemp();
					break;
				case 5:
					porganico+=1;
					lblIconOrganico.setText("x "+porganico); // se coletar organico
					temporizador.cancel();
					temporizador.purge();
					iniciatemp();
					break;
				}
			}
			else {
				yObj += 10;
				lblBola.setBounds(xObj, yObj, 30, 50);
			}
		}


		// if para testar de a tecla space foi pressionada
		if (e.getKeyCode() == e.VK_SPACE) {
			JOptionPane.showConfirmDialog(null, "PAUSA");
		}
	}

	public void iniciatemp() {
		temporizador = new Timer();
		temporizador.scheduleAtFixedRate(new TimerTask() {
			public void run() {

				flagReciclaveis = aleatorio.nextInt(6); // escolhe aleatoriamente um valor para a variavel entre 0 e 5
				atualizarFundo();
				int limiteY = aleatorio.nextInt(405); // variavel com o valor maximo da tela em Y
				if (limiteY < 120) { // condifional para o reciclavel não aparecer no menu do jogo
					limiteY = 120;
				}

				switch (flagReciclaveis) { // dependendo do valor da variavel, escolhe um tipo de reciclavel
				case 0:
					lblReciclavel.setIcon(new ImageIcon(
							"C:\\Users\\geova\\OneDrive\\Documentos\\APS_coleta\\3º Semestre\\ColetaFeliz\\src\\RecMetal.png",
							"C:\\\\\\\\Users\\\\\\\\Doug_King\\\\\\\\Documents\\\\\\\\Atividades Práticas Supervisionadas\\\\\\\\3º Semestre\\\\\\\\ColetaFeliz\\\\\\\\src\\\\\\\\RecMetal.png"));
					lblReciclavel.setBounds(aleatorio.nextInt(415), limiteY, 32, 32); // Objeto reciclavel generico para
																						// metal
					break;
				case 1:
					lblReciclavel.setIcon(new ImageIcon(
							"C:\\Users\\geova\\OneDrive\\Documentos\\APS_coleta\\3º Semestre\\ColetaFeliz\\src\\RecPlastico.png",
							"C:\\\\\\\\Users\\\\\\\\Doug_King\\\\\\\\Documents\\\\\\\\Atividades Práticas Supervisionadas\\\\\\\\3º Semestre\\\\\\\\ColetaFeliz\\\\\\\\src\\\\\\\\RecPlastico.png"));
					lblReciclavel.setBounds(aleatorio.nextInt(510), limiteY, 32, 32); // Objeto reciclavel generico para
																						// plástico
					break;
				case 2:
					lblReciclavel.setIcon(new ImageIcon(
							"C:\\Users\\geova\\OneDrive\\Documentos\\APS_coleta\\3º Semestre\\ColetaFeliz\\src\\RecVidro.png",
							"C:\\\\\\\\Users\\\\\\\\Doug_King\\\\\\\\Documents\\\\\\\\Atividades Práticas Supervisionadas\\\\\\\\3º Semestre\\\\\\\\ColetaFeliz\\\\\\\\src\\\\\\\\RecVidro.png"));
					lblReciclavel.setBounds(aleatorio.nextInt(510), limiteY, 32, 32); // Objeto reciclavel generico para
																						// vidro
					break;
				case 3:
					lblReciclavel.setIcon(new ImageIcon(
							"C:\\Users\\geova\\OneDrive\\Documentos\\APS_coleta\\3º Semestre\\ColetaFeliz\\src\\RecPapel.png",
							"C:\\\\\\\\Users\\\\\\\\Doug_King\\\\\\\\Documents\\\\\\\\Atividades Práticas Supervisionadas\\\\\\\\3º Semestre\\\\\\\\ColetaFeliz\\\\\\\\src\\\\\\\\RecPapel.png"));
					lblReciclavel.setBounds(aleatorio.nextInt(510), limiteY, 32, 32); // Objeto reciclavel generico para
																						// papel
					break;
				case 4:
					lblReciclavel.setIcon(new ImageIcon(
							"C:\\Users\\geova\\OneDrive\\Documentos\\APS_coleta\\3º Semestre\\ColetaFeliz\\src\\NoRec.png",
							"C:\\\\\\\\Users\\\\\\\\Doug_King\\\\\\\\Documents\\\\\\\\Atividades Práticas Supervisionadas\\\\\\\\3º Semestre\\\\\\\\ColetaFeliz\\\\\\\\src\\\\\\\\NoRec.png"));
					lblReciclavel.setBounds(aleatorio.nextInt(510), limiteY, 32, 32); // Objeto reciclavel generico para
																						// não reciclável
					break;
				case 5:
					lblReciclavel.setIcon(new ImageIcon(
							"C:\\Users\\geova\\OneDrive\\Documentos\\APS_coleta\\3º Semestre\\ColetaFeliz\\src\\RecOrganico.png",
							"C:\\\\\\\\Users\\\\\\\\Doug_King\\\\\\\\Documents\\\\\\\\Atividades Práticas Supervisionadas\\\\\\\\3º Semestre\\\\\\\\ColetaFeliz\\\\\\\\src\\\\\\\\RecOrganico.png"));
					lblReciclavel.setBounds(aleatorio.nextInt(510), limiteY, 32, 32); // Objeto reciclavel generico para
																						// organico
					break;

				}
			}
		}, delay, interval);

		getContentPane().add(lblReciclavel);
	}
	public void keyReleased(KeyEvent e) {
		lblBola.setIcon(new ImageIcon(
				"C:\\Users\\geova\\OneDrive\\Documentos\\APS_coleta\\3º Semestre\\ColetaFeliz\\src\\person.png"));
	} // é acionado no momento em que você libera a tecla pressionada

	public void keyTyped(KeyEvent e) {} // é acionado toda vez que o usuário digitar um caractere em algum elemento que
	
	public void iniciaTimer() {
		Timer timer = null;
        if (timer == null){
            timer = new Timer();
            TimerTask tarefa = new TimerTask() {
                 public void run(){
                	 
                	 // contagem de tempo na barra de progressão
                	 if (contMinutos <= 1) {
                		 if (contSegundos == 59) {
                			 contSegundos = 0;
                			 contMinutos += 1;
                		 }
                	 contSegundos += 1;
                	 contadorBar += 1;
                	 progressBar.setValue(contadorBar);
                	 lbltimer.setText("0" + Integer.toString(contMinutos) + ":" + Integer.toString(contSegundos));
                	 } else {
                		 contSegundos = 0;
                		 contMinutos = 0;
                		 lbltimer.setText("0" + Integer.toString(contMinutos) + ":" + Integer.toString(contSegundos));
                		 progressBar.setValue(0);
                		 cancel();
                		 JOptionPane.showMessageDialog(null, "         Sua pontuação: " + pontos, "Game Over ",
                		 JOptionPane.PLAIN_MESSAGE, null);
                		 Menu menu = new Menu(); // Chama tela menu
                		 dispose();
                	 }
                }
             };timer.scheduleAtFixedRate(tarefa, 0, 1000);
        }
	}
}


