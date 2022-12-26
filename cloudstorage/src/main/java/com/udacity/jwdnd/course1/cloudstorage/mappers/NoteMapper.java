package com.udacity.jwdnd.course1.cloudstorage.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;

@Mapper
public interface NoteMapper {
	@Insert("INSERT INTO NOTES (notetitle,notedescription,userid) VALUES (#{notetitle},#{notedescription},#{userid})")
	@Options(useGeneratedKeys = true, keyProperty = "noteid")
	int insertNote(Note note);
	
	@Select("SELECT * FROM NOTES WHERE userid = #{userid}")
	List<Note> getUserNotes(Integer userid);
	
	@Delete("DELETE FROM NOTES WHERE noteid = #{noteid}")
	void deleteNote(Integer noteid);
	
	@Update("UPDATE NOTES SET notetitle = #{notetitle}, notedescription=#{notedescription}, userid=#{userid} WHERE noteid=#{noteid}")
	void updateNote(Note note);

}
