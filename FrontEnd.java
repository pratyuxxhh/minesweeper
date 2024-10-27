
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrontEnd extends JFrame implements ActionListener{
    static int frmW =900 , frmH=650;
    static int btnSize = frmH/20;
    static JPanel pnl ;
    static JPanel pnl2 ;
    static JButton restart;

    static JButton play;
    static JButton[][] btnArr;

    FrontEnd(){
    pnl = new JPanel();
    pnl2 = new JPanel();
        btnArr = new JButton[15][15];
        homepage();

    }
    public void homepage(){
        play = new JButton();
        play.setBounds((frmW-200)/2 ,(frmH-80)/2 , 200 ,80);
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
                btnArr[i][j].setFocusable(false);
                btnArr[i][j].setFont(new Font("Arial" ,Font.PLAIN ,15));
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
            backEnd.left = 225;
            FrontEnd frn = new FrontEnd();
            backEnd.setMineCode();
            backEnd.setMinesPosition();
        }
        if (e.getSource()==play) {
            this.dispose();
            this.remove(play);
            backEnd.setMineCode();
            gamePage();
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
    public void gamePage(){
        this.setSize(frmW, frmH);
        pnl.setBounds(0, 0, frmH, frmH-40);
        pnl.setBackground(new Color(0,102,102));
        pnl.setLayout(new GridLayout(15,15,0,0));
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
        backEnd.setMinesPosition();

    }

}

