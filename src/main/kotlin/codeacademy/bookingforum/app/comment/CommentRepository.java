package codeacademy.bookingforum.app.comment;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    @Query(value = "select id from comment where post_id = :id", nativeQuery = true)
    List<Long> findAllIdByPost(@Param("id") Long id);
    @Query(value = "select * from comment where id = :id", nativeQuery = true)
    Comment findByIdOverride(@Param("id") Long id);
}
