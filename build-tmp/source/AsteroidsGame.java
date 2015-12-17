import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class AsteroidsGame extends PApplet {

private Spaceship spaceship = new Spaceship();
private ArrayList<Asteroid> ast = new ArrayList<Asteroid>();
private ArrayList<Bullet> bul = new ArrayList<Bullet>();
Star[] nightSky = new Star[200];

public void setup() {
  size(500, 500);
  for (int i = 0; i < nightSky.length; i ++) {
    nightSky[i] = new Star();
  }
  for (int j = 0; j < 20; j++) {
    ast.add(new Asteroid());
  }
}

public void draw() {
  background(0);
  for(int i = 0; i< nightSky.length; i++)
  {
    nightSky[i].show();
  } 
  for (int b = 0; b < bul.size(); b ++) {
    bul.get(b).move();
    bul.get(b).show(); 
    if(bul.get(b).getX()<2 ||bul.get(b).getY()<2 || bul.get(b).getX() >497 || bul.get(b).getY() >497){
      bul.remove(b);
      break;
    }
    for( int j = 0; j < ast.size(); j ++) {
      if (dist(bul.get(b).getX(), bul.get(b).getY(), ast.get(j).getX(), ast.get(j).getY()) < 20) {
        ast.remove(j);
        bul.remove(b);
        break;
      }
    }
  }
  spaceship.move();
  spaceship.show();
  textSize(15);
  text(" X Position: " + spaceship.getX(), 20, 20);
  text(" Y Position: " + spaceship.getY(), 20, 40);

  for( int j = 0; j < ast.size(); j ++) {
    ast.get(j).move();
    ast.get(j).show();
    ast.get(j).rotate(ast.get(j).getrotate());
  }
}

public void keyPressed() {
  int r = 0;
  if (keyCode == UP)//up
    spaceship.accelerate(.3f);
  if (keyCode == LEFT)//left
    spaceship.rotate(-10);
    
    for (int b = 0; b <bul.size(); b ++){
      bul.get(b).rotate(r);
    }
  if (keyCode == RIGHT)//right
    spaceship.rotate(10);
  if (keyCode == DOWN){//hyperspace
    for (int i = 0; i < 1000; i++){
      spaceship.setX((int)(Math.random()*500));
      spaceship.setY((int)(Math.random()*500));
      spaceship.setPointDirection((int)(Math.random()*360));
      spaceship.setDirectionX(0);
      spaceship.setDirectionY(0);
      if (keyCode == UP)
        return;
    }
  }
}
public void mousePressed()
{
  bul.add(new Bullet());
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
    myColor = color(50, 60, 180);
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
  private int rotate;
  public Asteroid() {
    rotate = (int)(Math.random()*20-10);
    myColor = color(50);
    myCenterX = (int)(Math.random()*400);
    myCenterY = (int)(Math.random()*400);
    myDirectionX = Math.random()*3-1;
    myDirectionY = Math.random()*3-1;
    myPointDirection = Math.random()*360;
    corners = 6;
    int[] xS = {-12, -8, 4, 8, 4, -8};
    int[] yS = {0, -8, -8, 0, 8, 8};
    xCorners = xS;
    yCorners = yS;
  }
  public void setX(int x){myCenterX = x;} 
  public int getX(){return (int)myCenterX;}
  public void setY(int y){myCenterY = y;} 
  public int getY(){return (int)myCenterY;}   
  public void setDirectionX(double x){myDirectionX = x;}   
  public double getDirectionX(){return (double)myDirectionX;};   
  public void setDirectionY(double y){myDirectionY = y;}  
  public double getDirectionY(){return (double)myDirectionY;};   
  public void setPointDirection(int degrees){myPointDirection = degrees;} 
  public double getPointDirection(){return (int)myPointDirection;}; 
  public int getrotate() {return (int)rotate;}
  }
  class Bullet extends Floater {
  private int speed;
  public Bullet() {
    myColor = color(255, 0, 0);
    myCenterX = spaceship.getX();
    myCenterY = spaceship.getY();    
    myPointDirection = spaceship.getPointDirection();
    double dRadians = myPointDirection*(Math.PI/180);
    myDirectionX = 5*Math.cos(dRadians) + spaceship.getDirectionX();
    myDirectionY = 5*Math.sin(dRadians) + spaceship.getDirectionY();
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

  public void show() {
    fill(myColor);
    ellipse((float)myCenterX, (float)myCenterY, (float)8, (float)8);
  }

}    
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
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "AsteroidsGame" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
