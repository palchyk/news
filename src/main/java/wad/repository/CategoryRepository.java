/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.repository;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Image;
import wad.domain.Category;
import wad.domain.Writer;

public interface CategoryRepository extends JpaRepository<Category, Long> {
//
//     List<Category> findByCategoryName(String name);
//
//     List<Category> findByCategoryName(String name, Sort sort);
    
}
