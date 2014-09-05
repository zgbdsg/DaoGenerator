/*
 * Copyright (C) 2011 Markus Junginger, greenrobot (http://greenrobot.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.greenrobot.daogenerator.gentest;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

/**
 * Generates entities and DAOs for the example project DaoExample.
 * 
 * Run it as a Java application (not Android).
 * 
 * @author Markus
 */
public class ExampleDaoGenerator {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1000, "com.famnotes.famnotes.Model");

//        addAlbum(schema);
        addAlbumPhoto(schema);
        addCoupon(schema);
        addCouponImage(schema);
        addGroup(schema);
        addImportantDay(schema);
        addNote(schema);
        addTodoList(schema);
        addUser(schema);
        addContacts(schema);
        addWishList(schema);
        addNotification(schema);
        addShoppingList(schema);

        new DaoGenerator().generateAll(schema, "src_gen");
    }
    
    
    private static void addShoppingList(Schema schema) {
        Entity shoppingList = schema.addEntity("ShoppingList");
        shoppingList.addIdProperty();
        shoppingList.addIntProperty("serverId");
        shoppingList.addIntProperty("tag");
        shoppingList.addIntProperty("grpId");
        shoppingList.addIntProperty("usrId");
        shoppingList.addIntProperty("buyFlag");
        shoppingList.addStringProperty("usrName");
        shoppingList.addStringProperty("content");

        shoppingList.addDateProperty("creatTime");
    }
    
    
    private static void addNotification(Schema schema) {
        Entity notification = schema.addEntity("Notification");
        notification.addIdProperty();
        notification.addIntProperty("serverId");
        notification.addIntProperty("type");
        notification.addIntProperty("cityId");
        notification.addIntProperty("grpId");
        notification.addIntProperty("usrId");
        notification.addStringProperty("usrName");
        notification.addStringProperty("p1");
        notification.addStringProperty("p2");
        notification.addStringProperty("p3");

        notification.addDateProperty("startDate");
        notification.addDateProperty("endDate");
    }
    

    private static void addCoupon(Schema schema) {
        Entity coupon = schema.addEntity("Coupon");
        coupon.addIdProperty();
        coupon.addIntProperty("serverId");
        coupon.addStringProperty("title1");
        coupon.addStringProperty("title2");
        coupon.addStringProperty("title3");
        coupon.addStringProperty("title4");

        coupon.addDateProperty("startDate");
        coupon.addDateProperty("endDate");
    }
    
    private static void addCouponImage(Schema schema) {
        Entity couponImage = schema.addEntity("CouponImage");
        couponImage.addIdProperty();
        couponImage.addIntProperty("serverId");
        couponImage.addIntProperty("type");
        couponImage.addStringProperty("activityId");
        couponImage.addStringProperty("welUrl");
        couponImage.addStringProperty("imgPath");
        couponImage.addStringProperty("name");

        couponImage.addDateProperty("startDate");
        couponImage.addDateProperty("endDate");
    }
    
    private static void addTodoList(Schema schema) {
        Entity toDoList = schema.addEntity("TodoList");
        toDoList.addIdProperty();
        toDoList.addIntProperty("serverId");
        toDoList.addIntProperty("grpId");
        toDoList.addIntProperty("usrId");
        toDoList.addStringProperty("usrName");
        toDoList.addStringProperty("title");
        toDoList.addStringProperty("content");
        toDoList.addDateProperty("creatTime");

        toDoList.addDateProperty("startDate");
        toDoList.addDateProperty("endDate");
    }
    
    private static void addWishList(Schema schema) {
        Entity WishList = schema.addEntity("WishList");
        WishList.addIdProperty();
        WishList.addIntProperty("serverId");
        WishList.addIntProperty("grpId");
        WishList.addIntProperty("usrId");
        WishList.addStringProperty("name");
        WishList.addStringProperty("description");
        WishList.addStringProperty("detailCount");
        WishList.addDateProperty("creatTime");

        WishList.addDateProperty("startDate");
        WishList.addDateProperty("endDate");
    }
    
//    private static void addAlbum(Schema schema) {
//        Entity album = schema.addEntity("Album");
//        album.addIdProperty();
//        album.addIntProperty("grpId");
//        album.addStringProperty("name");
//        album.addDateProperty("creatTime");
//    }
    
    private static void addAlbumPhoto(Schema schema) {
    	Entity album = schema.addEntity("Album");
        album.addIdProperty();
        album.addIntProperty("serverId");
        album.addIntProperty("photoNum");
//        Property albumId = album.addIntProperty("albumId").notNull().getProperty();
        album.addIntProperty("grpId");
        album.addStringProperty("name");
        album.addStringProperty("avatar");
        album.addDateProperty("creatTime");
        
        Entity photo = schema.addEntity("Photo");
        photo.addIdProperty();
        photo.addIntProperty("serverId");
        photo.addIntProperty("usrId").notNull();
        photo.addIntProperty("photoId").notNull();
        photo.addStringProperty("resrc");
        photo.addIntProperty("flag");
        Property albumId=  photo.addLongProperty("albumId").notNull().getProperty();
        Property photoCreatTime =  photo.addDateProperty("creatTime").getProperty();
        photo.addToOne(album, albumId);
        
        ToMany albumPhoto = album.addToMany(photo, albumId);
        albumPhoto.setName("AlbumPhoto");
        albumPhoto.orderAsc(photoCreatTime);
    }
    
    private static void addNote(Schema schema) {
        Entity note = schema.addEntity("Note");
        note.addIdProperty();
        note.addIntProperty("serverId");
        note.addIntProperty("usrId");
        note.addIntProperty("grpId");
        note.addStringProperty("content").notNull();
        note.addStringProperty("img1");
        note.addStringProperty("img2");
        note.addDateProperty("creatTime");
    }

    private static void addImportantDay(Schema schema) {
        Entity importantDay = schema.addEntity("ImportantDay");
        importantDay.addIdProperty();
        importantDay.addIntProperty("serverId");
        importantDay.addIntProperty("usrId");
        importantDay.addIntProperty("type");
        importantDay.addIntProperty("grpId");
        importantDay.addIntProperty("state");
        importantDay.addStringProperty("title");

        importantDay.addDateProperty("theDay");
        importantDay.addDateProperty("creatTime");
    }
    
    private static void addUser(Schema schema) {
        Entity user = schema.addEntity("User");
        user.addIdProperty();
        user.addIntProperty("serverId");
        user.addIntProperty("loginId");
        user.addStringProperty("name");
        user.addIntProperty("grpId");
        user.addStringProperty("email");
        user.addStringProperty("cellno");
        user.addStringProperty("password");
        user.addIntProperty("type");
        user.addIntProperty("flag");
        user.addIntProperty("role");
        user.addStringProperty("avatar");
    }
    
    private static void addContacts(Schema schema) {
        Entity contact = schema.addEntity("Contact");
        contact.addIdProperty();
        contact.addIntProperty("serverId");
        contact.addStringProperty("name");
        contact.addStringProperty("title");
        contact.addStringProperty("email");
        contact.addStringProperty("cellno");
        contact.addStringProperty("telno");
        contact.addStringProperty("avatar");
    }
    
    private static void addGroup(Schema schema) {
        Entity group = schema.addEntity("Group");
        group.addIdProperty();
        group.addIntProperty("serverId");
        group.addIntProperty("cityId");
        group.addStringProperty("famName");
        group.addIntProperty("famId");
        group.addIntProperty("orderday");
        group.addStringProperty("avatar");
        group.addDateProperty("creatTime");
    }

}
