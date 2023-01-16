package codeacademy.bookingforum.app.image;

import codeacademy.bookingforum.app.configuration.ResponseObject;
import codeacademy.bookingforum.app.ecxeption.global.UnsatisfiedExpectationException;
import codeacademy.bookingforum.app.ecxeption.image.ImageNotFoundException;
import codeacademy.bookingforum.app.ecxeption.image.UnsupportedImageFormatException;
import codeacademy.bookingforum.app.ecxeption.user.UserNotFoundException;
import codeacademy.bookingforum.app.user.auth.UserAuth;
import codeacademy.bookingforum.app.user.auth.UserAuthRepo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class ImageService {
    @Autowired
    ImageRepo imageRepo;
    @Autowired
    UserAuthRepo userAuthRepo;
    @Autowired
    FileStorageService storageService;

    // Upload an image (takes File and ImageDto)
    public ResponseObject upload(MultipartFile file, String imageString, WebRequest request) {
        Gson gson = new Gson();
        ImageDto imageDto = gson.fromJson(imageString, ImageDto.class);

        UserAuth user = userAuthRepo.findByUsername(imageDto.getUsername());
        if (user == null) {
            throw new UserNotFoundException("File owner not defined.");
        }
        checkAuth(user);
        ImageType type = ImageType.parse(imageDto.getType());

        try {
            if (type == ImageType.USER_AVATAR) {
                storageService.storeUserAvatar(file, imageDto, user);
            } else if (type == ImageType.SELLER_GALLERY || type == ImageType.POST) {
                storageService.storeImage(file, imageDto, user);
            } else if (type == ImageType.UNDEFINED) {
                throw new UnsupportedImageFormatException("Image type can not be UNDEFINED!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new ResponseObject(Collections.singletonList("File uploaded successfully."), HttpStatus.CREATED, request);
    }

    // Get profile picture of provided user
    public byte[] getAvatar(Long id) throws IOException {
        UserAuth user = userAuthRepo.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("User with id "+id+" does not exist!");
        }

        Image image = imageRepo.findOneByTypeAndUser(ImageType.USER_AVATAR, user);
        if (image == null) {
            return null;
        }

        return Files.readAllBytes(Paths.get(image.getLocation()));
    }

    // Get a list of image id's that belong to a gallery of provided SELLER
    public List<Long> getGallery(Long id) {
        UserAuth user = userAuthRepo.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("User with id "+id+" does not exist!");
        }

        List<Long> idList = new ArrayList<>();
        imageRepo.findAllByUserAndType(user, ImageType.SELLER_GALLERY).forEach(image -> idList.add(image.getId()));

        return idList;
    }

    // Find and return one image by provided id
    public byte[] getById(Long id) throws IOException {
        Image image = imageRepo.findById(id).orElse(null);
        if (image == null) {
            throw new ImageNotFoundException("Image with id "+id+" does not exist!");
        }
        return Files.readAllBytes(Paths.get(image.getLocation()));
    }

    // Return 1 random picture from provided seller's gallery
    public byte [] randomGalleryPicture(Long id) throws IOException {
        String path = imageRepo.findOneBySellerId(id);
        if (path == null) {
            throw new ImageNotFoundException("No image found.");
        }
        return Files.readAllBytes(Paths.get(path));
    }

    // Validate authorization, check whether user is requesting his own resource
    void checkAuth(UserAuth user) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!Objects.equals(userDetails.getUsername(), user.getUsername())) {
            throw new UnsatisfiedExpectationException("Requesting and requested user do not match!");
        }
    }
}
