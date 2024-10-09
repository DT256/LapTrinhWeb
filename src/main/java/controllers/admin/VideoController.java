package controllers.admin;


import entity.Category;
import entity.Video;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import services.ICategoryService;
import services.IVideoService;
import services.impl.CategoryServiceImpl;
import services.impl.VideoServiceImpl;
import utils.Constant;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
@WebServlet(urlPatterns = {"/admin/videos", "/admin/video/add","/admin/video/insert",
        "/admin/video/edit", "/admin/video/update", "/admin/video/delete"})
public class VideoController extends HttpServlet {
    IVideoService videoService = new VideoServiceImpl();
    ICategoryService categoryService = new CategoryServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String url = req.getRequestURI();
        if (url.contains("/admin/videos")) {
            List<Video> list = videoService.findAll();
            req.setAttribute("listVideo", list);
            req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
        }else if (url.contains("/admin/video/add")) {
            List<Category> list = categoryService.findAll();
            req.setAttribute("listCate", list);
            req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);
        }else if (url.contains("/admin/video/edit")) {
            int id  = Integer.parseInt(req.getParameter("id"));
            Video video = videoService.findById(id);
            req.setAttribute("video", video);
            List<Category> list = categoryService.findAll();
            req.setAttribute("listCate", list);
            req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
        }
        else if (url.contains("/admin/video/delete")) {
            int id  = Integer.parseInt(req.getParameter("id"));
            try {
                videoService.delete(id);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String url = req.getRequestURI();
        Video video = new Video();

        String fname = "";
        String uploadPath = Constant.ULOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);

        if(url.contains("/admin/video/insert")){
            //lay du lieu tu form
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            int categoryid = Integer.parseInt(req.getParameter("categoryid"));
            int active = Integer.parseInt(req.getParameter("active"));
            String poster = req.getParameter("poster");
            // dua du lieu vao model
            video.setTitle(title);
            video.setDescription(description);
            video.setCategory(categoryService.findById(categoryid));
            video.setActive(active);
            video.setViews(0);
            //Xu li anh
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            try{
                Part part = req.getPart("poster1");
                // part.write(uploadPath + "categories/" +fimename);
                if(part.getSize() > 0){
                    fname = this.getname(part);
                    part.write(uploadPath + File.separator + fname);
                    video.setPoster(fname);
                } else if (poster != null) {
                    video.setPoster(poster);
                }
                else {
                    video.setPoster("avatar.jpg");
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            // goi phuong thuc insert trong service
            videoService.insert(video);
            // quay ve view
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        }else if(url.contains("/admin/video/update")){
            //lay du lieu tu form
            int id  = Integer.parseInt(req.getParameter("videoId"));
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            int categoryid = Integer.parseInt(req.getParameter("categoryid"));
            int active = Integer.parseInt(req.getParameter("active"));
            String poster = req.getParameter("poster");

            video = videoService.findById(id);
            // dua du lieu vao model
            video.setTitle(title);
            video.setDescription(description);
            video.setCategory(categoryService.findById(categoryid));
            video.setActive(active);
            video.setViews(0);
            //Xu li anh
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            try{
                Part part = req.getPart("poster1");
                // part.write(uploadPath + "categories/" +fimename);
                if(part.getSize() > 0){
                    fname = this.getname(part);
                    part.write(uploadPath + File.separator + fname);
                    video.setPoster(fname);
                } else if (!poster.isEmpty()) {
                    video.setPoster(poster);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            // goi phuong thuc insert trong service
            videoService.update(video);
            // quay ve view
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        }
    }

    public String getname(Part part){
        String fname ="";
        String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        int index = filename.lastIndexOf(".");
        String ext = filename.substring(index+ 1);
        fname = System.currentTimeMillis() + "." + ext;
        return fname;
    }
}
