package com.udacity.jwdnd.course1.cloudstorage.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;


@Mapper
public interface CredentialMapper {
	@Insert("INSERT INTO CREDENTIALS (url,username,key,password,userid) VALUES (#{url},#{username},#{key},#{password},#{userid})")
	@Options(useGeneratedKeys = true, keyProperty = "credentialid")
	int insertCredential(Credential credentil);
	
	@Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid}")
	List<Credential> getUserCredentials(Integer userid);
	
	@Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialid}")
	Credential getCredential(Integer credentialid);
	
	@Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialid}")
	void deleteCredential(Integer credentialid);
	
	@Update("UPDATE CREDENTIALS SET url = #{url}, username=#{username}, key=#{key}, password=#{password},userid = #{userid} WHERE credentialid=#{credentialid}")
	void updateCredential(Credential credential);

}
