/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Writer extends AbstractPersistable<Long> {
   
    public String name;
    
     @ManyToMany(mappedBy = "writers")
    private List<NewClass> news;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<NewClass> getNews() {
        return news;
    }

    public void setNews(List<NewClass> news) {
        this.news = news;
    }
    
    public void addNew(NewClass news) {
        this.news.add(news);
    }
}
