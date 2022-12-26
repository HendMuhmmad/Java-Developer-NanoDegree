package com.udacity.jwdnd.course1.cloudstorage.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
@Mapper
public interface FileMapper {
	@Insert("INSERT INTO FILES (filename,contenttype,filesize,userid,file) VALUES (#{filename},#{contenttype},#{filesize},#{userid},#{file})")
	@Options(useGeneratedKeys = true, keyProperty = "fileid")
	int insertFile(File file);
	
	@Select("SELECT * FROM FILES WHERE filename = #{filename}")
	File getFile(String filename);
	
	@Select("SELECT * FROM FILES WHERE fileid = #{fileid}")
	File getFileById(Integer fileid);
	
	@Select("SELECT * FROM FILES WHERE userid = #{userid}")
	List<File> getUserFiles(Integer userid);
	
	@Delete("DELETE FROM FILES WHERE fileid = #{fileid}")
	void deleteFile(Integer fileid);
	
	

}
