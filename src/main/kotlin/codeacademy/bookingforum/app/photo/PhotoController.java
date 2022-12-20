package codeacademy.bookingforum.app.photo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/photo")
public class PhotoController {

    @Autowired
    PhotoService photoService;

    @PostMapping
    public PhotoDto addPhoto(@RequestBody PhotoDto photoDto){
        return photoService.createPhoto(photoDto);
    }

    @GetMapping("/{id}")
    public PhotoDto getPhoto(@PathVariable(name = "id") Long id){
        return photoService.findById(id);
    }

    @GetMapping
    public List<PhotoDto> getAllPhotos(){return photoService.findAllPhotos();}

    @DeleteMapping("/{id}")
    public void deletePhoto(@PathVariable(name = "id") Long id){
        photoService.deletePhoto(id);
    }

    @PutMapping("/{id}")
    public PhotoDto updatePhoto(@RequestBody PhotoDto photoDto, @PathVariable(name = "id") Long id){
        return photoService.updatePhoto(photoDto);
    }
}
