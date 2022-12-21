package codeacademy.bookingforum.app.photo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {

    @Autowired
    PhotoRepo photoRepo;

    @Autowired
    PhotoMapper photoMapper;

    public PhotoDto findById(Long id){
        Photo photo = photoRepo.findById(id).orElse(null);
        return photoMapper.toDto(photo);
    }

    public List<PhotoDto> findAllPhotos(){
        List<Photo> photos = (List<Photo>) photoRepo.findAll();
        return  photoMapper.toDto(photos);
    }


    public PhotoDto createPhoto(PhotoDto photoDto){
        Photo photo = photoMapper.fromDto(photoDto);
        photoRepo.save(photo);
        return photoMapper.toDto(photo);
    }

    public PhotoDto updatePhoto(PhotoDto photoDto){
        Photo photo = photoMapper.fromDto(photoDto);
        photoRepo.save(photo);
        return photoMapper.toDto(photo);
    }

    public void deletePhoto(Long id){
        photoRepo.deleteById(id);
    }




}
