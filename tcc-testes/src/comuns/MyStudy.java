package comuns;
import metricas.AcoplamentoAferenteVisitor;
import metricas.AcoplamentoEferenteVisitor;
import br.com.metricminer2.MetricMiner2;
import br.com.metricminer2.RepositoryMining;
import br.com.metricminer2.Study;
import br.com.metricminer2.filter.range.Commits;
import br.com.metricminer2.persistence.csv.CSVFile;
import br.com.metricminer2.scm.GitRepository;


public class MyStudy implements Study{
	
	public static void main(String[] args){
		new MetricMiner2().start(new MyStudy());
	}
	public void execute() {
		new RepositoryMining()
		.in(GitRepository.singleProject("C:/Users/vanesilv/git/change-metrics"))
		.through(Commits.all())
		.process(new AcoplamentoEferenteVisitor(), new CSVFile("C:/Users/vanesilv/git/file.csv"))
		.mine();
	}

}
