package hcmute.Controllers;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Locale.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import hcmute.Entities.CategoryEntity;
import hcmute.Services.CategoryService;
import hcmute.Services.ICategoryService;
import hcmute.Utils.Constants;
import hcmute.Utils.UploadUtils;

@MultipartConfig
@WebServlet(urlPatterns = { "/admin-category", "/admin-category/create", "/admin-category/delete",
		"/admin-category/edit", "/admin-category/update", "/admin-category/reset" })
public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	ICategoryService cateService = new CategoryService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		CategoryEntity category = null;
		if(url.contains("create")) {
			req.getRequestDispatcher("/views/admin/category/add.jsp").forward(req, resp);
		} else if(url.contains("delete")) {
			delete(req, resp);
			category = new CategoryEntity();
			req.setAttribute("category", category);
		} else if(url.contains("edit")) {
			edit(req, resp);
		} else if(url.contains("reset")) {
			category = new CategoryEntity();
			req.setAttribute("category", category);
		}
		findAll(req, resp);
		req.setAttribute("tag", "cate");
		req.getRequestDispatcher("/views/admin/category/list.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		if(url.contains("create")) {
			insert(req, resp);
		} else if(url.contains("update")) {
			update(req, resp);
		} else if(url.contains("delete")) {
			delete(req, resp);
		} else if(url.contains("reset")) {
			req.setAttribute("category", new CategoryEntity());
		}
		findAll(req, resp);
		req.getRequestDispatcher("/views/admin/category/list.jsp").forward(req, resp);
	}

	private void update(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			CategoryEntity category = new CategoryEntity();
			BeanUtils.populate(category, req.getParameterMap());
			CategoryEntity oldCate = cateService.findById(category.getCategoryID());
			if(req.getPart("images").getSize() == 0) {
				category.setIcon(oldCate.getIcon());
			} else {
				if(oldCate.getIcon() != null) {
					String fileName = oldCate.getIcon();
					File file = new File(Constants.DIR + "\\category\\" + fileName);
					if(file.delete()) {
						System.out.println("Đã xóa thành công!");
					} else {
						System.out.println(Constants.DIR + "\\category\\" + fileName);
					}
				}
				String fileName = category.getCategoryName() + System.currentTimeMillis();
				category.setIcon(UploadUtils.processUpload("images", req, Constants.DIR + "\\category\\", fileName));
			}
			cateService.update(category);
			
			req.setAttribute("category", category);
			req.setAttribute("message", "Cập nhật thành công!");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	private void insert(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			CategoryEntity category = new CategoryEntity();
			BeanUtils.populate(category, req.getParameterMap());
			String fileName = category.getCategoryName() + System.currentTimeMillis();
			category.setIcon(UploadUtils.processUpload("images", req, Constants.DIR + "\\category\\", fileName));
			cateService.insert(category);
			req.setAttribute("message", "Đã thêm thành công!");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}
		
	}

	private void findAll(HttpServletRequest req, HttpServletResponse resp) {
		try {
			List<CategoryEntity> list = cateService.findAll();
			req.setAttribute("categories", list);
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}
		
	}

	private void edit(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String CategoryID = req.getParameter("categoryid");
			CategoryEntity category = cateService.findById(Integer.parseInt(CategoryID));
			req.setAttribute("category", category);
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}
		
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String CategoryID = req.getParameter("categoryid");
			cateService.delete(Integer.parseInt(CategoryID));
			req.setAttribute("message", "Đã xóa thành công!");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}
		
	}
	
	public static void main(String[] args) {
		ICategoryService cateService = new CategoryService();
		List<CategoryEntity> list = cateService.findAll();
		System.out.println(list);
	}
	
}
