package codeacademy.bookingforum.app.image;

import codeacademy.bookingforum.app.user.auth.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class FileStorageService {
    @Autowired
    ImageRepo imageRepo;
    private String uploadDir = "/uploads/";

    void createDirectory(UserAuth user) {
        if (!new File(uploadDir+user.getUsername()).exists()) {
            if (!new File((uploadDir+user.getUsername())).mkdirs()); {
                System.out.println("File was not created!!!!!!");
            }
        }
        uploadDir = uploadDir + user.getUsername();
    }

    public void storeUserImage(MultipartFile file, List<String> tags, String description, UserAuth user) throws IOException {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        createDirectory(user);
        Image image = new Image(fileName, uploadDir, tags, description, ImageType.USER_AVATAR, user, null, file.getContentType());
        Image oldImage = imageRepo.findByType(ImageType.USER_AVATAR);
        if (oldImage != null) {
            imageRepo.delete(oldImage);
        }
        File newFile = new File(uploadDir+"/"+fileName);
        file.transferTo(newFile.getAbsoluteFile());

        System.out.println(newFile.getAbsolutePath());

        imageRepo.save(image);
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
