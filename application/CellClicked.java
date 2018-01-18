package application;
import javafx.event.*;
import javafx.scene.input.MouseEvent;

import java.util.LinkedList;
import java.util.List;


public class CellClicked implements EventHandler<MouseEvent> {
	private List<CellClickedReciver> recevers;
	
	public CellClicked() {
		this.recevers = new LinkedList<CellClickedReciver>();
	}
	
	public void AddRciver(CellClickedReciver res){
		this.recevers.add(res);
	}
	
	public void RemoveReciver(CellClickedReciver res) {
		this.recevers.remove(res);
	}
	
	@Override
	public void handle(MouseEvent event) {
		for(CellClickedReciver res : this.recevers){
			res.clicked((GridCell)event.getSource()) ;
		}
	}
			
}
