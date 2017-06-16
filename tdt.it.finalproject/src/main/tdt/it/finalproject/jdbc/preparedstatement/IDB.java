package main.tdt.it.finalproject.jdbc.preparedstatement;

import java.util.List;

public interface IDB<Model,ReturnType,IdType> {
	/*
	 * add
	 */
	public ReturnType add(Model e);	
	public ReturnType delete (IdType k);
	public ReturnType update (Model e);
	public Model find(IdType k);
	public List<Model> getAll();
	
	
	

}