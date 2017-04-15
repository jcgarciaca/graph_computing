package comboxproessing;

import javax.swing.ImageIcon;

import processing.core.*;
import controlP5.*;


public class ComBoxProcessing extends PApplet{	
	
	/**
	 * 
	 */
	ControlP5 controlP5;
	ListBox l;
	ControlWindow controlWindow;
	Slider ss;
	
	int slider = 60;
	
	ImageIcon icon = new ImageIcon("motoman_isometrico.png");
    
	Textlabel myTextlabelA;
	
    public void setup() {
        size(400, 400, P3D);
        controlP5 = new ControlP5(this);
		controlP5.setAutoDraw(false);
		controlWindow = controlP5.addControlWindow("controlP5window",500,100,400,200);
		controlWindow.hideCoordinates();
		controlWindow.setBackground(color(128));
		
//		myTextlabelA = controlP5.addTextlabel(icon);//"label","A SINGLE TESTLABEL.",20,134);
		
//		controlP5.addSlider("sliderValue1",0,255,40,40,100,10);
//		  controlP5.addTextfield("myTextfield",70,130,40,20);
//		  controlWindow.setTitle("abc");
		  
		
        l = controlP5.addListBox("myList", 30, 30, 100, 80);
        
        l.setItemHeight(20);
        l.setBarHeight(20);        
        
        l.setColorBackground(color(40, 128));
        l.setColorActive(color(255, 128));

		l.captionLabel().toUpperCase(true);
		l.captionLabel().set("Tipo de movimiento");
		l.captionLabel().setColor(0xffff0000);
		l.captionLabel().style().marginTop = 3;
		l.valueLabel().style().marginTop = 3;
		l.addItem("MOVJ", 0);
		l.addItem("MOVL", 1);
		
		l.moveTo(controlWindow);
		
		ss = controlP5.addSlider("slider",10,100,60,100,160,100,10);
//		ss.setNumberOfTickMarks(5);
//		controlP5.controller("slider").setValue(slider);
		ss.moveTo(controlWindow);
		
		//b = 
		
//		b = loadImage("motoman_isometrico.png");
		
		
    }

    

    public void draw() {
        background(128);
        controlP5.draw();
//        System.out.println(slider);
    }
    
    public void controlEvent(ControlEvent theEvent) {
    	   
		if(theEvent.isGroup() && theEvent.name().equals("myList")){
			int indice = (int)theEvent.group().value();
			System.out.println("Indice "+indice);
		}
	}   
}
