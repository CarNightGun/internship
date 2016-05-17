package com.km.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.km.common.bean.AbstractTreeEntity;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月17日下午12:25:43
 */
@Entity
@Table
public class Organization extends AbstractTreeEntity<Long, Organization>
{

}
