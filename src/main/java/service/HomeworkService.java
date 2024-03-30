package service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import model.Homework;
import model.MyUser;

public interface HomeworkService {
	public String selectAllHomeworkByPage(Model model, int currentPage,  HttpSession session);
	public String addHomework(Homework homework, HttpServletRequest  request, String act, HttpSession session) throws IllegalStateException, IOException;
	public String detail(Model model, int id, String act);
	public String delete(int id);
	public String loginOut(Model model, HttpSession session);
	public String toUpdatePwd(Model model, HttpSession session);
	public String updatePwd(MyUser myuser);
	public void downloadFile(String homework, HttpServletResponse response) throws IOException;
}
