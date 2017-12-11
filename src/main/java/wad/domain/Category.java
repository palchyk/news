/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Category extends AbstractPersistable<Long>{
//     @Id
    public String name;

    @ManyToMany
//        (mappedBy = "categories" , fetch = FetchType.EAGER
//    )
    private List<NewClass> news ;// =  new ArrayList<>();
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<NewClass> getNews() {
//         if (this.news == null) {
//            this.news = new ArrayList<>();
//        }
         return this.news;
    }

    public void setNews(List<NewClass> news) {
        this.news = news;
    }
    
     public void addNew(NewClass news) {
        this.news.add(news);
    }
  
    
   
}
