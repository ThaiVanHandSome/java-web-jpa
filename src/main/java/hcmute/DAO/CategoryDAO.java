package hcmute.DAO;

import java.util.List;
import java.util.Locale.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import hcmute.Entities.CategoryEntity;
import hcmute.JPAConfig.JPAConfig;

public class CategoryDAO implements ICategoryDAO {

	@Override
	public void insert(CategoryEntity category) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(category);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void update(CategoryEntity category) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(category);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void delete(int cateid) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Category category = enma.find(Category.class, cateid);
			if(category != null) {
				enma.remove(category);
			}
		} catch (Exception e) {
			throw new Exception("Not found!");
		} finally {
			enma.close();
		}
	}

	@Override
	public CategoryEntity findById(int cateid) {
		EntityManager enma = JPAConfig.getEntityManager();
		CategoryEntity category = enma.find(CategoryEntity.class, cateid);
		return category;
	}

	@Override
	public List<CategoryEntity> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<CategoryEntity> query = enma.createNamedQuery("Category.findAll", CategoryEntity.class);
		return query.getResultList();
	}

	@Override
	public List<CategoryEntity> findAll(int page, int pagesize) {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<CategoryEntity> query = enma.createNamedQuery("Category.findAll", CategoryEntity.class);
		query.setFirstResult(page * pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}

	@Override
	public CategoryEntity findByCateName(String cateName) {
		// TODO Auto-generated method stub
		return null;
	}
	

}