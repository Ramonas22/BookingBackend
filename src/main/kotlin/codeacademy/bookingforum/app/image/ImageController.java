package codeacademy.bookingforum.app.image;

import codeacademy.bookingforum.app.configuration.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/image")
public class ImageController {

    @Autowired
    ImageService imageService;

    // Upload image (params include File and ImageDto)
    @Secured({"ROLE_ADMIN", "ROLE_SELLER", "ROLE_USER"})
    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseObject upload(@RequestParam("file") MultipartFile file, @RequestParam("body") String imagestring, WebRequest request) {
        return imageService.upload(file, imagestring, request);
    }

    // Get avatar of provided user
    @GetMapping(value = "/avatar/{id}", produces = MediaType.IMAGE_JPEG_VALUE) // ID of UserAuth
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody byte[] getAvatar(@PathVariable("id") Long id) throws IOException {
        return imageService.getAvatar(id);
    }

    // Get a list of image id's that belong to a gallery of provided SELLER
    @GetMapping("/gallery/{id}") // User id (ROLE_SELLER)
    public List<Long> getGallery(@PathVariable("id") Long id) {
        return imageService.getGallery(id);
    }

    // Get file by id
    @GetMapping(value = "/get/{id}", produces = MediaType.IMAGE_JPEG_VALUE) // Image id
    public @ResponseBody byte[] getById(@PathVariable("id") Long id) throws IOException {
        return imageService.getById(id);
    }

    // Get random image file that belongs to provided user's (seller's) gallery
    @GetMapping(value = "/preview/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] randomGalleryPicture(@PathVariable("id") Long id) throws IOException {
        return imageService.randomGalleryPicture(id);
    }
}
