package codeacademy.bookingforum.app.image;

import codeacademy.bookingforum.app.ecxeption.image.DirectoryCreationException;
import codeacademy.bookingforum.app.user.auth.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

@Service
public class FileStorageService {
    @Autowired
    ImageRepo imageRepo;
    @Autowired
    ImageMapper imageMapper;

    void createDirectoryIfNotFound(String path) {
        if (!new File(path).exists()) {
            if (!new File(path).mkdirs()) {
                throw new DirectoryCreationException("Failed to create directory for your images. Please contact an Admin.");
            }
        }
    }

    public void storeUserAvatar(MultipartFile file, ImageDto imageDto, UserAuth user) throws IOException {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String path = "/var/www/irenteye.com/html/uploads/"+user.getUsername().trim();
        createDirectoryIfNotFound(path);

        File newFile = new File(path+"/"+fileName);
        Image oldImage = imageRepo.findByType(ImageType.USER_AVATAR);
        if (oldImage != null) {
            Image newImage = imageMapper.fromDto(imageDto);
            newImage.setId(oldImage.getId());
            newImage.setType(ImageType.USER_AVATAR);
            imageRepo.save(newImage);
            file.transferTo(newFile.getAbsoluteFile());
        } else {
            file.transferTo(newFile.getAbsoluteFile());
            Image image = new Image(fileName, path+"/"+fileName, imageDto.getTags(), imageDto.getDescription(), ImageType.USER_AVATAR, user, null, file.getContentType());
            imageRepo.save(image);
        }
    }
//    public Image storePostImage(MultipartFile file, List<String> tags, String description, UserAuth user) throws IOException {
//        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
//        Image image = new Image(fileName, file.getBytes(), tags, description, ImageType.FORUM, user, null, file.getContentType());
//
//        return imageRepo.save(image);
//    }
//    public Image storeSellerImage(MultipartFile file, List<String> tags, String description, UserAuth user) throws IOException {
//        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
//        Image image = new Image(fileName, file.getBytes(), tags, description, ImageType.SELLER_GALLERY, user, null, file.getContentType());
//
//        return imageRepo.save(image);
//    }

    public Image getImageByUser(UserAuth user) {
        return imageRepo.findByUser(user);
    }

    public List<Image> getSellerGallery(UserAuth user) {
        return imageRepo.findAllByUserAndType(user, ImageType.SELLER_GALLERY);
    }
}
