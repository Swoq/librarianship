package com.swoqe.librarianship.model.entity;

import com.swoqe.librarianship.model.ModelConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = ModelConstants.USER_TABLE)
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
//@NoArgsConstructor
public class UserEntity extends PersonEntity {



}
