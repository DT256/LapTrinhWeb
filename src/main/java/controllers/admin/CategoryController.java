package controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import models.CategoryModel;
import services.ICategoryService;
import services.impl.CategoryServiceImpl;
import utils.Constant;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB

@WebServlet(urlPatterns = {"/admin/categories", "/admin/category/add", "/admin/category/insert", "/admin/category/edit" })
public class CategoryController extends HttpServlet {
    ICategoryService categoryService = new CategoryServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String url = req.getRequestURI();
        if(url.contains("/admin/categories")){
            List<CategoryModel> list = categoryService.findAll();
            req.setAttribute("listCategory", list);
            req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
        } else if (url.contains("/admin/category/add")) {
            req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
        }
        else if (url.contains("/admin/category/edit")) {
            int id  = Integer.parseInt(req.getParameter("id"));
            CategoryModel category = categoryService.findById(id);
            req.setAttribute("cate", category);
            req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String url = req.getRequestURI();
        CategoryModel category = new CategoryModel();

        if(url.contains("/admin/category/insert")){
            //lay du lieu tu form
            String categoryname = req.getParameter("categoryname");
            int status = Integer.parseInt(req.getParameter("status"));
            String image = req.getParameter("image");

            // dua du lieu vao model
            category.setCategoryname(categoryname);
            category.setStatus(status);
            //Xu li anh
            String fname = "";
            String uploadPath = Constant.ULOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            try{
                Part part = req.getPart("image1");
                // part.write(uploadPath + "categories/" +fimename);
                if(part.getSize() > 0){
                    String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    int index = filename.lastIndexOf(".");
                    String ext = filename.substring(index+ 1);
                    fname = System.currentTimeMillis() + "." + ext;
                    part.write(uploadPath + File.separator + fname);
                    category.setImage(fname);
                    System.out.println(fname);
                } else if (image != null) {
                    category.setImage(image);
                }
                else {
                    category.setImage("avatar.jpg");
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            // goi phuong thuc insert trong service
            categoryService.insert(category);
            System.out.println(category);
            // quay ve view
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
        }
    }
}
