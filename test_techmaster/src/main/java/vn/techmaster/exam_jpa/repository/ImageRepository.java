package vn.techmaster.exam_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.exam_jpa.entity.Image;


@Repository
public interface ImageRepository extends JpaRepository<Image, String> {

}