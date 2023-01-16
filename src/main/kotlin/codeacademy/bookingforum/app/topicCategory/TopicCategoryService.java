package codeacademy.bookingforum.app.topicCategory;

import codeacademy.bookingforum.app.configuration.ResponseObject;
import codeacademy.bookingforum.app.user.auth.UserAuth;
import codeacademy.bookingforum.app.user.auth.UserAuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TopicCategoryService {
    @Autowired
    TopicCategoryRepo sectionRepo;
    @Autowired
    TopicCategoryMapper sectionMapper;
    @Autowired
    UserAuthRepo userRepo;

    // Create new section
    public ResponseObject create(TopicCategoryDto section, WebRequest request) {
        sectionRepo.save(sectionMapper.fromDto(section));
        return new ResponseObject(Collections.singletonList("Section created successfully."), HttpStatus.CREATED, request);
    }

    // Get a list of all sections, filter through depending on authorization state, return modified list of sections
    public List<TopicCategoryDto> getList() {
        List<TopicCategory> sections = (List<TopicCategory>) sectionRepo.findAll();
        UserDetails userDetails = null;

        try {userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {assert true;}

        if (userDetails != null) {
            UserAuth user = userRepo.findByUsername(userDetails.getUsername());
            List<String> userRoles = new ArrayList<>();
            user.getRoles().forEach(role -> userRoles.add(role.getDisplayName()));

            sections.forEach(section -> section.getRoles().forEach(role -> {
                if (!userRoles.contains(role)) {
                    sections.remove(section);
                }
            }));
        } else {
            sections.forEach(section -> {
                if (section.getRoles().contains("ROLE_ADMIN") || section.getRoles().contains("ROLE_SELLER")) {
                    sections.remove(section);
                }
            });
        }

        return sectionMapper.toDtoList(sections);
    }
}