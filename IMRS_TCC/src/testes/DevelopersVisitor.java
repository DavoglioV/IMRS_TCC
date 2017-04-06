package testes;

import java.io.ByteArrayInputStream;

import br.com.metricminer2.domain.Commit;
import br.com.metricminer2.domain.Modification;
import br.com.metricminer2.parser.jdt.JDTRunner;
import br.com.metricminer2.persistence.PersistenceMechanism;
import br.com.metricminer2.scm.CommitVisitor;
import br.com.metricminer2.scm.RepositoryFile;
import br.com.metricminer2.scm.SCMRepository;
import br.com.metricminer2.util.FileUtils;

public class DevelopersVisitor implements CommitVisitor {

	public String name() {
		return "NOME: ";
	}
	public void process(SCMRepository repo, Commit commit, PersistenceMechanism writer) {
		String nomeClasse;
		for (RepositoryFile r: repo.getScm().files()){
			nomeClasse = "";
			if(r.fileNameEndsWith(".java")){
				nomeClasse =  r.getFile().getName();
//				NumberOfMethodsVisitor  
//				new JDTRunner().visit(visitor, new ByteArrayInputStream(r.getSourceCode().getBytes()));
//				
//				
//				r.getClass().getDeclaredMethod(name, parameterTypes)
//				Object classe = r.getFile().getName().replaceAll(".java", "").getClass()
//				classe.getClass();
//				r.getFile().getName()CanonicalFile()CanonicalPath()AbsoluteFile().gParentFile()Name().;
			}
		}
		
		for(Modification m : commit.getModifications()) {
			writer.write(
					commit.getCommitter().getName(),
					m.getFileName(),
					m.getType()
			);
			
		}
	}
}
