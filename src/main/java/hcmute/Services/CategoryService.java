package hcmute.Services;

import java.util.List;

import hcmute.DAO.CategoryDAO;
import hcmute.DAO.ICategoryDAO;
import hcmute.Entities.CategoryEntity;

public class CategoryService implements ICategoryService {
	ICategoryDAO cateDAO = new CategoryDAO();

	@Override
	public void insert(CategoryEntity category) {
		cateDAO.insert(category);
	}

	@Override
	public void update(CategoryEntity category) {
		cateDAO.update(category);
	}

	@Override
	public void delete(int cateid) throws Exception {
		cateDAO.delete(cateid);
	}

	@Override
	public CategoryEntity findById(int cateid) {
		return cateDAO.findById(cateid);
	}

	@Override
	public CategoryEntity findByCateName(String cateName) {
		return cateDAO.findByCateName(cateName);
	}

	@Override
	public List<CategoryEntity> findAll() {
		return cateDAO.findAll();
	}

	@Override
	public List<CategoryEntity> findAll(int page, int pagesize) {
		return cateDAO.findAll(page, pagesize);
	}

}
