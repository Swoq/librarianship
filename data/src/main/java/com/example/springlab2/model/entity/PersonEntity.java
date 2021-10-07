package com.example.springlab2.model.entity;

import com.example.springlab2.model.BaseSqlEntity;
import com.example.springlab2.model.ModelConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public class PersonEntity extends BaseSqlEntity {

    @Column(name = ModelConstants.PERSON_FIRST_NAME_COLUMN)
    private String firstName;

    @Column(name = ModelConstants.PERSON_LAST_NAME_COLUMN)
    private String lastName;

    @Column(name = ModelConstants.PERSON_EMAIL_COLUMN)
    private String email;

}
