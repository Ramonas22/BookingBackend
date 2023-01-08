package codeacademy.bookingforum.app.image;

import codeacademy.bookingforum.app.configuration.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/photo")
public class ImageController {

    @Autowired
    ImageService imageService;

    @PostMapping("/upload")
    public ResponseObject file(@RequestParam("file") MultipartFile file, @RequestParam("body") String imageString, WebRequest request) {
        return imageService.upload(file, imageString, request);
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
