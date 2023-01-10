package codeacademy.bookingforum.app.image;

import codeacademy.bookingforum.app.configuration.ResponseObject;
import codeacademy.bookingforum.app.ecxeption.image.ImageNotFoundException;
import codeacademy.bookingforum.app.ecxeption.image.UnsupportedImageFormatException;
import codeacademy.bookingforum.app.ecxeption.user.UserNotFoundException;
import codeacademy.bookingforum.app.user.auth.UserAuth;
import codeacademy.bookingforum.app.user.auth.UserAuthRepo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageRepo imageRepo;
    @Autowired
    ImageMapper imageMapper;
    @Autowired
    UserAuthRepo userAuthRepo;
    @Autowired
    FileStorageService storageService;

    public ImageDto findById(Long id) {
        Image image = imageRepo.findById(id).orElse(null);
        return imageMapper.toDto(image);
    }

    public List<ImageDto> findAllPhotos() {
        List<Image> images = (List<Image>) imageRepo.findAll();
        return imageMapper.toDto(images);
    }

    public ResponseObject upload(MultipartFile file, String imageString, WebRequest request) {
        Gson gson = new Gson();
        ImageDto imageDto = gson.fromJson(imageString, ImageDto.class);

        UserAuth user = userAuthRepo.findByUsername(imageDto.getUsername());
        if (user == null) {
            throw new UserNotFoundException("File owner not defined.");
        }
        try {
            storageService.storeUserAvatar(file, imageDto, user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseObject(Collections.singletonList("File uploaded successfully."), HttpStatus.CREATED, request);
    }


//    public ImageDto upload(ImageDto imageDto) {
//
//        if(imageDto == null) {
//            throw new InvalidRequestException("Invalid request. Request body cannot be empty.");
//        } else if(imageDto.getUsername() == null) {
//            throw new UserNotFoundException("Invalid request. No user provided.");
//        }
//        checkSupportedExtension(imageDto.getExtension());
//
//        UserAuth user = userAuthRepo.findByUsername(imageDto.getUsername());
//        if(user == null) {
//            throw new UserNotFoundException("User "+ imageDto.getUsername()+" does not exist.");
//        }
//
//        Image image = imageMapper.fromDto(imageDto);
//        imageRepo.save(image);
//        return imageMapper.toDto(image);
//    }

    public String deletePhoto(Long id) {
        if (imageRepo.existsById(id)) {
            imageRepo.deleteById(id);
            return "Photo with id " + id + " deleted";
        } else {
            return "Photo with id " + id + " does not exist";
        }
    }

    void checkSupportedExtension(String extension) {
        if (extension == null) {
            throw new ImageNotFoundException("Invalid request. No photo provided.");
        }
        extension = extension.toLowerCase();
        if(extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")) {
            throw new UnsupportedImageFormatException("This type of file is not supported. We support these file extensions: .jpg .jpeg .png");
        }
    }
}
