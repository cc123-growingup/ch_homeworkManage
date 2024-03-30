package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Homework;
import model.MyUser;
import service.HomeworkService;

@Controller
@RequestMapping("/homework")
public class HomeworkController {
	@Autowired
	private HomeworkService homeworkService;
	@ModelAttribute
	public void checkLogin(HttpSession session) throws NoLoginException{
		if(session.getAttribute("userLogin") == null) {
			throw new NoLoginException();
		}
	}

	@RequestMapping("/selectAllHomeworkByPage")
	public String selectAllHomeworkByPage(Model model, int currentPage,  HttpSession session) {
		return homeworkService.selectAllHomeworkByPage(model, currentPage,  session);
	}

	@RequestMapping("/toAddHomework")
	public String toAddHomework(@ModelAttribute Homework homework) {
		return "addHomework";
	}

	@RequestMapping("/addHomework")
	public String addHomework(@ModelAttribute Homework homework, HttpServletRequest  request, String act, HttpSession session) throws IllegalStateException, IOException {
		return homeworkService.addHomework(homework, request, act, session);
	}

	@RequestMapping("/detail")
	public String detail(Model model, int id, String act) {
		
		return homeworkService.detail(model, id, act);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public String delete(int id) {
		return homeworkService.delete(id);
	}

	@RequestMapping("/loginOut")
	public String loginOut(Model model, HttpSession session) {
		return homeworkService.loginOut(model, session);
	}

	@RequestMapping("/toUpdatePwd")
	public String toUpdatePwd(Model model, HttpSession session) {
		return homeworkService.toUpdatePwd(model, session);
	}

	@RequestMapping("/updatePwd")
	public String updatePwd(@ModelAttribute MyUser myuser) {
		return homeworkService.updatePwd(myuser);
	}
	
	@Autowired
	private ServletContext servletContext; // 首先需要在Spring配置中声明ServletContextAware bean
	
	@RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
	public void downloadHomeworkFile(HttpServletResponse response,
	                                 @RequestParam("homework") String homework) throws IOException {
	    homeworkService.downloadFile(homework, response);
	}
}
