// Cell of the Matrix

public class Cell{
    private int x=5,y=5;
    private boolean alive= true;


    public Cell(int x,int y, boolean alive){
        this.x=x;
        this.y=y;
        this.alive=alive;
    }

    public Cell(int x,int y){
        this.x=x;
        this.y=y;
    }


    public boolean isAlive(){
        return alive;
    }

    public void setAlive(boolean alive){
        this.alive=alive;
    }

    public int getXX(){
        return x;
    }

    public void setXX(int x){
        this.x = x;
    }

    public int getYY(){
        return y;
    }

    public void setYY(int y){

        this.y = y;
    }


    @Override
    public boolean equals( Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cell otherCell = (Cell) obj;

        if (!(obj instanceof Cell))
            return false;
        return otherCell.getXX() == this.getXX() && otherCell.getYY() == this.getYY();
    }

}