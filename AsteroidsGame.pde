private Spaceship spaceship = new Spaceship();
private Asteroid ast = new Asteroid();
//private Bullet bul = new Bullet();
Star[] nightSky = new Star[200];
public void setup() {
  size(400, 400);
  for (int i = 0; i < nightSky.length; i ++) {
    nightSky[i] = new Star();
  }
}
public void draw() {
  background(0);
  for(int i = 0; i< nightSky.length; i++)
  {
    nightSky[i].show();
  } 
  spaceship.move();
  spaceship.show();
}

public void keyPressed() {
  if (keyCode == 38)//up
    spaceship.setY(spaceship.getY() - spaceship.getDirectionY());
  if (keyCode == 40)//down
    spaceship.setY(spaceship.getY() + spaceship.getDirectionY());
  if (keyCode == 37)//left
    spaceship.setX(spaceship.getX() - spaceship.getDirectionX());
  if (keyCode == 39)//right
    spaceship.setX(spaceship.getX() - spaceship.getDirectionX());
  //if (keyCode = 96)//numpad 0
    //spaceship.setDirectionX(0);
    //spaceship.setDirectionY(0);
}

class Star
{
  private int myX, myY;
  public Star()
  {
    myX = (int)(Math.random()*500);
    myY = (int)(Math.random()*500);
  }
  public void show()
  {
    fill(255);
    ellipse(myX, myY, 3, 3);
  }
}
class Spaceship extends Floater  
{ 
  public Spaceship() 
  {
    myColor = color(50, 60, 80);
    myCenterX = 200;
    myCenterY = 200;
    myDirectionX = 1;
    myDirectionY = 1;
    myPointDirection = 0;
    corners = 4;
    int[] xS = {-8, 16, -8, -2};
    int[] yS = {-8, 0, 8, 0};
    xCorners = xS;
    yCorners = yS;
  }
  public void setX(int x) {myCenterX = x;}
  public int getX() {return (int)myCenterX;}
  public void setY(int y) {myCenterY = y;}
  public int getY() {return (int)myCenterY;}
  public void setDirectionX(double x) {myDirectionX = x;}
  public double getDirectionX () {return (double)myDirectionX;}
  public void setDirectionY(double y) {myDirectionY = y;}
  public double getDirectionY() {return (double)myDirectionY;};
  public  void  setPointDirection (int degrees) {myPointDirection = degrees;}
  public double getPointDirection() {return (int)myPointDirection;}

}
class Asteroid extends Floater {
  public Asteroid() {
    myColor = color(50);
  }
  public void setX(int x){} 
  public int getX(){return 0;}
  public void setY(int y){} 
  public int getY(){return 0;}   
  public void setDirectionX(double x){}   
  public double getDirectionX(){return 0;};   
  public void setDirectionY(double y){}  
  public double getDirectionY(){return 0;};   
  public void setPointDirection(int degrees){} 
  public double getPointDirection(){return 0;}; 
  
}
/*class Bullet extends Floater {
  public Bullet() {
  }
 
}*/
abstract class Floater //Do NOT modify the Floater class! Make changes in the SpaceShip class 
{   
  protected int corners;  //the number of corners, a triangular floater has 3   
  protected int[] xCorners;   
  protected int[] yCorners;   
  protected int myColor;   
  protected double myCenterX, myCenterY; //holds center coordinates   
  protected double myDirectionX, myDirectionY; //holds x and y coordinates of the vector for direction of travel   
  protected double myPointDirection; //holds current direction the ship is pointing in degrees    
  abstract public void setX(int x);  
  abstract public int getX();   
  abstract public void setY(int y);   
  abstract public int getY();   
  abstract public void setDirectionX(double x);   
  abstract public double getDirectionX();   
  abstract public void setDirectionY(double y);   
  abstract public double getDirectionY();   
  abstract public void setPointDirection(int degrees);   
  abstract public double getPointDirection(); 

  //Accelerates the floater in the direction it is pointing (myPointDirection)   
  public void accelerate (double dAmount)   
  {          
    //convert the current direction the floater is pointing to radians    
    double dRadians =myPointDirection*(Math.PI/180);     
    //change coordinates of direction of travel    
    myDirectionX += ((dAmount) * Math.cos(dRadians));    
    myDirectionY += ((dAmount) * Math.sin(dRadians));       
  }   
  public void rotate (int nDegreesOfRotation)   
  {     
    //rotates the floater by a given number of degrees    
    myPointDirection+=nDegreesOfRotation;   
  }   
  public void move ()   //move the floater in the current direction of travel
  {      
    //change the x and y coordinates by myDirectionX and myDirectionY       
    myCenterX += myDirectionX;    
    myCenterY += myDirectionY;     

    //wrap around screen    
    if(myCenterX >width)
    {     
      myCenterX = 0;    
    }    
    else if (myCenterX<0)
    {     
      myCenterX = width;    
    }    
    if(myCenterY >height)
    {    
      myCenterY = 0;    
    }   
    else if (myCenterY < 0)
    {     
      myCenterY = height;    
    }   
  }   
  public void show ()  //Draws the floater at the current position  
  {             
    fill(myColor);   
    stroke(myColor);    
    //convert degrees to radians for sin and cos         
    double dRadians = myPointDirection*(Math.PI/180);                 
    int xRotatedTranslated, yRotatedTranslated;    
    beginShape();         
    for(int nI = 0; nI < corners; nI++)    
    {     
      //rotate and translate the coordinates of the floater using current direction 
      xRotatedTranslated = (int)((xCorners[nI]* Math.cos(dRadians)) - (yCorners[nI] * Math.sin(dRadians))+myCenterX);     
      yRotatedTranslated = (int)((xCorners[nI]* Math.sin(dRadians)) + (yCorners[nI] * Math.cos(dRadians))+myCenterY);      
      vertex(xRotatedTranslated,yRotatedTranslated);    
    }   
    endShape(CLOSE);  
  }   
} 

