package me.zouooh.invoker;


public class IndexPath implements CellType{
	
	public static boolean isHeadCell(int type){
		return type>1000&&type<=HEAD;
	}
	
	public static boolean isCell(int type){
		return type<=CELL;
	}
	
	public static boolean isFootCell(int type){
		return type>HEAD&&type<=FOOT;
	} 
	
	private int section = 0;
	private int row = 0;
	private int type = CELL;

	public IndexPath() {
	}

	public IndexPath(int section, int row) {
		this(section, row, CELL);
	}

	public IndexPath(int section, int row, int type) {
		this.section = section;
		this.row = row;
		this.type = type;
	}

	public int getSection() {
		return section;
	}

	public void setSection(int section) {
		this.section = section;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
	public boolean isHeadCell(){
		return isHeadCell(getType());
	}
	
	public boolean isFootCell(){
		return isFootCell(getType());
	}
	
	public boolean isCell(){
		return isCell(getType());
	}
	
	public boolean isType(int type){
		return this.type == type;
	}
}