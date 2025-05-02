package com.mycompany.a3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

// game objects
public class GameObjectCollection implements Iterable <GameObject>{
	private ArrayList<GameObject> objects = new ArrayList<>();

	// add objects
    public void add(GameObject obj) {
        objects.add(obj);
    }

    // override iterator design pattern method
    @Override
    public Iterator<GameObject> iterator() {
        return new GameObjectIterator();
    }

    private class GameObjectIterator implements Iterator<GameObject> {
        private int index = 0;

        public boolean hasNext() {
            return index < objects.size();
        }

        public GameObject next() {
            if (!hasNext()) throw new NoSuchElementException();
            return objects.get(index++);
        }
        
        public void remove() {
        	throw new UnsupportedOperationException();
    }
}

}
