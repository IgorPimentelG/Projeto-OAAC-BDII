package persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entidades.Ocorrencia;



public class OcorrenciaDAO {
	
	public void salvar(Ocorrencia ocorrencia) {
		
		EntityManager manager = getEntityManager();
		
		try {
			manager.getTransaction().begin();
			manager.persist(ocorrencia);
			manager.getTransaction().commit();
		} catch(Exception erro) {
			manager.getTransaction().rollback();
			System.out.println(erro);
		} finally {
			manager.close();
		}
	}
	
	public void atualizar(Ocorrencia ocorrencia) {
		
		EntityManager manager = getEntityManager();
		
		try {
			manager.getTransaction().begin();
			manager.merge(ocorrencia);
			manager.getTransaction().commit();
		} catch(Exception erro) {
			manager.getTransaction().rollback();
			System.out.println(erro);
		} finally {
			manager.close();
		}
	}

	public void deletar(Ocorrencia ocorrencia) {
		
		EntityManager manager = getEntityManager();
		
		try {
			manager.getTransaction().begin();
			manager.remove(manager.find(Ocorrencia.class, ocorrencia.getID()));
			manager.getTransaction().commit();
		} catch(Exception erro) {
			manager.getTransaction().rollback();
			System.out.println(erro);
		} finally {
			manager.close();
		}	
	}
	
	public Ocorrencia pesquisar(Integer ID) {
		
		EntityManager manager = getEntityManager();
		
		Ocorrencia ocorrencia = null;
		
		try {
			ocorrencia = manager.find(Ocorrencia.class, ID);
		} catch(Exception erro) {
			System.out.println(erro);
		} finally {
			manager.close();
		}
		
		return ocorrencia;
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DB_miniprojeto");
		return factory.createEntityManager();
	}
}
