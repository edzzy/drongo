/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.pfgen.lims.domain.documents;

import fr.pfgen.lims.domain.util.AbstractGenericEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 *
 * @author edouard
 */
@Entity
public class Document extends AbstractGenericEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    
    private String type;
    
    @Lob
    private String comments;
    
    private String hash_path;
    
    private String path;
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getId()!= null ? this.getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Document)) {
            return false;
        }
        Document other = (Document) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.pfgen.lims.domain.documents.Document[ id=" + this.getId() + " ]";
    }
    
}
