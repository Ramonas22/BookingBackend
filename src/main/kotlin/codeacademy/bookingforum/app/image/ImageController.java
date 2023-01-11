package codeacademy.bookingforum.app.image;

import codeacademy.bookingforum.app.configuration.ResponseObject;
import jakarta.annotation.security.RolesAllowed;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/image")
public class ImageController {

    @Autowired
    ImageService imageService;

    @Secured({"ROLE_ADMIN", "ROLE_SELLER", "ROLE_USER"})
    @PostMapping("/avatar")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseObject file(@RequestParam("file") MultipartFile file, @RequestParam("body") String imageString, WebRequest request) {
        return imageService.upload(file, imageString, request);
    }

    @Secured({"ROLE_ADMIN", "ROLE_SELLER", "ROLE_USER"})
    @PostMapping("/gallery")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseObject galleryImage(@RequestParam("file") MultipartFile file, @RequestParam("body") String imageString, WebRequest request) {
        return imageService.galleryImage(file, imageString, request);
    }

    @GetMapping(value = "/get/{username}/{filename}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getByLocation(@PathVariable("username") String username, @PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get("/var/www/irenteye.com/html/uploads/"+username+"/"+filename));
    }


    @GetMapping(value = "/profile", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] profilePicture(@RequestBody ImageDto imageDto) throws IOException {

        //InputStream in = getClass().getResourceAsStream(imageDto.getLocation()+imageDto.getUsername()+imageDto.getName());

        System.out.println(imageDto.getLocation());

        return Files.readAllBytes(Paths.get(imageDto.getLocation()));
    }

    @GetMapping("/getPhoto/{id}")
    public ImageDto getPhoto(@PathVariable(name = "id") Long id){
        return imageService.findById(id);
    }

    @GetMapping("/getAllPhotos")
    public List<ImageDto> getAllPhotos(){return imageService.findAllPhotos();}

    @DeleteMapping("/deletePhoto/{id}")
    public String deletePhoto(@PathVariable(name = "id") Long id){
        return imageService.deletePhoto(id);
    }



}
