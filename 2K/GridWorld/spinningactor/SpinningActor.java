package spinningactor;//© A+ Computer Science  -  www.apluscompsci.com
//Name - Diego Rodrigues Rodriguez
//Date - 03/05/22
//Class - Ap CS
//Lab  - Spinning Actor


import java.awt.Color;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Grid;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

public class SpinningActor extends Actor
{
	public static void main(String[] args) {
		ActorWorld world = new ActorWorld();
		SpinningActor spin = new SpinningActor();
		world.add(spin);
		world.show();
	}
	public SpinningActor()
	{
	}
	
	public void act()
	{
		setDirection(getDirection()+45);
	}
	
	public Color getColor()
	{
		return null;
	}
}