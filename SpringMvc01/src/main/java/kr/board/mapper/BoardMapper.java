package kr.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import kr.board.entity.Board;

@Mapper //- Mybatis API
public interface BoardMapper {
	public List<Board> getLists(); // 전체리스트
	public void boardInsert(Board vo);
	public Board boardContent(int idx);
	public void boardDelete(int idx);
	public void boardUpdate(Board vo);
	
	// 어노테이션을 이용한 쿼리 수행가능. 단, mapper.xml에는 동일한 쿼리문이 있으면 안됨
	@Update("update myboard set count=count+1 where idx=#{idx}")
	public void boardCount(int idx);
}
