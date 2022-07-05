package uz.pdp.heymasterapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.pdp.heymasterapp.entity.location.Region;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {

//    @Query(value = "select c from category c where c.name LIKE %:name%")
//    List<Category> getCategoryByNameCharacters(@Param("name") String name);
//
//
//    Optional<Category> findByName(String name);

    @Query("select r.nameUz from regions  r ")
    List<Region>getAllUzName();

//    @Query("select r.nameUz from regions  r ")
//    List<Region>getAllRuName();
    @Query(value = "select * from regions c where c.nameUz LIKE %:name%", nativeQuery = true)
    List<Region> getDistrictByNameCharacters(@Param("name") String name);

}
