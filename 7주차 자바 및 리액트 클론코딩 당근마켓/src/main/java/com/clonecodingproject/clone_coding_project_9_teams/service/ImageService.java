package com.clonecodingproject.clone_coding_project_9_teams.service;

import com.clonecodingproject.clone_coding_project_9_teams.domain.*;
import com.clonecodingproject.clone_coding_project_9_teams.dto.ImageRequestDto;
import com.clonecodingproject.clone_coding_project_9_teams.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.*;

import java.io.File;
import java.util.*;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final String baseUrl = "http://whitewise.shop/upload";
    private final String ubuntuUrl = "/home/ubuntu/upload";

    private final String slide = "/";

    private final ImageRepository imageRepository;

    public List<ImageUrl> imageSave(List<ImageRequestDto> imageRequestDtos) {
        List<ImageUrl> imageUrls = new ArrayList<>();

        if (Optional.ofNullable(imageRequestDtos).isPresent()) {
            for (ImageRequestDto imageRequestDto : imageRequestDtos) {
                imageUrls.add(new ImageUrl(imageRequestDto));
            }
        }

        return imageRepository.saveAll(imageUrls);
    }

    @Transactional
    public String imagesUpload(MultipartHttpServletRequest request) {
        List<MultipartFile> files = request.getFiles("file");
        return null;
    }

    public void imageUpdateToPost(List<ImageUrl> imageUrls, Post post) {
        for (ImageUrl imageUrl : imageUrls) {
            imageUrl.updateToPost(post);
        }
    }

    public void imageAllDeleteByPostId(Long postId) {
        imageRepository.deleteByPostId(postId);
    }

    public List<ImageUrl> ImageOverallPatch(Long postId, List<ImageRequestDto> imageRequestDtos) {
        imageAllDeleteByPostId(postId);  //이미지 삭제
        return imageSave(imageRequestDtos); //이미지 추가
    }

    public String imageUpload(MultipartFile attcFile) {
        String rtnVal = "";
        try {
            String storedFileName = makeFileName(attcFile);

            File f = new File(ubuntuUrl);

            if (!f.exists()) {
                f.mkdirs();
            }

            attcFile.transferTo(new File(ubuntuUrl + slide + storedFileName));

            rtnVal += baseUrl + slide + storedFileName;
        } catch (Exception e) {
            throw new IllegalArgumentException("업로드에 실패했습니다.");
        }
        return rtnVal;
    }

    public String makeFileName(MultipartFile attcFile) {
        String attcFileNm = UUID.randomUUID().toString().replaceAll("-", "");
        String attcFileOriNm = attcFile.getOriginalFilename();
        String attcFileOriExt = attcFileOriNm.substring(attcFileOriNm.lastIndexOf("."));
        return attcFileNm + attcFileOriExt;
    }
}
