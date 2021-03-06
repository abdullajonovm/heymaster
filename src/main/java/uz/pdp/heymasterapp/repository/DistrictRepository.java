package uz.pdp.heymasterapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.pdp.heymasterapp.entity.location.District;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {

//    @Query(value = "select c from category c where c.name LIKE %:name%")
//    List<Category> getCategoryByNameCharacters(@Param("name") String name);
//
//
//    Optional<Category> findByName(String name);

    @Query("select r.nameUz from districts r ")
    List<String>getAllUzName();

    @Query("select r.nameUz from districts  r ")
    List<District>getAllRuName();

    Optional<List<District>>findByRegionId(Integer region_id);

    @Query(value = "select * from districts c where c.nameUz LIKE %:name%", nativeQuery = true)
    List<District> getDistrictByNameCharacters(@Param("name") String name);
}
