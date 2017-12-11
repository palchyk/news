
package wad.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.NewClass;

public interface NewRepository extends JpaRepository<NewClass, Long> {

    
}