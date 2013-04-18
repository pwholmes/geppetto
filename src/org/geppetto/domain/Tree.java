package org.geppetto.domain;

public class Tree {

	TreeNode root;
	
	public Tree()
	{
		//root = new TreeNode(Phrase.INPUT);
	}
	
	public Tree(Object ob)
	{
		root = (TreeNode) ob;
	}
	
	public void print()
	{
		root.print("|--");
	}
	
	public void setRoot(Object tn)
	{
		root = (TreeNode) tn;
	}
	
}
