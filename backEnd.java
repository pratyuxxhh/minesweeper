
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class backEnd extends FrontEnd{
    static ArrayList<Integer> mineX ;
    static ArrayList<Integer> mineY ;
    static int left;
    static int[][] easyMineC ;
    public static void setMineCode(int gridlen){
        left = gridlen*gridlen;
        easyMineC=new int[gridlen][gridlen];
        for (int i = 0; i < easyMineC.length; i++) {
            for (int j = 0; j < easyMineC.length; j++) {
                easyMineC[i][j] =0;
            }
        }
    }

    public static void setMinesPosition(int noOfMine){
        mineY = new ArrayList<>();
        mineX = new ArrayList<>();
        for (int i = 1; i < easyMineC.length-1; i++) {
            mineX.add(i);
            mineY.add(i);
        } 
        Collections.shuffle(mineX);
        Collections.shuffle(mineY);

        for (int i = 0; i < mineX.size(); i++) {
            easyMineC[mineX.get(i)][mineY.get(i)]--;
        }
        borderOfMine();
    }
    public static void borderOfMine(){
        
        for (int i = 0; i < mineX.size(); i++) {
            if(easyMineC[mineX.get(i)-1][mineY.get(i)-1]!=-1) easyMineC[mineX.get(i)-1][mineY.get(i)-1]++;
            if(easyMineC[mineX.get(i)][mineY.get(i)-1]!=-1)easyMineC[mineX.get(i)][mineY.get(i)-1]++;
            if(easyMineC[mineX.get(i)+1][mineY.get(i)-1]!=-1)easyMineC[mineX.get(i)+1][mineY.get(i)-1]++;
            if(easyMineC[mineX.get(i)+1][mineY.get(i)]!=-1)easyMineC[mineX.get(i)+1][mineY.get(i)]++;
            if(easyMineC[mineX.get(i)-1][mineY.get(i)]!=-1)easyMineC[mineX.get(i)-1][mineY.get(i)]++;
            if(easyMineC[mineX.get(i)-1][mineY.get(i)+1]!=-1)easyMineC[mineX.get(i)-1][mineY.get(i)+1]++;
            if(easyMineC[mineX.get(i)][mineY.get(i)+1]!=-1)easyMineC[mineX.get(i)][mineY.get(i)+1]++;
            if(easyMineC[mineX.get(i)+1][mineY.get(i)+1]!=-1)easyMineC[mineX.get(i)+1][mineY.get(i)+1]++;
        }
    }
    
    public static void clickButton(int i , int j ){

        btnArr[i][j].setText(Integer.toString(easyMineC[i][j]));
        left--;
        setColor(i, j);
        btnArr[i][j].setEnabled(false);
        if (easyMineC[i][j]==-1) {
            playSound("explosion.wav");
            for (int j2 = 0; j2 < mineX.size(); j2++) {
                setColor(mineX.get(j2), mineY.get(j2));
                btnArr[mineX.get(j2)][mineY.get(j2)].setText("O");
                btnArr[mineX.get(j2)][mineY.get(j2)].setBackground(new Color(64,64,64));
            }
            JOptionPane.showMessageDialog(null,"YOU LOSE ");

        }
        // System.out.println(i+" "+j);
        checkAround(i , j);
        if (easyMineC[i][j]==-1) {
            

            for (int j2 = 0; j2 < btnArr.length; j2++) {
                for (int k = 0; k < btnArr.length; k++) {
                    btnArr[j2][k].setEnabled(false);
                    if (easyMineC[j2][k]!=-1) {
                        setColor(j2, k);
                        btnArr[j2][k].setText(Integer.toString(easyMineC[j2][k]));
                    }
                }
            }
            restart.setEnabled(true);
        }
    }
    public static void checkAround(int i , int j){
        // straight direction till end diagonal till 4 tiles
        if (easyMineC[i][j]==0 && i>=1 && i<=easyMineC.length-2 && j>=1 && j<=easyMineC.length-2) {
            btnArr[i-1][j-1].setText(Integer.toString(easyMineC[i-1][j-1]));
            if(btnArr[i-1][j-1].isEnabled()) left--;
            setColor(i-1, j-1);
            btnArr[i-1][j-1].setEnabled(false);

            btnArr[i-1][j].setText(Integer.toString(easyMineC[i-1][j]));
            if(btnArr[i-1][j].isEnabled()) left--;
            setColor(i-1, j);
            btnArr[i-1][j].setEnabled(false);

            btnArr[i-1][j+1].setText(Integer.toString(easyMineC[i-1][j+1]));
            if(btnArr[i-1][j+1].isEnabled()) left--;
            setColor(i-1, j+1);
            btnArr[i-1][j+1].setEnabled(false);

            btnArr[i][j-1].setText(Integer.toString(easyMineC[i][j-1]));
            if(btnArr[i][j-1].isEnabled()) left--;
            setColor(i, j-1);
            btnArr[i][j-1].setEnabled(false);

            btnArr[i][j+1].setText(Integer.toString(easyMineC[i][j+1]));
            if(btnArr[i][j+1].isEnabled()) left--;
            setColor(i, j+1);
            btnArr[i][j+1].setEnabled(false);

            btnArr[i+1][j-1].setText(Integer.toString(easyMineC[i+1][j-1]));
            if(btnArr[i+1][j-1].isEnabled()) left--;
            setColor(i+1, j-1);
            btnArr[i+1][j-1].setEnabled(false);

            btnArr[i+1][j].setText(Integer.toString(easyMineC[i+1][j]));
            if(btnArr[i+1][j].isEnabled()) left--;
            setColor(i+1, j);
            btnArr[i+1][j].setEnabled(false);

            btnArr[i+1][j+1].setText(Integer.toString(easyMineC[i+1][j+1]));
            if(btnArr[i+1][j+1].isEnabled()) left--;
            setColor(i+1, j+1);
            btnArr[i+1][j+1].setEnabled(false);

        }
        if (left<=13) {
            
            JOptionPane.showMessageDialog(null,"YOU WIN ");
            restart.setEnabled(true);
            for (int j2 = 0; j2 < btnArr.length; j2++) {
                for (int k = 0; k < btnArr.length; k++) {
                    btnArr[j2][k].setEnabled(false);
                   
                }
            }
            for (int j2 = 0; j2 < btnArr.length; j2++) {
                btnArr[mineX.get(j2)][mineY.get(j2)].setIcon(new ImageIcon("bomb.png"));
                btnArr[mineX.get(j2)][mineY.get(j2)].setOpaque(false);
                
                
            }

        }
    }
    public static void setColor(int i ,int  j){
        if (easyMineC[i][j]==0) btnArr[i][j].setBackground(new Color(102,255,102));
        if (easyMineC[i][j]==1) btnArr[i][j].setBackground(new Color(255,255,102));
        if (easyMineC[i][j]==2) btnArr[i][j].setBackground(new Color(255,153,51));
        if (easyMineC[i][j]==3) btnArr[i][j].setBackground(new Color(255,153,204));
        if (easyMineC[i][j]==4) btnArr[i][j].setBackground(new Color(255,51,153));
        if (easyMineC[i][j]==-1) btnArr[i][j].setIcon(new ImageIcon("bomb.png"));
        
    }
    public  void restart(){
        dispose();
        homepage();
    }
    
}

