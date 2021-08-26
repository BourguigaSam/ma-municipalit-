package com.fsb;

import javafx.scene.layout.AnchorPane;

public class Controller {
    public static AnchorPane holderPane;
    private static int userId;
    public static void setUserId(int id)
    {
        if(id == 0) return ;
        Controller.userId = id ;
    }
    public static int getUserId()
    {
        return Controller.userId;
    }



}
