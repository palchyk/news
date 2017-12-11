
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
