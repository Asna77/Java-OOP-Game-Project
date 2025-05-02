package com.mycompany.a3;

//abstract class Fixed for objects that are not movable
abstract class Fixed extends GameObject{
	public Fixed(int size, com.codename1.ui.geom.Point point, int color) {
		super(size, point, color); // declare size, location, and color
	}
}
