package com.pabhinav;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @author pabhinav
 */
@Controller
public class FileUploadController {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @RequestMapping(method = RequestMethod.GET, value = "/upload")
    public String provideUploadInfo(Model model){

        File rootApplication = new File(Application.ROOT);
        List<String> fileNames = Arrays.asList(rootApplication.list());
        model.addAttribute("files", fileNames);

        return "uploadForm";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    public String handleFileUpload(@RequestParam("name") String name, @RequestParam("file")MultipartFile multipartFile, RedirectAttributes redirectAttributes){
        if (name.contains("/") || name.contains("\\") ) {
            redirectAttributes.addFlashAttribute("message", "Folder separators '/' or '\' not allowed");
            return "redirect:upload";
        }
        if(name.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "File name is empty, name your uploaded file");
            return "redirect:upload";
        }
        if(!multipartFile.isEmpty()){
            try{
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(Application.ROOT+ "/"+ name)));
                FileCopyUtils.copy(multipartFile.getInputStream(), stream);
                stream.close();
                redirectAttributes.addFlashAttribute("SuccessMessage", "You Successfully upload " + name + " !");
            } catch (Exception e){
                redirectAttributes.addFlashAttribute("message", "You failed to upload " + name + " => " + e.getMessage());
            }
        } else{
            redirectAttributes.addFlashAttribute("message" , "You Failed to upload " + name + " because the file was empty");
        }
        return "redirect:upload";
    }
}
