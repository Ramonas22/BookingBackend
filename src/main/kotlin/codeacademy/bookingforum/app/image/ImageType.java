package codeacademy.bookingforum.app.image;

import java.util.Arrays;
import java.util.List;

public enum ImageType {

    POST,
    USER_AVATAR,
    SELLER_GALLERY,
    UNDEFINED;

    private static final List<String> FORUM_STRINGS = Arrays.asList("forum","post","forum_type","type_forum","type_post","post_type","posts","postimage");
    private static final List<String> AVATAR_STRINGS = Arrays.asList("avatar","user","user_avatar","avatars","profile","profilepicture","profile_picture","user_avatar");
    private static final List<String> GALLERY_STRINGS = Arrays.asList("gallery","seller","image_gallery","images","reel","sellers","sellerimages","seller_images","seller_gallery");

    public static ImageType parse(String type) {
        type = type.toLowerCase();
        if (FORUM_STRINGS.contains(type)) {
            return POST;
        } else if (AVATAR_STRINGS.contains(type)) {
            return USER_AVATAR;
        } else if (GALLERY_STRINGS.contains(type)) {
            return SELLER_GALLERY;
        } else return UNDEFINED;
    }
}
