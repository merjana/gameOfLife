import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomXY extends JPanel implements MouseListener{
    private List<Cell> listOfCells = new ArrayList<>();
    private int height=400,width=800;
    private int rectLenght =5;
    private Cell cell;
    private Random random = new Random();
    private boolean randomTest = false;
    private boolean reset = true;
    private int posX,posY;



    public RandomXY(){
        this.addMouseListener(this);
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        // initialize all the cells as dead cells
        if(reset){
            for(int i=0;i<width;i=i+rectLenght) {
                for(int j=0;j<height;j=j+rectLenght){
                    cell=new Cell(i,j,false);
                    listOfCells.add(cell);
                }
            }
            reset = !reset;
        }
        // to make random alive cells in the grid
        if (randomTest) {
            for(int i=1;i<=50;i++){
                int randomNumber = random.nextInt(listOfCells.size());
                listOfCells.get(randomNumber).setAlive(true);
            }
            randomTest = !randomTest;
        }

        // the background of the jPanel
        this.setBackground(Color.WHITE);

        // draw the grid

        for(Cell currentCell :listOfCells){
            posX = currentCell.getXX();
            posY = currentCell.getYY();

            if(currentCell.isAlive()){

                g.setColor(Color.RED);
                g.fillRect(posX,posY , rectLenght, rectLenght);
                g.setColor(Color.GRAY);
                g.drawRect(posX, posY, rectLenght, rectLenght);
            }else{
                g.setColor(Color.GRAY);
                g.drawRect(posX, posY, rectLenght, rectLenght);
            }




        }

    }


    @Override
    public void mouseClicked(MouseEvent e) {

        posX = e.getX();
        posY = e.getY();
        Cell makeCell = new Cell(posX - posX%rectLenght,posY - posY%rectLenght);


        if(listOfCells.contains(makeCell)){
            int i = listOfCells.indexOf(makeCell);
            //System.out.println(i);
            if(listOfCells.get(i).isAlive()) {
                listOfCells.get(i).setAlive(false);
            }
            else{
                listOfCells.get(i).setAlive(true);
            }

            repaint();

        }

    }

    public boolean entscheiden(Cell cell){
        int nachbaren=0;
        int x=cell.getXX();
        int y=cell.getYY();
         Cell nachbarUp = new Cell(x,y-rectLenght) ;
         Cell nachbarUpLeft = new Cell(x-rectLenght,y-rectLenght);
         Cell nachbarUpRight= new Cell(x+rectLenght,y-rectLenght);
         Cell nachbarRight= new Cell(x+rectLenght,y);
         Cell nachbarLeft= new Cell(x-rectLenght,y);
         Cell nachbarDown= new Cell(x,y+rectLenght);
         Cell nachbarDownLeft= new Cell(x-rectLenght,y+rectLenght);
         Cell nachbarDownRight= new Cell(x+rectLenght,y+rectLenght);


        if(x>0){
            if(listOfCells.get(listOfCells.indexOf(nachbarLeft)).isAlive()) nachbaren++;
            if(y>0) if(listOfCells.get(listOfCells.indexOf(nachbarUpLeft)).isAlive()) nachbaren++;
            if(y<height-rectLenght) if(listOfCells.get(listOfCells.indexOf(nachbarDownLeft)).isAlive()) nachbaren++;
        }
        if(x<width-rectLenght){
            if(listOfCells.get(listOfCells.indexOf(nachbarRight)).isAlive()) nachbaren++;
            if(y>0) if(listOfCells.get(listOfCells.indexOf(nachbarUpRight)).isAlive()) nachbaren++;
            if(y<height-rectLenght) if(listOfCells.get(listOfCells.indexOf(nachbarDownRight)).isAlive()) nachbaren++;
        }

        if(y>0) if(listOfCells.get(listOfCells.indexOf(nachbarUp)).isAlive()) nachbaren++;
        if(y<height-rectLenght) if(listOfCells.get(listOfCells.indexOf(nachbarDown)).isAlive()) nachbaren++;


        // jetzt zu enyscheideb ob den Cell wird leben oder nein
        if(nachbaren == 3) return true;

        if(cell.isAlive() && nachbaren==2) return true;

        return false;

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void setRandomTest(boolean randomTest){
        this.randomTest= randomTest;
    }

    public List<Cell> getListOfCells(){
        return listOfCells;
    }


    public void setReset(boolean reset){
        this.reset = reset;
    }
}
