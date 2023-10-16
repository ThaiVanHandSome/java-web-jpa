package hcmute.Services;

import java.util.List;

import hcmute.Entities.CategoryEntity;

public interface ICategoryService {
	void insert(CategoryEntity category);
	void update(CategoryEntity category);
	void delete(int cateid) throws Exception;
	CategoryEntity findById(int cateid);
	List<CategoryEntity> findByCateName(String cateName);
	List<CategoryEntity> findAll();
	List<CategoryEntity> findAll(int page, int pagesize);
	int count();
}
