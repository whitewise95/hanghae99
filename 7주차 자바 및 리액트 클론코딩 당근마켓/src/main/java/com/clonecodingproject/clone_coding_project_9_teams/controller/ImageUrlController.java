package com.clonecodingproject.clone_coding_project_9_teams.controller;

import com.clonecodingproject.clone_coding_project_9_teams.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

@RequiredArgsConstructor
@Controller
public class ImageUrlController {

    private final ImageService imageService;

    @ResponseBody
    @PostMapping("/image")
    public String imageUpload(@RequestParam("image") MultipartFile attcFile) {
        return imageService.imageUpload(attcFile);
    }

    @ResponseBody
    @PostMapping("/images")
    public String imagesUpload(MultipartHttpServletRequest request) {
        return imageService.imagesUpload(request);
    }
}
