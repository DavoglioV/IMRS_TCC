package testes;

import br.com.metricminer2.domain.Commit;
import br.com.metricminer2.persistence.PersistenceMechanism;
import br.com.metricminer2.scm.CommitVisitor;
import br.com.metricminer2.scm.SCMRepository;

public class DevelopersVisitor implements CommitVisitor {

	@Override
	public String name() {
		return "Nome: ";
	}

	@Override
	public void process(SCMRepository repo, Commit commit, PersistenceMechanism writer) {

		writer.write(commit.getHash(), commit.getCommitter().getName());

	}

}
