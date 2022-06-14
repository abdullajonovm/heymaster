package uz.pdp.heymasterapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.heymasterapp.entity.Category;
import uz.pdp.heymasterapp.entity.Profession;

import java.util.List;
import java.util.Optional;


public interface ProfessionRepository extends JpaRepository<Profession, Integer> {

//    @Query(value = "select c from category c where c.name LIKE %:name%")
//    List<Category> getCategoryByNameCharacters(@Param("name") String name);
//
//
//    Optional<Category> findByName(String name);

    @Query(value = "select c from profession c where c.name LIKE %:name%")
    List<Category> getProfessionByCharacter(@Param("name") String name);

    Optional<Profession> findByName(String name);

    @Query(value = "select * from profession p where p.is_active = true",nativeQuery = true)
    List<Profession>getAllByIsActiveTrue();

    @Query(value = "select * from profession p where p.category_id=:id",nativeQuery = true)
    List<Profession> findByCategory(Integer id);
}


