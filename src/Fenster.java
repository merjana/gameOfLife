import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Fenster extends JFrame implements ActionListener {
    private RandomXY pan = new RandomXY();
    private JButton randomBtn = new JButton("random");
    private JButton playBtn = new JButton("play");
    private JButton resetBtn = new JButton("reset");
    private boolean play =false;
    private List<Cell> copyOfCells = new ArrayList<>();
    private int generation = 0;
    private int aliveCells = 0;
    JLabel generationLabel = new JLabel();

    public Fenster(){
        pan.setPreferredSize(new Dimension(800, 400));
        //pan.setBackground(Color.blue);
        this.setSize(new Dimension(800,500));
        Image im = Toolkit.getDefaultToolkit().getImage("D:\\Master\\Softwaretechnik 1\\gameOflife2\\iconLife.png");
        this.setIconImage(im);
        this.setTitle("Game of life");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel northPanel = new JPanel();

        northPanel.add(playBtn);
        northPanel.add(resetBtn);
        northPanel.add(randomBtn);

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                if(play) {
                    int i=0;

                    double start = System.currentTimeMillis();
                    for(Cell cell:pan.getListOfCells()){
                        int xx=cell.getXX();
                        int yy=cell.getYY();
                        Cell celll = new Cell(xx,yy,pan.entscheiden(cell));

                        if(celll.isAlive()) aliveCells++;
                        copyOfCells.add(celll);

                    }
                    double spend =System.currentTimeMillis() - start;
                    System.out.println("GridTIME:"+spend);

                    for(Cell cell:copyOfCells){

                        pan.getListOfCells().set(i,cell);
                        i++;
                    }
                    System.out.println("loop2");
                    pan.repaint();
                    copyOfCells.clear();
                    generation++;
                    generationLabel.setText("Generation: "+generation+" | Lives cells :"+aliveCells);
                    aliveCells=0;
                }
            }
        };
        timer.scheduleAtFixedRate(task,0,4);
        generationLabel.setText("Generation: "+generation+" | Live cells : "+aliveCells);
        randomBtn.addActionListener(new RandomListener());
        resetBtn.addActionListener(new ResetListener());
        playBtn.addActionListener(new PlayListener());

        this.setLayout(new BorderLayout());

        this.getContentPane().add(generationLabel,BorderLayout.NORTH);
        this.getContentPane().add(northPanel,BorderLayout.SOUTH);
        this.getContentPane().add(pan,BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
        //drawNet();
    }

    // make a random cells in the grid with random Button
    public class RandomListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            pan.setRandomTest(true);
            pan.repaint();
        }
    }

    // make the grid empty again
    public class ResetListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            generation = 0;
            aliveCells = 0;
            generationLabel.setText("Generation: "+generation+" | Lives cells :"+aliveCells);
            for(Cell cell:pan.getListOfCells()){

                cell.setAlive(false);
            }

            pan.repaint();
        }
    }

    // start the play
    public class PlayListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            play=!play;

            if(play) playBtn.setText("pause");
            else playBtn.setText("play");
            if(play) {
                randomBtn.setEnabled(false);
                resetBtn.setEnabled(false);
            }else{
                randomBtn.setEnabled(true);
                resetBtn.setEnabled(true);
            }

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }
}
