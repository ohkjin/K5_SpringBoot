package edu.pnu.dao;

import java.util.List;

import edu.pnu.domain.LogVO;

public interface LogDAO {
	public List<LogVO> getLogs();
	public LogVO getLog();
	public int makelog();
}
