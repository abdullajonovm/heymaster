package uz.pdp.heymasterapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.pdp.heymasterapp.entity.Attachment;
import uz.pdp.heymasterapp.entity.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value = "select c from category c where c.name LIKE %:name%")
    List<Category> getCategoryByNameCharacters(@Param("name") String name);


    Optional<Category> findByName(String name);

    List<Category> findAllByIsActiveTrue();
}
