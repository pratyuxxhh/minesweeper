
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrontEnd extends JFrame implements ActionListener{
    static int frmW =900 , frmH=650;
    static int btnSize = frmH/20;
    static JPanel pnl ;
    static JPanel pnl2 ;
    static JButton restart;
    static JButton easy;
    static JButton hard;
    JLabel pnnl ;
    

    static JButton play;
    static JButton[][] btnArr;

    FrontEnd(){
    pnl = new JPanel();
    pnl2 = new JPanel();
        btnArr = new JButton[15][15];
        homepage();

    }
    public void homepage(){
         pnnl = new JLabel();
        pnnl.setBounds(0,0,900,650);
        pnnl.setIcon(new ImageIcon("bj4.png"));
        this.add(pnnl);
        play = new JButton();
        play.setBounds((frmW)/2-100 ,(frmH)/2-60 , 200 ,80);
        play.setText("PLAY");
        play.setFocusable(false);
        play.setFont(new Font("SansSerif", Font.ITALIC  ,25));
        play.setFont(new Font("SansSerif", Font.BOLD  ,25));
        play.addActionListener(this);
        this.setResizable(false);
        
        this.add(play);
        this.setTitle("MINESWEEPER");
        this.setSize(frmW , frmH);
        
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        
        
        this.setVisible(true);



    }
    public void makeButtons(){
        for (int i = 0; i < btnArr.length; i++) {
            for (int j = 0; j < btnArr[0].length; j++) {
                btnArr[i][j] = new JButton();
                btnArr[i][j].setBounds(i*btnSize ,j*btnSize, btnSize , btnSize);
                btnArr[i][j].setSize(40 ,40);
                btnArr[i][j].setPreferredSize(new Dimension(40,40));
                btnArr[i][j].setFocusable(false);
                btnArr[i][j].setFont(new Font("Arial" ,Font.BOLD ,15));
                btnArr[i][j].addActionListener(this);
                btnArr[i][j].setBackground(new Color(255,204,153));
                btnArr[i][j].setVerticalTextPosition(JButton.CENTER);
                btnArr[i][j].setHorizontalTextPosition(JButton.CENTER);
                pnl.add(btnArr[i][j]);
            }

        }
            // btnArr[4][7].setText("F");
    }
    @Override
    public void actionPerformed(ActionEvent e ) {
        // TODO Auto-generated method stub
        for (int i = 0; i < btnArr.length; i++) {
            for (int j = 0; j < btnArr.length; j++) {
                if (e.getSource()==btnArr[i][j]) {
                    backEnd.clickButton( i ,j );
                    return;
                }
            }
        }
        if (e.getSource()==restart) {
            this.dispose();
            // backEnd.left = 225;
            FrontEnd frn = new FrontEnd();
            // backEnd.setMineCode();
            // // backEnd.setMinesPosition();
            // easyOrHard();
        }
        if (e.getSource()==play) {
            this.dispose();
            this.remove(play);
            this.remove(pnnl);
            // backEnd.setMineCode();
            // gamePage();
            easyOrHard();
        }
        if (e.getSource()==easy) {
            this.dispose();
            this.remove(hard);
            this.remove(easy);
            backEnd.setMineCode(15);
            gamePage(15);
        }
        if (e.getSource()==hard) {
            btnArr = new JButton[25][25];

            this.dispose();
            this.remove(hard);
            this.remove(easy);
            backEnd.setMineCode(25);
            gamePage(25);
        }
    }
   
    public void restartButton(){
        restart = new JButton();
        JPanel pnll = new JPanel();
        pnll.setBounds(680 ,400 , 150 ,50);
        pnl2.add(pnll);
        restart.setBounds(680 ,400, 150,50);
        restart.setFocusable(false);
        restart.setFont(new Font("SansSerif" ,Font.BOLD ,20));
        restart.addActionListener(this);
        restart.setText("RESTART");
        restart.setEnabled(false);
        pnll.add(restart);
    }
    public void gamePage(int row){
        this.setSize(frmW, frmH);
        pnl.setBounds(0, 0, frmH, frmH-40);
        pnl.setBackground(new Color(0,102,102));
        pnl.setLayout(new GridLayout(row,row,0,0));
        pnl2.setBounds(600,0,300 , frmH);
        this.add(pnl);
        this.setTitle("MINESWEEPER");
        makeButtons();
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        pnl2.setBackground(new Color(64,64,64));
        restartButton();
        
        this.add(pnl2);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        
        this.setVisible(true);
        backEnd.setMinesPosition(row);

    }
    public void easyOrHard(){

        this.setSize(frmW, frmH);
        this.setResizable(false);
        getContentPane().setBackground(new Color(116,81,40));
        this.setTitle("MINESWEEPER");
        this.setSize(frmW , frmH);
        
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        
        
        easy = new JButton();
        hard= new JButton();
        easy.setBounds(frmW/2 -200 ,(frmH-80)/4,400,80);
        hard.setBounds((frmW/2) -200 ,((frmH-160)/4) *3,400,80);
        easy.setText("EAZZYY");
        hard.setText("HAARRD");
        editButton(easy);
        editButton(hard);
        this.setVisible(true);

    }
    public void editButton(JButton b){
        b.setSize(400,80);
        b.setFocusable(false);
        b.setBackground(new Color(229,111,18));
        b.setFont(new Font("SansSerif", Font.BOLD  ,30));
        b.addActionListener(this);
        this.add(b);
    }
public static  void playSound(String soundFile) {
    try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFile));
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        e.printStackTrace();
    }
}

}

