
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

public class backEnd extends FrontEnd{
    static ArrayList<Integer> mineX ;
    static ArrayList<Integer> mineY ;
    static int left=225;
    static int[][] mineCode = new int[15][15];
    public static void setMineCode(){
        for (int i = 0; i < mineCode.length; i++) {
            for (int j = 0; j < mineCode.length; j++) {
                mineCode[i][j] =0;
            }
        }
    }

    public static void setMinesPosition(){
        mineY = new ArrayList<>();
        mineX = new ArrayList<>();
        for (int i = 1; i < 14; i++) {
            mineX.add(i);
            mineY.add(i);
        } 
        Collections.shuffle(mineX);
        Collections.shuffle(mineY);

        for (int i = 0; i < mineX.size(); i++) {
            mineCode[mineX.get(i)][mineY.get(i)]--;
            // btnArr[mineX.get(i)][mineY.get(i)].setText(Integer.toString(mineCode[mineX.get(i)][mineY.get(i)]));
        }
        borderOfMine();
    }
    public static void borderOfMine(){
        
        for (int i = 0; i < mineX.size(); i++) {
            if(mineCode[mineX.get(i)-1][mineY.get(i)-1]!=-1) mineCode[mineX.get(i)-1][mineY.get(i)-1]++;
            if(mineCode[mineX.get(i)][mineY.get(i)-1]!=-1)mineCode[mineX.get(i)][mineY.get(i)-1]++;
            if(mineCode[mineX.get(i)+1][mineY.get(i)-1]!=-1)mineCode[mineX.get(i)+1][mineY.get(i)-1]++;
            if(mineCode[mineX.get(i)+1][mineY.get(i)]!=-1)mineCode[mineX.get(i)+1][mineY.get(i)]++;
            if(mineCode[mineX.get(i)-1][mineY.get(i)]!=-1)mineCode[mineX.get(i)-1][mineY.get(i)]++;
            if(mineCode[mineX.get(i)-1][mineY.get(i)+1]!=-1)mineCode[mineX.get(i)-1][mineY.get(i)+1]++;
            if(mineCode[mineX.get(i)][mineY.get(i)+1]!=-1)mineCode[mineX.get(i)][mineY.get(i)+1]++;
            if(mineCode[mineX.get(i)+1][mineY.get(i)+1]!=-1)mineCode[mineX.get(i)+1][mineY.get(i)+1]++;
        }
    }
    public static void printMineCode(){
        for (int i = 0; i < mineCode.length; i++) {
            for (int j = 0; j < mineCode.length; j++) {
                System.out.print(mineCode[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void clickButton(int i , int j ){
        btnArr[i][j].setText(Integer.toString(mineCode[i][j]));
        left--;
        setColor(i, j);
        btnArr[i][j].setEnabled(false);
        if (mineCode[i][j]==-1) {
            for (int j2 = 0; j2 < mineX.size(); j2++) {
                setColor(mineX.get(j2), mineY.get(j2));
                btnArr[mineX.get(j2)][mineY.get(j2)].setText("M");
            }
        }
        // System.out.println(i+" "+j);
        checkAround(i , j);
        if (mineCode[i][j]==-1) {
            for (int j2 = 0; j2 < btnArr.length; j2++) {
                for (int k = 0; k < btnArr.length; k++) {
                    btnArr[j2][k].setEnabled(false);
                    if (mineCode[j2][k]!=-1) {
                        setColor(j2, k);
                        btnArr[j2][k].setText(Integer.toString(mineCode[j2][k]));
                    }
                }
            }
            restart.setEnabled(true);
        }
    }
    public static void checkAround(int i , int j){
        // straight direction till end diagonal till 4 tiles
        if (mineCode[i][j]==0 && i>=1 && i<=13 && j>=1 && j<=13) {
            btnArr[i-1][j-1].setText(Integer.toString(mineCode[i-1][j-1]));
            if(btnArr[i-1][j-1].isEnabled()) left--;
            setColor(i-1, j-1);
            btnArr[i-1][j-1].setEnabled(false);

            btnArr[i-1][j].setText(Integer.toString(mineCode[i-1][j]));
            if(btnArr[i-1][j].isEnabled()) left--;
            setColor(i-1, j);
            btnArr[i-1][j].setEnabled(false);

            btnArr[i-1][j+1].setText(Integer.toString(mineCode[i-1][j+1]));
            if(btnArr[i-1][j+1].isEnabled()) left--;
            setColor(i-1, j+1);
            btnArr[i-1][j+1].setEnabled(false);

            btnArr[i][j-1].setText(Integer.toString(mineCode[i][j-1]));
            if(btnArr[i][j-1].isEnabled()) left--;
            setColor(i, j-1);
            btnArr[i][j-1].setEnabled(false);

            btnArr[i][j+1].setText(Integer.toString(mineCode[i][j+1]));
            if(btnArr[i][j+1].isEnabled()) left--;
            setColor(i, j+1);
            btnArr[i][j+1].setEnabled(false);

            btnArr[i+1][j-1].setText(Integer.toString(mineCode[i+1][j-1]));
            if(btnArr[i+1][j-1].isEnabled()) left--;
            setColor(i+1, j-1);
            btnArr[i+1][j-1].setEnabled(false);

            btnArr[i+1][j].setText(Integer.toString(mineCode[i+1][j]));
            if(btnArr[i+1][j].isEnabled()) left--;
            setColor(i+1, j);
            btnArr[i+1][j].setEnabled(false);

            btnArr[i+1][j+1].setText(Integer.toString(mineCode[i+1][j+1]));
            if(btnArr[i+1][j+1].isEnabled()) left--;
            setColor(i+1, j+1);
            btnArr[i+1][j+1].setEnabled(false);

        }
        if (left<=13) {
            restart.setEnabled(true);
        }
    }
    public static void setColor(int i ,int  j){
        if (mineCode[i][j]==0) btnArr[i][j].setBackground(new Color(102,255,102));
        if (mineCode[i][j]==1) btnArr[i][j].setBackground(new Color(255,255,102));
        if (mineCode[i][j]==2) btnArr[i][j].setBackground(new Color(255,153,51));
        if (mineCode[i][j]==3) btnArr[i][j].setBackground(new Color(255,153,204));
        if (mineCode[i][j]==4) btnArr[i][j].setBackground(new Color(255,51,153));
        if (mineCode[i][j]==-1) btnArr[i][j].setBackground(new Color(255,0,0));
        
    }
    public  void restart(){
        dispose();
        gamePage();
    }
    
}

