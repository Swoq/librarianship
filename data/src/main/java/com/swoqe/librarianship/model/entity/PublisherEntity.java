package com.swoqe.librarianship.model.entity;

import com.swoqe.librarianship.model.BaseSqlEntity;
import com.swoqe.librarianship.model.ModelConstants;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = ModelConstants.PUBLISHER_TABLE_NAME)
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PublisherEntity extends BaseSqlEntity {

    @Column(name = ModelConstants.PUBLISHER_NAME_COLUMN)
    private String name;

    @Column(name = ModelConstants.PUBLISHER_EMAIL_COLUMN)
    private String email;

    @Column(name = ModelConstants.PUBLISHER_ADDRESS_COLUMN)
    private String address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "publisher")
    private Set<BookEntity> books;

}
