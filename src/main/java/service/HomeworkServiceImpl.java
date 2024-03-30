package service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import util.MD5Util;

import dao.HomeworkMapper;
import model.Homework;
import model.MyUser;
import po.HomeworkTable;
import po.MyUserTable;
import javax.servlet.ServletContext;

@Service
public class HomeworkServiceImpl implements HomeworkService{
	@Autowired
	private HomeworkMapper homeworkMapper;
	/**
	 * 查询、修改查询、删除查询、分页查询
	 */
	@Override
	public String selectAllHomeworkByPage(Model model, int currentPage,  HttpSession session) {
		MyUserTable mut = (MyUserTable)session.getAttribute("userLogin");
		List<Map<String, Object>> allUser = homeworkMapper.selectAllHomework(mut.getId());
	    //共多少个用户
	  	int totalCount = allUser.size();
	  	//计算共多少页
	  	int pageSize = 5;
	  	int totalPage = (int)Math.ceil(totalCount*1.0/pageSize);
	  	List<Map<String, Object>> homeworkByPage = homeworkMapper.selectAllHomeworkByPage((currentPage-1)*pageSize, pageSize, mut.getId());
	  	model.addAttribute("allHomework", homeworkByPage);
	    model.addAttribute("totalPage", totalPage);
	    model.addAttribute("currentPage", currentPage);
		return "main";
	}
	/**
	 * 添加与修改作业
	 */
	@Override
	public String addHomework(Homework homework, HttpServletRequest  request, String act, HttpSession session) throws IllegalStateException, IOException {
	    MultipartFile myfile = homework.getHomework_file();
	    // 如果选择了上传文件，将文件上传到指定的目录static/multifile，并使用原始文件名
	    if (!myfile.isEmpty()) {
	        // 上传文件路径（生产环境）
	        String path = request.getServletContext().getRealPath("/static/multifile/");
	        
	        // 获得上传文件原名
	        String fileName = myfile.getOriginalFilename();

	        // 检查并创建目录（如果需要）
	        File directory = new File(path);
	        if (!directory.exists()) {
	            directory.mkdirs();
	        }

	        // 创建目标文件对象（使用原始文件名）
	        File targetFile = new File(directory, fileName);

	        // 将上传文件保存到目标文件中
	        myfile.transferTo(targetFile);

	        // 直接将原始文件名存到homework对象中，添加时使用
	        homework.setHomework(fileName);
	    }

	    if ("add".equals(act)) {
	        MyUserTable mut = (MyUserTable) session.getAttribute("userLogin");
	        homework.setUser_id(mut.getId());
	        int n = homeworkMapper.addHomework(homework);
	        if (n > 0) { // 成功
	            return "redirect:/homework/selectAllHomeworkByPage?currentPage=1";
	        }
	        // 失败
	        return "addHomework";
	    } else { // 修改
	        // 注意：如果原始文件名已存在数据库中，且未更改文件，则无需再次存储到homework对象中
	        int n = homeworkMapper.updateHomework(homework);
	        if (n > 0) { // 成功
	            return "redirect:/homework/selectAllHomeworkByPage?currentPage=1";
	        }
	        // 失败
	        return "updateHomework";
	    }
	}
	/**
	 * 打开详情与修改页面
	 */
	@Override
	public String detail(Model model, int id, String act) {
		HomeworkTable ct = homeworkMapper.selectAHomework(id);
		ct.setHomework(ct.getHomework());
		
		model.addAttribute("homework", ct);
		if("detail".equals(act)) {
			return "homeworkDetail";
		}else {
			return "updateHomework";
		}
	}
	/**
	 * 删除
	 */
	@Override
	public String delete(int id) {
		homeworkMapper.deleteAHomework(id);
		return "/homework/selectAllHomeworkByPage?currentPage=1";
	}
	/**
	 * 安全退出
	 */
	@Override
	public String loginOut(Model model, HttpSession session) {
		session.invalidate();
		model.addAttribute("myUser", new MyUser());
		return "login";
	}
	/**
	 * 打开修改密码页面
	 */
	@Override
	public String toUpdatePwd(Model model, HttpSession session) {
		MyUserTable mut = (MyUserTable)session.getAttribute("userLogin");
		model.addAttribute("myuser", mut);
		return "updatePwd";
	}
	/**
	 * 修改密码
	 */
	@Override
	public String updatePwd(MyUser myuser) {
		//将明文变成密文
		myuser.setUpwd(MD5Util.MD5(myuser.getUpwd()));
		homeworkMapper.updatePwd(myuser);
		return "login";
	}
	
	    @Override
	    public void downloadFile(String homework, HttpServletResponse response) throws IOException {
	        // 文件存储路径，可根据实际情况调整
	        String filePath = "/static/multifile/" + homework;

	        File file = new File(filePath);
	        if (file.exists()) {
	            response.setContentType(getServletContext().getMimeType(file.getName()));
	            response.setContentLengthLong(file.length());
	            response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

	            Files.copy(file.toPath(), response.getOutputStream());
	            response.flushBuffer();
	        } else {
	            // 处理文件不存在的情况
	            response.sendError(HttpServletResponse.SC_NOT_FOUND, "The requested file was not found.");
	        }
	    }
	    // 获取ServletContext的方式取决于你的环境配置，这里仅作演示
	    // 一般而言，可以通过@Component注解的类配合ServletContextAware接口来获取
	    private ServletContext getServletContext() {
	        // 这里需要实现获取ServletContext的方式，例如从ApplicationContext中获取
	        // 或者通过其他方式注入ServletContext
	        throw new UnsupportedOperationException("Not implemented yet.");
	    }
}
