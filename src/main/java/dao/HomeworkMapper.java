package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import model.Homework;
import model.MyUser;
import po.HomeworkTable;

public interface HomeworkMapper {
	public List<Map<String, Object>> selectAllHomework(int uid);
	public List<Map<String, Object>> selectAllHomeworkByPage(@Param("startIndex") int startIndex, @Param("perPageSize") int perPageSize, @Param("uid") int uid);
	public int addHomework(Homework homework);
	public int updateHomework(Homework homework);
	public HomeworkTable selectAHomework(int id);
	public int deleteAHomework(int id);
	public int updatePwd(MyUser myuser);

}
