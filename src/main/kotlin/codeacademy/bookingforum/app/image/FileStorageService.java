package codeacademy.bookingforum.app.image;

import codeacademy.bookingforum.app.ecxeption.image.DirectoryCreationException;
import codeacademy.bookingforum.app.user.auth.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Service
public class FileStorageService {
    @Autowired
    ImageRepo imageRepo;
    private void defaultPath(String username) {
        path = "/var/www/irenteye.com/html/uploads/"+username;
    }
    private String path;

    public void storeUserAvatar(MultipartFile file, ImageDto imageDto, UserAuth user) throws IOException {
        defaultPath(user.getUsername());
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        createDirectoryIfNotFound(path);

        File newFile = new File(path+"/"+fileName);
        Image oldImage = imageRepo.findOneByTypeAndUser(ImageType.USER_AVATAR, user);

        Image image = new Image(fileName, path+"/"+fileName, imageDto.getTags(), imageDto.getDescription(), ImageType.USER_AVATAR, user, null, file.getContentType());
        if (oldImage != null) {
            image.setId(oldImage.getId());

        }
        image.setTags(imageDto.getTags());
        imageRepo.save(image);

        file.transferTo(newFile.getAbsoluteFile());
    }

    public void storeImage(MultipartFile file, ImageDto imageDto, UserAuth user) throws IOException {
        defaultPath(user.getUsername());

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        createDirectoryIfNotFound(path);

        File newFile = new File(path+"/"+fileName);
        file.transferTo(newFile.getAbsoluteFile());

        Image image = new Image(fileName, path+"/"+fileName, imageDto.getTags(), imageDto.getDescription(), ImageType.SELLER_GALLERY, user, null, file.getContentType());
        imageRepo.save(image);
    }


    public Image storePostImage(MultipartFile file, ImageDto imageDto, UserAuth user) throws IOException {
        defaultPath(user.getUsername());

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        createDirectoryIfNotFound(path);

        File newFile = new File(path+"/"+fileName);
        file.transferTo(newFile.getAbsoluteFile());

        Image image = new Image(fileName, path+"/"+fileName, imageDto.getTags(), imageDto.getDescription(), ImageType.SELLER_GALLERY, user, null, file.getContentType());
        return imageRepo.save(image);
    }

    // Set path variable based on current user
    void createDirectoryIfNotFound(String path) {
        if (!new File(path).exists()) {
            if (!new File(path).mkdirs()) {
                throw new DirectoryCreationException("Failed to create directory for your images. Please contact an Admin.");
            }
        }
    }
}
