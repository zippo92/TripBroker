/**
 * Created by Simone on 27/01/2016.
 */

import Mvc.Control.*;
import Mvc.Dipendenti;
import Mvc.Model.DBResourcesManager;
import Mvc.Model.entityPackage.*;
import Mvc.TipoOfferta;
import Mvc.View.*;
import Patterns.DAOFactory.DAOFactory;
import javafx.event.ActionEvent;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class TestAll {


    @Test
    public void testPack()
    {
        DBResourcesManager.initHibernate();

        for(int i = 0;i<100 ; i++) {


            List<Pacchetto> packs = DAOFactory.getPacchettoDAO().getList();

            for (Pacchetto pack : packs) {

                assertEquals("Pacchetto non coerente!!", pack.getOffertaTrasporto().getCittà(), pack.getOffertaPernotto().getCittà());

                for (OffertaEvento offertaEvento : pack.getOffertaEvento())

                    assertEquals("Pacchetto non coerente!!", pack.getOffertaTrasporto().getCittà(), offertaEvento.getCittà());
            }
        }
    }

    @Test
    public void testOff()
    {

        OffertaEvento offerta = new OffertaEvento();

        OffertaPernotto offerta1 = new OffertaPernotto();

        OffertaTrasporto offerta2 = new OffertaTrasporto();

        offerta.setCittà(RandomStringUtils.randomAlphabetic(5));
        offerta.setNome(RandomStringUtils.randomAlphabetic(5));
        offerta.setTipologia(RandomStringUtils.randomAlphabetic(5));


        offerta2.setCittà(RandomStringUtils.randomAlphabetic(5));
        offerta2.setNome(RandomStringUtils.randomAlphabetic(5));
        offerta2.setTipologia(RandomStringUtils.randomAlphabetic(5));


        offerta1.setCittà(RandomStringUtils.randomAlphabetic(5));
        offerta1.setNome(RandomStringUtils.randomAlphabetic(5));
        offerta1.setTipologia(RandomStringUtils.randomAlphabetic(5));

        assertNotNull(offerta);

        assertNotNull(offerta1);

        assertNotNull(offerta2);


    }

    @Test
    public void testByCity()
    {

        DBResourcesManager.initHibernate();

        List<OffertaPernotto> offertePernotto = (List<OffertaPernotto>) DAOFactory.getDAOFactory(TipoOfferta.OffertaPernotto).getOffertaDAO().findByCity("Roma");

        List<OffertaEvento> offerteEvento = (List<OffertaEvento>) DAOFactory.getDAOFactory(TipoOfferta.OffertaEvento).getOffertaDAO().findByCity("Roma");

        List<OffertaTrasporto> offerteTrasporto = (List<OffertaTrasporto>) DAOFactory.getDAOFactory(TipoOfferta.OffertaTrasporto).getOffertaDAO().findByCity("Roma");


        for(OffertaPernotto offerta : offertePernotto)
            assertEquals("Ricerca offerte Pernotto errata",offerta.getCittà(),"Roma");



        for(OffertaTrasporto offerta : offerteTrasporto)
            assertEquals("Ricerca offerte Trasporto errata",offerta.getCittà(),"Roma");



        for(OffertaEvento offerta : offerteEvento)
            assertEquals("Ricerca offerte Evento errata",offerta.getCittà(),"Roma");


    }


    @Test
    public void enumTest() throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {

        DBResourcesManager.initHibernate();

        for(int i = 0;i<10000 ; i++){


        final String[] roles = {"Scout", "Admin", "Agente", "Designer"};
        Random random = new Random();
        int index = random.nextInt(roles.length);

        String ruolo = roles[index];

        User temp = new User("test",ruolo);

        Class<?> classType = Class.forName((Dipendenti.valueOf(temp.getRuolo()).getAccessoCatalogoControl()));
        Method method = classType.getMethod("getInstance",User.class);
        Object accessoCatalogoControl = method.invoke(classType.getClass(),temp);

        switch(index)
        {
            case 0 :assertTrue(accessoCatalogoControl instanceof AccessoCatalogoScoutControl);
                    break;
            case 1 :assertTrue(accessoCatalogoControl instanceof AccessoCatalogoAdminControl);
                    break;
            case 2 :assertTrue(accessoCatalogoControl instanceof AccessoCatalogoAgentControl);
                    break;
            case 3 :assertTrue(accessoCatalogoControl instanceof AccessoCatalogoDesignerControl);
                    break;

        }

            ((AccessoCatalogoControl) accessoCatalogoControl).logout();
        }


    }

    @Test
    public void sideBarTest() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {

        for(int i = 0;i<10000 ; i++) {

            final String[] roles = {"Scout", "Admin", "Agente", "Designer"};
            Random random = new Random();
            int index = random.nextInt(roles.length);

            String ruolo = roles[index];


            User temp = new User("test", ruolo);


            Class<?> classType = Class.forName((Dipendenti.valueOf(temp.getRuolo()).getAccessoCatalogoControl()));
            Method method = classType.getMethod("getInstance", User.class);
            Object accessoCatalogoControl = method.invoke(classType.getClass(), temp);


            classType = Class.forName(Dipendenti.valueOf(ruolo).getSideBar());
            Constructor constructor = classType.getConstructor(AccessoCatalogoControl.class);
            Object sideBar = constructor.newInstance(accessoCatalogoControl);


            switch (index) {
                case 0:
                    assertTrue(sideBar instanceof ScoutSideBar);
                    break;
                case 1:
                    assertTrue(sideBar instanceof AdminSideBar);
                    break;
                case 2:
                    assertTrue(sideBar instanceof AgentSideBar);
                    break;
                case 3:
                    assertTrue(sideBar instanceof DesignerSideBar);
                    break;

            }
            ((AccessoCatalogoControl) accessoCatalogoControl).logout();
        }


    }


}
