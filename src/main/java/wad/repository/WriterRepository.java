/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.repository;


import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.NewClass;
import wad.domain.Writer;

public interface WriterRepository extends JpaRepository<Writer, Long> {

//    List<Writer> findByWriterName(String name);
//
//    List<Writer> findByWriterName(String name, Sort sort);
    
}