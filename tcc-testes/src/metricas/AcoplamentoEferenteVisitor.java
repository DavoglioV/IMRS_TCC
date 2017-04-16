package metricas;

import java.util.ArrayList;
import java.util.List;

import comuns.Classe;
import br.com.metricminer2.domain.Commit;
import br.com.metricminer2.persistence.PersistenceMechanism;
import br.com.metricminer2.scm.CommitVisitor;
import br.com.metricminer2.scm.RepositoryFile;
import br.com.metricminer2.scm.SCMRepository;

public class AcoplamentoEferenteVisitor implements CommitVisitor{

    public void process(SCMRepository repo, Commit commit,
	    PersistenceMechanism writer) {

	List<Classe> classes = new ArrayList<Classe>();
	List<RepositoryFile> files = new ArrayList<RepositoryFile>();
	
	
	//Faz checkout dos arquivos no repositorio em determinado commit
	repo.getScm().checkout(commit.getHash());
	
	files = repo.getScm().files();
	
	//percorre a lista de arquivos para criar lista de Classes
//	for(int i = 0; i < files.size(); i++ ){
//	    //verifica se é classe Java
//	    if(files.get(i).fileNameEndsWith("java")){
//		classe = new Classe();
//		classe.setNomeDaClasse(files.get(i).getFile().getName().replace(".java", ""));
//		classes.add(classe);
//	    }
//	}
	

	
	
	for(int i = 0; i <files.size(); i++){
	   
	    if(!files.get(i).getFile().getName().endsWith(".java"))
		continue;
	    
	    Classe classe = new Classe();
	    classe.setNomeDaClasse(files.get(i).getFile().getName().replace(".java", ""));
	    for(int j = 0; j< files.size(); j++){
		
		if(!files.get(j).getFile().getName().endsWith(".java") ||
			files.get(j).getFile().getName().equals(files.get(i).getFile().getName()))
		    continue;
		
		if(files.get(i).getSourceCode().contains(files.get(j).getFile().getName().replace(".java", ""))){
		   classe.setAcoplamentoEferente(classe.getAcoplamentoEferente()+1);
		}
		
	    }
	    writer.write(
		    classe.getNomeDaClasse(), classe.getAcoplamentoEferente()
		    );
	}
	
	
	for(Classe classeImprime: classes){
	    writer.write(classeImprime.getNomeDaClasse(), classeImprime.getAcoplamentoEferente());
	}
	writer.write("");
	
    }

    public String name() {
	return "Nome: ";
    }

}
