/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class NewClass extends AbstractPersistable<Long> {

    @OneToOne
    private Image i;

    @ManyToMany (mappedBy = "news" , fetch = FetchType.EAGER )

    private List<Category> categories =  new ArrayList<>();

    @ManyToMany
    private List<Writer> writers;

    public List<Category> getCategories() {
        if (this.categories == null) {
            this.categories = new ArrayList<>();
        }
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(Category category) {
      String []categories= category.getName().split(",");
        for(int i = 0 ; i<categories.length;i++){
            Category c = new Category();
            c.setName(categories[i]);
            
            this.categories.add(c);
        }
        
      
    }

    public List<Writer> getWriters() {
        return writers;
    }

    public void setWriters(List<Writer> writers) {
        this.writers = writers;
    }

    public int read = 0;

    public String lead;
//Tämäkään ei kelvannut herokulle
//    @Column( length = 10000000 )
    public String text;

    public Date created;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return lead;
    }

    public void setDescription(String description) {
        this.lead = description;
    }

    public Image getI() {
        return i;
    }

    public void setI(Image i) {
        this.i = i;
    }
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
