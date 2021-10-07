package com.example.springlab2.model.entity;

import com.example.springlab2.model.ModelConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
