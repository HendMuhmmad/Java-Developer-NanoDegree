package com.udacity.jwdnd.course1.cloudstorage.controller;



import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
@RequestMapping("file")
public class FileController {
	private final FileService fileService;
	private final UserService userService;

	public FileController(FileService fileService, UserService userService) {
		this.fileService = fileService;
		this.userService = userService;
	}

	@PostMapping
	String uploadFile(Authentication authentication, @RequestParam("file") MultipartFile file,
			RedirectAttributes attributes) {
		String errorMessage = null;
		try {
			if (file.getSize() != 0) {
				Integer userid = userService.getUser(authentication.getName()).getUserId();
				if (!fileService.isFileNameAvailable(file.getOriginalFilename(),userid)) {
					int rowCount = fileService.insertFile(new File(null, file.getOriginalFilename(),
							file.getContentType(), Long.toString(file.getSize()),
							userid, file.getBytes()));
					if (rowCount < 0) {
						errorMessage = "Couldn't upload File. Please try again .";
					}

				} else {
					errorMessage = "Couldn't upload File. File name already exists Please try again .";
				}
			} else {
				errorMessage = "Please Select a File First. ";
			}

		} catch (Exception e) {
			errorMessage = "Couldn't upload File. Please try again .";
		}
		if (errorMessage == null) {
			attributes.addFlashAttribute("success", true);
		} else {
			attributes.addFlashAttribute("errorMessage", errorMessage);
		}
		return "redirect:/result";
	}

	@GetMapping("download")
	public ResponseEntity<byte[]> downloadFile(HttpServletResponse response, @RequestParam Integer fileid, Model model)
			throws Exception {
		try {
			File file = fileService.getFile(fileid);
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
					.contentType(MediaType.parseMediaType(file.getContenttype())).body(file.getFile());
		} catch (Exception e) {
			throw new Exception("Could not download File.");
		}
	}

	@PostMapping("deleteFile")
	String deleteNote(Authentication authentication, @RequestParam Integer fileid, RedirectAttributes attributes) {
		String errorMessage = null;
		try {
			this.fileService.deleteFile(fileid);
		} catch (Exception e) {
			errorMessage = "Couldn't delete file. Please try again .";
		}
		if (errorMessage == null) {
			attributes.addFlashAttribute("success", true);
		} else {
			attributes.addFlashAttribute("errorMessage", errorMessage);
		}
		return "redirect:/result";
	}

}
