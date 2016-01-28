package Mvc.View;

import Mvc.Control.AccessoCatalogoControl;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.layout.VBox;

/**
 * Created by Simone on 26/01/2016.
 */
public abstract class SideBar{

    protected VBox buttonBox;
    protected AccessoCatalogoControl accessoCatalogoControl;

    public SideBar(AccessoCatalogoControl accessoCatalogoControl)
    {
        buttonBox = new VBox();

        buttonBox.setPadding(new Insets(7,0,0,0));

        buttonBox.setAlignment(Pos.TOP_CENTER);

        buttonBox.setSpacing(10);

        this.accessoCatalogoControl = accessoCatalogoControl;

    }


    public VBox getButtonBox()
    {
        return buttonBox;
    }

    public abstract void draw();


}
