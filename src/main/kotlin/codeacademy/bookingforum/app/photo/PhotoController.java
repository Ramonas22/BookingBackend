package codeacademy.bookingforum.app.photo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/photo")
public class PhotoController {

    @Autowired
    PhotoService photoService;

    @PostMapping("/postPhoto")
    public PhotoDto addPhoto(@RequestBody PhotoDto photoDto){
        return photoService.createPhoto(photoDto);
    }

    @GetMapping("/getPhoto/{id}")
    public PhotoDto getPhoto(@PathVariable(name = "id") Long id){
        return photoService.findById(id);
    }

    @GetMapping("/getAllPhotos")
    public List<PhotoDto> getAllPhotos(){return photoService.findAllPhotos();}

    @DeleteMapping("/deletePhoto/{id}")
    public void deletePhoto(@PathVariable(name = "id") Long id){
        photoService.deletePhoto(id);
    }

    @PutMapping("/updatePhoto/{id}")
    public PhotoDto updatePhoto(@RequestBody PhotoDto photoDto, @PathVariable(name = "id") Long id){
        return photoService.updatePhoto(photoDto);
    }
}
