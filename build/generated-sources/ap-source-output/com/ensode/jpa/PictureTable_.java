package com.ensode.jpa;

import java.io.Serializable;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-07T10:34:24")
@StaticMetamodel(PictureTable.class)
public class PictureTable_ { 

    public static volatile SingularAttribute<PictureTable, String> date;
    public static volatile SingularAttribute<PictureTable, String> emailAddress;
    public static volatile SingularAttribute<PictureTable, String> price;
    public static volatile SingularAttribute<PictureTable, Integer> id;
    public static volatile SingularAttribute<PictureTable, String> itemDesc;
    public static volatile SingularAttribute<PictureTable, Serializable> pictures;

}