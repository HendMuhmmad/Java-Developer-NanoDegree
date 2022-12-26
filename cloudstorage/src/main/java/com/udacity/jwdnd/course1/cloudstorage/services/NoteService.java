package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;

@Service
public class NoteService {
	
	private final NoteMapper noteMapper;
	private final UserService userService;

	public NoteService(NoteMapper noteMapper, UserService userService) {
		this.noteMapper = noteMapper;
		this.userService = userService;
	}
	
	public List<Note> getUserNotes(Integer userid){
		return this.noteMapper.getUserNotes(userid);
	}
	
	public int addNote(Note note) {
		return this.noteMapper.insertNote(note);
	}
	
	public void deleteNote(Integer noteid) {
		this.noteMapper.deleteNote(noteid);
	}
	
	public void updateNote(Note note) {
		this.noteMapper.updateNote(note);
	}
	
	public List<Note> getUserNotes(String username){
		User user = userService.getUser(username);
		if(user != null) {
			return getUserNotes(user.getUserId());
		}
		return null;
	}

}
