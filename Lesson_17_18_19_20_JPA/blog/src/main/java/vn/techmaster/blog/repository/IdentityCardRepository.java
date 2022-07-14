package vn.techmaster.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.techmaster.blog.entity.IdentityCard;

public interface IdentityCardRepository extends JpaRepository<IdentityCard, Integer> {
}