package testes;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;


public class NumberOfMethodsVisitor extends ASTVisitor{
	
	private int qty = 0;
	
	public boolean visit(MethodDeclaration node){
		qty++;
		return super.visit(node);
	}
	
	public int getQty(){
		return qty;
	}

}
