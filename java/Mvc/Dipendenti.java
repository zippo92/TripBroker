package Mvc;

import Mvc.Control.AccessoCatalogoControl;
import Mvc.Model.entityPackage.User;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Simone on 25/01/2016.
 */
public enum Dipendenti {

    Scout("Mvc.Control.AccessoCatalogoScoutControl","Mvc.View.ScoutSideBar"),
    Designer("Mvc.Control.AccessoCatalogoDesignerControl","Mvc.View.DesignerSideBar"),
    Agente("Mvc.Control.AccessoCatalogoAgentControl","Mvc.View.AgentSideBar"),
    Admin("Mvc.Control.AccessoCatalogoAdminControl","Mvc.View.AdminSideBar");
    private final String accessoCatalogoControl;
    private final String sideBar;
    private Dipendenti(final String accessoCatalogoControl,final String sideBar) {
        this.accessoCatalogoControl = accessoCatalogoControl;
        this.sideBar = sideBar;}
    public String getAccessoCatalogoControl() { return this.accessoCatalogoControl; }

    public String getSideBar(){return this.sideBar;}
}
