/**
 * Nome: acoplamentoAferente.AcoplamentoAferenteVisitor.java
 * Propósito: Classe tcc-testes
 * 
 * Compilador: 1.8
 * Data da Criação: 14/04/2017-11:50:03
 * Registro de Manutenção: 14/04/2017
 * 
 * Autor: vanesilv
 */

package metricas;

import java.util.List;

import comuns.Classe;
import br.com.metricminer2.domain.Commit;
import br.com.metricminer2.persistence.PersistenceMechanism;
import br.com.metricminer2.scm.CommitVisitor;
import br.com.metricminer2.scm.RepositoryFile;
import br.com.metricminer2.scm.SCMRepository;



/**
 *
 * @author vanesilv
 * @version 1.0
 * @see 
 * @date 14/04/2017-11:43:18
 */
public class AcoplamentoAferenteVisitor implements CommitVisitor {

	/* (non-Javadoc)
	 * @see br.com.metricminer2.scm.CommitVisitor#name()
	 */
	public String name() {
		return "Nome: ";
	}

	/* (non-Javadoc)
	 * @see br.com.metricminer2.scm.CommitVisitor#process(br.com.metricminer2.scm.SCMRepository, br.com.metricminer2.domain.Commit, br.com.metricminer2.persistence.PersistenceMechanism)
	 */
	public void process(SCMRepository repo, Commit commit,
			PersistenceMechanism writer) {
		
		Classe classe = null;
		
		try{ 
			//Faz checkout de todos os arquivos contidos no repositório no momento deste commit
		    	repo.getScm().checkout(commit.getHash());
			
			//ArrayList dos arquivos 
			List<RepositoryFile> files = repo.getScm().files();
			
			//Percorre toda a lista de arquivos do repositorio
			for (int i = 0; i < files.size(); i++){
				classe = new Classe();
				
				//Se não for uma classe java (terminar com .java) não faz nada (continue)
				if(!files.get(i).getFile().getName().endsWith("java"))
					continue;
				
				classe.setNomeDaClasse(files.get(i).getFile().getName().replace(".java", ""));
				
				//Percorre toda a lista de arquivos 
				//para comparar com o nome do arquivo contido em files.get(i)
				for(int j = 0; j < files.size(); j++){
					RepositoryFile file = files.get(j);
					
					//Testa se o arquivo atual é o mesmo que esta sendo testado (1º for)
					//se for não pode contar para o calculo do acoplamento aferente
					if(file.getFile().getName().equals(classe.getNomeDaClasse() + ".java"))
						continue;
					
					//verifica se a classe atual contem chamada da classe do primeiro for
					if(file.getSourceCode().contains(classe.getNomeDaClasse())){
						classe.setAcoplamentoAferente(classe
								.getAcoplamentoAferente() + 1);
					}
				}
				
				//Escreve no arquivo CSV
				
				writer.write(
					"[Hash: " + commit.getHash() + "]",
					"[Nome da Classe: " + classe.getNomeDaClasse() + "]", 
					"[A. Aferente: " + classe.getAcoplamentoAferente() + "]"
					);
			}
			//pula uma linhe entre um Hash e outro
			writer.write("");
		}finally {
			repo.getScm().reset();
			
		}
		
		
//		
//		############## IMPRIME NUMERO DE METODOS DO REPOSITORIO #############
//		try{
//			repo.getScm().checkout(commit.getHash());
//		
//			List<RepositoryFile> files = repo.getScm().files();
//			
//			for(RepositoryFile file: files){
//				
//				if(!file.fileNameEndsWith("java"))
//					continue;
//				
//				File soFile = file.getFile();
//				
//				NumberOfMethodsVisitor visitor = new NumberOfMethodsVisitor();
//				new JDTRunner().visit(visitor, new ByteArrayInputStream(readFile(soFile).getBytes()));
//			
//				int methods = visitor.getQty();
//				
//				writer.write(
//						commit.getHash(),
//						commit.getAuthor().getName(),
//						file.getFullName(),
//						methods
//						);
//			}
//		}finally{
//			repo.getScm().reset();
//		}
//	}
//		
//		private String readFile(File f){
//			
//			try{
//			
//				FileInputStream input = new FileInputStream(f);
//				String text = IOUtils.toString(input);
//				
//				input.close();
//				
//				return text;
//			} catch(Exception e){
//				throw new RuntimeException("Erro ao ler o arquivo " + f.getAbsolutePath());
//		
//			}
//		}
		
		
		
//      ########### IMPRIMI QUANTIDADE DE METODOS DO COMMIT ############
//		
//		for(Modification m : commit.getModifications()){
//			
//			if(m.wasDeleted())
//				continue;
//			
//			NumberOfMethodsVisitor visitor = new NumberOfMethodsVisitor();
//			new JDTRunner().visit(visitor, new ByteArrayInputStream(m.getSourceCode().getBytes()));
//			
//			int metodos = visitor.getQty();
//			
//			writer.write(
//					commit.getAuthor().getName(),
//					m.getFileName(),
//					metodos);
//			
//		}
	}
}