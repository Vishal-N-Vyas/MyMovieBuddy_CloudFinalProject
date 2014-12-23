package com.mkyong.customer.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mkyong.customer.validator.FileUploadValidator;
import com.mkyong.customer.model.Customer;
import com.mkyong.customer.model.FileUpload;
import com.mkyong.db.DatabaseWrapper;

@Controller
@RequestMapping("/fileupload")
public class FileUploadController{
	
	FileUploadValidator fileUploadValidator;
	public String key = "XXXX";
	
	@Autowired
	public FileUploadController(FileUploadValidator fileUploadValidator){
		this.fileUploadValidator = fileUploadValidator;
	}
 
	@RequestMapping(value="/upload", headers = "content-type=multipart/*", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file, HttpSession session){
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                System.out.println("Bytes are + " + bytes.length + "User name = " + session.getAttribute("userName"));
                //DatabaseWrapper.insertPic((String)session.getAttribute("userName"), file);
                FileOutputStream out = new FileOutputStream(session.getAttribute("userName")+".jpg");
                out.write(bytes);
                String current = new java.io.File( "." ).getCanonicalPath();
                System.out.println("Current dir:"+current);
                return "FileUploadSuccess";//return "You successfully uploaded " + name + "!";
            } catch (Exception e) {
            	return "FileUploadSuccess";//return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
        	return "FileUploadSuccess";//return "You failed to upload " + name + " because the file was empty.";
        }
    }
	
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model){
		System.out.println("Get called");
		//FileUpload fileupload = new FileUpload();
		//model.addAttribute("fileupload", fileupload);
		
		//return form view
		return "FileUploadForm";
	}

	/*@Override
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
		throws ServletException {
		
		// Convert multipart object to byte[]
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		
	}*/
	
}