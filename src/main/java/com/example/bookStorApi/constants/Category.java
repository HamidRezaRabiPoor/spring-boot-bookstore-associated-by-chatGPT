package com.example.bookStorApi.constants;

import java.util.Map;
import java.util.WeakHashMap;

public enum Category {

    LITERATURE(1),
    NONFICTION(2),
    ACTION(3),
    ROMANCE(4),
    THRILLER(5),
    TECHNOLOGY(6),
    DRAMA(7),
    POETRY(8),
    MEDIA(9),
    OTHERS(0);

    private  final int value;
     Category(int value){
        this.value = value;
    }
    private static final Map<Object, Object> map = new WeakHashMap<>();

   static  {
       for(Category category : Category.values()){
           map.put(category.value, category);
       }
   }
public static Category valueOf(int i){
       return (Category) map.get(i);
}


}
