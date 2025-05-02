package com.mycompany.a3;

import com.codename1.ui.geom.Point;

public interface ISelectable {
	boolean isSelected();
	void setSelected(boolean selected);
	boolean contains(Point pPtrRelPrnt);

}
