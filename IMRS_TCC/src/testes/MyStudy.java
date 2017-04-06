package testes;

import br.com.metricminer2.MetricMiner2;
import br.com.metricminer2.RepositoryMining;
import br.com.metricminer2.Study;
import br.com.metricminer2.filter.range.Commits;
import br.com.metricminer2.persistence.csv.CSVFile;
import br.com.metricminer2.scm.GitRepository;

public class MyStudy implements Study {

	public static void main(String[] args) {
		new MetricMiner2().start(new MyStudy());
	}

	public void execute() {
		new RepositoryMining()
				.in(GitRepository.singleProject("C:/Users/Vanny/git/repodriller"))
				.through(Commits.onlyInHead())
				.process(new DevelopersVisitor(), new CSVFile("C:/Users/Vanny/git/IMRS_TCC/IMRS_TCC/src/files/fileTeste.csv"))
				.mine();

	}

}
