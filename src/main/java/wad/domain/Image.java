/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import javax.persistence.Entity;
import javax.persistence.Lob;
import lombok.Data;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Data
@Entity
public class Image extends AbstractPersistable<Long>{
    @Lob
 
    private byte[] content;

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }  
}
